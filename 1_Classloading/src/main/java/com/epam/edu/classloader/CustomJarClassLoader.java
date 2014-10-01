package com.epam.edu.classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.util.FileUtils;

/**
 * @author Alexey_Zalivko
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomJarClassLoader extends ClassLoader {

    private final static Logger LOG = LoggerFactory.getLogger(CustomJarClassLoader.class);
    private Hashtable classes = new Hashtable(); //used to cache already defined classes

    private String directory = "d:/JMP/Resources/"; //Path to the jar file

    public CustomJarClassLoader(String directory) {
        this();
        this.directory = directory;
    }

    public CustomJarClassLoader() {
        super(CustomJarClassLoader.class.getClassLoader()); //calls the parent class loader's constructor
    }

    public Class loadClass(String className) throws ClassNotFoundException {
        return findClass(className);
    }

    public Class findClass(String className) {
        byte classByte[];
        Class result = null;

        result = (Class) classes.get(className); //checks in cached classes
        if (result != null) {
            return result;
        }

        try {
            return findSystemClass(className);
        } catch (Exception e) {
        }

        try {

            for (Map.Entry<String, InputStream> srcFile : FileUtils.getJarFiles(directory).entrySet()) {

                try(JarFile jar = new JarFile(srcFile.getKey());) {
                    JarEntry entry = jar.getJarEntry(className + ".class");
                    if (entry != null) {
                        InputStream is = jar.getInputStream(entry);
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        int nextValue = is.read();
                        while (-1 != nextValue) {
                            byteStream.write(nextValue);
                            nextValue = is.read();
                        }

                        classByte = byteStream.toByteArray();
                        String fullClassName = className.replaceAll("/", ".");
                        result = defineClass(fullClassName, classByte, 0, classByte.length, null);
                        classes.put(className, result);
                        LOG.info("Requested class [" + className + "] found and loaded.");
                        return result;
                    }
                }
            }
            LOG.info("Requested class [" + className + "] wasn't found.");
            return null;
        } catch (Exception e) {
            LOG.info("Requested class [" + className + "] wasn't found.");
            return null;
        }
    }

}

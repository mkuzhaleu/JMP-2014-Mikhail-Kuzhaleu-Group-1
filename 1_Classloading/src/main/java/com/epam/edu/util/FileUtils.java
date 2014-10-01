package com.epam.edu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey_Zalivko
 */
public class FileUtils {
    private static final String JAR = ".jar";

    private FileUtils() {
    }

    /**
     * srcPath must contain a dir with jars to load
     * @return map of fileName, input streams to jar files denoted by srcPath
     */
    public static Map<String, InputStream> getJarFiles(String srcPath) throws IOException {
        if (srcPath == null || srcPath.isEmpty()) {
            throw new IOException("Undefined src path!");
        }
        Map<String, InputStream> result = new HashMap<String, InputStream>();
        File f = new File(srcPath);
        if (f.exists()) {
            if (!f.isDirectory()) {
                throw new FileNotFoundException("Source path must be directory " + srcPath);
            }
            for (File file : f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.contains(JAR);
                }})) {
                result.put(file.getAbsolutePath(), new FileInputStream(file));
            }
        } else {
            InputStream is = FileUtils.class.getResourceAsStream(srcPath);
            if (is == null) {
                throw new FileNotFoundException("File not found in " + srcPath);
            }
            result.put(srcPath, is);
        }

        if (result.isEmpty()) {
            throw new FileNotFoundException("No files found in [" + srcPath + "]");
        }

        return result;
    }
}

package com.epam.edu.classloader.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.classloader.CustomJarClassLoader;
import com.epam.edu.classloader.report.Report;

/**
 * @author Alexey_Zalivko
 */
@SuppressWarnings({"rawtypes"})
public class Main {

    private final static Logger LOG = LoggerFactory.getLogger(Main.class);
    private final static String CLASSES_PACKAGE = "com/epam/edu/classloader/report/impl/";

    private final static Scanner scanner = new Scanner(System.in);

    private static String PATH_TO_JARS;
    private final static String DEFAULT_PATH_TO_JARS;

    static {
        Path currentRelativePath = Paths.get("");
        DEFAULT_PATH_TO_JARS = Paths.get(currentRelativePath.toAbsolutePath().toString() + "/jars").normalize().toString();
    }

    public static void main( String[] args ) throws Exception {

        PATH_TO_JARS = DEFAULT_PATH_TO_JARS;

        int option = 0;
        do {
            System.out.println( "Make your choise:" );
            System.out.println( "1. Do report #1." );
            System.out.println( "2. Do report #2." );
            System.out.println( "3. Do report #3." );
            System.out.println( "4. Specify path to folder with jars needed." );
            System.out.println( "5. Reset path to default [" + DEFAULT_PATH_TO_JARS + "]" );
            System.out.println( "6. Show current path to jars" );
            System.out.println( "7. Exit" );
            option = scanner.nextInt();

            doReport(option);
        } while (option  != 7);
    }

    private static void doReport(int option) throws Exception {

        CustomJarClassLoader jarClassLoader = new CustomJarClassLoader(PATH_TO_JARS);

        String fullClassPath = "";

        switch(option) {
        case 1:
            fullClassPath = CLASSES_PACKAGE + "ReportOne";
            break;
        case 2:
            fullClassPath = CLASSES_PACKAGE + "ReportTwo";
            break;
        case 3:
            fullClassPath = CLASSES_PACKAGE + "ReportThree";
            break;
        case 4:
            System.out.println( "Type new path to folder with jars: " );
            scanner.nextLine();
            PATH_TO_JARS = scanner.nextLine();
            return;
        case 5:
            PATH_TO_JARS = DEFAULT_PATH_TO_JARS;
            return;
        case 6:
            System.out.println("Current path to jars " + PATH_TO_JARS);
            return;
        default:
            return;
        }

        Class clazz = jarClassLoader.loadClass(fullClassPath);

        if (clazz != null) {
            ((Report) clazz.newInstance()).doReport();
        } else {
            LOG.error("{} class not found!", fullClassPath);
            System.out.println(fullClassPath + " class not found!");
        }
    }

}

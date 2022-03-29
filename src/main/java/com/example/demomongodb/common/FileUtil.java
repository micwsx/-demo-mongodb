package com.example.demomongodb.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author: Michael
 * @date: 3/29/2022 1:49 PM
 */
public class FileUtil {

    private FileUtil() {
    }

    public static String rootDir() {
        String classPath = FileUtil.class.getClassLoader().getResource("").getFile();
        File file = new File(classPath);
        return file.getPath();
    }

    public static String getTempDateFolder() {
        String subFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
        String newPath = rootDir() + File.separator + "tempFolder" + File.separator + subFolder;
        File newDirectory = new File(newPath);
        if (!newDirectory.exists()) {
            boolean isCreated = newDirectory.mkdirs();
            if (isCreated)
                return newDirectory.getPath();
        }
        return "";
    }




    public static void main(String[] args) {
        String s = rootDir();
        System.out.println(s);

        String tempDateFolder = getTempDateFolder();
        System.out.println(tempDateFolder);
    }
}

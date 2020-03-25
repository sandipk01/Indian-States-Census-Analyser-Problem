package com.bridgelabz.censusanalyser.utils;

import java.io.File;

public class Utils {

    //Method to return file extension.
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}

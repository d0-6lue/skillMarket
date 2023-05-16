package com.team4.skillmarket.utill.file;

public class FileUploader {
    
    // 확장자 알아내기
    public static String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }
    
}

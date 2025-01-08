package com.dto;
//esta clase la usarre como modelo para descargar la imagen del frontend
public class FileUploadDTO {
    private String fileName;
    private String filePath;

    public FileUploadDTO(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

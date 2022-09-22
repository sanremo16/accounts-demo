package org.san.home.commons.error;

public enum ErrorMessageResource {

    APP("errors");

    private String fileName;

    ErrorMessageResource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

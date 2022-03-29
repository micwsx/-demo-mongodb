package com.example.demomongodb.controller.viewmodel;

import java.io.File;

/**
 * @author: Michael
 * @date: 3/29/2022 11:58 AM
 */
public class Document{

    private String documentId;
    private File file;
    private byte[] content;

    private DocumentMeta documentMeta;

    public Document(DocumentMeta documentMeta) {
        this.documentMeta = documentMeta;
    }

    public DocumentMeta getDocumentMeta() {
        return documentMeta;
    }

    public void setDocumentMeta(DocumentMeta documentMeta) {
        this.documentMeta = documentMeta;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

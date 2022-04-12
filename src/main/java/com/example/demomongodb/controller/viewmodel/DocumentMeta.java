package com.example.demomongodb.controller.viewmodel;

import java.util.Date;

/**
 * @author: Michael
 * @date: 3/28/2022 4:01 PM
 * document upload model
 */
public class DocumentMeta {

    private String documentName;
    private Date createdDate;

    private Document document;

    public DocumentMeta() {

    }

    public DocumentMeta(String documentName, Date createdDate) {
        this.documentName = documentName;
        this.createdDate = createdDate;
    }


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "DocumentMeta{" +
                "documentName='" + documentName + '\'' +
                ", createdDate=" + createdDate +
                ", document=" + document +
                '}';
    }
}

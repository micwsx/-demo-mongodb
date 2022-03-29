package com.example.demomongodb.controller.viewmodel;

import java.util.Date;

/**
 * @author: Michael
 * @date: 3/28/2022 4:01 PM
 */
public class Document {

    private String documentName;
    private Date createdDate;

    public Document() {
    }

    public Document(String documentName, Date createdDate) {
        this.documentName = documentName;
        this.createdDate = createdDate;
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
        return "Document{" +
                "documentName='" + documentName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

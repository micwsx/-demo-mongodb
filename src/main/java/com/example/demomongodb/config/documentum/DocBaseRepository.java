package com.example.demomongodb.config.documentum;

/**
 * @author: Michael
 * @date: 4/17/2022 9:39 PM
 */
public abstract class DocBaseRepository implements Repository{

    private String repository;
    private String username;
    private String password;
    private String folderLink;
    private String docType;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFolderLink() {
        return folderLink;
    }

    public void setFolderLink(String folderLink) {
        this.folderLink = folderLink;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @Override
    public String toString() {
        return "DocBaseRepository{" +
                "repository='" + repository + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", folderLink='" + folderLink + '\'' +
                ", docType='" + docType + '\'' +
                '}';
    }
}

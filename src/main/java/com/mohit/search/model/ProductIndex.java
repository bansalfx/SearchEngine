package com.mohit.search.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductIndex {

    @Id
    private String keywordIndex;

    @ElementCollection
    private List<String> documentID = new ArrayList<String>();;

    public ProductIndex(String keywordIndex, List<String> documentID) {
        this.keywordIndex = keywordIndex;
        this.documentID = documentID;
    }

    public ProductIndex(){

    }

    public String getKeywordIndex() {
        return keywordIndex;
    }

    public void setKeywordIndex(String keywordIndex) {
        this.keywordIndex = keywordIndex;
    }

    public List<String> getDocumentID() {
        return documentID;
    }

    public void setDocumentID(List<String> documentID) {
        this.documentID = documentID;
    }
}

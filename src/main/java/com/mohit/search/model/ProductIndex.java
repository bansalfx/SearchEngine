package com.mohit.search.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for Inverted  Index Object
 */

@Entity
public class ProductIndex {

    @Id
    private String keywordIndex;

    @ElementCollection
    private List<String> documentIds = new ArrayList<String>();

    public ProductIndex(String keywordIndex, List<String> documentIds) {
        this.keywordIndex = keywordIndex;
        this.documentIds = documentIds;
    }

    public ProductIndex(){

    }

    public String getKeywordIndex() {
        return keywordIndex;
    }

    public void setKeywordIndex(String keywordIndex) {
        this.keywordIndex = keywordIndex;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }
}

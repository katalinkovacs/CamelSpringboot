package com.kati.common.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Child2 {

    @JsonProperty
    private int childId;

    @JsonProperty
    private String givenName;

    @JsonProperty
    private String familyName;

    @JsonProperty
    private String dob;


    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

package com.edubuddy.backend.dto;

public class MatchResponse {

    private Long id;
    private String fullName;
    private String branch;
    private String year;
    private int matchPercentage;

    public MatchResponse() {
    }

    public MatchResponse(Long id, String fullName,
                         String branch,
                         String year,
                         int matchPercentage) {
        this.id = id;
        this.fullName = fullName;
        this.branch = branch;
        this.year = year;
        this.matchPercentage = matchPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
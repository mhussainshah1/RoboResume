package com.company;

enum Rating {
    Fundamental,
    Novice,
    Intermediate,
    Advanced,
    Expert;
}

public class Skill {
    private String Competency;
    private String Proficiency;
    private Rating rating;

    public String getCompetency() {
        return Competency;
    }

    public void setCompetency(String competency) {
        Competency = competency;
    }

    public String getProficiency() {
        return Proficiency;
    }

    public void setProficiency(String proficiency) {
        Proficiency = proficiency;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}

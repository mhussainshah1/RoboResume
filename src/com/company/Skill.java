package com.company;

public class Skill {
    private String Competency;
    private String Proficiency;

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

    @Override
    public String toString() {
        return Competency + "," +Proficiency + "\n";
    }
}

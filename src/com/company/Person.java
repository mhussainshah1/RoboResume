package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

public class Person implements Serializable{
    //default serialVersion id
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String phoneNumber;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Skill> skills;

    public Person(){
        educations = new ArrayList<>();
        experiences = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(isEmailValid(email)){
            this.email = email;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(isPhoneNumberValid(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    private boolean isEmailValid(String email){
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        boolean isValid = false;
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }

    @Override
    public String toString() {
        String output ="\n" +
                name + "\n" +
                email + "\n" +
                phoneNumber+ "\n";
        output +="\n"+"Education\n";
        for(Education education : educations){
            output += education + "\n";
        }
        output += "\n"+"Experience\n" ;
        for(Experience experience: experiences){
            output += experience+ "\n";
        }
        output += "\n"+"Skills\n";
        for(Skill skill: skills){
            output += skill;
        }
        return output;
    }
}

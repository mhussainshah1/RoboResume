package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * a name,
 * an email address,
 * a phone number,
 * zero or more educational achievements each of which contain a school and a year and a degree and major,
 * zero or more experiences each contains a company, title, date and description,
 * zero or more skills - each with a Competency Proficiency rating (Fundamental, Novice, Intermediate, Advanced, Expert)
 */
public class ResumeBuilder {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        char answer;
        char choice;
        List<Person> people = new ArrayList<>();

        do {
            Person person = new Person();
            System.out.print("Enter name: ");
            person.setName(keyboard.nextLine());

            System.out.print("Enter Email: ");
            person.setEmail(keyboard.nextLine());

            System.out.print("Enter Phone number: ");
            person.setPhoneNumber(keyboard.nextLine());

            //Education
            System.out.print("Do you have any Education? (y/n) ");
            choice = keyboard.nextLine().charAt(0);
            while( choice == 'y'){
                Education education = new Education();
                System.out.print("Enter School Name: ");
                education.setSchool(keyboard.nextLine());

                System.out.print("Enter year: ");
                education.setYear(keyboard.nextLine());

                System.out.print("Enter degree: ");
                education.setDegree(keyboard.nextLine());

                System.out.print("Enter major: ");
                education.setMajor(keyboard.nextLine());
                person.getEducations().add(education);

                System.out.print("Do you want to enter another Education? (y/n)");
                choice = keyboard.nextLine().charAt(0);
            }
            //Experience
            System.out.print("Do you have any Experience? ");
            choice = keyboard.nextLine().charAt(0);
            while( choice == 'y'){
                Experience experience = new Experience();
                System.out.print("Enter Company Name: ");
                experience.setCompany(keyboard.nextLine());

                System.out.print("Enter title: ");
                experience.setTitle(keyboard.nextLine());

                System.out.print("Enter dates from - to: (MMM YYYY - MMM YYYY) ");
                experience.setDate(keyboard.nextLine());

                List<String> description = experience.getDescriptions();
                while (choice == 'y'){
                    System.out.print("Enter Description: ");
                    description.add(keyboard.nextLine());
                    experience.setDescriptions(description);
                    System.out.print("Do you have another description? (y/n)");
                    choice = keyboard.nextLine().charAt(0);
                }
                person.getExperiences().add(experience);

                System.out.print("Do you want to enter another Experience? (y/n)");
                choice = keyboard.nextLine().charAt(0);
            }
          //Skills
            System.out.print("Do you have any Skill? (y/n)");
            choice = keyboard.nextLine().charAt(0);
            while( choice == 'y'){
                Skill skill = new Skill();
                System.out.print("Enter competency: ");
                skill.setCompetency(keyboard.nextLine());

                System.out.print("Enter Proficiency rating (Fundamental, Novice, Intermediate, Advanced, Expert): ");
                skill.setProficiency(keyboard.nextLine());

                person.getSkills().add(skill);

                System.out.print("Do you want to enter another Skill (y/n)");
                choice = keyboard.nextLine().charAt(0);
            }
            people.add(person);

            System.out.print("Do you want to enter another resume? (y/n)");
            choice = keyboard.nextLine().charAt(0);
        } while (choice == 'y');

        for (Person person: people) {
            System.out.println(person);
        }
    }
}

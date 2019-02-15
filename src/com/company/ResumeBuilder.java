package com.company;

import java.io.*;
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
 *
 * Done Already?
 * Allow a person to change their name, e-mail address and/or phone number
 *
 * Create a recruiter class. The recruiter can search all the resumes and find those with a particular skill. You only
 * have to search for one skill. So if you have five resumes and three contain Java then the search should return a list
 * of those three people's names.
 */
public class ResumeBuilder {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        char answer;
        char choice;
        List<Person> people = new ArrayList<>();

            //read from a file
            System.out.println("reading resume from a file");
            String filename = System.getProperty("user.dir") + File.separatorChar + "Muhammad Shah.txt";
            System.out.println(filename);

            BufferedReader reader = null;
            File file = new File(filename);
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
//                    String[] key_value_pair = line.split("\t");
//                    System.out.println("KV Pair:" + key_value_pair[0] + " " + key_value_pair[1]);
//                    //the key is in key_value_pair[0]
//                    //the value is in key_value_pair[1]
//                    // now you could add it back to the hash map if it isn't there already
//
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("File does not exist!");
            }

            //file into arraylist


        do {
            Person person = new Person();
            System.out.print("Enter name: ");
            person.setName(keyboard.nextLine());

            System.out.print("Enter Email: ");
            person.setEmail(keyboard.nextLine());

            System.out.print("Enter Phone number: ");
            person.setPhoneNumber(keyboard.nextLine());

            //Education
            System.out.print("Do you have any Education? (y/n):");
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

                System.out.print("Do you want to enter another Education? (y/n):");
                choice = keyboard.nextLine().charAt(0);
            }
            //Experience
            System.out.print("Do you have any Experience? (y/n):");
            choice = keyboard.nextLine().charAt(0);
            while( choice == 'y'){
                Experience experience = new Experience();
                System.out.print("Enter Company Name: ");
                experience.setCompany(keyboard.nextLine());

                System.out.print("Enter title: ");
                experience.setTitle(keyboard.nextLine());

                System.out.print("Enter dates from - to (MMM YYYY - MMM YYYY): ");
                experience.setDate(keyboard.nextLine());

                List<String> description = experience.getDescriptions();
                while (choice == 'y'){
                    System.out.print("Enter Description: ");
                    description.add(keyboard.nextLine());
                    experience.setDescriptions(description);
                    System.out.print("Do you have another description? (y/n):");
                    choice = keyboard.nextLine().charAt(0);
                }
                person.getExperiences().add(experience);

                System.out.print("Do you want to enter another Experience? (y/n):");
                choice = keyboard.nextLine().charAt(0);
            }
          //Skills
            System.out.print("Do you have any Skill? (y/n):");
            choice = keyboard.nextLine().charAt(0);
            while( choice == 'y'){
                Skill skill = new Skill();
                System.out.print("Enter competency: ");
                skill.setCompetency(keyboard.nextLine());

                System.out.print("Enter Proficiency rating (Fundamental, Novice, Intermediate, Advanced, Expert): ");
                skill.setProficiency(keyboard.nextLine());

                person.getSkills().add(skill);

                System.out.print("Do you want to enter another Skill? (y/n):");
                choice = keyboard.nextLine().charAt(0);
            }
            people.add(person);

            System.out.print("Do you want to enter another resume? (y/n):");
            choice = keyboard.nextLine().charAt(0);
        } while (choice == 'y');

        for (Person person: people) {
            System.out.println(person);

            System.out.print("Do you want to change your name? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if(choice == 'y') {
                System.out.print("Enter corrected name: ");
                person.setName(keyboard.nextLine());
            }

            System.out.print("Do you want to change your email? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if(choice == 'y') {
                System.out.print("Enter corrected Email: ");
                person.setEmail(keyboard.nextLine());
            }

            System.out.print("Do you want to change your Phone number? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if(choice == 'y') {
                System.out.print("Enter corrected Phone number: ");
                person.setPhoneNumber(keyboard.nextLine());
            }

            //writing resume into a file
            System.out.println("writing the resume into a file");
            filename = System.getProperty("user.dir") + File.separatorChar + person.getName() + ".txt";
            System.out.println(filename);

            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new File(filename));
                writer.println(person);
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist!");
            } finally {
                writer.close();
            }
        }
    }

    public static ArrayList<String> readLines(File file) throws Exception {
        if (!file.exists()) {
            return new ArrayList<String>();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> results = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            results.add(line);
            line = reader.readLine();
        }
        reader.close();
        return results;
    }
}

package com.company;
/**
 * The program overwriting existing file
 *
 */
import util.Finder;
import util.FileOperationOnList;

import java.io.*;
import java.nio.file.*;
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
 * <p>
 * Done Already?
 * Allow a person to change their name, e-mail address and/or phone number
 * <p>
 * Create a recruiter class. The recruiter can search all the resumes and find those with a particular skill. You only
 * have to search for one skill. So if you have five resumes and three contain Java then the search should return a list
 * of those three people's names.
 */
public class ResumeBuilder {

    private static int DATE_INDEX = 1;
    private static int NAME_INDEX = 2;
    private static int EMAIL_INDEX =3;
    private static int PHONE_NUMBER_INDEX = 4;

    public static void main(String[] args) {
        readResume();
        newResume();
    }

    public static void readResume() {
        Scanner keyboard = new Scanner(System.in);
        char choice;

        System.out.print("Enter your first name: ");
        String name = keyboard.nextLine();

        //String startingDir = System.getProperty("user.dir") + File.separatorChar;// + name + ".txt";
        Path path = Paths.get(System.getProperty("user.dir") + File.separatorChar);

        String pattern = "*" + name + "*.txt";
        Finder finder = new Finder(pattern);
        String filePath = "", filename = "";
        try {
            Files.walkFileTree(path, finder);
            System.out.println("We have found " + finder.done() +
                    " following resume(s) have " + name);
            for (Path p : finder.getPathList()) {
                filePath = p.toString();
                filename = p.getFileName().toString();
                System.out.println(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(filename);
        if (file.exists()) {
            //read from a file into arraylist
            System.out.println("reading resume from a file");
            List<String> document = new ArrayList<>();
            FileOperationOnList fo = new FileOperationOnList(document, filename);
            try {
                fo.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            document = fo.getDocument();
            for (String line : document) {
                System.out.println(line);
            }
//            for (int i = 0; i < 5; i++) {
//                System.out.println(i + " " + document.get(i));
//            }
            System.out.print("Do you want to change your name? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if (choice == 'y') {
                System.out.print("Enter corrected name: ");
                document.set(NAME_INDEX, keyboard.nextLine());
            }

            System.out.print("Do you want to change your email? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if (choice == 'y') {
                System.out.print("Enter corrected Email: ");
                document.set(EMAIL_INDEX, keyboard.nextLine());
            }

            System.out.print("Do you want to change your Phone number? (y/n)");
            choice = keyboard.nextLine().charAt(0);

            if (choice == 'y') {
                System.out.print("Enter corrected Phone number: ");
                document.set(PHONE_NUMBER_INDEX, keyboard.nextLine());
            }
            fo = new FileOperationOnList(document,  document.get(DATE_INDEX) + document.get(NAME_INDEX) + ".txt");
            try {
                fo.writeFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.print("Do you want to delete old file at (" + filePath + ")?");
            choice = keyboard.nextLine().charAt(0);
            if (choice == 'y') {
                try {
                    Files.delete(path);
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", path);
                } catch (DirectoryNotEmptyException x) {
                    System.err.format("%s not empty%n", path);
                } catch (IOException x) {
                    // File permission problems are caught here.
                    System.err.println(x);
                }
            }
        } else {
            System.out.println("There is no resume");
        }
    }

    public static void newResume(){
        List<Person> people = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        char choice;
        System.out.print("Do you want to enter resume? (y/n):");
        choice = keyboard.nextLine().charAt(0);

        if (choice == 'y') {
            do {
                Person person = new Person();
                System.out.print("Enter Date (MM/dd/yyyy)");
                person.setDate(keyboard.nextLine());

                System.out.print("Enter name: ");
                person.setName(keyboard.nextLine());

                System.out.print("Enter Email: ");
                person.setEmail(keyboard.nextLine());

                System.out.print("Enter Phone number: ");
                person.setPhoneNumber(keyboard.nextLine());

                //Education
                System.out.print("Do you have any Education? (y/n):");
                choice = keyboard.nextLine().charAt(0);
                while (choice == 'y') {
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
                while (choice == 'y') {
                    Experience experience = new Experience();
                    System.out.print("Enter Company Name: ");
                    experience.setCompany(keyboard.nextLine());

                    System.out.print("Enter title: ");
                    experience.setTitle(keyboard.nextLine());

                    System.out.print("Enter dates from - to (MMM YYYY - MMM YYYY): ");
                    experience.setDate(keyboard.nextLine());

                    List<String> description = experience.getDescriptions();
                    while (choice == 'y') {
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
                while (choice == 'y') {
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

            for (Person person : people) {
                System.out.println(person);

                System.out.print("Do you want to change your name? (y/n)");
                choice = keyboard.nextLine().charAt(0);

                if (choice == 'y') {
                    System.out.print("Enter corrected name: ");
                    person.setName(keyboard.nextLine());
                }

                System.out.print("Do you want to change your email? (y/n)");
                choice = keyboard.nextLine().charAt(0);

                if (choice == 'y') {
                    System.out.print("Enter corrected Email: ");
                    person.setEmail(keyboard.nextLine());
                }

                System.out.print("Do you want to change your Phone number? (y/n)");
                choice = keyboard.nextLine().charAt(0);

                if (choice == 'y') {
                    System.out.print("Enter corrected Phone number: ");
                    person.setPhoneNumber(keyboard.nextLine());
                }

                //writing resume into a file
                System.out.println("writing the resume into a file");
                String filename = System.getProperty("user.dir") + File.separatorChar + person.getDate() + person.getName()  + ".txt";
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
    }

}

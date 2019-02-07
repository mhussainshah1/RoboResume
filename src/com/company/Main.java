package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * a name,
 * an email address,
 * a phone number,
 *
 * zero or more educational achievements each of which contain a school and a year and a degree and major,
 * zero or more experiences each contains a company, title, date and description,
 * zero or more skills - each with a Competency Proficiency rating (Fundamental, Novice, Intermediate, Advanced, Expert)
 */
public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String answer;
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

            do{
                System.out.println("Enter School Name");

                System.out.println("Enter year");

                System.out.println("Enter degree");

                System.out.println("Enter major");

                System.out.print("Do you want to add another Education (y/n)");
                choice = keyboard.next().charAt(0);
            } while (choice == 'y');

            do{

                System.out.print("Do you want to add another Experience (y/n)");
                choice = keyboard.next().charAt(0);
            } while (choice == 'y');

            do{

                System.out.print("Do you want to add another Skill (y/n)");
                choice = keyboard.next().charAt(0);
            } while (choice == 'y');

            people.add(person);
            System.out.print("Do you want to add another resume (y/n)");
            choice = keyboard.next().charAt(0);
        } while (choice == 'y');

        for (Person person: people) {
            System.out.println(person);
        }
    }
}

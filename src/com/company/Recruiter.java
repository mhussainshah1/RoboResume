package com.company;

import util.FileOperationOnList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Create a recruiter class. The recruiter can search all the resumes and find those with a particular skill. You only
 * have to search for one skill. So if you have five resumes and three contain Java then the search should return a list
 * of those three people's names.
 */

public class Recruiter {
    private static int DATE_INDEX = 1;
    private static int NAME_INDEX = 2;
    public static void main(String[] args) {
        System.out.println("There are following people are in the system start from latest date");

        //Load all resume paths
        Path path = Paths.get(System.getProperty("user.dir") + File.separatorChar);

        List<String> fileNameList = new ArrayList<>();

        //HashMap of Recruit File name and their resume
        Map<LocalDate, List<String>> recruiterMap = new HashMap<>();

        //Add all names of txt files in the "fileNameList"
        try {
            Files.walk(path)
                    .filter(new Predicate<Path>() {
                        @Override
                        public boolean test(Path path) {
                            return path.toString().endsWith(".txt");
                        }
                    }).forEach(new Consumer<Path>() {
                        @Override
                        public void accept(Path path) {
                            fileNameList.add(path.getFileName().toString());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //The file names are added by default in the sorted order.
        //Make reverse to make most current date on top
        Collections.reverse(fileNameList);

        for (String fileName : fileNameList) {
            List<String> resume = new ArrayList<>();
            //System.out.println(fileName);
            FileOperationOnList fo = new FileOperationOnList(resume, fileName);
            try {
                fo.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resume = fo.getDocument();
            System.out.println(resume.get(NAME_INDEX) +"\tsubmited resume on\t" + resume.get(DATE_INDEX));
            //first 10 characters of file name is date submittted in string .
           // String string = fileName.substring(0,10);
            //parse into Local Date
            LocalDate date = LocalDate.parse(resume.get(DATE_INDEX));
            recruiterMap.put(date, resume);
        }

        System.out.print("\nEnter the skill you are looking for: ");
        Scanner keyboard = new Scanner(System.in);
        String skill = keyboard.nextLine().toLowerCase();

        //iterating over values only
        for (List<String> resume : recruiterMap.values()) {
            int start = resume.indexOf("Skills");
            int end = resume.size();
            //System.out.println(start + " " + end);
            for (int i = start; i < end; i++) {
                if (resume.get(i).toLowerCase().contains(skill)) {
                    System.out.println(resume.get(NAME_INDEX) + " has java skill");
                    break;
                }
            }
        }
    }
}

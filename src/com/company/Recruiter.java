package com.company;

import util.FileOperationOnList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

/**
 * Create a recruiter class. The recruiter can search all the resumes and find those with a particular skill. You only
 * have to search for one skill. So if you have five resumes and three contain Java then the search should return a list
 * of those three people's names.
 */

public class Recruiter {
    public static void main(String[] args) {
        System.out.println("There are following people files in the system");

        //Load all resume paths
        Path path = Paths.get(System.getProperty("user.dir") + File.separatorChar);
        List<String> fileNameList = new ArrayList<>();

        //HashMap of Recruit File name and their resume
        Map<String, List<String>> recruiterMap = new HashMap<>();

        //Add all path of txt files
        try {
            Files.walk(path)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(new Consumer<Path>() {
                        @Override
                        public void accept(Path path) {
                            fileNameList.add(path.getFileName().toString());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String fileName : fileNameList) {
            List<String> resume = new ArrayList<>();
            System.out.println(fileName);
            FileOperationOnList fo = new FileOperationOnList(resume, fileName);
            try {
                fo.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resume = fo.getDocument();
            recruiterMap.put(fileName, resume);
        }

        System.out.print("\nEnter the skill you are looking for: ");
        Scanner keyboard = new Scanner(System.in);
        String skill = keyboard.nextLine().toLowerCase();

        //iterating over values only
        for (List<String> resume : recruiterMap.values()) {
            int start = resume.indexOf("Skills");
            int end = resume.size();
            //System.out.println(start + " " + end);
            for (int i = start; i < resume.size(); i++) {
                if (resume.get(i).toLowerCase().contains(skill)) {
                    System.out.println(resume.get(2) + " has java skill");
                    break;
                }
            }
        }
    }
}

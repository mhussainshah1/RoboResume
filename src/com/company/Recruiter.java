package com.company;

import util.FileOperationOnList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Create a recruiter class. The recruiter can search all the resumes and find those with a particular skill. You only
 * have to search for one skill. So if you have five resumes and three contain Java then the search should return a list
 * of those three people's names.
 */
public class Recruiter {
    public static void main(String[] args) {
        //Load all resume paths
        Path path = Paths.get(System.getProperty("user.dir") + File.separatorChar);
//        System.out.println(path);
        List<Path> pathList = new ArrayList<>();

        //HashMap of Recruit File Path and their resume
        Map<Path , List<String> > recruiterMap = new HashMap<>();

        try{
            Files.walk(path)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(new Consumer<Path>() {
                        @Override
                        public void accept(Path path) {
                            pathList.add(path);
                        }
                    });
        }catch (IOException e){
            e.printStackTrace();
        }

        for (Path p: pathList) {
            List<String> resume = new ArrayList<>();
            String recuitFileName = p.getFileName().toString();
            System.out.println(recuitFileName);
            FileOperationOnList fo = new FileOperationOnList(resume, recuitFileName);
            try {
                fo.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resume = fo.getDocument();
//            for (String line : document) {
//                System.out.println(line);
//            }
            recruiterMap.put(p,resume);
        }


//        Path path1 = Paths.get(filename);
//        System.out.println(path1);
//
//        File file = new File(filename);
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String line = reader.readLine();
//            while (line != null) {
//                System.out.println(line);
////                    String[] key_value_pair = line.split("\t");
////                    System.out.println("KV Pair:" + key_value_pair[0] + " " + key_value_pair[1]);
////                    //the key is in key_value_pair[0]
////                    //the value is in key_value_pair[1]
////                    // now you could add it back to the hash map if it isn't there already
////
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            System.out.println("File does not exist!");
//        }

    }
}

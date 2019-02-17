package util;
/**
 * Sample code that finds files that match the specified glob pattern.
 * For more information on what constitutes a glob pattern, see
 * https://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
 * <p>
 * The file or directories that match the pattern are printed to
 * standard out.  The number of matches is also printed.
 * <p>
 * When executing this application, you must put the glob pattern
 * in quotes, so the shell will not expand any wild cards:
 * java Find . -name "*.java"
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Finder extends SimpleFileVisitor<Path> {

    private final PathMatcher matcher;
    private int numMatches = 0;
    private List<Path> pathList = new ArrayList<>();

    public Finder(String pattern) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
    }

    // Compares the glob pattern against
    // the file or directory name.
    public void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            numMatches++;
            pathList.add(file);
            //System.out.println(file);
        }
    }

    // Prints the total number of
    // matches to standard out.
    public String done() {
        return("Found: " + numMatches);
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public List<Path> getPathList() {
        return pathList;
    }
}

class FindUsage {

    static void usage() {
        System.err.println("java Find <path> -name \"<glob_pattern>\"");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
//        if (args.length < 3 || !args[1].equals("-name"))
//            usage();

        Path startingDir = Paths.get(System.getProperty("user.dir") + File.separatorChar);//args[0]);
        String pattern = "Muhammad*.txt";//args[2];

        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, finder);
        finder.done();
    }
}
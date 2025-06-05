package java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main (String [] args) {
        try{
            String resumeText = Files.readString(Paths.get("samples/sample_resume.txt"));
            String jobText = Files.readString(Paths.get("samples/sample_job_description.txt"));

            Set<String> resumeWords = tokenize(resumeText);
            Set<String> jobWords = tokenize(jobText);

            int wordMatch = 0;
            for(String words : resumeWords) {
                if(jobWords.contains(words)) {
                    wordMatch++;
                }
            }

            double percentage_Match = 100.0 * (wordMatch / jobWords.size());

            ProcessBuilder pb = new ProcessBuilder("python", "python/similarity.py", resumeText, jobText);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            Scanner sc = new Scanner(process.getInputStream());
            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (IOException E) {
            System.out.println("Error loading files: " + E.getMessage());
        }
    }
    public static Set<String> tokenize(String text) {
        String[] tokens = text.toLowerCase().split("\\W+");
        return new HashSet<>(Arrays.asList(tokens));
    }
}

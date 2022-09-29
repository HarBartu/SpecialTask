import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner; 
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Solution {
    public static void main(String[] args) {
        try {
            File[] listOfFiles = filterFiles("./"); //Provide path to directory with input files
            if(listOfFiles.length == 0) {
                System.out.println("No files found with .txt extension");
            }
            else {
                for (File file : listOfFiles) {
                    System.out.println("File: " + file.getName());
                    System.out.println();
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        if (checkNumbers(data)) {
                            System.out.println(data);
                        }
                    }
                    reader.close();
                    System.out.println();
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }    

    private static File[] filterFiles (String dirPath) {
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        File folder = new File(dirPath);
        return folder.listFiles(filter);   
    }

    private static boolean checkNumbers (String line) {
        String regexPattern = "(\\d+)?";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                if (Integer.parseInt(matcher.group(1)) >= 10) {
                    return true;
                }
            }
        }
        return false;
    }
}
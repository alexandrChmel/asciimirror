package asciimirror;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- getting user input ----
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Read the file and find the longest line
        LinkedList<String> list = new LinkedList<>();
        int maxLength = 0;

        try {
            File file = new File(input);
            Scanner fileSc = new Scanner(file);

            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                list.add(line); // adding user input file to list
                if (line.length() > maxLength) {
                    maxLength = line.length();  // Update maxLength to the longest line
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }
        // formatting input
        
        for (String line : list) {
            StringBuilder resultLine = new StringBuilder(line);
            int resultLength = resultLine.length();

            // adding spaces and mirror
            resultLine.append(" ".repeat(maxLength - resultLength));

            // Create the result mirrored line by reversing and swapping characters
            StringBuilder mirroredLine = new StringBuilder();
            mirroredLine.append(" ".repeat(maxLength - resultLength));

            for (int j = resultLength - 1; j >= 0; j--) {
                mirroredLine.append(resultLine.charAt(j));
            }
            String resultLineString2 = mirroredLine.toString();
            resultLineString2 = swapParens(resultLineString2, '<', '>');
            resultLineString2 = swapParens(resultLineString2, '[', ']');
            resultLineString2 = swapParens(resultLineString2, '{', '}');
            resultLineString2 = swapParens(resultLineString2, '(', ')');
            resultLineString2 = swapParens(resultLineString2, '\\', '/');

            // ---- printing results
            System.out.println(resultLine + " | " + resultLineString2);
        }
    }
    static String swapParens(String s, char oldChar, char newChar) {
        return s.replace(oldChar, '\0').replace(newChar, oldChar).replace('\0', newChar);
    }
}
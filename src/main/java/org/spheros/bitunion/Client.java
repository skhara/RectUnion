
package org.spheros.bitunion;

import org.spheros.bitunion.lib.BitUnion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Client {
    private static final String DEFAULT_INPUT_FILE_NAME = "input.txt";
    private static final String DEFAULT_OUTPUT_FILE_NAME = "output.txt";
    private static final String INPUT_DATA_HEADER = "INPUT:";
    private static final String OUTPUT_DATA_HEADER = "OUTPUT:";

    private String inputFileName = DEFAULT_INPUT_FILE_NAME;
    private String outputFileName = DEFAULT_OUTPUT_FILE_NAME;

    private List<String> lines;
    private BitUnion union;

    public static void main(String[] args) {
        Client client = new Client();
        client.findSquare(args);
    }

    private void findSquare(String[] args) {
        validateArguments(args);
        getInputFromFile();
        validateInput();
        unionRectangles();
        writeResultToFile();
    }

    private void validateArguments(String[] args) {
        if (args.length != 2) {
            System.err.println("Illegal number of arguments. Trying defaults parameters...");
        } else {
            inputFileName = args[0];
            outputFileName = args[1];
        }
    }

    private void getInputFromFile() {
        try {
            lines = Files.readAllLines(Paths.get(inputFileName));
        }catch (NoSuchFileException e) {
            printErrorMsgAndExit("No input file found: " + inputFileName);
        } catch (IOException e) {
            printErrorMsgAndExit(e.getMessage());
        }
    }

    private void validateInput() {
        String header = lines.remove(0).trim();
        if (!header.matches(INPUT_DATA_HEADER)) {
            printErrorMsgAndExit("Incorrect data header: " + header + ". Should be + " + INPUT_DATA_HEADER);
        }

        if (lines.size() >100) {
            printErrorMsgAndExit("Two many input data! 100 rectangles limit exceeded!");
        }

        int index = 1;
        for (String line : lines) {
            if (!line.trim().matches("(\\d+\\s+){3}\\d+")) {
                printErrorMsgAndExit("Incorrect input data in at line #" + index + ": " + line);
            }
            index++;
        }
    }

    private void unionRectangles() {
        union = BitUnion.getInstance();

        int index = 1;
        for (String line : lines) {
            String[] coords = line.trim().split("\\s+");
            try {
                union.unionRect(
                        Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
                        Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));
            } catch (IllegalArgumentException e) {
                printErrorMsgAndExit("Bounds are illegal at line #" + index + ": " + line);
            }
            index++;
        }
    }

    private void writeResultToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(outputFileName));
            writer.println(OUTPUT_DATA_HEADER);

            writer.println(union.getSquare());

            writer.close();
        } catch (IOException e) {
            printErrorMsgAndExit(e.getMessage());
        }
    }

    private void printErrorMsgAndExit(String errMsg) {
        System.err.println(errMsg);
        System.exit(1);
    }
}

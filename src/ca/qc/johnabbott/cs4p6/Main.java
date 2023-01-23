package ca.qc.johnabbott.cs4p6;

import java.io.*;
import java.util.Scanner;

public class Main {
    // We don't support more than this number of fields per line...
    public static final int MAX_FIELDS = 5;

    public static void main(String[] args) {
        try {
            int linesRead = cut("data/in1.txt", "data/out.txt", "1,2,2,3", ',');
            System.out.printf("Read %d lines.", linesRead);

        }
        catch (IOException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Extract specified fields from the input data structured in columns by a delimiter.
     * @param inputFileName The file to read the data from, if blank then read from standard input.
     * @param outputFileName The file to write the data to, if blank then write to standard output.
     * @param format Determines the format of the output file. Comma delimited numbers and/or number
     *               range corresponding to the columns in the input data. Ex: "1,3,2" would output
     *               columns 1 and 3 and 2 in the output (comma delimited). You can specify ranges "1-3"
     *               or open-ended ranges "-5" or "2-". Columns can be repeated in the output.
     * @param delimiter The character delimiting the columns.
     * @return The number of lines read.
     * @throws IOException If there is a problem reading or writing the files.
     */
    public static int cut(String inputFileName, String outputFileName, String format, char delimiter) throws IOException {
        Scanner scan;
        if(inputFileName == ""){
            scan = new Scanner(System.in).useDelimiter(String.valueOf(delimiter));
        }
        else {
            scan = new Scanner(new FileReader(inputFileName)).useDelimiter(String.valueOf(delimiter));
        }



        PrintWriter pw = new PrintWriter( new FileWriter(outputFileName));

        String [] formatValue = format.split(String.valueOf(delimiter));
        int [] numberValue = new int[formatValue.length];
        for(int i = 0; i<formatValue.length;i++){
            numberValue[i] = Integer.valueOf(formatValue[i]);
        }
        int counter = 0;
        while (scan.hasNext()){
            String line = scan.nextLine();
            String [] content = line.split(String.valueOf(delimiter));
            if (numberValue.length>1) {
                for (int i = 0; i < numberValue.length; i++) {
                    pw.print(content[numberValue[i] - 1]);
                    pw.print(", ");
                }
                pw.println("");
            }
            else{
                for (int i = 0; i < numberValue.length; i++) {
                    pw.print(content[numberValue[i] - 1]);
                    pw.println("");
                }

            }

            counter++;
        }


        scan.close();
        pw.close();
//
//        String [] order = format.split(",");
//        int [] number = new int[order.length];
//        for(int i = 0; i<order.length;i++){
//            number[i] = Integer.valueOf(order[i]);
//        }
//
//        String [] col1 = myArray[0].split(",");
//        String [] col2 = myArray[1].split(",");
//        String [] col3 = myArray[2].split(",");
//
//        for(int i = 0; i<number.length;i++)
//        {
//            col1
//        }

        return counter;
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {
    public List<List<Double>> getResultOfTests() {
        return resultOfTests;
    }

    public List<String> getStatusOfPatients() {
        return statusOfPatients;
    }

    protected List<List<Double>> resultOfTests = new ArrayList<>();

    protected List<String> statusOfPatients = new ArrayList<>();
    protected List<String> rows;



    public ReadFile(File file) {
           this.makeRows(file);
           ArrayList<ArrayList<String>> tmp = new ArrayList<>();
           tmp=this.convertRowToArray();
        try {
            // because this function throw an exception (class we already build )
            extractData(tmp);
        } catch (IncorrectInputException e) {
            // handel our exception
            System.out.println("incorrect input detected: " + e.getMessage());
        }
        }

        void makeRows(File file) {
            try {//to separate the file to list of rows
                rows = new ArrayList<>();
                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    String data = read.nextLine();
                    rows.add(data);
                }
                read.close();
            } catch (FileNotFoundException e) {//the file is not found
                System.out.println("the file is not found.");
                e.printStackTrace();
            } catch (IllegalArgumentException ex) {//the file is not supported
                System.out.println("illegal argument enter illegal argument");
                ex.printStackTrace();
            }
        }

        ArrayList<ArrayList<String>> convertRowToArray(){

            ArrayList<ArrayList<String>> valueAsString = new ArrayList<>();

            for (int index = 1; index < rows.size(); index++) {
                String tmp = rows.get(index);
                tmp=tmp.replaceAll(" ","");
                StringTokenizer separatedStirng = new StringTokenizer(tmp, ",");
                valueAsString.add(new ArrayList<>());
                while (separatedStirng.hasMoreTokens()) {
                    valueAsString.get(index - 1).add(separatedStirng.nextToken());
                }
            }

            return valueAsString;
    }

    void extractData(ArrayList<ArrayList<String>> list) throws IncorrectInputException
    {
        int numberOfColumns = list.get(0).size();
        for (int index = 0; index < list.size(); index++) {
            if (numberOfColumns - list.get(index).size() != 0) {
                System.out.println(index);
                throw new IncorrectInputException();
            }
            resultOfTests.add(new ArrayList<>());

            for (int second = 0; second < numberOfColumns-1; second++) {
                    try {
                        this.resultOfTests.get(index).add(Double.parseDouble(list.get(index).get(second)));
                    } catch (IllegalArgumentException ex1) {
                        System.out.println("Illegal argument in test results");
                        ex1.printStackTrace();
                    }
            }
            String result = list.get(index).get(numberOfColumns-1);
            if (result.equals("High") || result.equals("Low")) {
                this.statusOfPatients.add(result);
            }
            else {
                throw new IllegalArgumentException("Illegal argument in patient status");
            }
        }
    }
    void print(){
        int i=0;
        for (List<Double> sublist :resultOfTests ) {
            for (Double element : sublist) {
                System.out.print(element + " ");

            }
            System.out.println(statusOfPatients.get(i));
            i++;
            System.out.println();
        }
    }
}

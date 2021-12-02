import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        File input = new File("december-1/advent-of-code-dec-1/src/input.txt");
        System.out.println("Total number of measurements: " + input.length());
        System.out.println("Total number of increases in measurements: " + getNumIncreasesInMeasurements(input));
        System.out.println("Total number of increases in measurements (by 3): " + getNumIncreasesInMeasurementsByThree(input));
    }


    public static int getNumIncreasesInMeasurements(File input) {
        return getNumberOfIncreasesFromList(getNumberListFromFile(input));
    }

    public static int getNumIncreasesInMeasurementsByThree(File input) {
        ArrayList<Integer> measurements = getNumberListFromFile(input);
        ArrayList<Integer> measurementsBy3 = getTriplesSumsFromList(measurements);
        return getNumberOfIncreasesFromList(measurementsBy3);
    }

    public static ArrayList<Integer> getTriplesSumsFromList(ArrayList<Integer> list) {
        ArrayList<Integer> sums = new ArrayList<>();
        for(int i = 0; i < list.size()-2; i++) {
            Integer sum = list.get(i) + list.get(i+1) + list.get(i+2);
            sums.add(sum);
        }
        return sums;
    }

    public static int getNumberOfIncreasesFromList(ArrayList<Integer> list) {
        int numIncreases = 0;
        if(list.size() > 1) {
            for(int i = 1; i < list.size(); i++) {
                if(list.get(i-1) < list.get(i)) {
                    numIncreases++;
                }
            }
        }
        return numIncreases;
    }

    public static ArrayList<Integer> getNumberListFromFile(File file) {
        ArrayList<Integer> res = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currMeasurement;
            while ((currMeasurement = br.readLine()) != null) {
                res.add(Integer.parseInt(currMeasurement));
            }
        }  catch (Exception e) {
            System.out.println("Exception occured: \n" + e.getMessage());
        }

        return res;
    }

}

//Total number of increases in measurements: 1477
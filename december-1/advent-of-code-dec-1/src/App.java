

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        File input = new File("bin/input.txt");
        System.out.println("Total number of measurements: " + input.length());
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            int numIncreasesInMeasurement = 0;
            Integer previousMeasurement = null;
            String currMeasurement;
            while ((currMeasurement = br.readLine()) != null) {
                if(previousMeasurement != null) {
                    Integer currNum = Integer.parseInt(currMeasurement);
                    System.out.println("(prevNum,currNum):(" + previousMeasurement + "," + currNum + ")");
                    if(currNum > previousMeasurement) {
                        numIncreasesInMeasurement++;
                    }
                    previousMeasurement = currNum;
                } else {
                    previousMeasurement = Integer.parseInt(currMeasurement);
                }
            }
            System.out.println("Total number of increases in measurements: " + numIncreasesInMeasurement);
        }  catch (Exception e) {
            System.out.println("Exception occured: \n" + e.getMessage());
        }
    }
}

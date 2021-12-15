import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        int NUM_DIGITS_IN_BINARY_NUMS = 12;
        List<String> lines = Files.lines(Paths.get("december-3/src/input.txt")).collect(Collectors.toList());
        System.out.println("Total number of binary numbers: " + lines.size());
        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonRateString = new StringBuilder();
        for(int i = 0; i < NUM_DIGITS_IN_BINARY_NUMS; i++) {
            List<String> bitsFromIndex = getListOfBitsFromIndex(lines, i);
            gammaRateString.append(getMostCommonBit(bitsFromIndex));
            epsilonRateString.append(getLeastCommonBit(bitsFromIndex));
        }
        Integer gammaDecimalValue = getDemicalFromBinary(gammaRateString.toString());
        Integer epsilonDecimalValue = getDemicalFromBinary(epsilonRateString.toString());
        System.out.println("AoC answer is: " + gammaDecimalValue * epsilonDecimalValue);

        //Part Two

        int oxygenRating = getOxygenRating(lines, NUM_DIGITS_IN_BINARY_NUMS);
        System.out.println("getOxygenRating: " + oxygenRating);

        int co2Rating = getCo2Rating(lines, NUM_DIGITS_IN_BINARY_NUMS);
        System.out.println("getCo2Rating: " + co2Rating);

        System.out.println("AoC answer is: " + oxygenRating * co2Rating);
    }

    public static List<String> getListOfBitsFromIndex(List<String> binaryNumbers, int index) {
        return binaryNumbers.stream().map(num -> num.substring(index, index+1)).collect(Collectors.toList());
    }

    public static Integer getDemicalFromBinary(String binaryNumber) {
        return Integer.parseInt(binaryNumber, 2);
    }

    public static int getMostCommonBit(List<String> bits) {
        long numOnes = bits.stream().filter(bit -> bit.equalsIgnoreCase("1")).count();
        long numZeros = bits.stream().filter(bit -> bit.equalsIgnoreCase("0")).count();
        if(numOnes == numZeros) return 1;
        return (numOnes > numZeros) ? 1 : 0;
    }

    public static int getLeastCommonBit(List<String> bits) {
        long numOnes = bits.stream().filter(bit -> bit.equalsIgnoreCase("1")).count();
        long numZeros = bits.stream().filter(bit -> bit.equalsIgnoreCase("0")).count();
        if(numOnes == numZeros) return 0;
        return (numOnes > numZeros) ? 0 : 1;
    }

    /*
        To find oxygen generator rating, determine the most common value (0 or 1) in the current bit position, 
        and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values with
        a 1 in the position being considered.
    */
    public static Integer getOxygenRating(List<String> binaryNums, int numDigits) {
        String oxygenRating = null;
        int currIndex = 0;
        while(oxygenRating == null && currIndex < numDigits) {
            int mostCommonBit = getMostCommonBit(getListOfBitsFromIndex(binaryNums, currIndex));
            List<String> matchingNumbers = new ArrayList<>();
            for(String num : binaryNums) {
                int currValue = Integer.parseInt(num.substring(currIndex, currIndex+1));
                if(currValue == mostCommonBit) {
                    matchingNumbers.add(num);
                }
            }
            binaryNums = matchingNumbers;
            currIndex++;
            if(binaryNums.size() == 1) {
                oxygenRating = binaryNums.get(0);
            }
        }
        return (oxygenRating != null) ? getDemicalFromBinary(oxygenRating) : 0;
    }

    /*
        To find CO2 scrubber rating, determine the least common value (0 or 1) in the current bit position,
         and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values 
         with a 0 in the position being considered.
    */
    public static Integer getCo2Rating(List<String> binaryNums, int numDigits) {
        String co2Rating = null;
        int currIndex = 0;
        while(co2Rating == null && currIndex < numDigits) {
            int leastCommonBit = getLeastCommonBit(getListOfBitsFromIndex(binaryNums, currIndex));
            List<String> matchingNumbers = new ArrayList<>();
            for(String num : binaryNums) {
                String currChar = num.substring(currIndex, currIndex+1);
                int currValue = Integer.parseInt(currChar);
                if(currValue == leastCommonBit) {
                    matchingNumbers.add(num);
                }
            }
            binaryNums = matchingNumbers;
            currIndex++;
            if(binaryNums.size() == 1) {
                co2Rating = binaryNums.get(0);
            }
        }
        return (co2Rating != null) ? getDemicalFromBinary(co2Rating) : 0;
    }
}
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        return (numOnes > numZeros) ? 1 : 0;
    }

    public static int getLeastCommonBit(List<String> bits) {
        return (getMostCommonBit(bits) == 1) ? 0 : 1;
    }
}



//Total number of increases in measurements: 1477
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.lines(Paths.get("december-4/src/input.txt")).collect(Collectors.toList());
        System.out.println("Total number of lines: " + lines.size());
        BingoGameEngine engine = new BingoGameEngine();
        engine.initBingoGameEngine(lines);
        int numRounds = 1;
        while(!engine.isWinner() && !engine.isBingoCalloutsEmpty()) {
            System.out.println("Starting round " + numRounds);
            engine.playBingoRound();
            // engine.printAllBoardStates();
            numRounds++;
        }
        System.out.println("Winner is: " + engine.getWinner().getGameNumber());
        Bingo winner = engine.getWinner();

        System.out.println("getWinningCallout: " + winner.getWinningCallout());
        System.out.println("getSumOfUncalledNumbers: " + winner.getSumOfUncalledNumbers());
        System.out.println("AoC answer is: " + winner.getWinningCallout() * winner.getSumOfUncalledNumbers());
    
        //Part Two
        BingoGameEngine engine2 = new BingoGameEngine();
        engine.initBingoGameEngine(lines);
        
    }

}
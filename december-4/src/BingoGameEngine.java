import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BingoGameEngine {
    
    private LinkedList<Integer> bingoCalloutSequence = new LinkedList<>();
    private List<Bingo> bingoGames = new ArrayList<>();
    private Bingo winner = null;

    public BingoGameEngine () {

    }

    public void initBingoGameEngine(List<String> lines) {
        // Read first line into linked list
        List<String> bingoSequence = Arrays.asList(lines.get(0).split(","));
        System.out.println("Total number of bingo commands: " + bingoSequence.size());
        for(String bingoCallout : bingoSequence) {
            bingoCalloutSequence.addLast(Integer.parseInt(bingoCallout));
        }

        // Read remaining lines into seperate bingo games
        List<String> bingoRows = new ArrayList<>();
        for(int i = 2; i < lines.size(); i ++) {
            String currLine = lines.get(i);
            if(!currLine.equals("")) {
                bingoRows.add(currLine);
                if(bingoRows.size() == 5) {
                    bingoGames.add(new Bingo(bingoRows, bingoGames.size()+1));
                    bingoRows = new ArrayList<>();
                }
            }
        }
        System.out.println("Total number of bingo games: " + bingoGames.size());
    }

    public void playBingoRound() {
        if(bingoCalloutSequence.isEmpty()) {
            System.out.println("Could not play another round. There are no more numbers left to draw.");
        } else if(isWinner()){
            System.out.println("Could not play another round. There is already a winner.");
        } else {
            Integer nextBingoCallout = bingoCalloutSequence.removeFirst();
            for(Bingo game : bingoGames) {
                game.checkForMatch(nextBingoCallout);
                if(game.isGameWon()) {
                    winner = game;
                    return;
                }
            }
        }
    }

    public void printAllBoards() {

    }

    public void printAllBoardStates() {
        for(Bingo game : bingoGames) {
            game.printBoardState();
            System.out.println();
        }
    }

    public boolean isWinner() {
        return winner != null;
    }

    public Bingo getWinner() {
        return winner;
    }

    public boolean isBingoCalloutsEmpty() {
        return bingoCalloutSequence.isEmpty();
    }
}

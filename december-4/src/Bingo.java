import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bingo {
    
    private Integer gameNumber;
    private Integer winningCallout;
    private List<List<Integer>> bingoBoard = new ArrayList<>();
    private List<List<Boolean>> bingoBoardState = new ArrayList<>();
    final static int BOARD_SIZE = 5;

    public Bingo(List<String> bingoRows, Integer gameNum) {
        initBingoBoardState();
        initBingoBoard(bingoRows);
        gameNumber = gameNum;
    }

    private void initBingoBoardState() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            List<Boolean> currRow = new ArrayList<>();
            for(int j = 0; j < BOARD_SIZE; j++) {
                currRow.add(false);
            }
            bingoBoardState.add(currRow);
        }
    }

    private void initBingoBoard(List<String> rows) {
        rows = rows.stream().map(String::trim).collect(Collectors.toList());
        for(String row : rows) {
            List<String> rowVals = Arrays.asList(row.split(" ")).stream().filter(val -> !val.equals("")).collect(Collectors.toList());
            List<Integer> rowValsInteger = rowVals.stream().map(Integer::parseInt).collect(Collectors.toList());
            bingoBoard.add(rowValsInteger);
        }
    }

    public void checkForMatch(int callout) {
        for(int i = 0; i < bingoBoard.size(); i ++) {
            for(int j = 0; j < bingoBoard.get(i).size(); j++) {
                if(Objects.equals(bingoBoard.get(i).get(j), callout)) {
                    bingoBoardState.get(i).set(j, true);
                    if(isGameWon()) {
                        winningCallout = callout;
                    }
                }
            }
        }
    }

    public boolean isGameWonByRow() {
        for(List<Boolean> hitsInRow : bingoBoardState) {
            if(!hitsInRow.contains(false)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameWonByColumn() {
        for(int i = 0; i < bingoBoardState.size(); i ++) {
            final int currIndex = i;
            List<Boolean> hitsInColumn = bingoBoardState.stream().map(list -> list.get(currIndex)).collect(Collectors.toList());
            if(!hitsInColumn.contains(false)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameWon() {
        return isGameWonByColumn() || isGameWonByRow();
    }

    public Integer getGameNumber() {
        return gameNumber;
    }

    public Integer getWinningCallout() {
        return winningCallout;
    } 

    public Integer getSumOfUncalledNumbers() {
        int sum = 0;
        for(int i = 0; i < bingoBoard.size(); i++) {
            for(int j = 0; j < bingoBoard.size(); j++) {
                if(!bingoBoardState.get(i).get(j)) {
                    sum += bingoBoard.get(i).get(j);
                }
            }
        }
        return sum;
    }

    public void printBoard() {
        for(List<Integer> boardRow: bingoBoard) {
            for(Integer val : boardRow) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public void printBoardState() {
        for(List<Boolean> boardRow: bingoBoardState) {
            for(Boolean val : boardRow) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

import java.util.LinkedList;

public class Submarine {
    
    private int depth = 0;
    private int horizontalPosition = 0;
    private int aim = 0;

    public Submarine() {
    }

    public void executeCoursePlan(LinkedList<String> coursePlan) {
        while(coursePlan.size() > 0) {
            String[] currCommand = coursePlan.pollFirst().split(" ");
            String commandType = currCommand[0];
            Integer commandValue = Integer.parseInt(currCommand[1]);
            switch(commandType) {
                case "forward":
                    horizontalPosition += commandValue;
                    break;
                case "down":
                    depth += commandValue;
                    break;
                case "up":
                    depth -= commandValue;
                    break;
                default:
                    break;
            }
            // System.out.println("Submarine reached next checkpoint...");
            // System.out.println("Submarine position:" + horizontalPosition);
            // System.out.println("Submarine depth:" + depth);
        }
    }

    public void executeCoursePlanWithAim(LinkedList<String> coursePlan) {
        while(coursePlan.size() > 0) {
            String[] currCommand = coursePlan.pollFirst().split(" ");
            String commandType = currCommand[0];
            Integer commandValue = Integer.parseInt(currCommand[1]);
            switch(commandType) {
                case "forward":
                    horizontalPosition += commandValue;
                    depth += (aim * commandValue);
                    break;
                case "down":
                    aim += commandValue;
                    break;
                case "up":
                    aim -= commandValue;
                    break;
                default:
                    break;
            }
            // System.out.println("Submarine reached next checkpoint...");
            // System.out.println("Submarine position:" + horizontalPosition);
            // System.out.println("Submarine depth:" + depth);
        }
    }

    public int getDepth() {
        return depth;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }
}

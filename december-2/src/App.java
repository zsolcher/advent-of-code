import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        File input = new File("december-2/src/input.txt");
        LinkedList<String> submarineCourse = getQueueFromFile(input);
        System.out.println("Total number of commands: " + submarineCourse.size());

        System.out.println("Starting submarine journey!");
        Submarine submarine = new Submarine();
        submarine.executeCoursePlan(submarineCourse);

        System.out.println("Submarine final position...");
        System.out.println("Submarine position:" + submarine.getHorizontalPosition());
        System.out.println("Submarine depth:" + submarine.getDepth());
        System.out.println("AoC answer is:" + submarine.getDepth() * submarine.getHorizontalPosition());

        LinkedList<String> submarineCourseForAimSub = getQueueFromFile(input);
        Submarine submarineWithAim = new Submarine();
        System.out.println("Starting submarine journey with aim!");
        submarineWithAim.executeCoursePlanWithAim(submarineCourseForAimSub);
        System.out.println("Submarine final position...");
        System.out.println("Submarine position:" + submarineWithAim.getHorizontalPosition());
        System.out.println("Submarine depth:" + submarineWithAim.getDepth());
        System.out.println("AoC answer is:" + submarineWithAim.getDepth() * submarineWithAim.getHorizontalPosition());
    }

    public static LinkedList<String> getQueueFromFile(File file) {
        LinkedList<String> res = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currCommand;
            while ((currCommand = br.readLine()) != null) {
                res.addLast(currCommand);
            }
        }  catch (Exception e) {
            System.out.println("Exception occured: \n" + e.getMessage());
        }
        return res;
    }

}

//Total number of increases in measurements: 1477
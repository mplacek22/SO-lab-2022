import java.util.ArrayList;
import java.util.Random;

public class Generator_realtime {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Task> tasks = new ArrayList<>();



        for (int i = 0; i <100 ; i++) {

            tasks.add( new Task( 1000,random.nextInt(200),true,random.nextInt(200)+10));
        }


        for (int i = 0; i <1000 ; i++) {
            tasks.add(new Task(1000, random.nextInt(200)));
        }

        Algorithms_EDF.EDF_FCFS(tasks);

        Algorithms_FD_SCAN.FD_SCAN_FCFS(tasks);



    }
}

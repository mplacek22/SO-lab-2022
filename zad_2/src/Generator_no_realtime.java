import java.util.ArrayList;
import java.util.Random;

public class Generator_no_realtime {
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <1000; i++) {
            tasks.add(new Task(random.nextInt(200), random.nextInt(200)));
        }

        System.out.println("FCFS: "+Algorithms_no_realtime.FCFS((tasks), 0));
        System.out.println("SSTF: "+Algorithms_no_realtime.SSTF((tasks), 0));
        System.out.println("SCAN: "+Algorithms_no_realtime.SCAN(tasks,0));
        System.out.println("C-SCAN: "+Algorithms_no_realtime.C_SCAN(tasks,0));
    }

}

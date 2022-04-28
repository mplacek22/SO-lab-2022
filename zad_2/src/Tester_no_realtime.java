import java.util.ArrayList;

public class Tester_no_realtime {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(0, 98));
        tasks.add(new Task(0, 183));
        tasks.add(new Task(0, 37));
        tasks.add(new Task(0, 122));
        tasks.add(new Task(0, 14));
        tasks.add(new Task(0, 124));
        tasks.add(new Task(0, 65));
        tasks.add(new Task(0, 67));


        System.out.println("FCFS: "+Algorithms_no_realtime.FCFS((tasks), 53));
        System.out.println("SSTF: "+Algorithms_no_realtime.SSTF((tasks), 53));
        System.out.println("SCAN: "+Algorithms_no_realtime.SCAN(tasks,53));
        System.out.println("C-SCAN: "+Algorithms_no_realtime.C_SCAN(tasks,53));


    }
}

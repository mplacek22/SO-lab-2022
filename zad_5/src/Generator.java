import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public ArrayList<Process> generateProcesses(int n, int maxCycles, int maxOverload, int maxArrivalTime){
        ArrayList<Process> processes = new ArrayList<>(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            processes.add(new Process(random.nextInt(maxCycles)+1, random.nextInt(maxOverload)+1, random.nextInt(maxArrivalTime)+1));
        }
        return processes;
    }
}

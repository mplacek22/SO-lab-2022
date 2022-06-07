import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int nrProcesses = 5;
        ArrayList<Process> processes = new ArrayList<>(nrProcesses);

        for (int i = 0; i < nrProcesses; i++) {
            processes.add(new Process(i, 5*i+1, 1000));
        }

        int equal = Algorithms.equal(20, processes);
        System.out.println("Equal avg PF: " + equal+"\n\n");
        int proportional = Algorithms.proportional(processes);
        System.out.println("Proportional avg PF: " + proportional);
    }
}

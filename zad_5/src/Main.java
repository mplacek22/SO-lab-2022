import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int p = 80; //upper overload limit
        int r = 30; //lower overload limit
        int z  = 10; //number of enquired processors in alg1
        int n = 1000; //number of processes
        int nrProcessors = 50; //number of processors
        int maxCycles = 80;
        int maxOverload = 40;
        int maxArrivalTime = 300;

        Generator g = new Generator();

        ArrayList<Process> processes = g.generateProcesses(n, maxCycles, maxOverload, maxArrivalTime);

        Algorithms algorithms = new Algorithms(p, r, z, nrProcessors, processes);
        algorithms.getData(1);
        algorithms.getData(2);
        algorithms.getData(3);
    }
}

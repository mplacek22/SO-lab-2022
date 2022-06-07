import java.util.ArrayList;
import java.util.Random;

public class Algorithms {
    private int p; //upper overload limit
    private int r; //lower overload limit
    private int z; //number of enquired processors in alg1
    private int nrProcessors; //number of processors

    private int enquiriesCount;
    private int migrationsCount;

    private double deviation;
    private double avgOverload;

    ArrayList<Process> processList;

    public Algorithms(int p, int r, int z, int nrProcessors, ArrayList<Process> processList) {
        this.p = p;
        this.r = r;
        this.z = z;
        this.nrProcessors = nrProcessors;
        this.processList = processList;

        this.enquiriesCount = 0;
        this.migrationsCount = 0;
        this.avgOverload = 0;
    }

    public void cycle(ArrayList<Processor> processors){
        Processor tmpProcessor;
        for (int j = 0; j < processors.size(); j++) {
            tmpProcessor = processors.get(j);
            tmpProcessor.clockTack();
            tmpProcessor.updateRecord();
        }
    }

    public void execute (int alg){
        ArrayList<Processor> processors = new ArrayList<>();
        for (int i = 0; i < nrProcessors; i++) {
            processors.add(new Processor());
        }
        ArrayList<Process> processes = new ArrayList<>();
        for (int i = 0; i < processList.size(); i++) {
            processes.add(new Process(processList.get(i)));
        }

        int time = 1;
        Process p;
        int x;
        int enquired;
        Random random = new Random();

        while (processes.size() > 0 || !areProcessorsDone(processors)){
            if (processes.size() > 0){
                p = processes.get(0);

                if (p.getArrivalTime() <= time){
                    x = random.nextInt(nrProcessors);
                    switch (alg){
                        case 1:
                            enquired = enquiry1(processors, x);
                            break;
                        case 2:
                            enquired = enquiry2(processors, x);
                            break;
                        case 3:
                            enquired = enquiry3(processors, x);
                            break;
                        default:
                            enquired = enquiry1(processors, x);
                    }
                    processors.get(enquired).addProcess(p);
                    processes.remove(0);
                }
            }
            cycle(processors);
            time++;
        }
        countAvgOverload(processors);
        countDeviation(processors);
    }

    public int enquiry1 (ArrayList<Processor> processors, int x){
        int randNr;
        Random random = new Random();
        Processor tmpProcessor;

        for (int i = 0; i < z; i++) {
            randNr = random.nextInt(processors.size()-1);
            tmpProcessor = processors.get(randNr);
            enquiriesCount++;
            if (tmpProcessor.getCurrentOverload() < p){
                migrationsCount++;
                return randNr;
            }
        }

        return x;
    }

    public int enquiry2 (ArrayList<Processor> processors, int x){
        if (processors.get(x).getCurrentOverload() < p)
            return x;
        else {
            Random random = new Random();
            int y = random.nextInt(processors.size());
            Processor processor = processors.get(y);
            migrationsCount++;
            enquiriesCount++;
            while (processor.getCurrentOverload()>p){
                y = random.nextInt(processors.size());
                processor = processors.get(y);
                enquiriesCount++;
            }
            return y;
        }
    }

    public int enquiry3 (ArrayList<Processor> processors, int x){
        if (processors.get(x).getCurrentOverload() < p)
            return x;
        else {
            Random random = new Random();
            int y = random.nextInt(processors.size());
            Processor processor = processors.get(y);
            migrationsCount++;
            enquiriesCount++;
            int i = 0;
            while (processor.getCurrentOverload()>p){
                y = random.nextInt(processors.size());
                processor = processors.get(y);
                if (processor.getCurrentOverload() < r){
                    int a = random.nextInt(nrProcessors);
                    Processor proc2 = processors.get(a);
                    if (proc2.getCurrentOverload() > p){
                        migrationsCount++;
                        for (int j = 0; j < proc2.getProcesses().size()/2 ; j++) {
                            processor.addProcess(proc2.removeLastProcess());
                            migrationsCount++;
                        }
                    }

                }
                enquiriesCount++;
            }
            return y;
        }
    }

    public boolean areProcessorsDone(ArrayList<Processor> processors){
        for (Processor proc: processors) {
            if (proc.getProcesses().size() > 0)
                return false;
        }
        return true;
    }

    public void getData (int alg){
        this.execute(alg);
        System.out.println("\nAlgorithm "+ alg);
        System.out.println("Average overload: "+avgOverload+
                "\nStandard deviation: "+deviation+"\nEnquiries count: "+enquiriesCount+"\nMigrations count: "+migrationsCount);
        restart();
    }

    public void countAvgOverload(ArrayList<Processor> processors){
        for (int i = 0; i < nrProcessors; i++) {
            avgOverload += processors.get(i).avgOverload();
        }
        avgOverload = avgOverload/nrProcessors;
    }

    public void countDeviation(ArrayList<Processor> processors){
        double a = 0;
        for (int i = 0; i < processors.size(); i++) {
            a += Math.pow(processors.get(i).avgOverload()-avgOverload, 2);
        }
        a = Math.sqrt(a/processors.size());
        deviation = a;
    }

    public void restart(){
        avgOverload = 0;
        enquiriesCount = 0;
        migrationsCount = 0;
        deviation = 0;
    }








}

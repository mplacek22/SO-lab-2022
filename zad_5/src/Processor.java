import java.util.ArrayList;

public class Processor {
    private ArrayList<Process> processes;
    private int currentOverload;
    private ArrayList<Integer> overloadRecord;

    public Processor() {
        this.processes = new ArrayList<>();
        this.overloadRecord = new ArrayList<>();
        currentOverload = 0;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public int getCurrentOverload() {
        return currentOverload;
    }

    public ArrayList<Integer> getOverloadRecord() {
        return overloadRecord;
    }

    public void updateRecord(){
        overloadRecord.add(currentOverload);
    }

    public void addProcess(Process process){
        processes.add(process);
        currentOverload += process.getOverload();
    }

    public Process removeLastProcess(){
        Process p = null;
        if (processes.size() > 0){
            p = processes.remove(processes.size()-1);
            currentOverload -= p.getOverload();
        }
        return p;
    }

    public void clockTack(){
        if (processes.size() > 0){
            Process tmpProc = processes.get(0);
            tmpProc.doCycle();

            if (tmpProc.getNrCycles() == 0){
                currentOverload -= tmpProc.getOverload();
                processes.remove(0);
            }
        }
    }

    public double avgOverload(){
        double result = 0;
        for (int i = 0; i < overloadRecord.size(); i++) {
            result += (double) overloadRecord.get(i);
        }
        result = result/overloadRecord.size();
        return result;
    }



}

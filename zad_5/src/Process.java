public class Process {
    private int nrCycles; // cechy procesu; ilosc taktow zegara do zakonczenia tego procesu
    private int overload; // generowane obciążenie przez cały okres trwania procesu
    private int arrivalTime;

    public Process(int nrCycles, int overload, int arrivalTime) {
        this.nrCycles = nrCycles;
        this.overload = overload;
        this.arrivalTime = arrivalTime;
    }

    public Process (Process p){
        this.nrCycles = p.getNrCycles();
        this.overload = p.getOverload();
        this.arrivalTime = p.getArrivalTime();
    }

    public void doCycle(){
        this.nrCycles--;
    }

    public int getNrCycles() {
        return nrCycles;
    }

    public int getOverload() {
        return overload;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}

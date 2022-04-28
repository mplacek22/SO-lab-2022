import java.util.Comparator;

public class Proces {
    private int size;
    private int waiting_time;
    private int left_time;
    private int start_time;
    private int number;

    public Proces(int size, int start_time, int number) {
        this.size = size;
        this.waiting_time = 0;
        this.left_time = size;
        this.start_time = start_time;
        this.number = number;
    }

    public Proces(Proces proc) {
        this.size = proc.getSize();
        this.waiting_time = proc.getWaiting_time();
        this.left_time = proc.getLeft_time();
        this.start_time = proc.getStart_time();
        this.number = proc.getNumber();

    }

    public Proces(int size, int waiting_time, int left_time, int start_time, int number) {
        this.size = size;
        this.waiting_time = waiting_time;
        this.left_time = left_time;
        this.start_time = start_time;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    public int getLeft_time() {
        return left_time;
    }

    public void setLeft_time(int left_time) {
        this.left_time = left_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static class Comparators {

        public static Comparator<Proces> CmpLeft = new Comparator<Proces>() {
            @Override
            public int compare(Proces o1, Proces o2) {
                return o1.left_time - o2.left_time;}};

        public static Comparator<Proces> CmpStart = new Comparator<Proces>() {
            @Override
            public int compare(Proces o1, Proces o2) {
                return o1.start_time - o2.start_time;
            }};
    }

    public void procesInProgress() {
        left_time--;

    }
    public void procesInWaiting(){
        waiting_time++;

    }
}

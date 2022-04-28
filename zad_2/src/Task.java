import java.util.Comparator;

public class Task {
    private int arrival_time;
    private int cylinder;
    private boolean realtime;
    private int deadline;

    public Task(int arrival_time, int cylinder) {
        this.arrival_time = arrival_time;
        this.cylinder = cylinder;
        this.realtime = false;
        this.deadline = 0;
    }

    public Task(int arrival_time, int cylinder, boolean realtime, int deadline) {
        this.arrival_time = arrival_time;
        this.cylinder = cylinder;
        this.realtime = realtime;
        this.deadline = deadline;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }


     static class Comparators{
        public static Comparator<Task> Cmp_arrival_time = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getArrival_time() - o2.getArrival_time();
            }
        };

        public static Comparator<Task> cmp_cylinder_ascending = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getCylinder() - o2.getCylinder();
            }
        };
        public static Comparator<Task> cmp_cylinder_descending = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o2.getCylinder() - o1.getCylinder();
            }
        };
        public static Comparator<Task> cmp_deadline = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline()-o2.getDeadline();
            }
        };

    }
}

import java.util.ArrayList;
import java.util.Collections;

public class Procesy {
    public double FCFS(ArrayList<Proces> procesy){
        int time = 0;
        ArrayList<Proces> tmp = new ArrayList<>();
        for (Proces proces: procesy) {
            tmp.add(new Proces(proces));
        }

        Collections.sort(tmp, Proces.Comparators.CmpStart);

        for (int i = 0; i < tmp.size(); i++)
        {
            while(tmp.get(i).getStart_time()>time) {  time++;}

            for (int j = 0; j < tmp.get(i).getLeft_time(); ) {
                tmp.get(i).procesInProgress();

                for (int l = i + 1; l < procesy.size(); l++) {
                    if (tmp.get(l).getStart_time() <= time) {
                        tmp.get(l).procesInWaiting();
                    }
                }

                time++;
            }
        }

        double totalTime =0;

        for (int i = 0; i < tmp.size(); i++) {
            totalTime += tmp.get(i).getWaiting_time();
        }

        double averageTime = totalTime/ tmp.size();
        System.out.println("Average waiting time for FCFS: "+averageTime);
        return averageTime;
    }

    public double SJF (ArrayList<Proces> procesy){
        int time = 0;
        boolean available = true;
        int longestProces = 0;
        int switch_count = 0;
        ArrayList<Proces> tmp = new ArrayList<>();
        ArrayList<Proces> finished = new  ArrayList<>();

        for (Proces proces: procesy) {
            tmp.add(new Proces(proces));
        }

        while(available){
            ArrayList<Proces> queue = new ArrayList<Proces>();

            for (Proces proces : tmp){
                if (proces.getStart_time() <= time){
                    queue.add(proces);
                }
            }
            queue.sort(Proces.Comparators.CmpLeft);

            if(queue.size()==0)
                time++;
            else {
                queue.get(0).procesInProgress();

                for (int i = 1; i < queue.size(); i++) {
                    queue.get(i).procesInWaiting();
                }

                if(queue.get(0).getLeft_time()==0 && queue.size()==1) {
                    available = false;
                }}

            for (int i = 0; i < tmp.size() ; i++) {
                if(tmp.get(i).getLeft_time()==0) {
                    finished.add(new Proces(tmp.get(i)));
                    tmp.remove(i);
                    i--;
                }
            }
            time++;

        }

        double totalTime =0;

        for (Proces proces : finished) {
            if (proces.getWaiting_time()>longestProces)
                longestProces = proces.getWaiting_time();
            totalTime += proces.getWaiting_time();
        }

        double averageTime = totalTime/ finished.size();
        System.out.println("Average waiting time for SJF: "+averageTime);
        System.out.println("Longest waiting time for SJF: "+longestProces);
        if (longestProces>5*averageTime)
            System.out.println("Possible starvation");
        return averageTime;
    }

    public double RR (ArrayList<Proces> procesy, int time_slice){
        int time = 0;
        int current = 0;
        boolean available = true;
        int totalTime = 0;
        double averageTime = 0;

        ArrayList<Proces> finished = new ArrayList<>();
        ArrayList<Proces> queue = new ArrayList<>();
        ArrayList<Proces> notYetComing = new ArrayList<>();

        for (Proces proces: procesy) {
            notYetComing.add(new Proces(proces));
        }

        while (available){
            for (int i = 0; i < notYetComing.size(); i++) {
                if (notYetComing.get(i).getStart_time() <= time){
                    queue.add(new Proces(notYetComing.get(i)));
                    notYetComing.remove(i);
                    i--;
                }
            }
            Collections.sort(queue, Proces.Comparators.CmpLeft);
            if (current > queue.size())
                current = 0;
            for (int i = 0; i < time_slice; i++) {
                for (int j = 0; j < queue.size(); j++) {
                    if (current==j){
                        queue.get(j).procesInProgress();
                        if (queue.get(j).getLeft_time()<=0){
                            i=time_slice+2;
                            finished.add(new Proces(queue.get(j)));
                            queue.remove(j);
                        }
                    }
                    else{
                        queue.get(j).procesInWaiting();
                    }
                }
                time++;
            }
            current++;

            if(finished.size()==procesy.size()&&notYetComing.size()==0)
                available=false;
        }

        for (Proces proces : finished) {
            totalTime += proces.getWaiting_time();
        }
        averageTime = totalTime/finished.size();

        System.out.println("Average waiting time for RR: "+averageTime);
        return averageTime;
    }

    public void getData(ArrayList<Proces> procesy, int time_slice_for_RR){
        this.FCFS(procesy);
        this.SJF(procesy);
        this.RR(procesy, time_slice_for_RR);
    }
}

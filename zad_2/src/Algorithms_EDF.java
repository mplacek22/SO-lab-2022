import java.util.ArrayList;
import java.util.Comparator;

public class Algorithms_EDF {

    public static int EDF_FCFS(ArrayList<Task> tasks){

        ArrayList <Task> real = new ArrayList<Task>();
        ArrayList <Task> noreal = new ArrayList<Task>();

        for (int i = 0; i <tasks.size() ; i++) {
            if(tasks.get(i).isRealtime())
                real.add(tasks.get(i));
            else
                noreal.add(tasks.get(i));
        }

        ArrayList <Task> real_waiting = new ArrayList<>();
        ArrayList<Task> noreal_waiting = new ArrayList<>();
        ArrayList <Task> real_finished = new ArrayList<>();
        ArrayList <Task> noreal_finished = new ArrayList<>();

        int time= 0;
        int blocks = 0;
        int current_pos=0;
        int rejected_count = 0;

        tasks.sort(Task.Comparators.Cmp_arrival_time);

        while(real_finished.size()+noreal_finished.size()+rejected_count !=tasks.size())
        {
            for (int i = noreal_waiting.size()+noreal_finished.size() + real_waiting.size()+real_finished.size(); i < tasks.size() ; i++) {
                if((time+blocks>=tasks.get(i).getArrival_time())&&(!tasks.get(i).isRealtime()))
                    noreal_waiting.add(tasks.get(i));

                else if((time+blocks>=tasks.get(i).getArrival_time())&&(tasks.get(i).isRealtime()))
                    real_waiting.add(tasks.get(i));

            }



            if(real_waiting.size()>0)
            {
                while (real_waiting.size()>0) {
                    if(real_waiting.get(0).getDeadline()>=Math.abs(current_pos-real_waiting.get(0).getCylinder())){
                        blocks += Math.abs(real_waiting.get(0).getCylinder()-current_pos);
                        current_pos = real_waiting.get(0).getCylinder();
                        real_finished.add(real_waiting.remove(0));

                    }
                    else {
                        rejected_count++;
                        if (real_waiting.get(0).getCylinder() > current_pos)
                            current_pos += real_waiting.get(0).getDeadline();
                        else
                            current_pos = current_pos - real_waiting.get(0).getDeadline();
                        real_waiting.remove(0);
                    }
                }
            }


            else if(noreal_waiting.size()>0)
            {
                blocks = blocks + Math.abs(noreal_waiting.get(0).getCylinder()-current_pos);
                current_pos=noreal_waiting.get(0).getCylinder();
                noreal_finished.add(noreal_waiting.remove(0));
            }

            time++;

        }

        System.out.println("EDF FCFS: \nRejected count: " + rejected_count + "  blocks: " + blocks
                +"\nno_real: "+noreal_finished.size()+ "  real: "+real_finished.size());
        return blocks;
    }

    public static int EDF_SSTF(ArrayList<Task> tasks){
        ArrayList <Task> real = new ArrayList<Task>();
        ArrayList <Task> noreal = new ArrayList<Task>();

        for (int i = 0; i <tasks.size() ; i++) {
            if(tasks.get(i).isRealtime())
                real.add(tasks.get(i));
            else
                noreal.add(tasks.get(i));
        }

        ArrayList <Task> real_waiting = new ArrayList<>();
        ArrayList<Task> noreal_waiting = new ArrayList<>();
        ArrayList <Task> real_finished = new ArrayList<>();
        ArrayList <Task> noreal_finished = new ArrayList<>();

        int time= 0;
        int blocks = 0;
        int current_pos=0;
        int rejected_count = 0;

        tasks.sort(Task.Comparators.Cmp_arrival_time);

        while(real_finished.size()+noreal_finished.size()!=tasks.size()-1)
        {
            for (int i = noreal_waiting.size()+noreal_finished.size() + real_waiting.size()+real_finished.size(); i < tasks.size() ; i++) {
                if((time+blocks>=tasks.get(i).getArrival_time())&&(!tasks.get(i).isRealtime()))
                    noreal_waiting.add(tasks.get(i));

                else if((time+blocks>=tasks.get(i).getArrival_time())&&(tasks.get(i).isRealtime()))
                    real_waiting.add(tasks.get(i));

            }


            if(real_waiting.size()>0)
            {
                for (int i = 0; i <real_waiting.size() ; i++) {
                    if(real_waiting.get(i).getDeadline()<=time+blocks-real_waiting.get(i).getArrival_time()){
                        blocks += Math.abs(real_waiting.get(i).getCylinder()-current_pos);
                        current_pos = real_waiting.get(i).getCylinder();
                        real_finished.add(real_waiting.remove(i));
                    }
                    else {
                        rejected_count++;
                        real_waiting.remove(i);
                    }
                }
            }


            else if(noreal_waiting.size()>0)
            {
                final int finalPosition = current_pos;

                noreal_waiting.sort(Comparator.comparingInt(o -> Math.abs(finalPosition - o.getCylinder())));

                blocks = blocks + Math.abs(noreal_waiting.get(0).getCylinder()-current_pos);
                current_pos=noreal_waiting.get(0).getCylinder();
                noreal_finished.add(noreal_waiting.remove(0));

            }

            time++;

        }

        System.out.println("EDF SSTF: \nRejected count: " + rejected_count + "  blocks: " + blocks);
        return blocks;

    }

}

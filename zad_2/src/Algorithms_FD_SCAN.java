import java.util.ArrayList;

public class Algorithms_FD_SCAN {
    public static int FD_SCAN_FCFS(ArrayList<Task> tasks){

        ArrayList<Task> copy  = tasks;
        copy.sort(Task.Comparators.Cmp_arrival_time);

        ArrayList<Task> waiting_real = new ArrayList<>();
        ArrayList<Task> waiting = new ArrayList<>();
        ArrayList<Task> finished = new ArrayList<>();
        ArrayList<Task> rejected = new ArrayList<>();

        int time= 0;
        int blocks = 0;
        int current_pos=0;

        while(finished.size()+rejected.size() != copy.size()) {

            for (int i = waiting.size()+finished.size(); i < tasks.size() ; i++) {
                if(time>=tasks.get(i).getArrival_time()) {
                    if (tasks.get(i).isRealtime())
                        waiting_real.add(tasks.get(i));
                    else
                        waiting.add(tasks.get(i));
                }
            }

            if(!waiting_real.isEmpty()){
//                for (int i= 0; i< waiting_real.size(); i++) {
//                    if (waiting_real.get(i).getDeadline()<= Math.abs(waiting_real.get(0).getCylinder()-current_pos)){
//                        rejected.add(waiting.remove(i));
//                    }
//                }
                for (int i= 0; i< waiting_real.size(); i++) {
                    waiting_real.get(i).setDeadline(waiting_real.get(i).getDeadline()-1);
                    if (waiting_real.get(i).getCylinder() == current_pos){
                        finished.add(waiting_real.get(i));
                        waiting_real.remove(waiting_real.get(i));
                        i--;
                    }
                }

                if (!waiting_real.isEmpty()){
                    if (waiting_real.get(0).getCylinder() > current_pos)
                        current_pos++;
                    else
                        current_pos--;

                    blocks++;
                }
            }

            else if (!waiting.isEmpty()){
                if (current_pos == waiting.get(0).getCylinder())
                    finished.add(waiting.remove(0));

                if (!waiting.isEmpty()){
                    waiting.sort(Task.Comparators.Cmp_arrival_time);
                    if (waiting.get(0).getCylinder() > current_pos)
                        current_pos++;
                    else
                        current_pos--;

                    blocks++;
                }
            }


            time++;
        }

        System.out.println("FD SCAN FCFS: \n" + "blocks: " + blocks);


        return blocks;
    }

}

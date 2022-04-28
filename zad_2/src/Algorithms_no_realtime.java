import java.util.ArrayList;
import java.util.Comparator;

public class Algorithms_no_realtime {

    private static final int SIZE = 200;

    public static int FCFS(ArrayList<Task> tasks, int current_pos){
        ArrayList<Task> tasks_copy = tasks;
        tasks_copy.sort(Task.Comparators.Cmp_arrival_time);

        int blocks = Math.abs(current_pos-tasks_copy.get(0).getCylinder());


        for (int i = 1; i <tasks_copy.size() ; i++) {
            blocks+= Math.abs(tasks_copy.get(i).getCylinder()-tasks_copy.get(i-1).getCylinder());
        }

        return blocks;
    }

    public static int SSTF(ArrayList<Task> tasks, int current_pos){
        ArrayList<Task> tasks_copy = tasks;
            int blocks = 0;
            int time =0;
            tasks_copy.sort(Task.Comparators.Cmp_arrival_time);
            int current = current_pos;

            ArrayList <Task> queue = new ArrayList<Task>();
            ArrayList <Task> finished = new ArrayList<Task>();



            while (finished.size()!= tasks_copy.size())
            {
                for (int i = queue.size()+finished.size(); i < tasks_copy.size() ; i++) {
                    if(time+blocks>=tasks_copy.get(i).getArrival_time())
                        queue.add(tasks_copy.get(i));

                }

                int finalPosition = current;
                queue.sort(Comparator.comparingInt(o -> Math.abs(finalPosition - o.getCylinder())));


                if(queue.size()>0)
                {
                    finished.add(queue.get(0));
                    queue.remove(0);
                    blocks = blocks + Math.abs(current-finished.get(finished.size()-1).getCylinder());

                    current = finished.get(finished.size()-1).getCylinder();
                }
                time++;
            }
            return blocks;

    }

    public static int findClosestTask(ArrayList<Task> tasks, int position){
        int index = 0;
        int min = Math.abs(position - tasks.get(0).getCylinder());
        int dif = 0;
        for (int i = 0; i < tasks.size(); i++) {
            dif = Math.abs(position - tasks.get(i).getCylinder());
            if (dif<min){
                min = dif;
                index = i;
            }
        }
        return index;
    }


    public static int SCAN(ArrayList <Task> tasks, int current_pos){
        ArrayList<Task> tasks_copy = tasks;
        tasks_copy.sort(Task.Comparators.Cmp_arrival_time);

        int blocks = 0;
        int time =0;
        int current = current_pos;
        int direction;

        ArrayList <Task> queue = new ArrayList<Task>();
        ArrayList <Task> finished = new ArrayList<Task>();

        int closest_pos = findClosestTask(tasks_copy, current);
        if (tasks_copy.get(closest_pos).getCylinder()-current >= 0)
            direction = 1;
        else
            direction = -1;

        while (finished.size()!= tasks_copy.size()-1)
        {
            for (int i = queue.size()+finished.size(); i < tasks_copy.size() ; i++) {
                if(time+blocks>=tasks_copy.get(i).getArrival_time())
                    queue.add(tasks_copy.get(i));
            }


            if(direction==1)
                queue.sort(Task.Comparators.cmp_cylinder_ascending);
            else
                queue.sort(Task.Comparators.cmp_cylinder_descending);

            int indicator = 0;
            for (int i = 0; i < queue.size() ; i++) {
                if((current<queue.get(i).getCylinder() && direction==1)||(current>queue.get(i).getCylinder() && direction==-1))
                {   finished.add(queue.get(i));
                    blocks=blocks + Math.abs(current-queue.get(i).getCylinder());
                    current = queue.get(i).getCylinder();
                    queue.remove(i);
                    i= queue.size()+1;
                    indicator =1;

                }}


            if(indicator!=1)
            {
                if(direction==1&&queue.size()!=0) {
                blocks = blocks + Math.abs(current - SIZE);
                current=SIZE;
                }
                if(direction==-1) {
                    blocks = blocks + Math.abs(current);
                    current=0;

                }
                direction=direction* (-1);

            }

            time++;
        }

        blocks+= Math.abs(current - queue.get(0).getCylinder());

        return blocks;
    }


    public static int C_SCAN(ArrayList<Task> tasks, int current_pos){
        ArrayList<Task> tasks_copy = tasks;
        tasks_copy.sort(Task.Comparators.Cmp_arrival_time);

        int blocks = 0;
        int time =0;
        int current = current_pos;


        ArrayList <Task> queue = new ArrayList<Task>();
        ArrayList <Task> finished = new ArrayList<Task>();

        while (finished.size()!= tasks_copy.size())
        {
            for (int i = queue.size()+finished.size(); i < tasks_copy.size() ; i++) {
                if(time+blocks>=tasks_copy.get(i).getArrival_time())
                    queue.add(tasks_copy.get(i));
            }

            queue.sort(Task.Comparators.cmp_cylinder_ascending);

            int start_index = 0;

            for (int i = 0; i < queue.size()-1 && start_index <=0; i++) {
                if(current<queue.get(i).getCylinder())
                    start_index = i;
            }

            while (start_index!=queue.size() && queue.size()!=0){
                    finished.add(queue.get(start_index));
                    blocks += Math.abs(current-queue.get(start_index).getCylinder());
                    current = queue.get(start_index).getCylinder();
                    queue.remove(start_index);
            }
            if (!queue.isEmpty() || finished.size()!=tasks_copy.size()){
                blocks+= (SIZE-current)+SIZE-1;
                current = 0;
            }

            time++;
        }

        return blocks;
    }




}

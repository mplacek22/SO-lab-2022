import java.util.ArrayList;
import java.util.Collections;

public class Algorithms {

    public static int equal(int totalFrames, ArrayList<Process> processes){
        int globalPF = 0;
        int framesPerProcess = totalFrames/ processes.size();
        boolean thrashing = false;
        for (Process proc : processes) {
            int localPF = LRU(proc.getCalls(), framesPerProcess);
            int x = localPF/proc.getSize();
            if (x>framesPerProcess)
                thrashing = true;
            localData(proc.getId(), proc.getSize(), proc.getCalls().size(), framesPerProcess, localPF, thrashing);
            globalPF += localPF;
        }
        return globalPF/ processes.size();
    }

    public static int proportional(ArrayList<Process> processes){
        int globalPF = 0;
        boolean thrashing = false;
        int framesPerProcess;
        for (Process proc : processes) {
            framesPerProcess = proc.getSize()/processes.size();
            if (framesPerProcess == 0)
                framesPerProcess = 1;
            int localPF = LRU(proc.getCalls(), framesPerProcess);
            double x = proc.getSize()/framesPerProcess;
            if (x>2)
                thrashing = true;
            localData(proc.getId(), proc.getSize(), proc.getCalls().size(), framesPerProcess, localPF, thrashing);
            globalPF += localPF;
        }
        return globalPF/ processes.size();
    }



    private static void localData(int id, int pagesCount, int callsCount, int framesCount,int errorCount, boolean thrashing) {
        System.out.printf("Proces ID =%3d PAGES =%4d CALLS =%5d FRAMES =%3d ERRORS =%5d POSSIBLE THARSHING =%6s\n", id, pagesCount, callsCount, framesCount, errorCount, thrashing);
    }

    private static int LRU (ArrayList<Page> calls, int framesPerProcess){
        int fail_count = 0;
        int time = 0;

        ArrayList<Page> pages_call_arr = (ArrayList<Page>) calls.clone();
        ArrayList<Page> frames_arr = new ArrayList<>();

        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size() < framesPerProcess) {
                for (int j = 0; j < frames_arr.size(); j++) {
                    if (frames_arr.get(j).getId() == current_page.getId()){
                        is_not_present = false;
                        frames_arr.get(j).setLastUse(time);
                    }
                }
                if (is_not_present) {
                    frames_arr.add(current_page);
                    time++;
                    fail_count++;
                }
            }

            else {
                for (Page page: frames_arr) {
                    if (page.getId()==current_page.getId()){
                        page.setLastUse(time);
                        time++;
                        is_not_present = false;
                    }
                }
                if (is_not_present){
                    Collections.sort(frames_arr, Page.idComparator);
                    frames_arr.remove(0);
                    frames_arr.add(current_page);
                    time++;
                    fail_count++;
                }
            }
        }

        frames_arr.clear();

        return fail_count;
    }


}

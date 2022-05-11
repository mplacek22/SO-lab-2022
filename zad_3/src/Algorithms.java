import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Algorithms {
    private final int FRAMES_NR;
    private int PAGES_NR;

    public ArrayList<Page> pages_id_arr = new ArrayList<>();
    public ArrayList<Page> frames_arr = new ArrayList<>();

    public int fail_count;


    public Algorithms(int FRAMES_NR, ArrayList<Page> pages_id_arr) {
        this.FRAMES_NR = FRAMES_NR;
        this.pages_id_arr = pages_id_arr;
    }

    public int FIFO(){
        fail_count = 0;

        ArrayList<Page> pages_call_arr = (ArrayList<Page>) pages_id_arr.clone();
        
        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size()<FRAMES_NR){
                for (Page page: frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present = false;
                }
                if (is_not_present){
                    frames_arr.add(current_page);
                    fail_count++;
                }
            }

            else {
                for (Page page: frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present = false;
                }
                if (is_not_present){
                    frames_arr.remove(0);
                    frames_arr.add(current_page);
                    fail_count++;
                }
            }
        }

        frames_arr.clear();

        return fail_count;
    }

    public int OPT(){
        fail_count = 0;

        ArrayList<Page> pages_call_arr = (ArrayList<Page>) pages_id_arr.clone();

        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size() < FRAMES_NR) {
                for (Page page : frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present = false;
                }
                if (is_not_present) {
                    frames_arr.add(current_page);
                    fail_count++;
                }
            }

            else {
                for (Page page: frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present=false;
                }
                if (is_not_present){
                    Page latest_page = latest_call(current_page, pages_call_arr, frames_arr);

                    if (latest_page != null)
                        frames_arr.remove(latest_page);
                    else
                        frames_arr.remove(0);
                    frames_arr.add(current_page);
                    fail_count++;
                }
            }
        }

        frames_arr.clear();
        return fail_count;
    }

    public int LRU(){
        fail_count = 0;
        int time = 0;

        ArrayList<Page> pages_call_arr = (ArrayList<Page>) pages_id_arr.clone();

        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size() < FRAMES_NR) {
                for (int j = 0; j < frames_arr.size(); j++) {
                    if (frames_arr.get(j).getNr() == current_page.getNr()){
                        is_not_present = false;
                        frames_arr.get(j).setId(time);
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
                    if (page.getNr()==current_page.getNr()){
                        page.setId(time);
                        time++;
                        is_not_present = false;
                    }
                }
                if (is_not_present){
                    Collections.sort(frames_arr, Page.id_comparator);
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

    public int ALRU(){
        fail_count = 0;
        ArrayList<Page> fifo = new ArrayList<>();


        ArrayList<Page> pages_call_arr = (ArrayList<Page>) pages_id_arr.clone();

        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size() < FRAMES_NR) {

                for (Page page:
                     frames_arr) {
                    if (page.getNr() == current_page.getNr()){
                        page.setParityBit(true);
                        is_not_present = false;
                    }
                }
                if (is_not_present) {
                    frames_arr.add(current_page);
                    fifo.add(current_page);
                    fail_count++;
                }
            }
            else {
                for (Page page : frames_arr){
                    if (page.getNr() == current_page.getNr()){
                        page.setParityBit(true);
                        for (Page p: fifo) {
                           if (p.getNr() == page.getNr())
                               p.setParityBit(true);
                        }
                        is_not_present = false;
                    }
                }
                if (is_not_present){
                    //Collections.sort(frames_arr, Page.id_comparator);
                    boolean found = false;
                    parity:
                    do{
                        for (int j = 0; j < fifo.size(); j++) {
                            Page p = fifo.get(j);
                            if (!p.isParityBit()){
                                fifo.remove(p);
                                fifo.add(current_page);
                                frames_arr.set(frames_arr.indexOf(p), current_page);
                                break parity;
                            }
                            else{
                                p.setParityBit(false);
                                fifo.remove(p);
                                fifo.add(p);
                                j--;
                            }
                        }
                    } while (!found);
                    fail_count++;
                }
            }
        }
        frames_arr.clear();
        return fail_count;
    }

    public int RANDOM(){
        fail_count = 0;

        ArrayList<Page> pages_call_arr = (ArrayList<Page>) pages_id_arr.clone();

        Page current_page;

        for (int i = 0; i < pages_call_arr.size(); i++) {
            current_page = pages_call_arr.get(i);
            boolean is_not_present = true;

            if (frames_arr.size()<FRAMES_NR){
                for (Page page: frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present = false;
                }
                if (is_not_present){
                    frames_arr.add(current_page);
                    fail_count++;
                }
            }

            else {
                for (Page page: frames_arr) {
                    if (page.getNr() == current_page.getNr())
                        is_not_present = false;
                }
                if (is_not_present){
                    int random = (int) (Math.random()*FRAMES_NR);
                    frames_arr.set(random, current_page);
                    fail_count++;
                }
            }
        }

        frames_arr.clear();

        return fail_count;
    }

    public Page latest_call(Page page, ArrayList<Page> a, ArrayList<Page> f){
        ArrayList<Page> temp_arr = (ArrayList<Page>) f.clone();
        Page latest = null;

        for (int i = a.indexOf(page); i < a.size(); ++i) {
            for (int j = 0; j < temp_arr.size(); j++) {
                if (temp_arr.get(j).getNr() == a.get(i).getNr())
                    temp_arr.remove(j);
                if (temp_arr.size() == 1)
                    for (int k = 0; k < f.size(); k++) {
                        if (temp_arr.get(0).getNr() == f.get(k).getNr())
                            latest= f.get(k);
                    }
            }
        }
        
        return latest;
    }


}

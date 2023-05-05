package so_laby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class sjf {
    private ArrayList<proces> all_procesess;
    private ArrayList<proces> processes_queue;
    private ArrayList<proces> completed_processes;
    private int number_of_processes;

    private proces current_process;

    private int number_of_sorts;
    private int current_time; 

    public sjf(ArrayList<proces> procesy){
        all_procesess = procesy;
        current_time = 0;
        processes_queue = new ArrayList<>();
        completed_processes = new ArrayList<>();
        number_of_processes = all_procesess.size();
        current_process = null; 
        number_of_sorts = 0;
    }


    public double[] start_simulation(){

        add_processes_queue();
        if(processes_queue.size() != 0){
            if(processes_queue.size() == 1){
                current_process = processes_queue.get(0);
                processes_queue.remove(0);
            }
            else{
                Collections.sort(processes_queue,process_comparator);
                number_of_sorts += 1;

                current_process = processes_queue.get(0);
                processes_queue.remove(0);

            }
        }

        while(completed_processes.size() != number_of_processes){
            current_time += 1 ;
            update_processes_queue();
            add_processes_queue();
            update_process();


        }


        double sum_of_waiting = 0;
        int longest_waiting_time = 0;
        for(proces task:completed_processes){
            sum_of_waiting += task.getCzas_oczekiwania();
            if(task.getCzas_oczekiwania() > longest_waiting_time){
                longest_waiting_time = task.getCzas_oczekiwania();
            }
        }
        double averange_waiting_time = sum_of_waiting/number_of_processes;


        double[] report = new double[]{averange_waiting_time,longest_waiting_time,number_of_sorts};
        return report;
    }

    public void update_process(){ 
        if(current_process != null){
            current_process.setPostep(1);
            if(current_process.getPostep() == current_process.getCzas_trwania()){
                completed_processes.add(current_process);
                if(processes_queue.size() != 0){
                    if (processes_queue.size() == 1){
                        current_process = processes_queue.get(0);
                        processes_queue.remove(0);
                    }
                    else{
                        Collections.sort(processes_queue,process_comparator);
                        number_of_sorts += 1;
                        current_process = processes_queue.get(0);
                        processes_queue.remove(0);  
                    }
                }
                else {
                    current_process = null;
                }
            }

        } else if(processes_queue.size() != 0){
            if (processes_queue.size() == 1){
                current_process = processes_queue.get(0);
                processes_queue.remove(0);
            }
            else{
                Collections.sort(processes_queue,process_comparator);
                number_of_sorts += 1;
                current_process = processes_queue.get(0);
                processes_queue.remove(0);  
            }
        }
    } 

    public void update_processes_queue(){
        for(proces task:processes_queue){
            task.jeden_do_oczekiwania();
        }
    } 

    public void add_processes_queue(){
        for(int y = all_procesess.size()-1; y>=0;y--){
            proces tmp_process = all_procesess.get(y);
            if(tmp_process.getCzas_pojwania() == current_time){
                processes_queue.add(tmp_process); 
                all_procesess.remove(tmp_process);
            }
        }
    }
    public static Comparator<proces> process_comparator = new Comparator<proces>() {
 
        public int compare(proces s1, proces s2) {
 
            int proces1 = s1.getCzas_trwania();
            int proces2 = s2.getCzas_trwania();

            return proces1 - proces2;
        }
    };  
     
}

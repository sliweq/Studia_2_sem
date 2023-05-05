package so_laby;

import java.util.ArrayList;

public class round {
     private ArrayList<proces> all_procesess;
     private ArrayList<proces> processes_queue;
     private ArrayList<proces> completed_processes;
     private int number_of_processes;

     private proces current_process;

     private int current_time; 
     private int process_tik;
     private int current_tik_preogress;

     private int number_of_switches;

    public round(ArrayList<proces> processes, int process_tik1){
        if(process_tik1 <= 0 ){
            process_tik1 = 2;
            System.err.println("Tik jest mnejszy niż 1 tak sie nie robi");
        }

        all_procesess = processes;
        current_time = 0;
        number_of_switches = 0; 
        processes_queue = new ArrayList<>();
        completed_processes = new ArrayList<>();
        number_of_processes = all_procesess.size();
        current_process = null;
        process_tik = process_tik1;
        processes_queue = new ArrayList<>();
        completed_processes = new ArrayList<>();
        current_tik_preogress = 0;
    }  


     public double[] start_simulation(){

        //System.out.println("Test Okragly Robin");

        add_processes_queue();
        if(processes_queue.size() != 0){
            current_process = processes_queue.get(0);
            processes_queue.remove(0);
        }

        while(completed_processes.size() != number_of_processes){
            current_time +=1 ;
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

        double[] report = new double[]{averange_waiting_time,longest_waiting_time,number_of_switches};
        return report;

    }

     public void update_processes_queue(){ 
        for(proces task:processes_queue){
            if(task.getPostep() == 0){ 
                task.jeden_do_oczekiwania();
                
            }
        }
    }

     public void update_process(){ 
        if(current_process != null){
            current_process.setPostep(1);
            current_tik_preogress += 1;
            if(current_process.getPostep() == current_process.getCzas_trwania()){

                completed_processes.add(current_process);
                if(processes_queue.size() != 0){
                    current_process = processes_queue.get(0);
                    processes_queue.remove(0);
                    number_of_switches +=1;
                }
                else {
                    current_process = null;
                }
                current_tik_preogress = 0;
            }

            else if(current_tik_preogress == process_tik){
                processes_queue.add(current_process);
                current_process = processes_queue.get(0);
                processes_queue.remove(0);
                number_of_switches +=1;
                current_tik_preogress = 0;
            }
        } else if(processes_queue.size() != 0){
            current_process = processes_queue.get(0);
            processes_queue.remove(0);
            current_tik_preogress = 0;
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
    
}

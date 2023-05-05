package so_laby;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class test {
    private ArrayList<proces> all_processes_rr;
    private ArrayList<proces> all_processes_fifo;
    private ArrayList<proces> all_processes_sjf;

    private double[] fcfs_report;
    private double[] rr_report;
    private double[] sjf_report;
    private final int number_of_processes = 10000;
    
    public test(){
        all_processes_rr = new ArrayList<>();
        // all_processes_fifo = new ArrayList<>();
        // all_processes_sjf = new ArrayList<>();

        for(int i = 0; i < number_of_processes; i++ ){
            //nowy_proces();
            proces tak = nowy_proces();
            all_processes_rr.add(tak);

        }
    }

    public proces nowy_proces(){
        Random rand = new Random();
        double tmp = Math.random();
        int tmp_execute_time = 0;
        int tmp_spawn_time = 0;


        if(tmp<= 0.99 && tmp>=0.25 ){ //tmp<= 0.99 && tmp>=0.25
            tmp_execute_time = rand.nextInt(5,15);
            

        } else if(tmp<0.25){ //tmp<0.25
            tmp_execute_time = rand.nextInt(1,5);
        }
        else {
            tmp_execute_time = rand.nextInt(15,25);
        }   
        tmp = Math.random();

        if(tmp<0.05){
            tmp_spawn_time=rand.nextInt(0,(int)Math.ceil(number_of_processes/10.0)+1);
        }
        else {
            tmp_spawn_time= rand.nextInt((int)Math.ceil(number_of_processes/10.0),10*number_of_processes);
        }

        // all_processes_rr.add(new proces(tmp_execute_time, tmp_spawn_time));
        // all_processes_fifo.add(new proces(tmp_execute_time, tmp_spawn_time));
        // all_processes_sjf.add(new proces(tmp_execute_time, tmp_spawn_time));
        return new proces(tmp_execute_time, tmp_spawn_time);
    }

    public void clonowanie(){
        all_processes_fifo = new ArrayList<>();
        all_processes_sjf = new ArrayList<>();
        for(proces task: all_processes_rr){
            all_processes_fifo.add(task.clone());
            all_processes_sjf.add(task.clone());
        }

    }
    public double[][] start_test(){
        clonowanie();
        round rr = new round(all_processes_rr,10);
        rr_report =  rr.start_simulation();
        fcfs fifo_test = new fcfs(all_processes_fifo);
        fcfs_report = fifo_test.start_simulation();
        sjf sjf_test = new sjf(all_processes_sjf);
        sjf_report = sjf_test.start_simulation();

        double[][] report = new double[][]{fcfs_report,sjf_report,rr_report};
        return report;
    }
}

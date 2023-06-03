package so_laby_part5;

import java.util.ArrayList;

public class Cpu {
    private ArrayList<Process> arrayOfProcesses;
    private double cpuLoad;

    public Cpu(){
        cpuLoad = 0;
        arrayOfProcesses = new ArrayList<>();
    }   

    public void addProcess(Process process){
        arrayOfProcesses.add(process);
        cpuLoad += process.getLoad();
    }
    public void removeProcesses(){
        //usuwanie procesów 
        for(int x = arrayOfProcesses.size()-1; x >= 0 ;){
            if(arrayOfProcesses.get(x).isDone()){
                cpuLoad -= arrayOfProcesses.remove(x).getLoad();
            }
        }
    }

    public double getCurrentLoad(){
        return cpuLoad;
    }
}

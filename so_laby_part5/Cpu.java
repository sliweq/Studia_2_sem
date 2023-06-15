package so_laby_part5;

import java.util.ArrayList;

public class Cpu {
    private ArrayList<Process> arrayOfProcesses;
    private int number;
    private double cpuLoad;

    public void removeProcess(Process process){
        boolean tmp = arrayOfProcesses.remove(process);
        if(tmp){
            cpuLoad-= process.getLoad();
        }
    }

    public Cpu(int number){
        this.number = number;
        cpuLoad = 0;
        arrayOfProcesses = new ArrayList<>();
    }   

    public void addProcess(Process process){
        arrayOfProcesses.add(process);
        cpuLoad += process.getLoad();
    }

    public void updateProcesses(){
        for(int x = arrayOfProcesses.size()-1; x >= 0 ;x--){
            if(arrayOfProcesses.get(x).isDone()){
                cpuLoad -= arrayOfProcesses.remove(x).getLoad();
            }
            else{
                arrayOfProcesses.get(x).increaseProgressOfProcess();
            }
        }
    }

    public ArrayList<Process> getProcesses(){
        return arrayOfProcesses;
    }
 
    public double getCurrentLoad(){
        return cpuLoad;
    }

    public int getCpuNumber(){
        return number; 
    }
}

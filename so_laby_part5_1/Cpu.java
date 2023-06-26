package so_laby_part5_1;

import java.util.ArrayList;

public class Cpu {
    private ArrayList<Process> waitingProcesses;
    private ArrayList<Process> workingProcesses;
    private ArrayList<Process> doneProcesses;
    private int number;
    private int cpuLoad;
    private ArrayList<Integer> historyOfCpu;

    public Cpu(int number){
        waitingProcesses = new ArrayList<>();
        workingProcesses = new ArrayList<>();
        doneProcesses = new ArrayList<>();
        this.number = number;
        cpuLoad = 0;
        historyOfCpu = new ArrayList<>();
    }   

    public void startNewProcess(){
        Process proces = waitingProcesses.remove(0);
        cpuLoad += proces.getLoad();

        workingProcesses.add(proces);

    }

    public void addNewProcess(Process process, int i){
        if(i == 0){
            waitingProcesses.add(process);
        }
        else{
            if(process.getLoad() == -1){
                Process tmp = waitingProcesses.remove(0);
                workingProcesses.add(tmp);
                
                cpuLoad += tmp.getLoad();
            }
            else{
                workingProcesses.add(process);
                cpuLoad += process.getLoad();
            }
        }
    }

    public void resetCpu(){
        historyOfCpu.clear();
        while(doneProcesses.size() != 0 ){
            Process process = doneProcesses.remove(0);
            if(process.getProcessesOrigin()){
                process.resertProgress();
                waitingProcesses.add(process);
            }
        }
    }

    public void updateCpu(){
        for(int x = workingProcesses.size()-1; x >= 0; x--){
            workingProcesses.get(x).increaseProgressOfProcess();
            if(workingProcesses.get(x).isDone()){
                Process proces = workingProcesses.remove(x);
                cpuLoad -= proces.getLoad();
                doneProcesses.add(proces);
            }
        }
    }

    public void saveCpuLoad(){
        historyOfCpu.add(cpuLoad);
    }

    public boolean isCpuWorks(){
        return !(workingProcesses.isEmpty() && waitingProcesses.isEmpty());
    }

    public int getCpuNumber(){
        return number;
    }
    
    public int getCurrentLoad(){
        return cpuLoad;
    }

    public Process giveBackProcess(){
        Process process =  waitingProcesses.remove(0);
        Process tmpProcess = new Process(process.getlengthOfProcess(), process.getlengthOfProcess(), false);
        doneProcesses.add(process);
        return tmpProcess; 
    }

    public boolean isWaitingQueueEmpty(){
        return (waitingProcesses.size() == 0);
    }

    public Process getMostDemandingProcess(){
        if(workingProcesses.size() == 0){
            System.out.println("chuj");
            System.out.println(cpuLoad);
        }
        int x = 0;
        for(int i = 1; i < workingProcesses.size(); i++){
            if(workingProcesses.get(x).getLoad() < workingProcesses.get(i).getLoad()){
                x = i;
            }
        }
        Process process =  workingProcesses.remove(x);
        doneProcesses.add(process);
        cpuLoad -= process.getLoad();
        Process tmpProcess = new Process(process.getlengthOfProcess(), process.getLoad(), false, process.getExecutionStatus());

        return tmpProcess; 
    }

    public void printCpuStats(){
        int avg = 0;
        for(int d: historyOfCpu){
            avg += d;
        }
        double avgdouble = (double)((int)((avg/(historyOfCpu.size()*1.0))*100))/100.0 ;
        System.out.print("Średnie obciazenie cpu " + number + ": " + avgdouble);
        
        double x = 0.0;
        for(int d: historyOfCpu){
            x += Math.pow((d-avgdouble),2);
        }
        avgdouble = (double)((int)(Math.sqrt(x/historyOfCpu.size())*100))/100.0 ;
        System.out.println(" " + avgdouble );
    }
    
    public void tmpMethod(){
        System.out.print(waitingProcesses.size() + " ");
    }

}

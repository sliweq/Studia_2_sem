package so_laby_part5;

public class Process {

    private int progressOfProcess;
    private int lengthOfProcess;
    private int cpuNumber;
    private double load;
    
    public Process(int length, double load, int cpuNumber){
        lengthOfProcess = length;
        progressOfProcess = 0;
        this.load = load;
        this.cpuNumber = cpuNumber;
    }

    public int getExecutionStatus(){
        return progressOfProcess;
    }

    public int getlengthOfProcess(){
        return lengthOfProcess;
    }

    public int getCpuNumber(){
        return cpuNumber;
    }

    public void setCpuNumber(int cpuNumber){
        this.cpuNumber = cpuNumber;
    }

    public void increaseProgressOfProcess(){
        progressOfProcess += 1;
    }

    public boolean isDone(){
        return progressOfProcess == lengthOfProcess;
    }
    public double getLoad(){
        return load;
    }
}

package so_laby_part5;

public class Process {

    private int progressOfProcess;
    private int lengthOfProcess;
    private double load;
    
    public Process(int length, double load){
        lengthOfProcess = length;
        progressOfProcess = 0;
        this.load = load;
        
    }

    public int getExecutionStatus(){
        return lengthOfProcess;
    }

    public int getlengthOfProcess(){
        return lengthOfProcess;
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

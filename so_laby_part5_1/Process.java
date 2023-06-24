package so_laby_part5_1;

public class Process {

    private int progressOfProcess;
    private int lengthOfProcess;
    private boolean original;
    private int load;
    
    public Process(int length, int load, boolean original){
        lengthOfProcess = length;
        progressOfProcess = 0;
        this.load = load;
        this.original = original;
    }

    public void resertProgress(){
        progressOfProcess = 0;
    }

    public int getExecutionStatus(){
        return progressOfProcess;
    }

    public int getlengthOfProcess(){
        return lengthOfProcess;
    }

    public boolean getProcessesOrigin(){
        return original;
    }

    public void increaseProgressOfProcess(){
        progressOfProcess += 1;
    }

    public boolean isDone(){
        return progressOfProcess == lengthOfProcess;
    }
    
    public int getLoad(){
        return load;
    }
}

package so_laby_part3;

public class Page {
    private int numberOfPage;
    private int timeInRam;
    private int chance; 
    private int numberOfProcess;

    public void setNumberOfProcess(int numberofProcess){
        this.numberOfProcess = numberofProcess;
    }

    public int getNumberOfProcess(){
        return numberOfProcess;
    }

    public Page(int numberOfPage){
        this.numberOfPage = numberOfPage;
        timeInRam = 0; 
        chance = 1;
    }

    public int getNumberOfPage(){
        return numberOfPage;
    }

    public void increaseTime(){
        timeInRam +=1;
    }
    public void resetTimeInRam(){
        timeInRam = 1;
    }
    public int getTimeInRam(){
        return timeInRam;
    }

    public void decreaseChance(){
        chance -= 1;
    }
    public void increaseChance(){
        chance += 1;
    }

    public int getChance(){
        return chance;
    }
}

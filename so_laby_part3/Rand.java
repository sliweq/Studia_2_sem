package so_laby_part3;

import java.util.ArrayList;
import java.util.Random;

public class Rand {

    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;

    public Rand(ArrayList<Page> fifoList, int numberOfFrames){
        this.fifoList = fifoList;
        this.numberOfFrames = numberOfFrames;
        ram = new Page[numberOfFrames];
    }

    public void startSimualtion(){
        int tmp = fifoList.size();
        for(int i = 0; tmp > i; i++){
            Page page = fifoList.get(i);
            if(!checkRam(page)){
                addToRam(page);
            }
            increaseTimeInRam();

        }

        System.out.println("Pages errors rand: "+numberOfPagesErrors);
    }
    private void increaseTimeInRam(){
        for(int x = 0; x < ram.length; x++){
            if(ram[x] != null){
                ram[x].increaseTime();
            }
        }
    }

    private void addToRam(Page page){
        boolean isNull = false;
        for(int x = 0; x < ram.length; x++){
            if(ram[x] == null){
                isNull = true;
                break;
            }
        }

        if(isNull){
            // jesli sa same nulle
            numberOfPagesErrors += 1;

            for(int x = 0; x < ram.length; x++){
                if(ram[x] == null){
                    ram[x] = page;
                    break;
                }
            }

        }
        else{
            //jesli nie ma null
            numberOfPagesErrors +=1;                
            Random rand = new Random();
            ram[rand.nextInt(0,ram.length)] = page;
        }
    }

    private boolean checkRam(Page page){
        for(int x = 0; x < ram.length; x++){
            if(ram[x] != null){
                if(ram[x].getNumberOfPage() == page.getNumberOfPage()){
                    return true;
                }
            }
            else{
                return false;
            }
        }
        return false;   
    }
    
}

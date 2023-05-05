package so_laby_part3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Alru {
    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;


    
    private Queue<Page> chanceQueue;

    public Alru(ArrayList<Page> fifoList, int numberOfFrames){
        this.fifoList = fifoList;
        this.numberOfFrames = numberOfFrames;
        ram = new Page[numberOfFrames];
        chanceQueue = new LinkedList<>();
    }

    public void startSimualtion(){
        int tmp = fifoList.size();
        for(int currentPage = 0; tmp > currentPage; currentPage++){
            Page page = fifoList.get(currentPage);
            if(!checkRam(page)){
                addToRam(page);
            }
            else{
                //zaaktualizowanie odpowiedniej strony
            }
            increaseTimeInRam();
        }

        System.out.println("Pages errors alru: "+numberOfPagesErrors);
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
                    chanceQueue.add(page);
                    break;
                }
            }

        }
        else{
            //jesli nie ma null
                numberOfPagesErrors +=1;
                 
                Page toDeletePage = null;

                int index = 0;

                while(toDeletePage == null){
                    //System.out.println(chanceQueue.size());
                    Page tmpPage = chanceQueue.remove();
                    
                    if(tmpPage.getChance() == 0){
                        toDeletePage = tmpPage;       
                        break;
                    }
                    else{
                        tmpPage.decreaseChance();
                        chanceQueue.add(tmpPage);
                    }
                }
                for(int x = 0; x< ram.length ; x++){
                    if(ram[x].getNumberOfPage() == toDeletePage.getNumberOfPage()){
                        ram[x] = page;
                        chanceQueue.add(page);
                        break;
                    }
                }
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

package so_laby_part3;

import java.util.ArrayList;

public class Fifo {

    //TO nie jest fifo tylko lru

    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;

    public Fifo(ArrayList<Page> fifoList, int numberOfFrames){
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

        System.out.println("Pages errors LRU: " + numberOfPagesErrors);
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

            //sprawdzeni czy jest ta strona
            numberOfPagesErrors +=1;
                
            int longestInRamPage = 0;
            for(int x = 1; x < ram.length; x++){
                if(compare(ram[x], ram[longestInRamPage]) == 1){
                    longestInRamPage = x;
                }
            }

            ram[longestInRamPage] = page;
            
        }
    }

    private int compare(Page p1,Page p2){  

        if(p1.getTimeInRam()==p2.getTimeInRam()){
            return 0;  
        }
        else if(p1.getTimeInRam()>p2.getTimeInRam()){  return 1;  }
        else  {return -1;}  
        }  

    private boolean checkRam(Page page){
        for(int x = 0; x < ram.length; x++){
            if(ram[x] != null){
                if(ram[x].getNumberOfPage() == page.getNumberOfPage()){
                    ram[x].resetTimeInRam();
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

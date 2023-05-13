package so_laby_part3;

import java.util.ArrayList;

public class Lru {

    // lru ale to fifo 

    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;

    private int currentPage = 0;

    private int lastOut = 0;

    public Lru(ArrayList<Page> fifoList, int numberOfFrames){
        this.fifoList = fifoList;
        this.numberOfFrames = numberOfFrames;
        ram = new Page[numberOfFrames];
    }

    public void startSimualtion(){
        int tmp = fifoList.size();
        for(int currentPage = 0; tmp > currentPage; currentPage++){
            Page page = fifoList.get(currentPage);
            if(!checkRam(page)){
                addToRam(page);
            }
            increaseTimeInRam();
        }

        System.out.println("Pages errors fifo: "+numberOfPagesErrors);
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
                
                //wybieranie którego tzreba wywalić
                ram[lastOut] = page;
                lastOut +=1;
                if(lastOut >= ram.length){
                    lastOut = 0; 
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

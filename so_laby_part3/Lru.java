package so_laby_part3;

import java.util.ArrayList;

public class Lru {
    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;

    private int currentPage = 0;

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

        System.out.println("Pages errors lru: "+numberOfPagesErrors);
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
        
                //sprawdzanie odelglosci
                int[] pagesDistances = new int[ram.length];
                for(int x = 0; x < pagesDistances.length; x++){
                    pagesDistances[x] = divinerMaciej(ram[x]);
                }
                
                //wybieranie którego tzreba wywalić
                int longestInRamPage = 0;

                    for(int x = 1; x < pagesDistances.length; x++){
                        if(compare(pagesDistances[x], pagesDistances[longestInRamPage]) == 1){
                            longestInRamPage = x;
                        }
                    }
                
                ram[longestInRamPage] = page;
            
        }
    }
    
    private int divinerMaciej(Page page){

        int tmp = fifoList.size()-1;
        int distance = 0; 

        int x = fifoList.indexOf(page);

        for(int i = x-1; i > 0; i--){
            if(fifoList.get(i).getNumberOfPage() == page.getNumberOfPage()){
                return distance;
            }
            distance+=1;
        }
        return distance;
    }

    private int compare(int p1,int p2){  

        if(p1==p2){
            return 0;  
        }
        else if(p1>p2){  return 1;  }
        else  {return -1;}  
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

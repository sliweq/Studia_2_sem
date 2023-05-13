package so_laby_part3;

import java.util.ArrayList;

public class Opt {
    private ArrayList<Page> fifoList;

    private int numberOfFrames; 

    private Page[] ram;

    private int numberOfPagesErrors = 0;

    public Opt(ArrayList<Page> fifoList, int numberOfFrames){
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
            //sprawdzanie w ramie czy jest 
            //for(int x = 0 )
        }

        System.out.println("Pages errors opt: "+numberOfPagesErrors);
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
                    int tmpLocation = fifoList.indexOf(page);
                    pagesDistances[x] = divinerMaciej(ram[x],tmpLocation);
                }
                
                //wybieranie którego tzreba wywalić
                int longestInRamPage = 0;
                    for(int x = 1; x < pagesDistances.length; x++){
                        if(pagesDistances[x] > pagesDistances[longestInRamPage]){
                            longestInRamPage = x;
                        }
                    }
                
                ram[longestInRamPage] = page;
            
        }
    }
    
    private int divinerMaciej(Page page, int tmpLocation){

        int tmp = fifoList.size();
        int distance = 0; 

        int x = tmpLocation;

        for(int i = x + 1; i < tmp; i++){
            if(fifoList.get(i).getNumberOfPage() == page.getNumberOfPage()){
                return distance;
            }
            distance+=1;
        }
        return distance;
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

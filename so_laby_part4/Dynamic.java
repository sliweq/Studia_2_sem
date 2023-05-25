package so_laby_part4;

import java.util.ArrayList;
import java.util.Random;

import so_laby_part3.Page;

public class Dynamic {
    private boolean dynamic;
    private int ramSize;
    private int numberOfProcesses;

    private ArrayList<Page> finalList;
    private int[] sizeOfFragments;

    private ArrayList<Page>[] ram;

    private int u;
    private int l; 

    private int[] arrayOfErrors; // wszystkie błdy stron

    private int[] historyOfErrors ;
    private int tik;
    private int time;

    public Dynamic(int ramSize, int numberOfProcesses, ArrayList<Page> finalList, int[] sizeOfProcessesv2, int tik, int u,int l) throws Exception{
        this.tik = tik;
        this.sizeOfFragments = sizeOfProcessesv2;
        this.ramSize = ramSize;
        this.numberOfProcesses = numberOfProcesses;
        this.finalList = finalList;
        this.u = u; 
        this.l = l; 

        dynamic = false;

        time = 0; 

        Random rand = new Random();

        //przypisywanie ramek
        int tmp = 0;
        for(int x: sizeOfProcessesv2){
            tmp += x;
        }
        for(int x = 0; x < sizeOfFragments.length ;x ++){
            double tmpv2 = sizeOfProcessesv2[x]/(tmp*1.0);
            int tmpSize = (int) Math.round(ramSize*tmpv2);
            if(tmpSize < 1){
                
                throw new Exception("One of the processes get not enough frames");
            }
            sizeOfFragments[x] = tmpSize; 
        }
        int sum = 0; 
        for(int x :sizeOfFragments){
            sum+=x;
        }
        if(sum != ramSize){
            for(int x = 0; x < (ramSize-sum); x++){
                sizeOfFragments[rand.nextInt(0,numberOfProcesses)] += 1;

            }
        }

        ram = new ArrayList[numberOfProcesses];

        for(int x = 0; x < numberOfProcesses;x++ ){
            ram[x] = new ArrayList<>();
        }

        arrayOfErrors = new int[numberOfProcesses];
        for(int x : arrayOfErrors){
            x = 0;
        }

        historyOfErrors = new int[numberOfProcesses];
        for(int x: historyOfErrors){
            x = 0;
        }

        startSimualtion();
        printCosTam();
    }

    
    private void startSimualtion(){
        int finalArraySize = finalList.size();
        for(int x = 0; x < finalArraySize; x ++){
            if(!dynamic){
                if(checkNullInRam() == false){
                    dynamic = true;
                } 
            }
            Page tmpPage = finalList.get(x);
            if(!checkInRam(tmpPage)){

                arrayOfErrors[tmpPage.getNumberOfProcess()] += 1;
                if(dynamic){
                    // update history of errors
                    historyOfErrors[tmpPage.getNumberOfProcess()] +=1;

                    if(time % tik == 0 ){
                        //sprawdzanie które procesy przekroczyły l lub u
                        int[] tableOfLU = new int[numberOfProcesses];
                        for(int i = 0; i < numberOfProcesses; i++){
                            if(historyOfErrors[i] >= u){
                                tableOfLU[i] = 1;
                            
                            }
                            else if(historyOfErrors[i] <= l){
                                tableOfLU[i] = -1;
                            }
                            else{
                                tableOfLU[i] = 0;
                            }
                        }
                        
                        addFrames(tableOfLU,  removeFrames(tableOfLU));
                        
                        //czyszczenie historii
                        for(int i = 0; i < historyOfErrors.length; i++){
                            historyOfErrors[i] = 0;
                        }
                    }
                    
                }else{
                    addToRam(tmpPage);
                }
                time +=1;
            }
            increaseTimeInRam();
        }
        System.out.println("Dynamic frames errors:");

        for(int x = 0; x < arrayOfErrors.length; x++){
            System.out.println("Proces:" + x + " Errors: " + arrayOfErrors[x]);
        }

    }

    private void addFrames(int[] array, int removed){
        Random rand = new Random();
        for(int x = 0;  x< array.length; x++){
            if(array[x] == 1 ){
                if(removed == 0){
                    break;
                }
                else if( removed >= 1){
                    sizeOfFragments[x] +=1;
                    removed -= 1;
                }
            }
        }
        while(removed > 0){
            int tmp = rand.nextInt(array.length);
            sizeOfFragments[tmp] += 1;
            removed-=1;
        }
    }

    private int removeFrames(int[] array){
        int removed = 0; 

        Random rand = new Random();
        for(int x = 0; x < array.length; x++){
            if(array[x] == -1){
                if(ram[x].size() > 1){
                    ram[x].remove(rand.nextInt(0,ram[x].size()));
                    removed +=1;
                    sizeOfFragments[x] -=1;
                }
            }
        }
        return removed;
    }

    private boolean checkNullInRam(){
        boolean nulls = false;
        for(int x = 0; x < ram.length; x++){
            if(ram[x].size() != sizeOfFragments[x]){
                return true ;
            }
        }
        return nulls;
    }

    private void addToRam(Page page){
        boolean containsNull = true;
        ArrayList<Page> fragmentOfRam = ram[page.getNumberOfProcess()];
        if(fragmentOfRam.size() == sizeOfFragments[page.getNumberOfProcess()]){
            containsNull = false;
        } 
        
        //same nulle
        
        if(containsNull){
            fragmentOfRam.add(page);
        }
        //gdy wszystkie to są inne procesy
        else{
            int longestInRam = 0;
            for(int x = 1; x< fragmentOfRam.size(); x++){
                if(fragmentOfRam.get(x).getTimeInRam() > fragmentOfRam.get(longestInRam).getTimeInRam()){
                    longestInRam = x;
                }
            }
            fragmentOfRam.set(longestInRam, page);
        }

    }

    private void increaseTimeInRam(){
        for(int x = 0; x < numberOfProcesses; x++){
            for(Page page: ram[x]){
                page.increaseTime();
            }
        }
    }

    private boolean checkInRam(Page page){
        ArrayList<Page> fragmenOfRam = ram[page.getNumberOfProcess()]; 
        
        for(int x = 0; x < fragmenOfRam.size(); x++){
            if(fragmenOfRam.get(x).getNumberOfPage() == page.getNumberOfPage()){
                fragmenOfRam.get(x).resetTimeInRam();
                return true;
            }
        }

        return false;
    }
    
    public void printCosTam(){
        for(int x: sizeOfFragments){
            System.out.println(x);
        }
    }
}

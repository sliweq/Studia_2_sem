package so_laby_part4;

import java.util.ArrayList;
import java.util.Random;

import so_laby_part3.Page;

public class WssAlgo {
    private boolean wss ;
    private int ramSize;
    private int numberOfProcesses;

    private ArrayList<Page> finalList;
    private int[] sizeOfFragments;

    private ArrayList<Page>[] ram;

    private ArrayList<Page>[] D;
    private int c; 

    private int[] arrayOfErrors; // wszystkie błdy stron

    private int deltaT;
    private int time;

    private ArrayList<Integer> frozenProcesses;

    public WssAlgo(int ramSize, int numberOfProcesses, ArrayList<Page> finalList, int[] sizeOfProcessesv2, int tik) throws Exception{
        this.deltaT = tik;
        this.sizeOfFragments = sizeOfProcessesv2;
        this.ramSize = ramSize;
        this.numberOfProcesses = numberOfProcesses;
        this.finalList = finalList;

        c = (int) (0.75*deltaT);
        wss = false;

        time = 0; 
        frozenProcesses = new ArrayList<>();

        Random rand = new Random();


        //przypisywanie porporcjonalne 
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

        startSimualtion();
    }

    
    private void startSimualtion() throws Exception{
        int finalArraySize = finalList.size();
        for(int x = 0; x < finalArraySize; x ++){
            Page tmpPage = finalList.get(x);
            if(!wss){
                if(checkNullInRam() == false){
                    wss = true;
                }
                
            }
            if(wss){
                boolean isFrozen = false;
                for(Integer tmp: frozenProcesses){
                    if(tmp == tmpPage.getNumberOfProcess()){
                        isFrozen = true;
                    }
                }
                
                if(!isFrozen){
                    if(!checkInRam(tmpPage)){
                            if(time % c == 0){
                                frozenProcesses.clear();

                                int[] historyOfRequests = new int[numberOfProcesses];
                                
                                createHistory(tmpPage, historyOfRequests);
                                int d = 0; 
                                for(int histor: historyOfRequests){
                                    d += histor;
                                }

                                if(d> ramSize){
                                    //sprawdzanei ktory proces nalezy wywalic
                                    int overFlow = d-ramSize;
        
                                    int counter = historyOfRequests[0];
                                    int proces = 0;
                                    // gdy jest 1 dostepny
                                    for(int i = 1; i< historyOfRequests.length; i++){
                                        if(historyOfRequests[i] >= overFlow && counter> historyOfRequests[i]){
                                            counter = historyOfRequests[i];
                                            proces = i;
                                        }
                                    }

                                    //gdy ten jeden nie wystarczy to szukamy wiecej akurat ja będę brał najwieksze procesy
                                    if(counter < overFlow){
                                        ArrayList<Integer> toDelete = new ArrayList<>();

                                        counter = 0; 
                                        int index = 0;
                                        int max = 0;
                                        while(counter<overFlow){
                                            for(int anotherTMP = 1; anotherTMP< historyOfRequests.length; anotherTMP ++){
                                                if(historyOfRequests[anotherTMP] > max && !toDelete.contains(anotherTMP)){
                                                    max = historyOfRequests[anotherTMP];
                                                    index = anotherTMP;
                                                }
                                            }
                                            counter +=max;
                                            toDelete.add(index);
                                        }
                                        //ok
                                        for(int t: toDelete){
                                            removeProcesses(t);
                                        }
                                        robinHood(historyOfRequests);
                                        
                                    }
                                    else{
                                        //ok 
                                        removeProcesses(proces);
                                        robinHood(historyOfRequests);

                                    }
                                }
                                else if(d< ramSize){
                                    //przydział gdy jest mniejszy
                                    robinHood(historyOfRequests);
                                }
                            }
                            else{
                                //git
                                addToRam(tmpPage);
                                arrayOfErrors[tmpPage.getNumberOfProcess()] +=1;
                            }
                        
                    }
                }
            }

            else{
                if(!checkInRam(tmpPage)){
                    arrayOfErrors[tmpPage.getNumberOfProcess()] +=1;
                    addToRam(tmpPage);
                }
            }


            time +=1;
            increaseTimeInRam();
              
        }
        System.out.println("Wss frames errors:");

        for(int x = 0; x < arrayOfErrors.length; x++){
            System.out.println("Proces:" + x + " Errors: " + arrayOfErrors[x] + " Ram usage: " + sizeOfFragments[x]);
        }

    }

    private boolean processIsFrozen(int process){
        for(int x: frozenProcesses){
            if(x == process){
                return true;
            }
        }
        return false ;
    }



    private void robinHood(int[] historyOfRequests) throws Exception{
        Random rand = new Random();
        int tmp = 0;
        for(int x: historyOfRequests){
            if(!processIsFrozen(x)){
                tmp += x;

            }
        }

        for(int x = 0; x < sizeOfFragments.length ;x ++){
            double tmpv2 = (historyOfRequests[x]*1.0/(tmp));
            int tmpSize = (int) Math.round(ramSize*tmpv2);
            if(tmpSize > 0){
                if(tmpSize ==0){
                    tmpSize = 1;
                }
                if(!processIsFrozen(x)){
                    if(sizeOfFragments[x]>tmpSize){
                        while(sizeOfFragments[x] != tmpSize){
                            if(sizeOfFragments[x] > ram[x].size()){
                                sizeOfFragments[x] -=1;
                            }
                            else{
                                ram[x].remove(rand.nextInt(0,ram[x].size()));
                                sizeOfFragments[x] -=1;
                            }
                       }
                    }
                    else if(sizeOfFragments[x] < tmpSize){
                        while(sizeOfFragments[x] != tmpSize){
                            sizeOfFragments[x] +=1;
                        }
                    }
                }
            }
        }

        int sum = 0; 
        for(int x :sizeOfFragments){
            sum+=x;
        }
        if(sum < ramSize){
            for(int x = 0; x < (ramSize-sum); x++){
                int process = rand.nextInt(0,numberOfProcesses);
                if(!processIsFrozen(process)){
                    sizeOfFragments[rand.nextInt(0,numberOfProcesses)] += 1;
                }
            }
        }
        else if(sum > ramSize){
            int najwiecej = 0;
            while(sum>ramSize){
                for(int i = 1; i < numberOfProcesses; i++){
                    if(sizeOfFragments[i] > sizeOfFragments[najwiecej]){
                        najwiecej = i;
                    }
                }
                if(sizeOfFragments[najwiecej] > ram[najwiecej].size()){
                    sizeOfFragments[najwiecej] -= 1;
                    sum-=1;
                }
                else{
                    ram[najwiecej].remove(rand.nextInt(0,ram[najwiecej].size()));
                    sizeOfFragments[najwiecej] -=1;
                }
                
            }
            //throw new Exception("error!");
        }
    }

    private void removeProcesses(int x){
        if(!processIsFrozen(x)){
            int sizeToDelete = ram[x].size();
            sizeOfFragments[x] = 0;
            ram[x].clear();   
            frozenProcesses.add(x);
        }
    }

    private int[] createHistory(Page page, int[] history){
        for(int i = 0; i < history.length; i++){
            history[i] =0;
        }

        int tmp = 0; 
        int index = finalList.indexOf(page)-1;
        while(tmp < deltaT && index >=0){
            history[finalList.get(index).getNumberOfProcess()] +=1;
            index -=1; 
            tmp +=1;
        }

        return null; 
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
            try{

                fragmentOfRam.set(longestInRam, page);
            }
            catch(Exception ex){
                
            }
        }

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

    private void increaseTimeInRam(){
        for(int x = 0; x < numberOfProcesses; x++){
            for(Page page: ram[x]){
                page.increaseTime();
            }
        }
    }
}
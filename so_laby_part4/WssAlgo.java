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

    
    private void startSimualtion(){
        int finalArraySize = finalList.size();
        for(int x = 0; x < finalArraySize; x ++){
            if(!wss){
                if(checkNullInRam() == false){
                    wss = true;
                }
            }

            Page tmpPage = finalList.get(x);

            boolean isFrozen = false;
            for(Integer tmp: frozenProcesses){
                if(tmp == tmpPage.getNumberOfProcess()){
                    isFrozen = true;
                }
            }
            
            if(!isFrozen){
                if(!checkInRam(tmpPage)){
                    if(wss){
                        if(time % c == 0){
                            int[] historyOfRequests = new int[numberOfProcesses];
                            
                            createHistory(tmpPage, historyOfRequests);
                            int d = 0; 
                            for(int histor: historyOfRequests){
                                d += histor;
                            }
                            if(d> ramSize){
                                //sprawdzanei ktory proces nalezy wywalic
                                int overFlow = d-ramSize;

                                int finalProcess = -1;

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
                                    counter = 0; 
                                    int index = 0;
                                    int max = 0;
                                    while(counter< overFlow){
                                        for(int anotherTMP = 1; anotherTMP< historyOfRequests.length; anotherTMP ++){
                                            if(historyOfRequests[anotherTMP] > max){
                                                max = historyOfRequests[anotherTMP];
                                                index = anotherTMP;
                                            }
                                        }
                                        counter +=1;
                                        historyOfRequests[index] = -1;
                                    }

                                    // mamy znalezionie procesy do usuniecia
                                    // wiec przydało by sie je usunac i dodac ramki innym
                                }
                                else{
                                    // jesli mamy jeden porces do wywalenia
                                }
                                
                                //TODO aktualizacja ilosc ramek dla każdego procesu które nie sa zawieszone


                            }

                            else{
                                // jesli d < ramSize
                                //sprawdzamy ile jest wolnego miejsca i sprawdzamy ostatnie dowlowania
                                //jesli ktorys z freezowanych ma odpowiednia ilosc to wrzucamy go do ramu
                                //TODO aktualizacja ilosc ramek dla każdego procesu które nie sa zawieszone
                            }
    
                        }
                    }
                    else{
                        addToRam(tmpPage);
                    }
                }
            }

            time +=1;
            increaseTimeInRam();
              
        }
        System.out.println("Dynamic frames errors:");

        for(int x = 0; x < arrayOfErrors.length; x++){
            System.out.println("Proces:" + x + " Errors: " + arrayOfErrors[x]);
        }
    }

    private int[] createHistory(Page page, int[] history){
        for(int i = 0; i < history.length; i++){
            history[i] =0;
        }

        int tmp = 0; 
        int index = finalList.indexOf(page);
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
            fragmentOfRam.set(longestInRam, page);
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
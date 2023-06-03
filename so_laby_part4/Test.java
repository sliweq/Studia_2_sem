package so_laby_part4;

import java.util.ArrayList;
import java.util.Random;

import so_laby_part3.Page;

public class Test {
    // parametry wielkosciowe jezei chodzi o procesy
    private int minSizeOfProcesses = 20;
    private int maxSizeOfProcesses = 70;

    private int maxSizeOfReferences = 15_000;
    private int minSizeOfReferences = 5_000;

    private int l = 5;
    private int u = 17;

    private int numberOfProcesses = 7;
    private int ramSize = 100;

    private int[] sizeOfProcesses = new int[numberOfProcesses];

    private int[] amountOfReferences = new int[numberOfProcesses];

    private ArrayList<ArrayList<Page>> rawArray = new ArrayList<ArrayList<Page>>();

    private ArrayList<Page> finalArray = new ArrayList<>();
    
    private int tik = 50;
    private int detlaT = 75;

    public Test(){
        Random rand = new Random();

        for(int x = 0; x < numberOfProcesses; x++){
            sizeOfProcesses[x] = rand.nextInt(minSizeOfProcesses,maxSizeOfProcesses);
            amountOfReferences[x] = rand.nextInt(minSizeOfReferences, maxSizeOfReferences);
        }

        createProcesses();

        while(rawArray.size() != 0){

            int randomProcess = rand.nextInt(0,rawArray.size());

            finalArray.add(rawArray.get(randomProcess).remove(0));
            
            if(rawArray.get(randomProcess).size() == 0){
                rawArray.remove(randomProcess);
            }

        }
        
    }

    private void createProcesses(){

        for(int x = 0; x < numberOfProcesses; x++){
            rawArray.add(new ArrayList<>());

            int numberOfPages = sizeOfProcesses[x];
            
            boolean locality = false;
            int localityMeter = 0;
            int localityLocation = 0; 

            int maxLocal = 5;

            Random rand = new Random();
        
            for(int i= 0; i < amountOfReferences[x]; i++){
                if(locality){

                    int tmp_local = rand.nextInt(1,maxLocal);

                    if(localityLocation-tmp_local<0){
                        int randomPage = rand.nextInt(Math.max(0, localityLocation-tmp_local),localityLocation+tmp_local);
                        Page tmpPage = new Page(randomPage);

                        tmpPage.setNumberOfProcess(x);
                        rawArray.get(x).add(tmpPage);

                    }
                    else if(localityLocation+tmp_local > numberOfPages-1){
                        int randomPage = rand.nextInt(localityLocation-tmp_local,Math.min(numberOfPages-1, localityLocation+tmp_local));
                        Page tmpPage = new Page(randomPage);

                        tmpPage.setNumberOfProcess(x);
                        rawArray.get(x).add(tmpPage);

                    }
                    else{
                        int randomPage = rand.nextInt(localityLocation -tmp_local, localityLocation+tmp_local);
                        Page tmpPage = new Page(randomPage);

                        tmpPage.setNumberOfProcess(x);
                        rawArray.get(x).add(tmpPage);
                    }

                    localityMeter -=1;
                    if(localityMeter <=0 ){
                        locality = false;
                        localityMeter = 0;
                    }

                }
                else{
                    double localityRate = Math.random();
                    if(localityRate <= 0.1){
                        locality = true;
                        localityMeter = 10;

                        
                        int randomPage = rand.nextInt(0,numberOfProcesses);

                        localityLocation = randomPage;


                        Page tmpPage = new Page(randomPage);

                        tmpPage.setNumberOfProcess(x);
                        rawArray.get(x).add(tmpPage);


                        
                        localityMeter -= 1;
                    }
                    else{
                        int randomPage = rand.nextInt(0,numberOfPages);
                        Page tmpPage = new Page(randomPage);

                        tmpPage.setNumberOfProcess(x);
                        rawArray.get(x).add(tmpPage);
                    }
                }
            }
        }
    }
    
    private void createProcessesTest(){
        Page p = new Page(0);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(2);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(0);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(1);
        finalArray.add(p);
        p = new Page(2);
        p.setNumberOfProcess(1);
        finalArray.add(p);
        p = new Page(0);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(3);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(4);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(2);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(1);
        finalArray.add(p);
        p = new Page(0);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(3);
        p.setNumberOfProcess(0);
        finalArray.add(p);
        p = new Page(2);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(2);
        finalArray.add(p);
        p = new Page(0);
        p.setNumberOfProcess(1);
        finalArray.add(p);
        p = new Page(2);
        p.setNumberOfProcess(1);
        finalArray.add(p);
        p = new Page(1);
        p.setNumberOfProcess(2);
        finalArray.add(p);

        p = new Page(0);
        p.setNumberOfProcess(0);
        finalArray.add(p);

        p = new Page(4);
        p.setNumberOfProcess(0);
        finalArray.add(p);

        p = new Page(2);
        p.setNumberOfProcess(1);
        finalArray.add(p);

        p = new Page(3);
        p.setNumberOfProcess(2);
        finalArray.add(p);

        p = new Page(0);
        p.setNumberOfProcess(1);
        finalArray.add(p);

        p = new Page(0);
        p.setNumberOfProcess(2);
        finalArray.add(p);
    }

    public void startSimualtion() throws Exception{
        Equal e = new Equal(ramSize, numberOfProcesses, finalArray);

        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }

        Prop p = new Prop(ramSize, numberOfProcesses, finalArray, sizeOfProcesses);

        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }

        Dynamic d = new Dynamic(ramSize, numberOfProcesses, finalArray, sizeOfProcesses, tik,u , l);
         
        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }

        WssAlgo wss = new WssAlgo(ramSize, numberOfProcesses, finalArray, sizeOfProcesses, detlaT);
    }

    public void startSimualtionv2() throws Exception{
        Equal e = new Equal(6, 3, finalArray);

        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }

        Prop p = new Prop(6, 3, finalArray, new int[]{5,3,4});

        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }

        Dynamic d = new Dynamic(6, 3, finalArray, new int[]{5,3,4}, 5,2 , 4);
         
        for(Page tmp: finalArray){
            tmp.resetTimeInRam();
        }
    }

    public void printSettings(){
        System.out.println("Max size of processes: " + minSizeOfProcesses);
        System.out.println("Min size of processes: " + maxSizeOfProcesses);
        System.out.println("Max references amount: " + minSizeOfReferences);
        System.out.println("Min references amount: " + maxSizeOfReferences
        + "\nProcesses amount: "+ numberOfProcesses + "\nRam size: " + ramSize + "\nAll references: " + finalArray.size());

        for(int x = 0;sizeOfProcesses.length > x; x++){
            System.out.println("Process " + x + " Size of process " + sizeOfProcesses[x] + " Amount of refernces " + amountOfReferences[x]);
        }
    }
    public static void main(String[] args) throws Exception{

        Test t = new Test();
        t.printSettings();
        t.startSimualtion();
        //t.createProcessesTest();
        //t.startSimualtionv2();
    }
    
}


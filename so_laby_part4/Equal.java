package so_laby_part4;
import java.util.ArrayList;

import so_laby_part3.Page;

public class Equal {
    private int ramSize;
    private int numberOfProcesses;

    private ArrayList<Page> finalList;
    private int[] sizeOfFragments;

    private int frameDivision;

    private ArrayList<Page>[] ram;


    private int[] arrayOfErrors;

    public Equal(int ramSize, int numberOfProcesses, ArrayList<Page> finalList) throws Exception{
        this.ramSize = ramSize;
        this.numberOfProcesses = numberOfProcesses;
        frameDivision = (int) ramSize/numberOfProcesses;
        if(frameDivision < 1){
            throw new Exception("One of the processes get not enough frames");
        }

        ram = new ArrayList[numberOfProcesses];
        sizeOfFragments = new int[numberOfProcesses];

        for(int x = 0; x < numberOfProcesses;x++ ){
            ram[x] = new ArrayList<>();
            sizeOfFragments[x] = frameDivision;
        }

        this.finalList = finalList;

        arrayOfErrors = new int[numberOfProcesses];
        for(int x : arrayOfErrors){
            x = 0;
        }

        startSimualtion();
    }

    
    private void startSimualtion(){
        int finalArraySize = finalList.size();
        for(int x = 0; x < finalArraySize; x ++){
            Page tmpPage = finalList.get(x);
            if(!checkInRam(tmpPage)){
                arrayOfErrors[tmpPage.getNumberOfProcess()] += 1;
                addToRam(tmpPage);
                
            }
            increaseTimeInRam();
        }
        System.out.println("Equal frames errors:");
        int licznik = 0; 
        for(int x = 0; x < arrayOfErrors.length; x++){
            System.out.println("Proces:" + x + " Errors: " + arrayOfErrors[x]+ " Ram usage: " + sizeOfFragments[x]);
            licznik += arrayOfErrors[x];
        }
        System.out.println(licznik);

    }

    private void addToRam(Page page){
        boolean containsNull = true;
        ArrayList<Page> fragmentOfRam  = ram[page.getNumberOfProcess()];
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
}
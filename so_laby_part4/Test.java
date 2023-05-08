package so_laby_part4;

import java.util.ArrayList;
import java.util.Random;

import so_laby_part3.Page;

public class Test {

    private int numberOfProcesses;
    private int ramSize = 100;

    private int[] sizeOfProcesses;

    private ArrayList<Page> allPages;
    private ArrayList<Page>[] allProcesses;
    //private ArrayList<Page> 

    public Test(){
        allProcesses = new ArrayList[numberOfProcesses];
        Random rand = new Random();

        //wielkosc poszczegolnych procesow
        for(int i = 0; i < numberOfProcesses; i ++){
            sizeOfProcesses[i] = rand.nextInt();
        }


        //dodawanie do poszczególnych procesów
        for(int x = 0; x < numberOfProcesses; x++){

            boolean locality = false;
            int localityMeter = 0;
            int localityLocation = 0; 
    
            int maxLocal = 9;
                
            for(int i= 0; i < sizeOfProcesses[x]; i++){
                if(locality){
                    //dodawanie 
                    // w lokalnosci
    
                    int tmp_local = rand.nextInt(1,maxLocal);
    
                    if(localityLocation-tmp_local<0){
                        int randomPage = rand.nextInt(Math.max(0, localityLocation-tmp_local),localityLocation+tmp_local);
                        allProcesses[x].add(new Page(randomPage));
                        //z zakresu od 0 do 4
                    }
                    else if(localityLocation+tmp_local > sizeOfProcesses[x]-1){
                        int randomPage = rand.nextInt(localityLocation-tmp_local,Math.min(sizeOfProcesses[x]-1, localityLocation+tmp_local));
                        allProcesses[x].add(new Page(randomPage));
    
                    }
                    else{
                        // System.out.println(tmp_local);
                        // System.out.println((localityLocation -tmp_local)+" "+ (localityLocation+tmp_local));
                        int randomPage = rand.nextInt(localityLocation -tmp_local, localityLocation+tmp_local);
                        allProcesses[x].add(new Page(randomPage));
                    }
    
                    localityMeter -=1;
                    if(localityMeter <=0 ){
                        locality = false;
                        localityMeter = 0;
                    }
    
                }
                else{
                    double localityRate = Math.random();
                    // rng lokalmności mozna podregulowac zmieniajac localityRate lub localityMeter różnie
                    if(localityRate <= 0.1){
                        locality = true;
                        localityMeter = 10;
    
                        
                        int randomPage = rand.nextInt(0,sizeOfProcesses[x]);
    
                        localityLocation = randomPage;
    
                        allProcesses[x].add(new Page(randomPage));
                        
                        localityMeter -= 1;

                    }
                    else{
                        //dodanie randomowej strony chyba rand int;
                        int randomPage = rand.nextInt(0,sizeOfProcesses[x]);
                        allProcesses[x].add(new Page(randomPage));                    }
                }
            }
            
        }
    }
   
    public static void main(String[] args){
    }
    
}

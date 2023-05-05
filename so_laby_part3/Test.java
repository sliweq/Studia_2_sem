package so_laby_part3;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    private int numberOfPages = 100;
    private int numberOfFrames = 10;
    private int numberOfAllPages = 5000;

    private ArrayList<Page> fifoList;
    private ArrayList<Page> randList;
    private ArrayList<Page> optList;
    private ArrayList<Page> lruList;
    private ArrayList<Page> alruList;

    private int tmp  = 0;

    public Test(){
        fifoList = new ArrayList<>();
        randList = new ArrayList<>();
        optList = new ArrayList<>();
        lruList = new ArrayList<>();
        alruList = new ArrayList<>();
    }


    public void createPages(){

        //random jest ponizej 0.1 to jest lokalność czli kojelne 10 załóżmy procesów będzie lokalnie losowanych
        //lokalnie czyli w odległości np +-3/4;
        //reszta randomowo no to wiadomix
    
        boolean locality = false;
        int localityMeter = 0;
        int localityLocation = 0; 

        Random rand = new Random();
        
        for(int i= 0; i < numberOfAllPages; i++){
            if(locality){
                //dodawanie 
                // w lokalnosci

                if(localityLocation-5<0){
                    int randomPage = rand.nextInt(Math.max(0, localityLocation-5),localityLocation+5);
                    fifoList.add(new Page(randomPage));
                    randList.add(new Page(randomPage));
                    optList.add(new Page(randomPage));
                    lruList.add(new Page(randomPage));
                    alruList.add(new Page(randomPage));


                    //z zakresu od 0 do 4
                }
                else if(localityLocation+5 > numberOfPages-1){
                    int randomPage = rand.nextInt(localityLocation-5,Math.min(numberOfPages-1, localityLocation+5));
                    fifoList.add(new Page(randomPage));
                    randList.add(new Page(randomPage));
                    optList.add(new Page(randomPage));
                    lruList.add(new Page(randomPage));
                    alruList.add(new Page(randomPage));

                }
                else{
                    int randomPage = rand.nextInt(localityLocation -5, localityLocation+5);
                    fifoList.add(new Page(randomPage));
                    randList.add(new Page(randomPage));
                    optList.add(new Page(randomPage));
                    lruList.add(new Page(randomPage));
                    alruList.add(new Page(randomPage));
                }
                tmp +=1;

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

                    
                    int randomPage = rand.nextInt(0,numberOfPages);

                    localityLocation = randomPage;


                    fifoList.add(new Page(randomPage));
                    randList.add(new Page(randomPage));
                    optList.add(new Page(randomPage));
                    lruList.add(new Page(randomPage));
                    alruList.add(new Page(randomPage));
                    
                    localityMeter -= 1;
                    tmp +=1;
                    
                    // //chyba trzeba to usunąć xd 

                    // if(randomPage-4<0){
                    //     //z zakresu od 0 do 4
                    // }
                    // else if(randomPage+4 > 49){
                    //     //od 45 do 59
                    // }
                    // else{
                    //     //normlanie juz
                    // }
                    // //wylosowanie strony
                    // //ustawienie locality location
                    // //itd 
                }
                else{
                    //dodanie randomowej strony chyba rand int;
                    int randomPage = rand.nextInt(0,numberOfPages);
                    fifoList.add(new Page(randomPage));
                }
            }
        }
    }
    public void printStats(){
        System.out.println("Number of random pages: " + (numberOfAllPages-tmp));
        System.out.println("Number of pages with locality: " + tmp);

        int[] p = new int[numberOfPages];
        
        for(Page page: fifoList){
            p[page.getNumberOfPage()] += 1;
        }
        for(int x = 0 ;x < numberOfPages; x++){
            System.out.print(x + ":"+p[x] + "|");
        }
        System.out.println();

    }

    public void startTest(){
        Fifo fifo = new Fifo(fifoList, numberOfFrames);
        fifo.startSimualtion();
        Rand rand = new Rand(randList, numberOfFrames);
        rand.startSimualtion();
        Opt opt = new Opt(optList, numberOfFrames);
        opt.startSimualtion();
        Lru lru = new Lru(lruList, numberOfFrames);
        lru.startSimualtion();
        Alru alru = new Alru(alruList, numberOfFrames);
        alru.startSimualtion();
    }

    public static void main(String[] args){
        Test t = new Test();
        t.createPages();
        t.printStats();
        t.startTest();
        
    }
}

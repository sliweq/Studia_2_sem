package so_laby_part2;
import java.lang.Math;


import java.util.ArrayList;

import so_laby.fcfs;

public class Test {

    private int amountOfRequests = 5000;
    private int diskCapacity = (int)(amountOfRequests/10);
    private double realTimeProbability = 0.1;
    private int allRealTime = 0;

    private int arrivalProbabylity = 20;
    private int deadlineProbability = 100;


    private ArrayList<Request> fscsList = new ArrayList<>();
    private ArrayList<Request> sstfList = new ArrayList<>();
    private ArrayList<Request> scanList = new ArrayList<>();
    private ArrayList<Request> cscanList = new ArrayList<>();
    private ArrayList<Request> edfSstfList = new ArrayList<>();
    private ArrayList<Request> edfScanSstfList = new ArrayList<>();
    

    public Test(){
        int tmpLastArrivallTime = 0;
        for(int i = 0; i < amountOfRequests; i++){
            double isRealTime = Math.random();
            if(isRealTime<realTimeProbability){

                int tmpPlaceOnDisk = (int) (Math.random()*diskCapacity);


                int tmpdeadline = (int) (Math.random()*(deadlineProbability));
                
                sstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));
                scanList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));
                cscanList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));
                fscsList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));
                edfSstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));
                edfScanSstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, true,tmpdeadline));

                tmpLastArrivallTime += (int) (Math.random()*(arrivalProbabylity));
                allRealTime +=1;
            }
            else{
                int tmpPlaceOnDisk = (int) (Math.random()*diskCapacity);

                fscsList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));
                scanList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));
                sstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));
                cscanList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));
                edfSstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));
                edfScanSstfList.add(new Request(tmpPlaceOnDisk,tmpLastArrivallTime, false));


                tmpLastArrivallTime += (int) (Math.random()*(arrivalProbabylity));
            }
        }
    }

    public void startTest(){
        
        Fcfs fcfs = new Fcfs(amountOfRequests, allRealTime, fscsList);
        fcfs.startSimulatio();

        Sstf sstf = new Sstf(amountOfRequests, allRealTime, sstfList);
        sstf.startSimulatio();

        Scan scan = new Scan(amountOfRequests, allRealTime, scanList);
        scan.startSimulatio();

        Cscan cscan = new Cscan(amountOfRequests, allRealTime, cscanList);
        cscan.startSimulatio();

        EdfSstf edf = new EdfSstf(amountOfRequests, allRealTime, edfSstfList);
        edf.startSimulatio();

        EdfscanSstf edfscanSstf = new EdfscanSstf(amountOfRequests, allRealTime, edfScanSstfList);
        edfscanSstf.startSimulatio();

    }
    public static void main(String[] args){
        Test t = new Test();
        t.startTest();

    }
}

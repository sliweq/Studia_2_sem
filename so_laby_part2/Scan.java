package so_laby_part2;
import java.util.ArrayList;


public class Scan {
    private ArrayList<Request> allRequests;
    private ArrayList<Request> requestsQueue;
    private ArrayList<Request> doneRequests;

    private int currentTime;
    private int armMovesVersion2;


    private int armMoves;
    private int armPosition;
    private int armDirection; //-1 left 1 right

    private int realTimeDone;
    private int allRealTime;

    private int allRequestsAmount;
    private int diskCapacity;


    public Scan(int allRequestsAmount,int allRealTime, ArrayList<Request> allRequests ){
        this.allRequestsAmount = allRequestsAmount;
        this.allRequests = allRequests;
        requestsQueue = new ArrayList<Request>();
        doneRequests = new ArrayList<Request>();
        
        realTimeDone = 0;
        this.allRealTime = allRealTime;
        armMoves = 0;
        currentTime = 0;
        armDirection = 1;
        armPosition = (int)(allRequestsAmount/20);
        //armPosition = 45;
        diskCapacity = (int)(allRequestsAmount/10);
    }

    
    public void startSimulatio(){

        allRequestsUpdate();
        
        while(doneRequests.size() != allRequestsAmount){
            // najpierw dodajemy wszystkie procesy do oczekujacych. Później wskazujemy ramieniu kierunek   

            allRequestsUpdate();
            updateArm();
            armPosition += armDirection;
            updateArmAndRequest();
            updateRealTimeRequestsInQueue();

            currentTime +=1;
            if(armDirection != 0){
                armMovesVersion2 +=1;
            }  
        }

        System.out.println("scan");

        //System.out.println(armMoves);
        System.out.println("Requesty spełnione na czas: "+realTimeDone);
        System.out.println("Requesty które nie zosatły spełnione na czas: "+(allRealTime-realTimeDone));
        System.out.println("Poruszenia ramienia: "+armMovesVersion2);

    }


    //update wszystkich porcesów dodawanie do oczekujacych
    public void allRequestsUpdate(){ 
        while(allRequests.size() != 0 && allRequests.get(0).getArrivalTime() == currentTime){
            requestsQueue.add(allRequests.get(0));
            allRequests.remove(0);
        }
    }

    // funkcja dla fcfs
    public void updateArm(){
        armPosition += armDirection;
        if(armPosition >= diskCapacity){

            armDirection = -1;
            armPosition += armDirection;
            //armPosition = 0;
            //armMoves +=2;
        }
        else if(armPosition < 0){
            armDirection = 1;
            armPosition +=  armDirection;
        }
    }

    //update wszystkich procesów które znajdują się na ramieniu gdy dobiliśmy do właściwego taska
    public void updateArmAndRequest(){
        if(requestsQueue.size()!=0){
            for(int y = requestsQueue.size()-1; y >= 0; y--){
                if(requestsQueue.get(y).getPlaceOnDrive() == armPosition){
                    doneRequests.add(requestsQueue.get(y));
                    if(requestsQueue.get(y).isRealTimeRequest()){
                        realTimeDone += 1;
                    }
                    requestsQueue.remove(y);         
                }
            }
            
        }
    }

    //odjecie czasu od real time procesów i usuniecie tych co maja zero
    public void updateRealTimeRequestsInQueue(){
        for(int i = requestsQueue.size()-1; i >= 0; i--){
            if(requestsQueue.get(i).isRealTimeRequest()){
                requestsQueue.get(i).decreaseRealTime();
                if(requestsQueue.get(i).getDeadLine() == 0){
                    requestsQueue.get(i).setRealTimeDoneOnTime(false);
                    doneRequests.add(requestsQueue.get(i));
                    requestsQueue.remove(i);
                }
            }
        }
    }
    
}

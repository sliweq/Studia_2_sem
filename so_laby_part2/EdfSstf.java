package so_laby_part2;
import java.util.ArrayList;


public class EdfSstf {
    private ArrayList<Request> allRequests;
    private ArrayList<Request> requestsQueue;
    private ArrayList<Request> doneRequests;

    private int currentTime;

    private int armMoves;
    private int armPosition;
    private int armDirection; //-1 left 1 right

    private Request priorityRequest;
    private int armMovesVersion2;


    private int realTimeDone;
    private int allRealTime;

    private int allRequestsAmount;
    private int diskCapacity;


    public EdfSstf(int allRequestsAmount,int allRealTime, ArrayList<Request> allRequests ){
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
        priorityRequest = null;
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

        System.out.println("edfsstf");


        //System.out.println(armMoves);
        System.out.println("Requesty spełnione na czas: "+realTimeDone);
        System.out.println("Requesty które nie zosatły spełnione na czas: "+(allRealTime-realTimeDone));
        System.out.println("Poruszenia ramienia: "+armMovesVersion2);

    }


    //update wszystkich porcesów
    public void allRequestsUpdate(){ 
        while(allRequests.size() != 0 && allRequests.get(0).getArrivalTime() == currentTime){
            requestsQueue.add(allRequests.get(0));
            if(allRequests.get(0).getRealTimeRequest() && priorityRequest == null){
                if(priorityRequest == null){
                    priorityRequest = allRequests.get(0);
                }
                else{
                    if(allRequests.get(0).getDeadLine() < priorityRequest.getDeadLine()){
                        priorityRequest = allRequests.get(0);
                    }
                }
            }
            allRequests.remove(0);
        }
    }

    public void updateArm(){

        if(priorityRequest != null){
            if(armPosition-priorityRequest.getPlaceOnDrive() < 0 ){
                if(armDirection != 1){
                    armMoves +=1;
                }
                armDirection = 1;
            }
            else if(armPosition-priorityRequest.getPlaceOnDrive() > 0 ){
                if (armDirection != -1){
                    armMoves +=1;
                }
                armDirection = -1;
            }
            else{
                armDirection = 0;
            }
        }

        else if(requestsQueue.size() != 0 && priorityRequest == null){
            int tmp = requestsQueue.get(0).getPlaceOnDrive();
            for(Request req: requestsQueue){
                if(Math.abs(req.getPlaceOnDrive()-armPosition) < Math.abs(tmp-armPosition)){
                    tmp = req.getPlaceOnDrive();
                }
            }

            if(armPosition-tmp < 0 ){
                if (armDirection != 1){
                    armMoves +=1;
                }
                armDirection = 1;
            }
            else if(armPosition-tmp > 0 ){
                if (armDirection != -1){
                    armMoves +=1;
                }
                armDirection = -1;
            }
            else{
                armDirection = 0;
            }
        }
        else{
            armDirection = 0;
        }
    }

    //update wszystkich procesów które znajdują się na ramieniu gdy dobiliśmy do włąsciwego kaska
    public void updateArmAndRequest(){
        if(requestsQueue.size()!=0 && priorityRequest == null){
            for(int y = requestsQueue.size()-1; y >= 0; y--){
                if(requestsQueue.get(y).getPlaceOnDrive() == armPosition){
                    doneRequests.add(requestsQueue.get(y));
                    requestsQueue.remove(y);         
                }
            }
        }
        else if(priorityRequest != null){
            if(armPosition == priorityRequest.getPlaceOnDrive()){
                doneRequests.add(priorityRequest);
                requestsQueue.remove(priorityRequest);
                priorityRequest = null;
                realTimeDone += 1;
                
                for(int y = requestsQueue.size()-1; y >= 0; y--){
                    if(requestsQueue.get(y).getPlaceOnDrive() == armPosition && requestsQueue.get(y).getRealTimeRequest()){
                        doneRequests.add(requestsQueue.get(y));
                        realTimeDone += 1;
                        requestsQueue.remove(y);         
                    }
                }

                for(Request req: requestsQueue){
                    if(req.isRealTimeRequest()){
                        if(priorityRequest == null){
                            priorityRequest = req;
                        }
                        else if(req.getDeadLine() < priorityRequest.getDeadLine()){
                            priorityRequest = req; 
                        }
                    }
                }

            }   
        }
    }

    //odjecie czasu od real time procesów i usuniecie tych co maja zero
    public void updateRealTimeRequestsInQueue(){
        if(priorityRequest != null && priorityRequest.getDeadLine() == 0){
            priorityRequest = null;
        }
        for(int i = requestsQueue.size()-1; i >= 0; i--){
            if(requestsQueue.get(i).isRealTimeRequest()){
                
                if(requestsQueue.get(i).getDeadLine() == 0){
                    requestsQueue.get(i).setRealTimeDoneOnTime(false);
                    doneRequests.add(requestsQueue.get(i));
                    requestsQueue.remove(i);
                }
                else{
                    requestsQueue.get(i).decreaseRealTime();
                }
            }
        }
        if(priorityRequest == null){
            for(Request req: requestsQueue){
                if(req.isRealTimeRequest()){
                    if(priorityRequest == null){
                        priorityRequest = req;
                    }
                    else if(req.getDeadLine() < priorityRequest.getDeadLine()){
                        priorityRequest = req; 
                    }
                }
            }  
        }
    }
}


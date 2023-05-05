package so_laby_part2;


public class Request{

    private int placeOnDrive;
    private int arrivalTime;

    private boolean realTimeRequest;
    private boolean realTimeDoneOnTime;
    private int deadLine;

    public Request(int placeOnDrive, int arrivalTime, boolean realTimeRequest){
        this.placeOnDrive = placeOnDrive;
        this.arrivalTime = arrivalTime;
        this.realTimeRequest = realTimeRequest;
        realTimeDoneOnTime = false;
    }
    
    public Request(int placeOnDrive, int arrivalTime, boolean realTimeRequest, int deadLine){
        this.placeOnDrive = placeOnDrive;
        this.arrivalTime = arrivalTime;
        this.realTimeRequest = realTimeRequest;
        this.deadLine = deadLine;
        realTimeDoneOnTime = false;
    }

    public void decreaseRealTime(){
        deadLine -=1; 
    }

    public boolean getRealTimeDoneOnTime() {
        return this.realTimeDoneOnTime;
    }

    public void setRealTimeDoneOnTime(boolean realTimeDoOnTime) {
        this.realTimeDoneOnTime = realTimeDoOnTime;
    }


    public int getPlaceOnDrive() {
        return this.placeOnDrive;
    }

    public void setPlaceOnDrive(int placeOnDrive) {
        this.placeOnDrive = placeOnDrive;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDeadLine() {
        return this.deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isRealTimeRequest() {
        return this.realTimeRequest;
    }

    public boolean getRealTimeRequest() {
        return this.realTimeRequest;
    }

    public void setRealTimeRequest(boolean realTimeRequest) {
        this.realTimeRequest = realTimeRequest;
    }


    public boolean isRealTimeDoneOnTime() {
        return this.realTimeDoneOnTime;
    }
    

    @Override
    public String toString() {
        return "{" +
            " placeOnDrive='" + getPlaceOnDrive() + "'" +
            ", arrivalTime='" + getArrivalTime() + "'" +
            ", realTimeRequest='" + isRealTimeRequest() + "'" +
            ", realTimeDoneOnTime='" + isRealTimeDoneOnTime() + "'" +
            ", deadLine='" + getDeadLine() + "'" +
            "}";
    }
    

}


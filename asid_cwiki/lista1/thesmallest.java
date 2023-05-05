package asid_cwiki.lista1;

public class thesmallest {

    public static int getSecondSmallest(int[] list){
        if(list.length <= 1){
            System.out.println("nie ma takiej wartości");
        }
        int thesmallest = list[0];
        int second = list[1];

        for(int i: list){
            if(i < thesmallest){
                second = thesmallest;    
                thesmallest = i;
            }
            else if(i > thesmallest && i < second){
                second = i;
            }
        }
        return second;
    }

    public static void main(String[] args){
        int[] l = new int[]{1,2,100,1,0,100,23,123,1235,345,-1};
        System.out.println(getSecondSmallest(l));
        
    }
    
}

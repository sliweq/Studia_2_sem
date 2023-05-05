import java.lang.Math;

public class tak {

    public static void main(String[] args){
        int x = 0;
        int y = 0;
        int z  = 0;
        for(int i = 0; i <1000; i++){
            double tmp = Math.random();
            if(tmp*10> 9.9){
                x += 1;
            } else if(tmp*10<2.5){
                y+=1;
            }
            else {
                z +=1;
            }         
        }
    
        System.out.println(x+ " " + y+ " " + z);

        y = 0;
        z  = 0;
        for(int i = 0; i <1000; i++){
            double tmp = Math.random();
            if(tmp*10<0.5){
                y+=1;
            }
            else {
                z +=1;
            }
            
        }
        System.out.println(y+ " " + z);


    }
    
}

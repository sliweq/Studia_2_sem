package asid_cwiki.lista1;
import java.util.ArrayList;

public class permutation {

    public void perm3(){
        int[] perm = new int[]{1,2,3};
        ArrayList<Integer> copy = new ArrayList<>();
        copy.add(1);
        copy.add(2);
        copy.add(3);

        for(int x = 0; x < 3; x ++){
            perm[0] = copy.get(x);
            pokaz(perm);
            perm[1] = copy.get(2);
            perm[2] = copy.get(1);
            pokaz(perm);
            int tmp = copy.indexOf(x+1);
            copy.remove(tmp);
            copy.add(0,tmp);
        }
    
    }

    public void pokaz(int[] x ){
        for(int y = 0 ;y < x.length;y++){
            System.out.print(x[y]+ " ");
        }
        System.out.println();
    }


    public static void main(String[] args){
        permutation p = new permutation();
        p.perm3();
    }
    
}

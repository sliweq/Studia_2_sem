package Lista8;

public class Wersja2 {

    public static void execute(Integer[] odstep, int[] tablica){
        int tmp = odstep.length-1;

        while(tmp > 0){
            
            // //insert sort ale zrobiony pod algorytm shella
            for(int x = odstep[tmp]; x < tablica.length; x+=1){

                int tmpNumber = tablica[x];
                int tmpIndex = x-odstep[tmp];

                    while(tmpIndex >= 0 && tablica[tmpIndex] > tmpNumber){
                        tablica[tmpIndex +odstep[tmp]] = tablica[tmpIndex];
                        tmpIndex -= odstep[tmp];
                    }

                tablica[tmpIndex+odstep[tmp]] = tmpNumber;
            }


            tmp -=1;
        }

        int iloscLiczb = tablica.length;
        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(tablica[x]> tablica[x+1]){
                    zamien(tablica, x, x+1);
                }
            }
        }
    }

    private static void zamien(int[] tablica ,int index1, int index2){
        int tmp = tablica[index1];
        tablica[index1] = tablica[index2];
        tablica[index2] = tmp;
    }

}    



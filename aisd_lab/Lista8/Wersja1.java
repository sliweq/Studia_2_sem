package Lista8;

public class Wersja1 {
    public static void execute(Integer[] odstep, int[] tablica){

        int tmp = odstep.length-1;

        while(tmp > 0){

            //insert sort ale zrobiony pod algorytm shella
            for(int x = odstep[tmp]; x < tablica.length ; x+=1){

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
        // sort normalny tzn insert juz po jednym elemencie

        for(int x = 1; x < tablica.length; x++){
            int tmpNumber = tablica[x];
            int tmpIndex = x-1;

            while(tmpIndex >= 0 && tablica[tmpIndex] > tmpNumber){
                tablica[tmpIndex +1] = tablica[tmpIndex];
                tmpIndex -= 1;
            }
            tablica[tmpIndex+1] = tmpNumber;
        }  
    }
    
}

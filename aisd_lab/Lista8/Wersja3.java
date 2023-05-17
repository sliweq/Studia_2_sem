package Lista8;

public class Wersja3 {

    public static void execute(Integer[] odstep, int[] tablica){
        int tmp = odstep.length-1;

        while(tmp > 0){
            int startindex = 0;
            //tu sie zaczyna bubble/shell sort
            while(startindex<odstep[tmp] && startindex+ odstep[tmp] < tablica.length ){
                // iteracje to jest po prostu ilosc elementow po ktorych mamy iterowac
                int iteracje = (int)Math.ceil(((tablica.length-startindex)*1.0)/odstep[tmp]);

                //tu jest powiedzmy zwykły bubble sort
                
                for(int x = 0; x< iteracje-1 ; x++){
                    for(int i = startindex; i+odstep[tmp] < (tablica.length-(x*odstep[tmp])) ; i+= odstep[tmp]){
                        if(tablica[i] > tablica[i+odstep[tmp]]){
                            zamien(tablica, i, i+odstep[tmp]);
                        }
                    }
                }
                startindex +=1;
            }

            tmp -=1;
        }

        //insert sort dla odtepu równego jeden
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


    private static void zamien(int[] tablica ,int index1, int index2){
        int tmp = tablica[index1];
        tablica[index1] = tablica[index2];
        tablica[index2] = tmp;
    }
}

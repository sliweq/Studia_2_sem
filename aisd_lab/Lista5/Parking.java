package Lista5;

public class Parking {


    ListStack<Integer> parking;

    public Parking(){
        parking = new ListStack<>();
    }

    public void dodajNoweAuto() {
        try{
            
            parking.push((Integer)(parking.size()+1));
            System.out.println("Prosze o wjazd nowego auta");
        }
        catch(FullStackException ex){
            System.err.println("Blad dodaj nowe auto");
        }
    }
    public Integer dodajAuto(Integer auto){
        try{
            parking.push(auto);
            return auto;
        }
        catch(FullStackException ex){
            System.err.println("Blad dodaj auto");
        }
        return auto;
    }

    public void wyjazdAuta(int nrAuta) throws FullStackException, EmptyStackException{
        ListStack<Integer> wyjazdy = new ListStack<>();
        int ilsocAut = parking.size();
        for(int x= 0; x < (ilsocAut-nrAuta);x++){
            Integer auto = parking.pop();
            System.out.println("Proszę o wyjazd auta nr" + auto);
            wyjazdy.push(auto);
            // 1 2 3 4 5 6 7 8    wyjazd 3
        }
        parking.pop();
        while(!wyjazdy.isEmpty()){
            System.out.println("Proszę o wjazd auta nr: " +  dodajAuto(wyjazdy.pop()));
        }

    }

}

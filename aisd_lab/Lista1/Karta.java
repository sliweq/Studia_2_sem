package Lista1;

public class Karta {
    private int Wartosc; //1(as),2,3...11 (jopek), 12(dama), 13(król)
    private int Kolor; //0-3

    public Karta(int war, int kol){
        Wartosc = war;
        Kolor = kol;
    }

    public int getKolor(){return Kolor;}
    public int getWartosc(){return Wartosc;}

}

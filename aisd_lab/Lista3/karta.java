package Lista3;


public class karta {
    private int wartosc;
    private int kolor;
    private boolean znacznik;

    public karta(){
        wartosc = 1;
        kolor = 0;
        znacznik = true;
    }

    public karta(int wartosc, int kolor, boolean znacznik){
        this.wartosc = wartosc;
        this.kolor = kolor;
        this.znacznik = znacznik;
    }

    @Override
    public String toString() {
        return "(" +
                "wartosc=" + wartosc +
                ", kolor=" + kolor +
                ", znacznik=" + znacznik +
                ')';
    }
    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getKolor() {
        return kolor;
    }

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }

    public boolean isZnacznik() {
        return znacznik;
    }

    public void setZnacznik(boolean znacznik) {
        this.znacznik = znacznik;
    }
}

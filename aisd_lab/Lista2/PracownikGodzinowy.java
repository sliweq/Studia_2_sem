package Lista2;

public class PracownikGodzinowy extends Pracownik {
    private double stawka;
    private int liczba_godzin;

    public PracownikGodzinowy(){
        super();
    }

    public PracownikGodzinowy(String nazwisko, String imie, long pesel, String stanowiko, int staz, int liczba_godzin, double stawka){
        super(nazwisko,imie, pesel, stanowiko, staz);
        this.stawka = stawka;
        this.liczba_godzin = liczba_godzin;
    }

    @Override
    public double pensja() {
        return stawka*liczba_godzin;
    }

    public double getStawka() {
        return this.stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    public int getLiczba_godzin() {
        return this.liczba_godzin;
    }

    public void setLiczba_godzin(int liczba_godzin) {
        this.liczba_godzin = liczba_godzin;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-9s%4d %-8s%5.2f"," Godziny: ",getLiczba_godzin(),"Stawka: ",getStawka());
    }

    

    
}

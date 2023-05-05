package Lista2;

public class PracownikEtatowy extends Pracownik {
    private double etat;
    private double stawka;

    public PracownikEtatowy(){
        super();
        stawka = 2800.50;
        etat = 0.75;
    }
    public PracownikEtatowy(String nazwisko, String imie, long pesel, String stanowiko, int staz, double stawka, double etat){
        super(nazwisko,imie, pesel, stanowiko, staz);
        this.stawka = stawka;
        this.etat = etat;
    }


    @Override
    public String toString() {
        return super.toString()+ String.format("%-7s%1.2f %-8s%5.2f"," Etat: ",getEtat(),"Stawka: ",getStawka());
    }
    
    @Override
    public double pensja() {
        return etat*stawka; //pensja*etat chyba nie o to chodziło 
    }

    
    public double getEtat() {
        return this.etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }

    public double getStawka() {
        return this.stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }
    

}

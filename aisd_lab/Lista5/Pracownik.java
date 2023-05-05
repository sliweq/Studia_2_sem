package Lista5;

import java.io.Serializable;

public class Pracownik implements Serializable {
    private long pesel;
    private String imieNazwisko;
    private String dataUrodzenia;
    private String stanowisko;
    private int pensja;
    private int staz;
    private double premia;

    public Pracownik(){
        pesel = 12345678910l;
        imieNazwisko = "Jan Kowala";
        dataUrodzenia = "10.12.1990";
        stanowisko = "Kierownik";
        pensja = 4500;
        staz = 10;
        premia = 0.1*4500;
    }
    public Pracownik(long pesel, String imieNazwisko,String dataUrodzenia,String stanowisko,int pensja,int staz){
        this.pesel = pesel;
        this.imieNazwisko = imieNazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.stanowisko = stanowisko;
        this.pensja = pensja;
        this.staz = staz;

        if(pensja <= 0){
            this.pensja = 4000;
        }

        if(staz < 10){
            premia = 0;
        } else if(staz < 20){
            premia = 0.1*pensja;
        } else {
            premia = 0.2*pensja;
        }
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;

        if(staz < 10){
            premia = 0;
        } else if(staz < 20){
            premia = 0.1*pensja;
        } else {
            premia = 0.2*pensja;
        }
    }

    public int getStaz() {
        return staz;
    }

    public void setStaz(int staz) {
        this.staz = staz;

        if(staz < 10){
            premia = 0;
        } else if(staz < 20){
            premia = 0.1*pensja;
        } else {
            premia = 0.2*pensja;
        }
    }

    public double getPremia() {
        return premia;
    }

    public void setPremia(double premia) {
        this.premia = premia;

    }

    public String toString(){
        return "Pracownik: pesel: " + pesel + " | imie i nazwisko: " + imieNazwisko+ " | data urodzenia: " + dataUrodzenia
                + " | stanowisko: " + stanowisko + " | pensja: " + pensja + " | staz: " + staz + "| premia: " + premia;
    }
}

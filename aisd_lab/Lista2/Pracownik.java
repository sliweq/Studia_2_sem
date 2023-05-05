package Lista2;
import java.io.Serializable;

abstract public class Pracownik implements Serializable {
    private String imie; 
    private long pesel;
    private String stanowisko;
    private int staz;  
    private String nazwisko;

    public Pracownik(){
        nazwisko = "Kowalski";
        imie = "Jan";
        pesel = 11111111111L;
        stanowisko = "tak";
        staz = 1;
    }
    public Pracownik(String nazwisko, String imie, long pesel, String stanowiko, int staz){
        this.imie = imie;
        this.pesel = pesel;
        this.stanowisko = stanowiko;
        this.staz = staz;
        this.nazwisko = nazwisko;
    }

    public void wyswietl(){
        System.out.printf("%-2s%-20s%-2s%-20s %-2s%-13d %-2s%-20s %-2s%3d %-2s%10.2f %2s %n","|",getNazwisko(),"|",getImie(),"|",getPesel(),"|",getStanowisko(),"|",getStaz(),"|",pensja(),"|");
    }


    public String toString() {
        return String.format("%-6s%-15s%-10s%-15s%-7s%13d %-12s%-15s %-6s%3d %-8s%10.2f","Imie: ",getImie(),"Nazwisko: ",
        getNazwisko(), "Pesel: ",getPesel() ,"Stanowisko: ", getStanowisko(), "Staz: ", getStaz(), "Pensja: ", pensja());
    }

    abstract double pensja();
    
    public String getNazwisko(){
        return nazwisko;
    }
    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }
    public String getImie() {
        return this.imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public long getPesel() {
        return this.pesel;
    }
    public void setPesel(long pesel) {
        this.pesel = pesel;
    }
    public String getStanowisko() {
        return this.stanowisko;
    }
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
    public int getStaz() {
        return this.staz;
    }
    public void setStaz(int staz) {
        this.staz = staz;
    }
    
}

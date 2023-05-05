package so_laby;


public class proces implements Cloneable{

    private int czas_trwania;
    private int czas_oczekiwania;
    private int czas_pojwania;
    private int postep;
        
    public proces(int trwanie, int pojawienie){
        czas_pojwania = pojawienie;
        czas_trwania = trwanie;
        czas_oczekiwania = 0;
        postep = 0;
        
    }

    public void jeden_do_oczekiwania(){
        czas_oczekiwania +=1;
    }
    
    public int getPostep() {
        return this.postep;
    }

    public void setPostep(int postep){
        this.postep += postep;
    }

    public int getCzas_trwania() {
        return this.czas_trwania;
    }

    public int getCzas_oczekiwania() {
        return this.czas_oczekiwania;
    }

    public void setCzas_oczekiwania(int czas_oczekiwania) {
        this.czas_oczekiwania = czas_oczekiwania;
    }

    public int getCzas_pojwania() {
        return this.czas_pojwania;
    }
    
    @Override
    public String toString() {
        return "{" +
            " czas_trwania='" + getCzas_trwania() + "'" +
            ", czas_oczekiwania='" + getCzas_oczekiwania() + "'" +
            ", czas_pojwania='" + getCzas_pojwania() + "'" +
            ", postep='" + getPostep() + "'" +
            "}";
    }

    public proces clone() {
        try {
            proces copy = (proces) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }    

}

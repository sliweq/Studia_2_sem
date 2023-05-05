package Lista6;

public class Klient {
    private String imie;
    private ListQueue<Integer> zamowienia;

    public Klient(){
        imie = "";
        zamowienia = new ListQueue<>();
    }
    public Klient(String name){
        this.imie = name;
        zamowienia = new ListQueue<>();
    }

    public String getImie(){
        return imie;
    }

    public void dodajZamowienie(Integer zam) throws FullQueueException{
        zamowienia.enqueue(zam);;

    }
    public Integer getZamowienie() throws EmptyQueueException{
        return zamowienia.dequeue();
    }
    public int getZamowieniaSize(){
        return zamowienia.size();
    }
    
}

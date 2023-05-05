package Lista6;

public class Firma {
    private ListQueue<Magazyn> magazyny= new ListQueue<>();

    public int dochodFirmy = 0;

    public void dodajDomyslneMagazyny() throws FullQueueException{
        Magazyn m1  = new Magazyn();
        m1.stworzDomyslnychKlientow();
        magazyny.enqueue(m1);   

        Magazyn m2 = new Magazyn();
        m2.stworzDomyslnychKlientowv2();
        magazyny.enqueue(m2);
    }

    public void zrealizujMagazyn() throws EmptyQueueException{
        if(!magazyny.isEmpty()){
            int tmpDochod = 0;
            Magazyn magazyn = magazyny.dequeue();
            while(magazyn.czyMaKlientow()){
                tmpDochod += magazyn.zrealizujZamowienie();
            }
            dochodFirmy += tmpDochod;
            System.out.println("Z tego magazynu przychod wynosi: " + tmpDochod);
        }
    }

    public void zrealizujWszystko() throws EmptyQueueException{
        while(!magazyny.isEmpty()){
            Magazyn magazyn = magazyny.dequeue();
            while(magazyn.czyMaKlientow()){
                dochodFirmy += magazyn.zrealizujZamowienie();
            }

        }
        System.out.println("Całkowity dochód wynosi: " + dochodFirmy);
    }

    public static void main(String[] args) throws FullQueueException, EmptyQueueException{
        Firma firma = new Firma();
        firma.dodajDomyslneMagazyny();
        firma.zrealizujMagazyn();
        firma.dodajDomyslneMagazyny();
        firma.zrealizujWszystko();
    } 
    
}

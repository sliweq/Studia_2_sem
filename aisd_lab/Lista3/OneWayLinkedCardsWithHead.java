package Lista3;


import java.util.AbstractList;
import java.util.ListIterator;

public class OneWayLinkedCardsWithHead<karta> extends AbstractList<karta> {
    private class Element{
        private karta wartosc;
        private Element nastepny;

        public karta getWartosc(){
            return wartosc;
        }
        public void setWartosc(karta wartosc){
            this.wartosc = wartosc;
        }
        public Element getNastepny(){
            return nastepny;
        }
        public void setNastepny(Element nastepny){
            this.nastepny = nastepny;
        }
        Element(karta card){
            wartosc = card;
        }
    }

    Element glowa = null;

    public OneWayLinkedCardsWithHead(){}

    @Override
    public boolean add(karta card){
        Element nowyElem = new Element(card);
        if(glowa==null){
            glowa=nowyElem;
            return true;
        }
        Element ogon = glowa;
        while(ogon.getNastepny()!=null){
            ogon = ogon.getNastepny();
        }
        ogon.setNastepny(nowyElem);
        return true;
    }
    @Override
    public void add(int index, karta card){
        if (index >=0){
            Element nowyElem = new Element(card);
            if(index == 0){
                nowyElem.setNastepny(glowa);
                glowa = nowyElem;
            }else{
                Element obecnyElem = getElement(index-1);
                if(obecnyElem != null){
                    nowyElem.setNastepny(obecnyElem.getNastepny());
                    obecnyElem.setNastepny(nowyElem);
                }
            }
        }
    }


    @Override
    public karta remove(int index){
        if(glowa == null){
            return null;
        }
        if(index ==0){
            karta retValue = glowa.getWartosc();
            glowa = glowa.getNastepny();
            return retValue;
        }
        Element actElem = getElement(index-1);
        if(actElem == null || actElem.getNastepny() == null){
            return null;
        }
        karta retValue = actElem.getNastepny().getWartosc();
        actElem.setNastepny(actElem.getNastepny().getNastepny());
        return retValue;
    }

   @Override
   public boolean remove(Object value) {
       value = (karta) value;
       if(glowa==null)
           return false;
       if(glowa.getWartosc().equals(value)){
           glowa=glowa.getNastepny();
           return true;
       }

       Element actElem=glowa;
       while(actElem.getNastepny()!=null && !actElem.getNastepny().getWartosc().equals(value))
           actElem=actElem.getNastepny();

       if(actElem.getNastepny()==null)
           return false;
       actElem.setNastepny(actElem.getNastepny().getNastepny());
       return true;
   }



    @Override
    public karta get(int index) {
        Element obencyElement = getElement(index);
        return obencyElement==null?null:obencyElement.getWartosc();
    }
    private Element getElement(int index) {
        Element obencyElement = glowa;
        while(index>0 && obencyElement!= null){
            index-=1;
            obencyElement = obencyElement.getNastepny();
        }
        return obencyElement;
    }
    @Override
    public int size() {
        int pos = 0;
        Element obecnyElement = glowa;
        while(obecnyElement != null){
            pos += 1;
            obecnyElement = obecnyElement.getNastepny();
        }
        return pos;
    }


    private class KartIterator implements ListIterator<karta> {
        Element actElem;
        public KartIterator() {
            actElem=glowa;
        }
        @Override
        public boolean hasNext() {
            return actElem!=null;
        }
        @Override
        public karta next() {
            karta value=actElem.getWartosc();
            actElem=actElem.getNastepny();
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public karta previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(karta karta) {

        }

        @Override
        public void add(karta karta) {

        }
    }

    @Override
    public ListIterator<karta> listIterator() {
        return new KartIterator();
    }
}

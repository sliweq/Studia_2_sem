package asid_cwiki.lista2;

import java.util.Iterator;

public class NewIter<T> implements Iterator {

    private Iterator<T> Olditer;
    private int k; 
    private T next = null ;
    private int index = 0;
    private boolean hNext;

    public NewIter(Iterator<T> it, int k){
            Olditer = it;
            this.k = k;
            while(Olditer.hasNext()){
                T elem = Olditer.next();
                index+=1;
                if (index%k == 0){
                    next = elem;
                }
            }
            if (next == null){
                hNext = false;
            }
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Object next() {
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }
    
}

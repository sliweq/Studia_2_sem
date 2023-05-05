package Lista5;

import java.io.Serializable;
import java.util.AbstractList;

public class Baza<E> extends AbstractList<E> implements Serializable {

    private class Element implements Serializable{
        private E value;
        private Element next;
        private Element prev;
        Element(E data){
            this.value = data;
        }
        public E getValue(){
            return value;
        }
        public void setValue(E value){
            this.value = value;
        }
        public Element getNext() {
            return next;
        }
        public void setNext(Element next) {
            this.next = next;
        }
        public Element getPrev() {
            return prev;
        }
        public void setPrev(Element prev) {
            this.prev = prev;
        }

        public void remove(){
            next.setPrev(this.getPrev());
            prev.setNext(this.getNext());
        }

        public void insertBefore(Element elem){
            elem.setPrev(prev);
            elem.setNext(this);
            prev.setNext(elem);
            prev = elem;
        }
        public void insertAfter(Element elem){
            elem.setPrev(this);
            elem.setNext(next);
            next.setPrev(elem);
            next = elem;
        }

    }

    Element sentinel = null;

    public Baza(){
        sentinel = new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    private Element getElement(int index){
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }
        Element elem = sentinel.getNext();
        int counter = 0;
        while(counter < index && elem!= sentinel){
            counter+=1;
            elem = elem.getNext();
        }
        if(elem == sentinel){
            throw new IndexOutOfBoundsException();
        }
        return elem;
    }
    private Element getElement(E value){
        Element elem = sentinel.getNext();
        int counter = 0;
        while(!value.equals(elem.getValue()) && elem!= sentinel){
            counter+=1;
            elem = elem.getNext();
        }
        if(elem == sentinel){
            return null;
        }
        return elem;
    }

    public boolean isEmpty(){
        return sentinel.getNext() == sentinel;
    }

    public void clear(){
        sentinel.setPrev(sentinel);
        sentinel.setNext(sentinel);
    }

    public boolean contain(E value){
        return indexO(value) != -1;
    }

    public E get(int index){
        Element elem = getElement(index);
        return elem.getValue();
    }
    public E set(int index, E value){
        Element elem = getElement(index);
        E retValue = elem.getValue();
        elem.setValue(value);
        return retValue;
    }


    public boolean add(E value){
        Element newElem = new Element(value);
        sentinel.insertBefore(newElem);

        return true;
    }

    public void add(int index, E value){
        Element newElem = new Element(value);
        if(index == 0){
            sentinel.insertAfter(newElem);
        }else {
            Element elem = getElement(index -1);
            elem.insertAfter(newElem);
        }
        //return true;
    }
    
    public int indexO(E value){
        Element elem = sentinel.getNext();
        int counter = 0;
        while(elem != sentinel && !elem.getValue().equals(value)){
            counter+=1;
            elem = elem.getNext();
        }
        if(elem == sentinel){
            return -1;
        }
        return counter;
    }

    public E remove(int index){
        Element elem = getElement(index);
        elem.remove();
        return elem.getValue();
    }

    public boolean remov(E value){
        Element elem = getElement(value);
        if(elem == null){
            return false;
        }
        elem.remove();
        return true;
    }

    public int size(){
        Element elem = sentinel.getNext();
        int counter = 0;
        while(elem != sentinel){
            counter +=1;
            elem = elem.getNext();
        }
        return counter;
    }

}



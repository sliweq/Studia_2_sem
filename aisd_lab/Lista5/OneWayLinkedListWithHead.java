package Lista5;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;

public class OneWayLinkedListWithHead<E> extends AbstractList<E> {
	private class Element{

		private E value;
		private Element next;

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Element getNext() {
			return next;
		}

		public void setNext(Element next) {
			this.next = next;
		}



		Element(E data){
			this.value=data;
		}
	}

	Element head=null;

	public OneWayLinkedListWithHead(){
	}

	public boolean isEmpty(){
		return head==null;
	}
	
	@Override
	public void clear() {
		head=null;
	}
	
	@Override
	public int size() {
		int pos=0;
		Element actElem=head;
		while(actElem!=null)
		{
			pos++;
			actElem=actElem.getNext();
		}
		return pos;
	}

	/** zwraca referencj� na Element, wewn�trzn� klas� */
	private Element getElement(int index){
		Element actElem=head;
		while(index>0 && actElem!=null){
			index--;
			actElem=actElem.getNext();
		}
		return actElem;		
	}

	@Override
	public boolean add(E e) {
		Element newElem=new Element(e);
		if(head==null){
			head=newElem;
			return true;
		}
		Element tail=head;
		while(tail.getNext()!=null)
			tail=tail.getNext();
		tail.setNext(newElem);
		return true;
	}

	public void add(int index, E data) {
		Element newElem=new Element(data);
		if(index==0)
		{
			newElem.setNext(head);
			head=newElem;
		}
		else if(index> 0){
			Element actElem=getElement(index-1);
			if(actElem!=null){
				newElem.setNext(actElem.getNext());
				actElem.setNext(newElem);
			}
		}

	}

	public int indexOf(Object data) {
		int pos=0;
		Element actElem=head;
		while(actElem!=null)
		{
			if(actElem.getValue()==data)
				return pos;
			pos++;
			actElem=actElem.getNext();
		}
		return -1;
	}

	public boolean contains(Object data) {
		return indexOf(data)>=0;
	}

	@Override
	public E get(int index) {
		Element actElem=getElement(index);
		return actElem==null?null:actElem.getValue();
	}

	@Override
	public E set(int index, E data) {
		Element actElem=getElement(index);
		if(actElem==null)
			return null;
		E elemData=actElem.getValue();
		actElem.setValue(data);
		return elemData;
	}

	@Override
	public E remove(int index) {
		if(head==null)
			return null;
		if(index==0){
			E retValue=head.getValue();
			head=head.getNext();
			return retValue;
		}
		Element actElem=getElement(index-1);
		if(actElem==null || actElem.getNext()==null)
			return null;
		E retValue=actElem.getNext().getValue();
		actElem.setNext(actElem.getNext().getNext());
		return retValue;
	}

	public boolean remove(Object value) {
		if(head==null)
			return false;
		if(head.getValue().equals(value)){
			head=head.getNext();
			return true;
		}

		Element actElem=head;
		while(actElem.getNext()!=null && !actElem.getNext().getValue().equals(value))
			actElem=actElem.getNext();

		if(actElem.getNext()==null)
			return false;
		actElem.setNext(actElem.getNext().getNext());
		return true;
	}


	private class InnerIterator implements Iterator<E>{
		Element actElem;
		public InnerIterator() {
			actElem=head;
		}
		@Override
		public boolean hasNext() {
			return actElem!=null;
		}
		@Override
		public E next() {
			E value=actElem.getValue();
			actElem=actElem.getNext();
			return value;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}
}

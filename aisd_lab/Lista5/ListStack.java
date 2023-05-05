package Lista5;

import java.util.List;

public class ListStack<E> implements IStack<E> {
	List<E> _list;
	public ListStack(){
		_list = (List<E>) new OneWayLinkedListWithHead<E>();
	}
	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}
	@Override
	public boolean isFull() {
		return false;
	}
	@Override
	public E pop() throws EmptyStackException {
		E value=_list.remove(0);
		if(value==null) throw new EmptyStackException(); 
		return value;
	}
	@Override
	public void push(E elem) throws FullStackException {
		_list.add(0,elem);	
	}
	@Override
	public int size() {
		return _list.size();
	}
	@Override
	public E top() throws EmptyStackException {
		E value=_list.get(0);
		if(value==null) throw new EmptyStackException(); 
		return value;
	}
	
}

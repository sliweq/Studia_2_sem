package Lista6;


public class ListQueue<E> implements IQueue<E>{
	OneWayLinkedListWithHead<E> _list;
	public ListQueue() {
		_list=new OneWayLinkedListWithHead<E>();
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
	public E dequeue() throws EmptyQueueException {
		E value=_list.remove(0);
		if(value==null) throw new EmptyQueueException();
		return value;
	}
	@Override
	public void enqueue(E elem) throws FullQueueException {
		_list.add(elem);
	}
	@Override
	public int size() {
		return _list.size();
	}
	@Override
	public E first() throws EmptyQueueException {
		E value=_list.get(0);
		if(value==null) throw new EmptyQueueException();
		return value;
	}
}

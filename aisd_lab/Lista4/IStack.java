package Lista4;

public interface IStack<T> {
    boolean isEmpty();
    boolean isFull();
    void push(T element) throws FullStackException;
    T pop() throws EmptyStackException;
    int size();
    T top() throws EmptyStackException;
    
}

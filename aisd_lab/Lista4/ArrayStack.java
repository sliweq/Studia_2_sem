package Lista4;

public class ArrayStack<T> implements IStack<T>{

    private static int wielkosc = 32;
    T array[];
    int topIndex;

    public ArrayStack(int wielkosc){
        array = (T[])(new Object[wielkosc]);
        this.wielkosc = wielkosc;

    }

    public ArrayStack(){
        this(wielkosc);
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topIndex==array.length;
    }

    @Override
    public void push(T element ) throws FullStackException{
        if(isFull()){
            throw new FullStackException();
        }
        array[topIndex++] = element;
    }

    @Override   
    public T pop() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }    
        return array[--topIndex];  
    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return array[topIndex];
    }

    public T[] getArray(){
        return array;
    }


    public void reverse() throws FullStackException, EmptyStackException{

        T tmpCharacter = null;
        ArrayStack<T> finalStack = new ArrayStack<>(wielkosc);

        if(!this.isEmpty()){
            int iloscPowtorzen =  this.size()-1;

            for(int x =0; x< iloscPowtorzen ;x++){
                tmpCharacter = this.pop();
                finalStack = this.reverseReturn();
                for(int y = 0; y < x; y++){
                    this.push(finalStack.pop());
                }
                this.push(tmpCharacter);
                while(!finalStack.isEmpty()){
                    this.push(finalStack.pop());
                }
                
            }
        }


        // ArrayStack<T> tmpStack = new ArrayStack<>(wielkosc);

        // while(!isEmpty()){
        //     tmpStack.push(this.pop());
        // }
        // array = (T[]) tmpStack.getArray();
        // topIndex = tmpStack.size();
    }

    public ArrayStack<T> reverseReturn() throws FullStackException, EmptyStackException{

        ArrayStack<T> tmpStack = new ArrayStack<>(wielkosc);

        while(!isEmpty()){
            tmpStack.push(this.pop());
        }
        
        return tmpStack;
    }


    
}

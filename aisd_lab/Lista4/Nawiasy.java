package Lista4;

import java.util.Scanner;



public class Nawiasy {

    private ArrayStack<Character> stos = new ArrayStack<>();
    private String napisString;

    public boolean nawiasOtwierajacy(char ch){
        String chs = String.valueOf(ch);
        if(chs == "{" || chs == "[" || chs =="("){
            return true;
        }
        return false;
    }

    public boolean nawiasyZrownowazone() throws FullStackException, EmptyStackException{
        for(int x = 0; x < napisString.length(); x++){
            if(nawiasOtwierajacy(napisString.charAt(x))){
                if(stos.isFull()){
                    System.out.println("Stos nie jest wystarczająco wielki");
                    return false;
                }
                else{
                    stos.push(Character.valueOf(napisString.charAt(x)));
                }
            }
            else if(nawiasZamykajacy(napisString.charAt(x))){
                if(stos.isEmpty()){
                    return false;
                }
                else if(Math.abs((int) napisString.charAt(x) - (int) stos.top().charValue()) > 2){
                    return false;
                }
                else{
                    stos.pop();
                }
            }
        }
        return true;
    } 
    
    public boolean nawiasZamykajacy(char ch){

        String chs = String.valueOf(ch);
        if(chs == ")" || chs == "}" || chs =="]"){
            return true;
        }
        return false;
    }

    public void setNapis(String napis){
        this.napisString = napis;
    }

    public void reverseStos() throws FullStackException, EmptyStackException{
        ArrayStack<Character> as1 = new ArrayStack<>(napisString.length()); //stack pierwotny 
        ArrayStack<Character> asreverse = new ArrayStack<>(napisString.length()); //stack który odwracamy

        for(int x = 0;x < napisString.length(); x++ ){
            Character tmpCha = Character.valueOf(napisString.charAt(x));
            as1.push(tmpCha);
            asreverse.push(tmpCha);
        }
        System.out.print("Ciag nieodwrocony: ");
        while(!asreverse.isEmpty()){
            System.out.print(asreverse.pop());
        }

        System.out.println();
        as1.reverse();
        System.out.print("Ciag odwrocony: ");

        while(!as1.isEmpty()){
            System.out.print(as1.pop());
        }

        System.out.println();
    }

    public boolean isPalindrome() throws FullStackException, EmptyStackException{
        ArrayStack<Character> originalStack = new ArrayStack<>(napisString.length());
        ArrayStack<Character> reversedStack = new ArrayStack<>(napisString.length());

        for(int x = 0;x < napisString.length(); x++ ){
            Character tmpCha = Character.valueOf(napisString.charAt(x));
            originalStack.push(tmpCha);
            reversedStack.push(tmpCha);
        }

        reversedStack.reverse();
        
        while(!originalStack.isEmpty()){
            if(originalStack.pop() != reversedStack.pop()){
                return false;
            }
        }
        return true;
    }

    public void stos1NaStos2() throws FullStackException, EmptyStackException{
        ArrayStack<Character> originalStack = new ArrayStack<>(napisString.length()); //S1
        ArrayStack<Character> tmpStack ; //pomocniczy stack
        ArrayStack<Character> finalStack; //S2 
         
        for(int x = 0;x < napisString.length(); x++ ){
            Character tmpCha = Character.valueOf(napisString.charAt(x));
            originalStack.push(tmpCha);
        }
        tmpStack = originalStack.reverseReturn();
        finalStack = tmpStack.reverseReturn();

        System.out.println("--------odwrocony stos (i)--------");

        while(!finalStack.isEmpty()){
            System.out.print(finalStack.pop());
        }
        System.out.println();


    }

    public void stos1NaStos2version2() throws FullStackException, EmptyStackException{
        
        ArrayStack<Character> originalStack = new ArrayStack<>(napisString.length()); //S1
        Character tmpCharacter = null; //pomocnicza zmienna
        ArrayStack<Character> finalStack = new ArrayStack<>(napisString.length()); //S2 

        for(int x = 0;x < napisString.length(); x++ ){
            Character tmpCha = Character.valueOf(napisString.charAt(x));
            originalStack.push(tmpCha);
        }

        if(!originalStack.isEmpty()){
            int iloscPowtorzen =  originalStack.size()-1;

            for(int x =0; x< iloscPowtorzen ;x++){
                tmpCharacter = originalStack.pop();
                finalStack = originalStack.reverseReturn();
                for(int y = 0; y < x; y++){
                    originalStack.push(finalStack.pop());
                }
                originalStack.push(tmpCharacter);
                while(!finalStack.isEmpty()){
                    originalStack.push(finalStack.pop());
                }
                
            }
            finalStack = originalStack.reverseReturn();


            // int iloscPowtorzen =  originalStack.size()-1;
            // for(int x =0; x< iloscPowtorzen ;x++){
            //     int originalStackSize = originalStack.size()-1;
            //     for(int y =0; y< originalStackSize ;y++){
            //         finalStack.push(originalStack.pop());
            //     }
            //     tmpCharacter = originalStack.pop();
            //     int finalStackSize = finalStack.size()-x;
            //     for(int z = 0; z<finalStackSize;z++){
            //         originalStack.push(finalStack.pop());
            //     }
            //     finalStack.push(tmpCharacter);
            // }
            // finalStack.push(originalStack.pop());
        }
        System.out.println("--------odwrocony stos (ii)--------");

        while(!finalStack.isEmpty()){
            System.out.print(finalStack.pop());
        }
        System.out.println();
        
    }

    


    public static void printMenu(){
        System.out.println("Menu");
        System.out.println("1 - sprawdz czy wyrazenie jest poprawne");
        System.out.println("2 - pokaz odwrocony stos");
        System.out.println("3 - sprawdz czy jest palindromem");
        System.out.println("4 - przenies ze stosu S1 na S2 podpunkt i");
        System.out.println("5 - przenies ze stosu S1 na S2 podpunkt ii");

    }

    public static void main(String[] args) {
        String napis;
        Nawiasy n = new Nawiasy();
        int wybor = 1 ;
        Scanner scan = new Scanner(System.in);
        
        try{
            printMenu();
            System.out.println("podaj ciag znaków");
            napis = scan.nextLine();
            
            n.setNapis(napis);

            
            System.out.println("Co chcesz zrobić z ciagiem?");
            wybor = scan.nextInt();
            
            while(wybor > 0 && wybor < 6){
                switch(wybor){
                    case 1: System.out.println( "Czy ciąg jest zrownowazony: "+ n.nawiasyZrownowazone()); break;
                    case 2: n.reverseStos(); break;
                    case 3: System.out.println( "Czy ciąg jest palindromem: "+ n.isPalindrome()); break;
                    case 4: n.stos1NaStos2(); break;
                    case 5: n.stos1NaStos2version2(); break; 
                }

                wybor = scan.nextInt();
        
            }

        }catch(Exception ex){
            System.err.println("Input error");
        }

        
    }

}

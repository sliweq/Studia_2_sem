package Lista12;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        //Later, it was said the man came from the north, from Ropers Gate. He came
        //on foot, leading his laden horse by the bridle.

        //ala i ola.

        Huffman h = new Huffman("tekst.txt");
        h.wczytajTekst();
        h.zapisDopliku();
        
        System.out.println("----------------------------");
        System.out.println("Znaki i kodowania:");
        h.pokazKod();
        System.out.println("----------------------------");
        h.kodNaTekst(h.odczytajPlik("kod.txt"));
        System.out.println("\n----------------------------");
        
        //System.out.println("Huffman:");
        //h.pokazHuffmana();
    }
    
}

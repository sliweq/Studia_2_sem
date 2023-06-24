package Lista12;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        //Later, it was said the man came from the north, from Ropers Gate. He came
        //on foot, leading his laden horse by the bridle.

        //ala i ola.

        Huffman h = new Huffman("tekst.txt");
        h.wczytajTekst();
        String tekst = h.stworzHuffmana();
        System.out.println("----------------------------");
        System.out.println("Kod Tekstu:");
        System.out.println(tekst);
        System.out.println("----------------------------");
        System.out.println("Znaki i kodowania:");
        h.pokazKod();
        System.out.println("----------------------------");
        System.out.println("Tekst:");;
        h.kodNaTekst(tekst);
        System.out.println("\n----------------------------");
        System.out.println("Huffman:");
        h.pokazHuffmana();
    }
    
}

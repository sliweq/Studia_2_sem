package Lista12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import Lista12.Huffman.Node;


public class Huffman {
    private List<String> znaki;
    private List<Integer> wystapienia;

    private PriorityQueue<Node> queue;

    private String zakodowyanyTeskt;

    private List<String> tekst;
    private String plik;

    private Node root; 

    class Node{
        private int wartosc;
        private String znak;

        private String code = "";

        private Node lewy;
        private Node prawy;

        public Node(){}
        public void setLewy(Node node){
            lewy = node;
        }
        public void setPrawy(Node node){
            prawy = node;
        }
        public Node getLewy(){
            return lewy;
        }
        public Node getPrawy(){
            return prawy;
        }
        public void setDodajZnak(String znak, int wystapienia){
            this.znak = znak;
            this.wartosc = wystapienia;
        }
        public String getZnak(){
            return znak;
        }
        public int getWartosc(){
            return wartosc;
        }
        public void setWartosc(int wartosc){
            this.wartosc = wartosc;
        }
        public void setCode(String code){
            this.code = code;
        }
        public String getCode(){
            return code;
        }
    }

    public Huffman(String plik){
        this.plik = plik;
        tekst = new ArrayList<>();
        znaki = new ArrayList<>();
        wystapienia = new ArrayList<>();
    }

    public void wczytajTekst() throws IOException{
        File f = new File(plik);

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = br.readLine();
        while(line != null){

            String[] splitowanyTekst = line.split("");

            for(String x: splitowanyTekst){
                if(znaki.indexOf(x) == -1){
                    znaki.add(x);
                    wystapienia.add(1);
                }
                else{
                    int tmp = wystapienia.get(znaki.indexOf(x));
                    wystapienia.set(znaki.indexOf(x),tmp+1);
                }
                tekst.add(x);
            }
            line = br.readLine();
            if(line != null){
                if(znaki.indexOf("\n") == -1){
                    znaki.add("\n");
                    wystapienia.add(1);
                }
                else{
                    int tmp = wystapienia.get(znaki.indexOf("\n"));
                    wystapienia.set(znaki.indexOf("\n"),tmp+1);
                }
                tekst.add("\n");
            }
        }
    }

    public String stworzHuffmana(){
        queue = new PriorityQueue<>(new HuffmanComparator());
        for(int x = 0; x < znaki.size(); x++){
            Node tmpNode = new Node();
            tmpNode.setDodajZnak(znaki.get(x), wystapienia.get(x));
            queue.add(tmpNode);
        }

        while(queue.size() > 1){
            Node lewy = queue.poll();

            Node prawy = queue.poll();

            Node nowy = new Node();
            nowy.setLewy(lewy);
            nowy.setPrawy(prawy);
            nowy.setWartosc(lewy.getWartosc()+prawy.getWartosc());
            queue.add(nowy);
        }

        root = queue.poll();
        stworzKody();

        pokazKodTekstu();
        return zakodowyanyTeskt;
    }

    //drzewko skończone to trzeba zakodowac
    private void stworzKody(){
        if(root.getZnak() != null){
            root.setCode("0");
        }
        else{
            przypiszKod(root,"");
        }
    }

    private void przypiszKod(Node node, String code){
        node.setCode(code);

        if(node.getLewy() != null){
            przypiszKod(node.getLewy(), code+ "0");
        }
        if(node.getPrawy() != null){
            przypiszKod(node.getPrawy(), code+"1");
        }
    }

    //kod na tekst

    public void kodNaTekst(String tekst){
        List<String> lista = new ArrayList<>(Arrays.asList(tekst.split("")));
        Node tmpNode = root;
        while(!lista.isEmpty()){
            String tmp = lista.remove(0);
            if(tmp.equals("1")){
                tmpNode = tmpNode.getPrawy();
            }
            else{
                tmpNode = tmpNode.getLewy();
            }
            if(tmpNode.getZnak() != null){
                System.out.print(tmpNode.getZnak());
                tmpNode = root;
            }
        }
    }

    //zamiana tekstu na kod
    private String getCode(Node node,String znak){
        String lewe = null;
        String prawe = null;
        if(node.getZnak() == null){
            lewe = getCode(node.getLewy(), znak);
            prawe = getCode(node.getPrawy(), znak);
        }else if(node.getZnak().equals(znak)){
            return node.getCode();
        }
        else{
            if(node.getPrawy() != null){
                prawe = getCode(node.getPrawy(), znak);
            }
            if(node.getLewy() != null){
                lewe = getCode(node.getLewy(), znak);
            }
        }
        if(prawe != null){
            return prawe;
        }
        else{
            return lewe;
        }
    }

    private void pokazKodTekstu(){
        zakodowyanyTeskt = "";
        for(int x = 0; x < tekst.size(); x++){
            String code = getCode(root, tekst.get(x));
            zakodowyanyTeskt += code;
        }
    }

    //Funkcje które printuja
    public void pokazKod(){
        pokazKod(root);
    }

    private void pokazKod(Node node){
        if(node != null){
            if(node.getZnak() != null){
                System.out.println("'"+node.getZnak() + "'-" + node.getWartosc());
            }
            else{
                pokazKod(node.getLewy());
                pokazKod(node.getPrawy());
            }
        }
    }

    public void pokazHuffmana(){
        pokazHuffmana(root,0);
        System.out.println();
    }
    private void pokazHuffmana(Node node,int x){
        System.out.println();
        if(node.getZnak() != null){
            for(int i = 0; i < x; i++ ){
                System.out.print(" ");
            }
            System.out.print("|__ znak: '" + node.getZnak() + "' wartosc:(" + node.getWartosc() + ") kod:[" + node.getCode() + "]");
            if(node.getLewy() != null){
                pokazHuffmana(node.getLewy(), x+2);
            }

            if(node.getPrawy() != null){
                pokazHuffmana(node.getPrawy(), x+2);
            }
        }
        else{
                        for(int i = 0; i < x; i++ ){
                System.out.print(" ");
            }
            System.out.print("|__ " + " (" + node.getWartosc() + ")");
                        if(node.getLewy() != null){
                pokazHuffmana(node.getLewy(), x+2);
            }

            if(node.getPrawy() != null){
                pokazHuffmana(node.getPrawy(), x+2);
            }
        }
    }

}


class HuffmanComparator implements Comparator<Node>{
    public int compare(Node arg0, Node arg1){
        if(arg0.getZnak() != null){
            if(arg1.getZnak() != null){
                return arg0.getWartosc()-arg1.getWartosc();
            }
            return arg0.getWartosc();
        }
        if(arg1.getZnak() != null){
            return arg1.getWartosc();       
        }
        return arg0.getWartosc()-arg1.getWartosc();
    }
}


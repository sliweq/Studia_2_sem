package asid_cwiki.Lista5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class zad8{
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("bstjava.txt");
        BinarySeachTree bst = new BinarySeachTree();
        bst.insert(12);
        bst.insert(10);
        bst.insert(122);
        bst.insert(20);
        bst.insert(11);
        System.out.println("In order v1 ");
        bst.preOrder();

        PrintWriter write = new PrintWriter(file.getName());
        bst.writeToFile(write);

        BinarySeachTree bstv2 = new BinarySeachTree();
        Scanner scan = new Scanner(file);
        System.out.println("\nWczytywanie ");

        while(scan.hasNextLine()){
            String tmp =scan.nextLine();
            System.out.println(tmp);
            bstv2.insert(Integer.valueOf(tmp));

        }
        System.out.println("Po wczytaniu ");

        bstv2.preOrder();
    }
}
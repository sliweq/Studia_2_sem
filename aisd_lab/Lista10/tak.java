package Lista10;

import java.io.*; 
public class tak {
    private static BST bst;

    public static int comparator(String one, String two){
        if(one.equals(two)){
            return 0;
        }
        if(one.length() >= two.length()){
            for(int x = 0; x < two.length(); x++){
                if(one.charAt(x)>two.charAt(x)){
                    return 1;
                }
                else if(one.charAt(x)<two.charAt(x)){
                    return -1;
                }
            }
            return 1;
        }
        if(two.length() > one.length()){
            for(int x = 0; x < one.length(); x++){
                if(one.charAt(x)>two.charAt(x)){
                    return 1;
                }
                else if(one.charAt(x)<two.charAt(x)){
                    return -1;
                }
            }
            return -1;
        }
        return -1;
    }

    public static void wczytajPlik() throws Exception{
        File file = new File("tekst.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line =  br.readLine();

        bst = new BST();
        int linijka = 1;
        while(line != null){
            for(String x:line.split("[,. ?!-_]+")){
                bst.insert(x, linijka);
            }
            linijka +=1;
            line = br.readLine();
        }
        System.out.println("tak.wczytajPlik()");
    }   

    public static void main(String[] args) throws Exception {
        wczytajPlik();
        bst.inorder();
        bst.wszerz();
        System.out.println(bst);
        bst.remove("będą");
        bst.wszerz();
    }
    
}

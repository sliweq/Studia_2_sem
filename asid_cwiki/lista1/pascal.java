package asid_cwiki.lista1;
class pascal1 {
    static public int[] NextPascal(int[] line){
        int[] nowa = new int[line.length+1];
        nowa[0] = 1;
        nowa[nowa.length-1] = 1;
        for(int x = 0; x < line.length-1; x++){
            nowa[x+1] = line[x] + line[x+1];
        }
        
        return nowa;
    }

    public static void main(String[] args){
        int[] pascal = new int[1];
        pascal[0] = 1;
        for(int x = 0; x < 5; x++){
            for(int i = 0; i < pascal.length; i++){
                System.out.print(pascal[i]);
            }
            pascal = NextPascal(pascal);
            System.out.println();
        }
    }
    
}

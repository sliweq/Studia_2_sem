package asid_cwiki.Lista5;

import java.util.Hashtable;

public class zad4 {


    static class Student{
        String imie;
        int index;
        int rok;

        public Student(String imie){
            this.imie = imie;
            index = 213213;
            rok = 2;
        }
        
        public String getImie(){
            return imie;
        }
        

    }

    static Student s1 = new Student("Jan");
    static Student s2 = new Student("Anna");
    static Student s3 = new Student("Tomasz");
    static Student s4 = new Student("Agnieszka");



    public static void main(String[] args){
        Hashtable<Integer,Student> hashtable = new Hashtable<>();

        // key / value

        hashtable.put(0, s1);
        hashtable.put(10, s2);
        hashtable.put(5, s3);
        hashtable.put(100, s4);

        // dostep do elementu
        Student tmp = hashtable.get(5);
        System.out.println(tmp.getImie());

        //hashtable.remove(100);

        //hashtable.keys();
        //hashtable.values();
        //hashtable.isEmpty()
    }
    
}

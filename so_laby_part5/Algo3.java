package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Algo3 {
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;
    private int p; // dane obciazenie
    private int r; // minimalne obiciazenie
    private int migrationMeter;
    private int queryMeter;

    public Algo3(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int p, int r){
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        this.r = r;
        queryMeter = 0;
        migrationMeter = 0;

        startSimulation();

        System.out.println("Algorytm 3");
        System.out.println("Ilość zapytań: " + queryMeter);
        System.out.println("Ilość migracji: " + migrationMeter);
    }

    public void startSimulation(){
         while(arrayOfProcesses.size() != 0 || isCpusWorks()){
            if(arrayOfProcesses.size() == 0){
                updateCPUs();
            }
            else{
                Process process = arrayOfProcesses.remove(0);
                updateCPUs();
                allocateProcess(process);
                helpfulCpus();
            }

        }
    }

    public boolean isCpusWorks(){
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() > 0){
                return true;
            }
        }
        return false;
    }

    public void allocateProcess(Process process){
        if(arrayOfCpus.get(process.getCpuNumber()).getCurrentLoad() >= p ){
            Random rand = new Random();
            int choosenCpu = process.getCpuNumber();
            ArrayList<Integer>  asked = new ArrayList<>();

            for(int x = 0; x < arrayOfCpus.size()-1; x++){
                int tmp = rand.nextInt(0,arrayOfCpus.size());
                while(tmp == choosenCpu || containsSomething(asked, tmp)){
                    tmp = rand.nextInt(0,arrayOfCpus.size());
                }
                queryMeter+=1;

                if(arrayOfCpus.get(tmp).getCurrentLoad() < p ){
                    choosenCpu = tmp;
                    break;
                }
                asked.add(tmp);
        }   
               if(choosenCpu != process.getCpuNumber()){
            migrationMeter +=1;
            }
            arrayOfCpus.get(choosenCpu).addProcess(process);
        }
        else{
            //na tym procku
            arrayOfCpus.get(process.getCpuNumber()).addProcess(process);
        }
    }

    public void updateCPUs(){
        for(Cpu cpu:arrayOfCpus){
            cpu.updateProcesses();
        }
    }   
    
    public void helpfulCpus(){
        ArrayList<Cpu> aboveP = new ArrayList<>();
        ArrayList<Cpu> belowR = new ArrayList<>();
        Random rand = new Random();

        // ponizej r ustalanie
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() <= r){
                belowR.add(cpu);
            }
        }
        //te co maja wiecej
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() >= p){
                //System.out.println(cpu.getCurrentLoad());
                aboveP.add(cpu);
            }
        }

        int aPSize = aboveP.size();
        int bRSzie = belowR.size();

        while(bRSzie != 0 && aPSize != 0){
            int tmpAbove = rand.nextInt(aboveP.size());
            int tmpBelow = rand.nextInt(belowR.size());
            
            if(aboveP.get(tmpAbove).getCurrentLoad() == 0){
                System.out.println("asd");
            }

            Process tmpProcess = getMostDamandingProcess(aboveP.get(tmpAbove));
                
            aboveP.get(tmpAbove).removeProcess(tmpProcess);
            
            belowR.get(tmpBelow).addProcess(tmpProcess);

            if(belowR.get(tmpBelow).getCurrentLoad() > r){
                belowR.remove(tmpBelow);
                bRSzie -=1;
            }
            if(aboveP.get(tmpAbove).getCurrentLoad() < p){
                aboveP.remove(tmpAbove);
                aPSize -=1 ;
            }  
        }
    }

    public Process getMostDamandingProcess(Cpu cpu){
        int index = 0;
        for(int x = 1; x < cpu.getProcesses().size(); x++){
            if(cpu.getProcesses().get(index).getLoad() < cpu.getProcesses().get(x).getLoad()){
                index = x;
            }
        }
        return cpu.getProcesses().get(index);
    }

    public boolean containsSomething(ArrayList<Integer>array, int x ){
        for(int tmp: array){
            if(x == tmp){
                return true;
            }
        }
        return false;
    }
}

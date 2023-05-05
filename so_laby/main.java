package so_laby;

public class main {

    public static void main(String[] args){
        int number_of_tests = 1;
        double[][][] all_reports = new double[number_of_tests][][];

        for(int i = 0; i < number_of_tests; i++ ){
            test tests = new test();
            double[][] report =  tests.start_test();
            all_reports[i] = report;
        }

        double averange_waiting_time_fcfs = 0;
        double averange_longest_job_fcfs = 0;
        double averange_waiting_time_sjf = 0;
        double averange_longest_job_sjf = 0;
        double averange_number_of_sorts = 0;
        double averange_waiting_time_rr = 0;
        double averange_longest_job_rr = 0;
        double averange_number_of_switch = 0;



        for(double[][] tmp_report: all_reports){
            averange_waiting_time_fcfs += tmp_report[0][0];
            averange_longest_job_fcfs += tmp_report[0][1];
            averange_waiting_time_sjf += tmp_report[1][0];
            averange_longest_job_sjf +=tmp_report[1][1];
            averange_number_of_sorts += tmp_report[1][2];
            averange_waiting_time_rr += tmp_report[2][0];
            averange_longest_job_rr += tmp_report[2][1];
            averange_number_of_switch += tmp_report[2][2];
        }

        System.out.println("Oficjalny raport:");
        System.out.println("----------------FCFS----------------");
        System.out.println("averange waiting time fcfs: "+averange_waiting_time_fcfs/number_of_tests);
        System.out.println("averange longest job fcfs: "+averange_longest_job_fcfs/number_of_tests);
        System.out.println("----------------SJF----------------");
        System.out.println("averange waiting time sjf: "+averange_waiting_time_sjf/number_of_tests);
        System.out.println("averange longest job sjf: "+averange_longest_job_sjf/number_of_tests);
        System.out.println("averange number of sorts: "+averange_number_of_sorts/number_of_tests);
        System.out.println("----------------RR----------------");
        System.out.println("averange waiting time rr: "+averange_waiting_time_rr/number_of_tests);
        System.out.println("averange longest job rr: "+averange_longest_job_rr/number_of_tests);
        System.out.println("averange number of switch: "+averange_number_of_switch/number_of_tests);
    }
    
}

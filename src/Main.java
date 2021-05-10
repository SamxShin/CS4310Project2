import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        String[] fileContent;
        int totalTestCases = 50;

        //number of page faults on a job to job basis
        String fifoPageFaultCount;
        String lruPageFaultCount;
        String oaPageFaultCount;

        //Create file objects to run
        FileGenerator fileGenerator = new FileGenerator();
        FileUtility fileUtility = new FileUtility();

        //declare algorithm test cases;
        FirstInFirstOut fifo;
        LeastRecentlyUsed lru;
        OptimalAlgorithm oa;


        //create a list to hold each number of faults per file basis for each algorithm
        List<Integer> fifoPageFaults = new ArrayList<Integer>();
        List<Integer> lruPageFaults = new ArrayList<Integer>();
        List<Integer> oaPageFaults = new ArrayList<Integer>();

        //total running number of page faults (total)
        double fifoFaultCountTotal = 0;
        double lruFaultCountTotal = 0;
        double oaFaultCountTotal = 0;

        //create the test data files
        fileGenerator.dataCreation();


        for(int i = 0; i < totalTestCases; i++){
            fileContent = fileUtility.readData(i);
            fileContent = fileUtility.parseDataFromFile(fileContent);


            fifo = new FirstInFirstOut(fileContent[0], fileContent[1]);
            lru = new LeastRecentlyUsed(fileContent[0], fileContent[1]);
            oa = new OptimalAlgorithm(fileContent[0], fileContent[1]);


            fifoPageFaultCount = fifo.FifoRun();
            lruPageFaultCount = lru.lruRun();
            oaPageFaultCount = oa.oaRun();

            //add each of the individual page faults to arraylist to process
            fifoPageFaults.add(Integer.parseInt(fifoPageFaultCount));
            lruPageFaults.add(Integer.parseInt(lruPageFaultCount));
            oaPageFaults.add(Integer.parseInt(oaPageFaultCount));

            //sum for all page faults
            fifoFaultCountTotal += Integer.parseInt(fifoPageFaultCount);
            lruFaultCountTotal += Integer.parseInt(lruPageFaultCount);
            oaFaultCountTotal += Integer.parseInt(oaPageFaultCount);

            displayPerReference(fileContent, fifoPageFaultCount, lruPageFaultCount, oaPageFaultCount);

        }
        displayTotalAverage(fifoFaultCountTotal/totalTestCases,
                lruFaultCountTotal/totalTestCases,
                oaFaultCountTotal/totalTestCases);
        System.out.println("All page faults for First In First Out: " + fifoPageFaults);
        System.out.println("All page faults for Least Recently Used: " + lruPageFaults);
        System.out.println("All page faults for Optimal Algorithm: " + oaPageFaults);
    }

    public static void displayPerReference(String[] fileContent, String fifoPageFaultCount, String lruPageFaultCount,
                                     String oaPageFaultCount){

        System.out.println("\n\nReference String: " + fileContent[1] + "\t\tPage Count: " + fileContent[0]);
        System.out.println("First In First Out:\t\t" + fifoPageFaultCount + " faults");
        System.out.println("Least Recently Used:\t" + lruPageFaultCount + " faults");
        System.out.println("Optimal Algorithm:\t\t" + oaPageFaultCount + " faults\n\n");
    }

    public static void displayTotalAverage(double fifoTotalPageFaults, double lruTotalPageFaultCounts,
                                           double oaTotalPageFaultCounts){

        System.out.println("||--------------Average of all 50 Tests--------------||");
        System.out.println("    First In First Out:  \t" + fifoTotalPageFaults + " faults");
        System.out.println("    Least Recently Used: \t" + lruTotalPageFaultCounts + " faults");
        System.out.println("    Optimal Algorithm:   \t" + oaTotalPageFaultCounts + " faults\n\n");
    }

}

public class Main {
    public static void main(String[] args){

        String[] fileContent;

        int totalTestCases = 50;

        //total running number of page faults (total)
        double fifoFaultCountTotal = 0;
        double lruFaultCountTotal = 0;
        double oaFaultCountTotal = 0;

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


        //create the test data files
        fileGenerator.dataCreation();

        for(int i = 0; i < totalTestCases; i++){
            fileContent = fileUtility.readData(i);
            fileContent = fileUtility.parseDataFromFile(fileContent);


            fifo = new FirstInFirstOut(fileContent[0], fileContent[1]);
            lru = new LeastRecentlyUsed(fileContent[0], fileContent[1]);
            //oa = new OptimalAlgorithm(fileContent[0], fileContent[1]);


            //fifoPageFaultCount = fifo.FifoRun();
            lruPageFaultCount = lru.lruRun();
            //oaPageFaultCount = oa.oaRun();
        }

    }
}

public class Main {
    public static void main(String[] args){

        String[] fileContent;

        int totalTestCases = 50;

        double fifoFaultCount = 0;
        double lruFaultCount = 0;
        double oaFaultCount = 0;

        //Create file objects to run
        FileGenerator fileGenerator = new FileGenerator();
        FileUtility fileUtility = new FileUtility();

        fileGenerator.dataCreation();

    }
}

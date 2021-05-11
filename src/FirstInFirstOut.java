import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FirstInFirstOut {

    private String referenceString;
    private int numberOfPageFrames;

    private int pageFaultCount = 0;

    private HashSet<String> set;
    private Queue<String> indexes = new LinkedList<>();

    public FirstInFirstOut(String numberOfPageFramesParse, String referenceStringParse){
        this.numberOfPageFrames = Integer.parseInt(numberOfPageFramesParse);
        this.referenceString = referenceStringParse;
        set = new HashSet<String>(this.numberOfPageFrames);
    }

    public String FifoRun(){
        displayInfo();

        for(int i = 0; i < this.referenceString.length(); i++){

            String currentPage = Character.toString(referenceString.charAt(i));

            System.out.print(currentPage + " ");

            //checking if the current set can hold more pages
            if(set.size() < numberOfPageFrames){
                //insert it into set if it's not already present
                if(!set.contains(currentPage)){

                    set.add(currentPage);
                    indexes.add(currentPage);
                    pageFaultCount++;

                    System.out.print("\tPage Fault at: " + currentPage + " added \t\t\t\t\t\t");
                } else{
                    System.out.print("\tNo page fault\t\t\t\t\t\t\t\t");
                }
            }
            /*
                if the set is full then we perform FIFO and remove the
                first page of the queue from the set and queue & set the
                current page
            */
            else {
                if(!set.contains(currentPage)){
                    //pop the first page from the queue
                    String firstQueuePage = indexes.peek();
                    indexes.remove();
                    set.remove(firstQueuePage);

                    set.add(currentPage);
                    indexes.add(currentPage);

                    pageFaultCount++;
                    System.out.print("\tPage fault occurred: " + currentPage + " added and " + firstQueuePage + " was removed");


                } else{

                    System.out.print("\tno page fault \t\t\t\t\t\t\t\t");
                    //
                }
            }
            //%1$-10s %2$10s
            String paddedLine1 = String.format("%19s\n", indexes);
            System.out.print(paddedLine1);
            //System.out.print("\t\t\t" + indexes + "\t\n");
        }


        System.out.println("\nTotal Page Faults: " + Integer.toString(pageFaultCount));

        return Integer.toString(pageFaultCount);

    }

    private void displayInfo(){
        String paddedLine2 = String.format("%1$-10s %2$10s", "Reference String: ", referenceString + "\t\tPage Frames: "
                + numberOfPageFrames + "\n");
        System.out.println("\n||---------------------First In First Out Algorithm---------------------||");
        System.out.println(paddedLine2);
    }

}

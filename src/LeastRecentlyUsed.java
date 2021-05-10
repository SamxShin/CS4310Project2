import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class LeastRecentlyUsed {

    private String referenceString;
    private int numberOfPageFrames;

    private int pageFaultCount = 0;

    private int maxValue;
    private int minValue;

    //represents the set of current pages
    private HashSet<String> set;

    //store the least recently used indexes of pages
    private HashMap<String, Integer> indexes = new HashMap<>();

    public LeastRecentlyUsed(String numOfPageFramesParse, String referenceStringParse){

        this.numberOfPageFrames = Integer.parseInt(numOfPageFramesParse);
        this.referenceString = referenceStringParse;
        set = new HashSet<String>(this.numberOfPageFrames);
    }

    public String lruRun(){

        displayInfo();

        for(int i = 0; i < referenceString.length(); i++){

            String currentPage = Character.toString(referenceString.charAt(i));
            System.out.print(currentPage + " ");

            //checking if the set can hold more pages
            if(set.size() < numberOfPageFrames){

                //insert into set of it isn't already present
                if(!set.contains(currentPage)){
                    set.add(currentPage);
                    pageFaultCount++;

                    System.out.print("\tPage Fault at: " + currentPage + " added \t\t\t\t\t\t");
                }
                else{
                    System.out.print("\tNo page fault\t\t\t\t\t\t\t\t");
                }

                indexes.put(currentPage, i);
            }
            /*
                if the set is full then we perform lru algorithm and remove the
                least recently used page and insert the current page
            */
            else{
                if(!set.contains(currentPage)){

                    maxValue = Integer.MAX_VALUE;
                    minValue = Integer.MIN_VALUE;

                    Iterator<String> itrtr = set.iterator();

                    //find the least recently used value in the map
                    while(itrtr.hasNext()){

                        int temp = Integer.parseInt(itrtr.next());

                        if(indexes.get(Integer.toString(temp)) < maxValue){

                            maxValue = indexes.get(Integer.toString(temp));
                            minValue = temp;
                        }

                    }
                    //remove from indexes and remove lru from hashmap
                    set.remove(Integer.toString(minValue));
                    indexes.remove(Integer.toString(minValue));

                    set.add(currentPage);
                    pageFaultCount++;

                    System.out.print("\tPage fault occured: " + currentPage + " added and "
                            + Integer.toString(minValue) + " was removed");

                }
                else {
                    System.out.print("\tno page fault \t\t\t\t\t\t\t\t");
                }

                //add currentpage and index into the map
                indexes.put(currentPage, i);

            }
            String paddedLine1 = String.format("%19s\n", set);
            System.out.print(paddedLine1);

        }

        System.out.println("\nTotal Page Faults: " + Integer.toString(pageFaultCount));
        return Integer.toString(pageFaultCount);
    }

    private void displayInfo(){
        String paddedLine2 = String.format("%1$-10s %2$10s", "Reference String: ", referenceString + "\t\tPage Frames: "
                + numberOfPageFrames + "\n");
        System.out.println("\n||---------------------Least Recently Used Algorithm---------------------||");
        System.out.println(paddedLine2);
    }
}


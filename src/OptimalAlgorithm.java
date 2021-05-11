import java.util.ArrayList;

public class OptimalAlgorithm {

    private String referenceString;
    private int numberOfPageFrames;

    private int pageFaultCount = 0;

    private ArrayList<String> list = new ArrayList<>();

    public OptimalAlgorithm(String numOfPageFramesParse, String referenceStringParse){
        this.numberOfPageFrames = Integer.parseInt(numOfPageFramesParse);
        this.referenceString = referenceStringParse;
    }

    public String oaRun(){
        displayInfo();

        for(int i = 0; i < referenceString.length(); i++){

            String currentPage = Character.toString(referenceString.charAt(i));
            System.out.print(currentPage + " ");

            //check to see if the list can hold more pages
            if(list.size() < numberOfPageFrames){

                list.add(currentPage);
                pageFaultCount++;

                System.out.print("\tPage Fault at: " + currentPage + " added \t\t\t\t\t\t");
            }
            //go to next page if value is present
            else if(list.contains(currentPage)){

                System.out.print("\tNo page fault   \t\t\t\t\t\t\t\t\t\t");
                System.out.print(list.toString() + "\t\n");
                continue;
            }
            //find value needing to be replaced by Optimal Algorithm
            else{
                //the index of the value being replaced is grabbed from reference string
                int recplaceAtIndex = getFurthestValue(i);
                String valueRemoved = Character.toString(referenceString.charAt(recplaceAtIndex));

                for(int j = 0; j < list.size(); j++){

                    String valueFromPage = list.get(j);

                    //remove page from frame and add it to the current frame
                    if(valueFromPage.equals(valueRemoved)){
                        list.remove(j);
                        list.add(currentPage);
                        pageFaultCount++;

                        System.out.print("\tPage fault occurred: " + currentPage + " added and "
                                + valueRemoved + " was removed");

                        break;
                    }
                }
            }
            String paddedLine1 = String.format("%19s\n", list);
            System.out.print(paddedLine1);
        }

        System.out.println("\nTotal Page Faults: " + Integer.toString(pageFaultCount));
        return Integer.toString(pageFaultCount);
    }

    private int getFurthestValue(int currentIndex){

        int furthestIndex = 0;

        String checkReferenceString = referenceString + "9";

        //parse the page frame and current index to find the the index of a page furthest from the reference string
        for(int i = 0; i < list.size(); i++){

            for(int j = currentIndex; j < checkReferenceString.length(); j++){

                String valueFromPage = list.get(i);
                String valueFromReference = Character.toString(checkReferenceString.charAt(j));

                //if equal, update furthest index, otherwise find the first time a page is occured in the reference string
                if(valueFromPage.equals(valueFromReference)){

                    if(furthestIndex < j)
                        furthestIndex = j;
                    break;
                }
                //if the value is no longer in the reference string, then we replace it
                else if(valueFromReference.equals("9"))
                    return referenceString.lastIndexOf(valueFromPage);
            }
        }
        return furthestIndex;
    }
    private void displayInfo(){
        String paddedLine2 = String.format("%1$-10s %2$10s", "Reference String: ", referenceString + "\t\tPage Frames: "
                + numberOfPageFrames + "\n");
        System.out.println("\n||--------------------------Optimal Algorithm--------------------------||");
        System.out.println(paddedLine2);
    }

}

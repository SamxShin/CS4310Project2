import java.io.*;
import java.util.Random;

public class FileGenerator {

    Random random = new Random();

    //these values will not change
    private int lengthOfReferenceString;
    private int numberOfReferenceString;
    private int minNumOfPages;
    private int maxNumberOfPages;

    //these values will change
    private int numberOfPageFrames;
    private String outputFileName;
    private String referenceString;

    public FileGenerator(){

        this.referenceString = "";
        this.lengthOfReferenceString = 30;
        this.numberOfReferenceString = 50;

        this.minNumOfPages = 0;
        this.maxNumberOfPages = 8;

        //change this value to (3, 4, 5, and 6) during testing
        this.numberOfPageFrames = 3;
    }

    public void dataCreation(){
        for(int i= 0; i < numberOfReferenceString; i++){

            //creating the "ReferenceString['x'].txt
            outputFileName = "ReferenceString[" + i + "].txt";

            for(int j = 0; j < lengthOfReferenceString; j++){
                //appended number needs to be between 0 and 7 to the reference string
                referenceString += Integer.toString(random.nextInt(maxNumberOfPages) + minNumOfPages);
            }

            File fileHandler = new File(outputFileName);

            try{

                FileOutputStream fileWriter = new FileOutputStream(fileHandler);
                OutputStreamWriter fileOutputWriter = new OutputStreamWriter(fileWriter);
                try{

                    fileOutputWriter.write("NumberOfPageFrameValue: \n");
                    fileOutputWriter.write(numberOfPageFrames + "\n");
                    fileOutputWriter.write("Reference String: \n");
                    fileOutputWriter.write(referenceString);
                    fileOutputWriter.close();
                } catch (IOException e){

                    e.printStackTrace();
                }

            } catch (FileNotFoundException e){

                e.printStackTrace();
            }

            referenceString = "";
        }
    }
}

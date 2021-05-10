import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class FileUtility {

    public String[] readData(int fileBeingRead) {

        String[] referenceText = new String[4];
        //String fileText = null;
        String fileText;
        int count = 0;

        //getting the current ReferenceString['x'].txt file
        String currentRefString = "ReferenceString";
        currentRefString += "[" + (fileBeingRead) + "].txt";


        FileReader fileHandler;

        try{

            fileHandler = new FileReader(currentRefString);
            BufferedReader fileReader = new BufferedReader(fileHandler);
            try{

                while((fileText = fileReader.readLine()) != null) {

                    referenceText[count] = fileText;
                    count++;
                }

            }  catch (IOException e){

                e.printStackTrace();
            }

        } catch(FileNotFoundException e){

            e.printStackTrace();
        }

        return referenceText;
    }


    public String[] parseDataFromFile(String[] fileData){
        String[] parseDataFromFile = {"", ""};

        parseDataFromFile[0] = fileData[1];
        parseDataFromFile[1] = fileData[3];

        return parseDataFromFile;
    }

    //public void display(String[] fileData, String fifow)

}

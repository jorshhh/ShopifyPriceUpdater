import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jorge on 14/12/16.
 */
public class Main {


    public static void main(String...args){

        try {

	    //el archivo que shopify exporto
            CSVReader reader = new CSVReader(new FileReader("/home/jorge/Downloads/db.csv"));
            //El archivo donde se va a escribir la bdd modificada
	    CSVWriter writer = new CSVWriter(new FileWriter("/home/jorge/Downloads/result.csv"));

            String [] nextLine;

            int i = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(i == 0) {
                    i++;

                    ArrayList<String> toWrite = new ArrayList(Arrays.asList(nextLine));

                    Object[] objDays = toWrite.toArray();
                    String[] strDays = Arrays.copyOf(objDays, objDays.length, String[].class);
                    writer.writeNext(strDays);

                    continue;
                }

                List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));
                // Add stuff using linesAsList.add(index, newValue) as many times as you need.
                ArrayList<String> toWrite = new ArrayList(Arrays.asList(nextLine));

                if(!lineAsList.get(19).equals("")) {
                    Double doubleVal = Double.parseDouble(lineAsList.get(19));
		    //La constante por la que se va a multiplicar el precio
                    doubleVal = doubleVal*1.16;
                    doubleVal = Double.valueOf(Math.round(doubleVal));

                    toWrite.set(19,Double.toString(doubleVal));
                    //System.out.println(doubleVal);
                }

                if(lineAsList.get(8).equals("Default")){
                    toWrite.set(8,"Default title");
                }

                //First Step: convert ArrayList to an Object array.
                Object[] objDays = toWrite.toArray();

                //Second Step: convert Object array to String array
                String[] strDays = Arrays.copyOf(objDays, objDays.length, String[].class);

                writer.writeNext(strDays);



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

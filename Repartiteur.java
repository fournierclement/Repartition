import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Repartiteur {

	public void main( String[] arg){
		try {
            Reader reader = Files.newBufferedReader(Paths.get(arg[0]));
            CSVReader csvReader = new CSVReader(reader);
    
            // Reading Records One by One in a String array
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
            }
        } catch (IOException e) {
        	System.err.println(e);
        	System.exit(-1);
        }
		
	}
}
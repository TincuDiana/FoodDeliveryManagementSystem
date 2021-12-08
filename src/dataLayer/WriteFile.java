package dataLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public WriteFile(String numeFisier, String str) throws IOException {
        writeFile(numeFisier,str);
    }
    public void writeFile(String numeFisier, String str) throws IOException {
    	FileWriter fileWriter= new FileWriter(numeFisier);
    	fileWriter.write(str);
    	fileWriter.close();
    }
}

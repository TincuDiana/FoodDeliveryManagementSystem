package dataLayer;


import java.io.*;
import java.util.List;

import bussinessLayer.User;

public class Serializator implements  Serializable {
	private static final long SerialVersionUID = 456l;
    public void serialization(Object objectToWrite,String fileName)
    {
            FileOutputStream file =  new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(objectToWrite);
            out.close();
            fileOut.close();
    }
    public Object deserialization(String fileName) 
    {
        Object obj = null;
       
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            obj = in.readObject();
            in.close();
            fileIn.close();

        
        return obj;
    }

}
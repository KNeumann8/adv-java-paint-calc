package edu.wctc;
import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;

public class RoomReader implements Serializable{
    public ArrayList<Paintable> readRoomFile(String fileName ) throws IOException, ClassNotFoundException {

        ArrayList paintableList = new ArrayList();

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object obj;

        try{
            while((obj = ois.readObject()) != null){
                Paintable aRoom = (Paintable) obj;
                paintableList.add(aRoom);
            }
        }catch(EOFException e){
            //Jedi business. Go back to your drinks.
        }
        return paintableList;
    }
}

package edu.wctc;

import java.io.*;
import java.util.ArrayList;

public class RoomWriter implements Serializable{
    public void writeRoomFile( String fileName, ArrayList<Paintable> roomList ) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(Paintable r: roomList){
            oos.writeObject(r);
        }

        oos.close();

    }
}

package win.knutzuidema.spotifyalarm.interfaces;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Serializer {

    default void serialize(String id){
        try {
            FileOutputStream fos = new FileOutputStream(id + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        }catch(IOException ioe){
            System.out.println("Couldn't create file");
            ioe.printStackTrace();
        }
    }
}

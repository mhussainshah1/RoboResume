package help;
/**
 * Writing
 * ------
 * Create a class that implements the Serializable interface.
 * Open or create a new file using FileOutputStream.
 * Create an ObjectOutputStream giving the above FileOutputStream as an argument to the constructor.
 * Use ObjectOutputStream.writeObject method to write the object you want to the file.
 *
 * Reading
 * -------
 * Open a FileInputStream to the file that you’ve stored the Object to.
 * Open a ObjectInputStream to the above FileInpoutStream.
 * Use readObject method of ObjectInputStream class to read the Object from the file.
 * The above method returns an Object of type Object. So you have to cast it to the original type to use it properly.
 */

import com.company.Person;
import java.io.*;
import java.time.LocalDate;

public class ObjectIOExample {

    private static final String filepath=System.getProperty("user.dir") + File.separatorChar + "test.txt";

    public static void main(String args[]) {

        ObjectIOExample objectIO = new ObjectIOExample();

        Person person = new Person();
        person.setDate("02/17/2019");
        person.setName("Muhammad Shah");
        person.setEmail("m_hussain_shah@hotmail.com");
        person.setPhoneNumber("9176054834");

        objectIO.WriteObjectToFile(person);

        //Read object from file
        Person st = (Person) objectIO.ReadObjectFromFile(filepath);
        System.out.println(st);
    }

    public void WriteObjectToFile(Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object ReadObjectFromFile(String filepath) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return obj;
        }
    }
}

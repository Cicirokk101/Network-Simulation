import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //creating list
        ArrayEventList l1 = new ArrayEventList();
        //creating arraylist to track the timers for the cancel command
        ArrayList<Event> array = new ArrayList<Event>();

        //need to read from  event file
        Scanner input = new Scanner(System.in);
        String filename = input.nextLine();
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        //while there is a next line continue to read file
        while (myReader.hasNextLine()) {
            String data = myReader.next();
//            System.out.println(data);
            //create branch point
            if (data.equals("I")){

                //create new timer
                //System.out.println("Inserting ");
                int num = myReader.nextInt();

                Timer t = new Timer(num);
                l1.insert(t);// add timer to array event list
                array.add(t); //add timer to arraylist
                System.out.println(t);
            }
            else if(data.equals("R")){
                //remove timer
                //System.out.println("Removing");
                Event removedEvent = l1.removeFirst();
                removedEvent.handle();
            }
            else if(data.equals("C")){
                int num = myReader.nextInt();
                Event rT = null;

                rT = array.get(num);// get timer from arraylist
                array.set(num, null);// set that space to null

                l1.remove(rT); // remove timer from list

                rT.cancel(l1.getSimulationTime());


            }

        }
        myReader.close();// close file
        System.out.println();

        System.out.println("Future event list size: " + l1.size());// how many elements are in the list
        System.out.println("Future event list capacity: " + l1.capacity()); //how many timers the list can actually hold

    }
}


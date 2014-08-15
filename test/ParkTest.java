package C42.test;

import C42.main.ParkingClient;

/**
 * Created by saurav on 15/8/14.
 */
public class ParkTest {

    public static void main(String args []){

        ParkingClient client = new ParkingClient();

        if(args.length > 0 && args.length == 3){
            System.out.println(" Running in file mode");
            String inFile = args[0];
            String outFile = args[2];
            client.fromFile(inFile,outFile);
        }else{
            System.out.println(" Running in  Interactive mode");
        }
    }

}

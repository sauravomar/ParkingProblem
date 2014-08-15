package C42.main;

/**
 * Created by saurav on 13/8/14.
 */

public class ParkingException extends  RuntimeException{


    public ParkingException(){
    }

    public ParkingException(String message){
        super(message);
    }

    public ParkingException(Exception e){
        super(e);
    }


}

package C42.main;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by saurav on 11/8/14.
 */
public class ParkingServer {

    private static int emptySlots;
    private static int totalSlots;
    private int maxSize = 6;
    public static File file = null;
    public static BufferedWriter writer = null;
    private HashSet<Integer>occupiedSlots ;  //Space not matter Unique and O(1) access
    private static Map<Integer,Car>occpCarInfo = new HashMap<Integer,Car>(); //Information of car for that slots contains


    //intializing with size

    ParkingServer(int slots){
        totalSlots = slots;
        emptySlots = slots;
        occupiedSlots = new HashSet<Integer>(totalSlots);
        occpCarInfo =  new HashMap<>(totalSlots);
    }

    ParkingServer(){
        totalSlots = maxSize;
        emptySlots = maxSize;
        occupiedSlots = new HashSet<Integer>(totalSlots);
        occpCarInfo =  new HashMap<>(totalSlots);
    }

    public String  leave(int slot) throws ParkingException {

        if( !occpCarInfo.containsKey(slot) || !occupiedSlots.contains(slot)){
            throw new ParkingException("Slot is already Empty ");
        }else{
            occpCarInfo.remove(slot);
            occupiedSlots.remove(slot);
            out("Slot number " + slot + " is free \n");
            emptySlots++;
            return "SUCCESS";
        }



    }

    public String park(String str) throws  ParkingException{

        if(emptySlots == 0){
            out("Parking lot is full \n");
            throw new ParkingException(" Parking lot is full \n");
        }else{
            Car car = popCar(str);
            for(int i=1;i<=totalSlots;i++){
                if(!occupiedSlots.contains(i)){
                    occupiedSlots.add(i);
                    occpCarInfo.put(i,car);
                    emptySlots--;
                    out("Allocated slot number: " + i + "\n");
                    return "Success";
                }
            }
        }
        return "Failure";
    }

    public String getRegNo(String color) throws ParkingException{

        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Integer,Car> entry : occpCarInfo.entrySet()){

            if(entry.getValue().getColor().equalsIgnoreCase(color)){
                sb.append(entry.getValue().getRegNo());
                sb.append(" ");
            }
        }

        if(sb.length() == 0){
            throw new ParkingException(" Car is not found for Color   " + color);
        }
        out(sb.toString() + " \n");
        return sb.toString();
    }

    public Car getCar(int slot){
        return occpCarInfo.get(slot);
    }

    // regNo is always unique hence always have one entry
    public String getColor(String regNo) throws ParkingException{

        for(Map.Entry<Integer,Car> entry : occpCarInfo.entrySet()){

            if(entry.getValue().getRegNo().equalsIgnoreCase(regNo)){
               return entry.getValue().getColor();
            }

        }
        return null;
    }

    public ArrayList<Integer> getSlotFromColor(String color)throws ParkingException{
        ArrayList<Integer> slots = new ArrayList<Integer>() ;

        for(Map.Entry<Integer,Car> entry : occpCarInfo.entrySet()){

            if(entry.getValue().getColor().equalsIgnoreCase(color)){
                int key = entry.getKey()+1;
                out( key +" ,");
                slots.add(key);
            }
        }
        return slots;
    }

    public int getSlotFromReg(String reg) throws ParkingException{
        ArrayList<Integer> slots = new ArrayList<Integer>() ;

        for(Map.Entry<Integer,Car> entry : occpCarInfo.entrySet()){

            if(entry.getValue().getRegNo().equalsIgnoreCase(reg)){
                int key = entry.getKey()+1;
                out(key + " ,");
                return key;
            }
        }
        return 0;
    }

    public void status(){
        out("Slot No. Registration No Colour \n");
        for(int i=1;i<=totalSlots;i++){
            if(occupiedSlots.contains(i)){
                Car car = occpCarInfo.get(i);
                out(i + " , " + car.getRegNo() + " ," + car.getColor() + "\n");
            }
        }

    }

    public String [] parse(String str){
        return str.split(" ");
    }

    public Car popCar(String str) throws ParkingException{
       String st [] = parse(str);
       if(st.length != 3){
          throw new ParkingException("[ Exception : Illegal no of Argument ]");
       }
       Car car = new Car();
       car.setColor(st[2]);
       car.setRegNo(st[1]);
       return car;
    }

    public void out(String result) {
        try {
            if(writer != null && file != null){
                writer.write(result);
                return;
            }
            System.out.println(result);

        }catch (Exception ex){
            System.err.println(" not able to write in file ");
            ex.printStackTrace();
        }



    }
}

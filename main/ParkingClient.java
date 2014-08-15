package C42.main;

import java.io.*;

/**
 * Created by saurav on 11/8/14.
 */

public class ParkingClient {

    private static ParkingServer server = null;

    public void fromFile(String fil, String outFile){
        try{

            File file = new File(fil);

            if(!file.exists()){
                throw new FileNotFoundException("Not a valid location ");
            }

            ParkingServer.file =  new File(outFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            ParkingServer.writer = bw;

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null){
                options(line.trim());
                line = br.readLine();
            }

            br.close();
            bw.close();
        }catch (Exception  ex){
            System.err.println(" Exception " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void interactive(){

    }

    public void options( String opt){
        String options[] = opt.split(" ");
        String result = "";

        if(server == null && ! options[0].equalsIgnoreCase("create_parking_lot") ){
            server =  new ParkingServer();
        }

        try{

            switch (options[0]){

                case "create_parking_lot" :  server = new ParkingServer(Integer.parseInt(options[1]));
                                             break;

                case "park":  server.park(opt);
                                break;

                case "leave": server.leave(Integer.parseInt(options[1])) ;
                              break;

                case "registration_numbers_for_cars_with_colour" : server.getRegNo(options[1]);
                                                        break;

                case "slot_numbers_for_cars_with_colour": server.getSlotFromColor(options[1]);
                                                           break;
                case "status": server.status();
                                break;

                case "slot_number_for_registration_number" : server.getSlotFromReg(options[1]);
                                                              break;
                default:
                    System.out.println("not valid request");

            }
        }catch (ParkingException ex){
            System.out.println("[ Exception  due to " + ex.getMessage() + " ]");
            ex.printStackTrace();
        }catch (Exception ex){
            System.out.println("[ Exception  due to " + ex.getMessage() + " ]");
            ex.printStackTrace();
        }
    }


}

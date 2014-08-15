package C42.main;

/**
 * Created by saurav on 11/8/14.
 */
public enum ParkCase {

    create_parking_lot("createLot"),
    status("status"),
    park("park"),
    registration_numbers_for_cars("regNoFromCars"),
    leave("leave"),
    slot_numbers_for_cars_with_colour("slotNum") ;

    private String val;
    private ParkCase(String val){
        this.val =  val;
    }

    public String getParkCase(ParkCase pcase){
          return pcase.val;
    }
}

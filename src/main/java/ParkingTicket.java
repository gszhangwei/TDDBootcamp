public class ParkingTicket {
    private String id;
    private String parkingLotName;

    public ParkingTicket(String number) {
        this.id = number;
    }

    public ParkingTicket(String id, String parkingLotName) {
        this.id = id;
        this.parkingLotName = parkingLotName;
    }

    public String getId() {
        return id;
    }


    public String getParkingLotName() {
        return parkingLotName;
    }
}

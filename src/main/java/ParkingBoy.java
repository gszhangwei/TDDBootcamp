import exception.NoCarException;

import java.util.List;

public abstract class ParkingBoy {
    private List<ParkingLot> parkingLots;

    ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket park(Car car) throws Exception;

    Car pick(ParkingTicket ticket) throws Exception {
        return pickCar(ticket, parkingLots);
    }

    private Car pickCar(ParkingTicket ticket, List<ParkingLot> parkingLots) throws NoCarException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getName().equals(ticket.getParkingLotName()))
                .findFirst()
                .orElseThrow(NoCarException::new)
                .pick(ticket);
    }
}

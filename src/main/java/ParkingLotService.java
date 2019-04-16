import exception.NoAvailableException;

import java.util.List;

public class ParkingLotService {
    private List<ParkingLot> parkingLots;

    public ParkingLotService(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws NoAvailableException {
        ParkingLot firstAvailableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity()).findFirst().orElse(null);
        if (firstAvailableParkingLot != null) {
            return new ParkingTicket(car.getNumber(), firstAvailableParkingLot.getName());
        }
        throw new NoAvailableException();
    }
}

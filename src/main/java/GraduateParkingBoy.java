import exception.NoAvailableException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = getAvailableParkingLotForGraduateParking();
        ParkingLotUtils.checkIfDuplicated(car, parkingLots);
        return ParkingLotUtils.getParkingTicket(car, firstAvailableParkingLot);
    }

    private ParkingLot getAvailableParkingLotForGraduateParking() throws NoAvailableException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity())
                .findFirst()
                .orElseThrow(NoAvailableException::new);
    }
}

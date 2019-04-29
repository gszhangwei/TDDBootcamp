import exception.NoAvailableException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = getAvailableParkingLotForSmartParking();
        ParkingLotUtils.checkIfDuplicated(car, parkingLots);
        return getParkingTicket(car, firstAvailableParkingLot);
    }

    private ParkingTicket getParkingTicket(Car car, ParkingLot firstAvailableParkingLot) throws Exception {
        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }

    private ParkingLot getAvailableParkingLotForSmartParking() throws NoAvailableException {
        return parkingLots.stream()
                .max(Comparator.comparing(parkingLot -> Math.subtractExact(parkingLot.getCapacity(), parkingLot.getCars().size())))
                .orElseThrow(NoAvailableException::new);
    }
}

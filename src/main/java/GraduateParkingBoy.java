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
        return getParkingTicket(car, firstAvailableParkingLot);
    }

    private ParkingTicket getParkingTicket(Car car, ParkingLot firstAvailableParkingLot) throws Exception {
        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }

    private ParkingLot getAvailableParkingLotForGraduateParking() throws NoAvailableException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity())
                .findFirst()
                .orElseThrow(NoAvailableException::new);
    }
}

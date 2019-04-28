import exception.DuplicatedCarException;
import exception.NoAvailableException;
import exception.NoCarException;

import java.util.List;

public class ParkingLotService {
    private List<ParkingLot> parkingLots;

    public ParkingLotService(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity())
                .findFirst()
                .orElseThrow(() -> new NoAvailableException());

        if (parkingLots.stream().anyMatch(parkingLot -> parkingLot.getCars().containsKey(car.getNumber()))) {
            throw new DuplicatedCarException();
        }

        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }

    public Car pick(ParkingTicket ticket) throws Exception {
        return parkingLots.stream()
                .filter(parkingLot1 -> parkingLot1.getName().equals(ticket.getParkingLotName()))
                .findFirst()
                .orElseThrow(() -> new NoCarException())
                .pick(ticket);
    }
}

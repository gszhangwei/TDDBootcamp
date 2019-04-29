import exception.DuplicatedCarException;

import java.util.List;

public class ParkingLotUtils {

    public static void checkIfDuplicated(Car car, List<ParkingLot> parkingLots) throws DuplicatedCarException {
        if (parkingLots.stream().anyMatch(parkingLot -> parkingLot.getCars().containsKey(car.getNumber()))) {
            throw new DuplicatedCarException();
        }
    }
    public static ParkingTicket getParkingTicket(Car car, ParkingLot firstAvailableParkingLot) throws Exception {
        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }
}

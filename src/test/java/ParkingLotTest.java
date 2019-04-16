import exception.DuplicatedCarException;
import exception.NoAvailableException;
import exception.NoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_available_position_and_unique_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingTicket ticket = parkingLot.park(new Car("A1123"));
        assertEquals("A1123", ticket.getId());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_and_no_number() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(NoNumberException.class,() -> parkingLot.park(new Car()));
    }

    @Test
    void should_throw_no_available_exception_when_park_given_not_available_position_and_unique_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("B12345"));
        assertThrows(NoAvailableException.class,() -> parkingLot.park(new Car("A1123")));
    }

    @Test
    void should_return_null_when_park_given_available_position_and_duplicated_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car("A1123"));
        assertThrows(DuplicatedCarException.class,() -> parkingLot.park(new Car("A1123")));
    }

    @Test
    void should_return_the_car_when_get_car_given_valid_parking_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A1123");
        ParkingTicket ticket = parkingLot.park(car);
        Car actualCar = parkingLot.getCar(ticket);
        assertEquals(actualCar, car);
    }

    @Test
    void should_return_null_when_get_car_given_invalid_parking_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A1123"));
        ParkingTicket invalidTicket = new ParkingTicket("hack");
        Car actualCar = parkingLot.getCar(invalidTicket);
        assertNull(actualCar);
    }

    @Test
    void should_return_ticket_a_when_park_given_available_position_in_park_lot_a_and_unique_number() throws NoAvailableException {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        ParkingLotService parkingLotService = new ParkingLotService(parkingLots);
        ParkingTicket ticket = parkingLotService.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("A",ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_b_when_park_given_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        final ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA,new ParkingLot(1, "B"));
        ParkingLotService parkingLotService = new ParkingLotService(parkingLots);
        ParkingTicket ticket = parkingLotService.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

}

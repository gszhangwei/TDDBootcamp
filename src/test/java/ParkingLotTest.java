import exception.DuplicatedCarException;
import exception.NoAvailableException;
import exception.NoNumberException;
import org.junit.jupiter.api.Test;

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
}

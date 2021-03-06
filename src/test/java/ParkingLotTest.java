import exception.DuplicatedCarException;
import exception.NoAvailableException;
import exception.NoCarException;
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
    void should_throw_duplicated_car_exception_when_park_given_available_position_and_duplicated_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car("A1123"));
        assertThrows(DuplicatedCarException.class,() -> parkingLot.park(new Car("A1123")));
    }

    @Test
    void should_return_the_car_when_pick_car_given_valid_parking_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A1123");
        ParkingTicket ticket = parkingLot.park(car);
        Car actualCar = parkingLot.pick(ticket);
        assertEquals(actualCar, car);
    }

    @Test
    void should_return_null_when_pick_car_given_invalid_parking_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A1123"));
        ParkingTicket invalidTicket = new ParkingTicket("hack");
        Car actualCar = parkingLot.pick(invalidTicket);
        assertNull(actualCar);
    }

    @Test
    void should_return_ticket_a_when_park_given_available_position_in_park_lot_a_and_unique_number() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingTicket ticket = graduateParkingBoy.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("A",ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_b_when_park_given_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA,new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingTicket ticket = graduateParkingBoy.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_no_number() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        assertThrows(NoNumberException.class,() -> graduateParkingBoy.park(new Car()));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_a() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(2, "A");
        parkingLotA.park(new Car("A12345"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA, new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        assertThrows(DuplicatedCarException.class,() -> graduateParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_b() throws Exception {
        ParkingLot parkingLotB = new ParkingLot(2, "B");
        parkingLotB.park(new Car("A12345"));
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        assertThrows(DuplicatedCarException.class,() -> graduateParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_no_available_exception_when_park_given_not_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        ParkingLot parkingLotB = new ParkingLot(1, "B");
        parkingLotB.park(new Car("A1234"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA, parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        assertThrows(NoAvailableException.class,() -> graduateParkingBoy.park(new Car("A12345")));

    }

    @Test
    void should_return_the_right_car_when_pick_car_given_valid_parking_ticket() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car("A1123");
        ParkingTicket ticket = graduateParkingBoy.park(car);
        Car actualCar = graduateParkingBoy.pick(ticket);
        assertEquals(actualCar, car);
    }

    @Test
    void should_throw_no_car_exception_when_pick_car_given_invalid_parking_ticket() {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingTicket invalidTicket = new ParkingTicket("hack", "hack");
        assertThrows(NoCarException.class, () -> graduateParkingBoy.pick(invalidTicket));
    }

    @Test
    void should_return_ticket_a_when_park_car_given_available_position_in_park_lot_b_and_and_available_position_more_than_park_lot_a_and_unique() throws Exception {
        final ParkingLot parkingLotA = new ParkingLot(1, "A");
        final ParkingLot parkingLotB = new ParkingLot(2, "B");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        ParkingTicket ticket = smartParkingBoy.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_b_when_smart_park_given_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA,new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket ticket = smartParkingBoy.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

    @Test
    void should_throw_no_number_exception_when_smart_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_no_number() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertThrows(NoNumberException.class,() -> smartParkingBoy.park(new Car()));
    }

    @Test
    void should_throw_duplicated_car_exception_when_smart_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_a() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(2, "A");
        parkingLotA.park(new Car("A12345"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA, new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertThrows(DuplicatedCarException.class,() -> smartParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_duplicated_car_exception_when_smart_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_b() throws Exception {
        ParkingLot parkingLotB = new ParkingLot(2, "B");
        parkingLotB.park(new Car("A12345"));
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertThrows(DuplicatedCarException.class,() -> smartParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_no_available_exception_when_smart_park_given_not_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        ParkingLot parkingLotB = new ParkingLot(1, "B");
        parkingLotB.park(new Car("A1234"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotA, parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertThrows(NoAvailableException.class,() -> smartParkingBoy.park(new Car("A12345")));

    }

    @Test
    void should_return_the_right_car_when_smart_pick_car_given_valid_parking_ticket() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1123");
        ParkingTicket ticket = smartParkingBoy.park(car);
        Car actualCar = smartParkingBoy.pick(ticket);
        assertEquals(actualCar, car);
    }

    @Test
    void should_throw_no_car_exception_when_smart_pick_car_given_invalid_parking_ticket() {
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1, "A"), new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket invalidTicket = new ParkingTicket("hack", "hack");
        assertThrows(NoCarException.class, () -> smartParkingBoy.pick(invalidTicket));
    }
}

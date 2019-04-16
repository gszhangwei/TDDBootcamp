import exception.DuplicatedCarException;
import exception.NoAvailableException;
import exception.NoNumberException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private String name;
    private int capacity;
    private Map<String, Car> cars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashMap<>();
    }

    public ParkingLot(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
        cars = new HashMap<>();
    }

    public ParkingTicket park(Car car) throws Exception {
        validateAvailableLot();
        validateNoNumber(car);
        validateDuplicate(car);
        cars.put(car.getNumber(), car);
        return new ParkingTicket(car.getNumber());
    }

    public Car getCar(ParkingTicket ticket) {
        return cars.get(ticket.getId());
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    private void validateAvailableLot() throws NoAvailableException {
        if (cars.size() >= capacity) {
            throw new NoAvailableException();
        }
    }

    private void validateNoNumber(Car car) throws NoNumberException {
        if (car.getNumber() == null) {
            throw new NoNumberException();
        }
    }

    private void validateDuplicate(Car car) throws DuplicatedCarException {
       if (Objects.nonNull(cars.get(car.getNumber()))) {
           throw new DuplicatedCarException();
        }
    }
}

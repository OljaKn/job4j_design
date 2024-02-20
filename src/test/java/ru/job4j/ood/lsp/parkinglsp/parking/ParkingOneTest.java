package ru.job4j.ood.lsp.parkinglsp.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parkinglsp.model.Auto;
import ru.job4j.ood.lsp.parkinglsp.model.Car;
import ru.job4j.ood.lsp.parkinglsp.model.TruckCar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ParkingOneTest {
    ParkingOne parkingOne = new ParkingOne(2, 2);

    @Test
    void whenCarParkingIsTrue() {
        Auto newCar = new Car(1, "Honda");
        boolean expected = parkingOne.checkSpaces(newCar);
        assertThat(expected).isTrue();
    }

    @Test
    void whenCarParkingIsFalse() {
        Auto newCar = new Car(1, "Honda");
        Auto newCar1 = new Car(1, "Toyote");
        Auto newCar2 = new Car(1, "Kia");
        parkingOne.checkSpaces(newCar);
        parkingOne.checkSpaces(newCar1);
        boolean expected = parkingOne.checkSpaces(newCar2);
        assertThat(expected).isFalse();
    }

    @Test
    void whenTruckCarParkingIsTrue() {
        Auto newTruckCar = new Car(2, "Honda");
        boolean expected = parkingOne.checkSpaces(newTruckCar);
        assertThat(expected).isTrue();
    }

    @Test
    void whenTruckCarParkingIsTrueOnParkingCar() {
        Auto newTruckCar = new TruckCar(2, "Honda");
        Auto newTruckCar1 = new TruckCar(2, "Toyote");
        Auto newTruckCar2 = new TruckCar(2, "kia");
        parkingOne.checkSpaces(newTruckCar);
        parkingOne.checkSpaces(newTruckCar1);
        boolean expected = parkingOne.checkSpaces(newTruckCar2);
        assertThat(expected).isTrue();
    }
}
package ru.job4j.ood.lsp.parkinglsp.parking;

import ru.job4j.ood.lsp.parkinglsp.model.Auto;
import java.util.ArrayList;
import java.util.List;

public class ParkingOne implements Parking {
    private int spacesCar;
    private int spacesTruckCar;
    private List<Auto> carList;
    private List<Auto> truckCarList;

    public ParkingOne(int spacesCar, int spacesTruckCar) {
        this.spacesCar = spacesCar;
        this.spacesTruckCar = spacesTruckCar;
        carList = new ArrayList<>();
        truckCarList = new ArrayList<>();
    }

    @Override
    public boolean checkSpaces(Auto auto) {
        boolean rsl = false;
        int size = auto.getSize();
        if (size == 1 && spacesCar > 0) {
            carList.add(auto);
           spacesCar--;
           rsl = true;
        } else if (size > 1 && spacesTruckCar > 0) {
                truckCarList.add(auto);
                spacesTruckCar--;
                rsl = true;
        } else if (size <= spacesCar) {
            carList.add(auto);
            spacesCar -= size;
            rsl = true;
        }
        return rsl;
    }

}

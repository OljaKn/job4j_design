package ru.job4j.ood.lsp.parkinglsp.parking;

import ru.job4j.ood.lsp.parkinglsp.model.Auto;

public class ParkingOne implements Parking {
    @Override
    public boolean checkSpaces(Auto auto) {
        return false;
    }
}

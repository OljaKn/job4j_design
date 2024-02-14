package ru.job4j.ood.srp;


import java.util.List;

public interface AutoCenterListModel {
    List<String> modelAutoOld();
    /* получаем список б/у автомобилей  */
    List<String> modelAutoNew();
    /* получаем список всех авто, которые есть в наличии; */
}

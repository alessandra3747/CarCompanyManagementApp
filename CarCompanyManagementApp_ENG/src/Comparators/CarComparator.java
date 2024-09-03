package Comparators;

import Classes.Client;
import AbstractClasses.Car;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {
    private Client client;

    public CarComparator(Client client){
        this.client = client;
    }

    @Override
    public int compare(Car car1, Car car2) {
        if (car1.calculateWholePrice(client) < car2.calculateWholePrice(client))
            return -1;
        else if (car1.calculateWholePrice(client) < car2.calculateWholePrice(client))
            return 0;
        else if (car1.calculateWholePrice(client) > car2.calculateWholePrice(client))
            return 1;
        return 0;
    }

}

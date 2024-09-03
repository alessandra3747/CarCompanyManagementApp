package CarTypesClases;

import Classes.Client;
import AbstractClasses.Car;

public class PassengerCar extends Car {

    public PassengerCar(String name, int maksKilometers){
        this.name = name;
        this.maksKilometers = maksKilometers;
        this.type = "passenger";
        this.enumType = checkEnum();
    }

    public String calculatePrice(Client client) {

        return calculatePriceHelper(this.enumType, client);

    }

    public double calculateWholePrice(Client client) {

        return calculateWholePriceHelper(this.enumType, client);

    }

}

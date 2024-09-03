package CarTypesClases;

import Classes.Client;
import AbstractClasses.Car;

public class DeliveryCar extends Car {

    public DeliveryCar(String name, int maksKilometers){
        this.name = name;
        this.maksKilometers = maksKilometers;
        this.type = "delivery";
        this.enumType = checkEnum();
    }

    public String calculatePrice(Client client) {

        return calculatePriceHelper(this.enumType, client);

    }

    public double calculateWholePrice(Client client) {

        return calculateWholePriceHelper(this.enumType, client);

    }

}

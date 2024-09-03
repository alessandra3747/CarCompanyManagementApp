package CarTypesClases;

import Enums.CarType;
import Classes.PriceList;
import Classes.Client;
import Classes.Parameters;
import AbstractClasses.Car;

public class FreeCar extends Car {

    public FreeCar(String name, int maksKilometers){
        this.name = name;
        this.maksKilometers = maksKilometers;
        this.type = "free";
        this.enumType = checkEnum();
    }

    public String calculatePrice(Client client) {

        PriceList priceList = PriceList.getPriceList();
        if(priceList.find(CarType.FREE, name) == null)
            return "---";
        Parameters parameters = priceList.find(CarType.FREE, name);


        if ( (parameters.getKmLimit() != null) && (this.maksKilometers > parameters.getKmLimit()) )
            this.maksKilometers = parameters.getKmLimit();

        return "0.00";

    }

    public double calculateWholePrice(Client client) {
        return 0.0;
    }

    @Override
    public String toString(){

        PriceList priceList = PriceList.getPriceList();

        if(priceList.find(CarType.FREE, name) == null)
            return name + ", type: " + type + ", km: --";

        Parameters parameters = priceList.find(CarType.FREE, name);

        if(this.maksKilometers > parameters.getKmLimit())
            return name + ", type: " + type + ", km: " + parameters.getKmLimit();

        return name + ", type: " + type + ", km: " + maksKilometers;
    }

}

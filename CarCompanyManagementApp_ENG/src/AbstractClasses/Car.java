package AbstractClasses;

import Enums.CarType;
import Classes.PriceList;
import Classes.Client;
import Classes.Parameters;

public abstract class Car {

    protected String name;
    protected int maksKilometers;
    protected String type;
    protected CarType enumType;

    public abstract String calculatePrice(Client client);

    protected String calculatePriceHelper(CarType carType, Client client){

        PriceList priceList = PriceList.getPriceList();

        if(priceList.find(carType, name) == null)
            return "---";

        Parameters parameters = priceList.find(carType, name);

        if ( parameters.getKmLimit() != null ){

            if( this.maksKilometers <= parameters.getKmLimit() ){

                if ( (client.getSubscription()) && (parameters.getPriceWithSubscription() != null) )
                    return parameters.getPriceWithSubscription() + "";

                else
                    return parameters.getPriceWithoutSubscription() + "";

            }

            if ( this.maksKilometers > parameters.getKmLimit() ){

                if( (client.getSubscription()) && (parameters.getPriceWithSubscription() != null) )
                    return parameters.getPriceWithSubscription() + " (do " + parameters.getKmLimit() + "), " + parameters.getPriceAfterLimit() + " (od " + (parameters.getKmLimit() + 1) + ")";

                else
                    return parameters.getPriceWithoutSubscription() + " (do " + parameters.getKmLimit() + "), " + parameters.getPriceAfterLimit() + " (od " + (parameters.getKmLimit() + 1) + ")";
            }

        }

        if ( parameters.getKmLimit() == null ) {

            if ( (parameters.getPriceWithSubscription() != null) && (client.getSubscription()) )
                return parameters.getPriceWithSubscription() + "";

            else
                return parameters.getPriceWithoutSubscription() + "";
        }

        return "ERROR";
    }


    public abstract double calculateWholePrice(Client client);

    protected double calculateWholePriceHelper(CarType carType, Client client){

        double wholePrice = 0.0;

        PriceList priceList = PriceList.getPriceList();

        if(priceList.find(carType, name) == null)
            return 0.0;

        Parameters parameters = priceList.find(carType, name);

        if ( parameters.getKmLimit() != null ){

            if( this.maksKilometers <= parameters.getKmLimit() ){

                if ( (client.getSubscription()) && (parameters.getPriceWithSubscription() != null) ) {
                    wholePrice += this.maksKilometers * parameters.getPriceWithSubscription();

                    return wholePrice;
                }

                else {
                    wholePrice = this.maksKilometers * parameters.getPriceWithoutSubscription();

                    return wholePrice;
                }
            }

            if ( this.maksKilometers > parameters.getKmLimit() ){

                if( (client.getSubscription()) && (parameters.getPriceWithSubscription() != null) ) {
                    wholePrice += parameters.getKmLimit() * parameters.getPriceWithSubscription();
                    wholePrice += (this.maksKilometers - parameters.getKmLimit()) * parameters.getPriceAfterLimit();

                    return wholePrice;
                }

                else {
                    wholePrice += parameters.getKmLimit() * parameters.getPriceWithoutSubscription();
                    wholePrice += (this.maksKilometers - parameters.getKmLimit()) * parameters.getPriceAfterLimit();

                    return wholePrice;
                }

            }

        }

        if ( parameters.getKmLimit() == null ) {

            if ( (parameters.getPriceWithSubscription() != null) && (client.getSubscription()) ) {
                wholePrice += this.maksKilometers * parameters.getPriceWithSubscription();

                return wholePrice;
            }
            else {
                wholePrice += this.maksKilometers * parameters.getPriceWithoutSubscription();

                return wholePrice;
            }

        }

        return 0.0;
    }


    public String getName(){
        return this.name;
    }

    public CarType getEnumType(){
        return this.enumType;
    }

    public int getMaksKilometers(){
        return this.maksKilometers;
    }


    public void setMaksKilometers(int maksKilometers) {
        this.maksKilometers = maksKilometers;
    }


    protected CarType checkEnum(){

        if(this.type.equals("passenger")) return CarType.PASSENGER;

        if(this.type.equals("delivery")) return CarType.DELIVERY;

        if(this.type.equals("vintage")) return CarType.VINTAGE;

        return CarType.FREE;

    }

    @Override
    public String toString(){
        return name + ", type: " + type + ", km: " + maksKilometers;
    }

}

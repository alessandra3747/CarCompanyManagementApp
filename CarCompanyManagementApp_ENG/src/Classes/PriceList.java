package Classes;

import Enums.CarType;

import java.util.HashMap;
import java.util.Map;

public class PriceList {

    private Map<CarType, Map<String, Parameters>> positions;

    private PriceList(){
        this.positions = new HashMap<>();
    }


    //WZORZEC SINGLETON
    private static PriceList priceList_instance = null;

    public static PriceList getPriceList() {
        if (priceList_instance == null) {
            priceList_instance = new PriceList();
        }
        return priceList_instance;
    }

    public void add(CarType type, String model, Object priceWithSubscription, Object priceWithoutSubscription, Object maksKilometers, Object priceAfterLimit){

        if(priceWithSubscription instanceof Integer) priceWithSubscription = ((Integer)priceWithSubscription).doubleValue();
        if(priceWithoutSubscription instanceof Integer) priceWithoutSubscription = ((Integer)priceWithoutSubscription).doubleValue();
        if(priceAfterLimit instanceof Integer) priceAfterLimit = ((Integer)priceAfterLimit).doubleValue();

        if (!positions.containsKey(type)) {
            positions.put(type, new HashMap<>());
        }

        positions.get(type).put(model, new Parameters((Double)priceWithSubscription, (Double)priceWithoutSubscription, (Integer)maksKilometers, (Double)priceAfterLimit));
    }

    public<T> void add(CarType type, String model, T priceWithoutSubscription, T maksKilometers, T priceAfterLimit){
        this.add(type,model, null, priceWithoutSubscription, maksKilometers, priceAfterLimit);
    }

    public<T> void add(CarType type, String model, T priceWithSubscription, T priceWithoutSubscription){
        this.add(type,model, priceWithSubscription, priceWithoutSubscription, null, null);
    }

    public<T> void add(CarType type, String model, T priceWithoutSubscription){
        this.add(type, model, null, priceWithoutSubscription, null, null);
    }

    public<T> void add(CarType type, T maksKilometers, String model){
        this.add(type, model, 0, null, maksKilometers, null);
    }

    public Parameters find(CarType type, String name){
        return positions.get(type).get(name);
    }

}

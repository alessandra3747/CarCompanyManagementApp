package Classes;

import Enums.CarType;
import AbstractClasses.Car;

import java.util.HashMap;
import java.util.Map;

public class LastTransaction {

    private static Map<Car, Integer> positions;

    public LastTransaction(){
        positions = new HashMap<>();
    }

    public void add(Car car, Integer kilometers){
        positions.put(car, kilometers);
    }

    public void addAll(Cart cartInstance) {
        for(Car car : cartInstance){
            positions.put(car, car.getMaksKilometers());
        }
    }

    public static boolean isFound(CarType type, String name){
        for (Car car : positions.keySet()){
            if(car.getEnumType() == type && car.getName().equals(name))
                return true;
        }
        return false;
    }

    public static Car find (CarType type, String name){
        for (Car car : positions.keySet()){
            if(car.getEnumType() == type && car.getName().equals(name))
                return car;
        }
        return null;
    }

    public static Integer getKilometers(CarType type, String name){
        for (Car car : positions.keySet()){
            if(car.getEnumType() == type && car.getName().equals(name))
                return positions.get(car);
        }
        return null;
    }

    public static void clear(){
        positions.clear();
    }
}

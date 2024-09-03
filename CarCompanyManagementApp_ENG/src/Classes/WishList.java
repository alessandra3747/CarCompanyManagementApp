package Classes;

import AbstractClasses.Car;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WishList implements Iterable<Car>{

    private List<Car> wishList = null;
    protected Client client;

    public WishList(Client client){
        wishList = new LinkedList<>();
        this.client = client;
    }


    public void add(Car car){
        wishList.add(car);
    }

    public void remove(Car car){
        wishList.remove(car);
    }

    public void clear(){
        wishList.clear();
    }

    public Car getIndex(int i){
        return wishList.get(i);
    }

    public int getSize(){
        return wishList.size();
    }

    @Override
    public String toString(){

        if(wishList.isEmpty())
            return client.getName() + " --- empty";

        StringBuilder result = new StringBuilder(client.getName() + "\n");

        for(Car car : wishList) {
            result.append(car.toString());
            result.append(", price: ");
            result.append(car.calculatePrice(this.client));
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public Iterator<Car> iterator() {
        return wishList.iterator();
    }

}

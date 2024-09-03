package Classes;

import AbstractClasses.Car;
import Comparators.CarComparator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Cart implements Iterable<Car>{

    private List<Car> cart;
    private Client client;

    protected Cart(Client client){
        cart = new LinkedList<>();
        this.client = client;

    }


    public void add(Car car){
        cart.add(car);
    }

    public void remove(Car car){
        cart.remove(car);
    }

    public void clear(){
        cart.clear();
    }

    public Car getIndex(int i){
        return cart.get(i);
    }

    public int getSize(){
        return cart.size();
    }

    public Client getKlient(){
        return this.client;
    }

    public double cartValue(){
        double result = 0.0;

        for(Car car : this.cart)
            result += car.calculateWholePrice(this.client);

        return result;
    }

    public void sortCart(){
        cart.sort(new CarComparator(client));
    }


    public boolean isFound(Car car) {
        for(Car c : cart){
            if(car == c)
                return true;
        }
        return false;
    }

    @Override
    public String toString(){

        if(cart.isEmpty())
            return client.getName() + " --- empty";

        StringBuilder result = new StringBuilder(client.getName() + "\n");

        for(Car car : cart) {
            result.append(car.toString());
            result.append(", price: ");
            result.append(car.calculatePrice(this.client));
            result.append("\n");
        }

        return result.toString();
    }


    @Override
    public Iterator<Car> iterator() {
        return cart.iterator();
    }

}

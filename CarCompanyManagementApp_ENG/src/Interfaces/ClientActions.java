package Interfaces;

import Enums.Payment;
import Enums.CarType;
import AbstractClasses.Car;
import Classes.WishList;
import Classes.Cart;

public interface ClientActions {

    WishList getWishList();

    void add(Car car);

    Cart getCart();

    void repack();

    void pay(Payment payment, boolean condition);

    double getWallet();

    void returnPositions(CarType type, String name, int amountOfKilometers);

}

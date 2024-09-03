import Classes.*;
import AbstractClasses.*;
import CarTypesClases.*;

import static Enums.Payment.*;
import static Enums.CarType.*;

public class CarshareTest {

    // price of the specified car in the cart
    static double countPrice(Cart cart, String carBrand) {

        double result = 0.0;

        for( Car car : cart) {
            if (car.getName().equals(carBrand))
                result += car.calculateWholePrice(cart.getKlient());
        }

        return (int)result;
    }

    public static void main(String[] args) {

        // priceList
        PriceList priceList = PriceList.getPriceList();

        // adding new prices to the priceList
        priceList.add(PASSENGER, "Syrena", 1.5, 2.5, 100, 1.85);
        // 1.5 zł for 1 km if the client has subscription,
        // otherwise: 2.5 zł for 1 km (under or equal to 100 km), 1.85 zł for 1 km (above 101 kilometers)

        priceList.add(DELIVERY, "Żuk", 4, 150, 3);
        // 4 zł for 1 km (under or equal to 150 km),
        // 3 zł for 1 km (above 151 kilometers)

        priceList.add(VINTAGE, "Ford T", 10);		// 10 zł for 1 km


        priceList.add(FREE, 50, "Tuk-Tuk");		// free drive only for clients with subscription (<= 50 km)


        // Client f1 declares a sum of 900 zł for orders; true means that client has subscription
        Client f1 = new Client("f1", 900, true);

        // Client f1 adds various cars to his wishlist:
        // "Syrena" passenger type maks. 80 km
        // "Żuk" delivery type maks. 200 km,
        // "Lublin" vintage type maks. 30 km,
        // "Tuk-Tuk" free type maks. 60 km (but can use only under or equal to 50 km).
        f1.add(new PassengerCar("Syrena", 80));
        f1.add(new DeliveryCar("Żuk", 200));
        f1.add(new VintageCar("Lublin", 30));
        f1.add(new FreeCar("Tuk-Tuk", 60));

        // Wishlist of the f1 client
        WishList listF1 = f1.getWishList();

        System.out.println("Wishlist of " + listF1);

        // Before paying, the customer will repack the cars from the wish list into the cart
        // (after removing them from the wish list).
        //It is possible that there are cars on the wish list that do not have a price in the price list,
        //in this case, they would not be added to the basket
        Cart cartF1 = f1.getCart();
        f1.repack();

        // What is on the client f1 wishlist
        System.out.println("After repacking, wishlist of " + f1.getWishList());

        // What is in the client f1 cart
        System.out.println("After repacking, cart of " + cartF1);

        // What is the price of all passenger cars in client f1 cart
        System.out.println("Syrena cars in the f1 client's cart cost:  " + countPrice(cartF1, "Syrena"));

        // Client pays...
        f1.pay(CARD, false);	// with card, provision 1%
        // true means that in case of lack of funds, the application itself will save excess kilometers/cars,
        // false means giving up paying and clearing the cart and wish list

        // How much money does the f1 customer have left?
        System.out.println("\nAfter paying, client f1 has left: " + f1.getWallet() + " zł\n");

        //
        //The client may have run out of funds, then, optionally, cars/kilometers may be put back,
        // otherwise, the cart is empty after payment
        System.out.println("After payment, cart of " + f1.getCart());
        System.out.println("After payment, cart of " + cartF1);

        // Now comes the Dakar client, declares 850 zł for orders
        Client dakar = new Client("dakar", 850, false);

        //He ordered too much for this amount
        dakar.add(new DeliveryCar("Żuk", 100));
        dakar.add(new VintageCar("Ford T", 50));

        // What does a Dakar customer have on their wish list?
        System.out.println("\nWishlist of " + dakar.getWishList());

        Cart cartDakar = dakar.getCart();
        dakar.repack();

        // What is on the Dakar client's wishlist
        System.out.println("After repacking, wishlist of " + dakar.getWishList());

        // What is in the Dakar client's cart
        System.out.println("\nAfter repacking, cart of " + dakar.getCart());

        // Client pays...
        dakar.pay(TRANSFER, true);	// transfer, no provision

        // How much money does the Dakar client have left?
        System.out.println("After payment, client dakar has left: " + dakar.getWallet() + " zł\n");

        // What's left in the Dakar customer's basket (he didn't have enough money)
        System.out.println("After payment, cart of " + cartDakar);

        dakar.returnPositions(DELIVERY, "Żuk", 50);	//return (add to cart) 50 km of "Żuk" delivery van from the last transaction

        // How much money does the Dakar client have left?
        System.out.println("After return, client dakar has left: " + dakar.getWallet() + " zł\n");

        // What is left in the Dakar client's cart
        System.out.println("After return, cart of " + cartDakar);

    }
}
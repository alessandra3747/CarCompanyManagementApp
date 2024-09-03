package Classes;

import Enums.Payment;
import Enums.CarType;
import Interfaces.ClientActions;
import AbstractClasses.Car;

import static java.lang.Math.round;

public class Client implements ClientActions {

    public static int idCounter = 0;

    private final int id;
    private String name;
    private double declaredSum;
    private boolean hasSubscription;

    private WishList wishList_instance;
    private Cart cart_instance;


    public Client(String name, int declaredSum, boolean hasSubscription) {
        this.id = ++idCounter;
        this.name = name;
        this.declaredSum = declaredSum;
        this.hasSubscription = hasSubscription;
        this.wishList_instance = new WishList(this);
        this.cart_instance = new Cart(this);
    }


    public WishList getWishList() {
        return wishList_instance;
    }

    public Cart getCart() {
        return cart_instance;
    }

    public void add(Car car) {
        wishList_instance.add(car);
    }

    public void repack() {

        for (int i = wishList_instance.getSize()-1; i >= 0; i--) {

            if( !wishList_instance.getIndex(i).calculatePrice(this).equals("---") ) {

                cart_instance.add(wishList_instance.getIndex(i));

                wishList_instance.remove(wishList_instance.getIndex(i));

            }
        }

    }

    public void pay(Payment payment, boolean condition) {

        LastTransaction transaction = new LastTransaction();

        double paymentTotal = cart_instance.cartValue();
        double maksSum = declaredSum;

        if(payment == Payment.CARD) {

            paymentTotal += provision(paymentTotal);
            maksSum -= provision(maksSum);

        }

        //CONDITION FALSE
        if ( !condition && paymentTotal > declaredSum) {

            cart_instance.clear();
            wishList_instance.clear();

            return;
        }

        //CONDITION TRUE
        if ( condition && paymentTotal > declaredSum) {

            paymentTotal = 0.0;

            //sorting from the least expensive positions to the most
            cart_instance.sortCart();

            for (int i = cart_instance.getSize()-1; i >= 0; i-- ){
                Car car = cart_instance.getIndex(i);

                //is it possible to buy whole position
                //if not
                if ( paymentTotal + car.calculateWholePrice(this) > maksSum ){

                    paymentTotal = payForPartOfCart(car, paymentTotal, maksSum, transaction);

                }
                //if yes
                else {
                    cart_instance.remove(car);
                    paymentTotal += car.calculateWholePrice(this);
                    transaction.add(car, car.getMaksKilometers());
                }

            }

        }

        if ( paymentTotal <= declaredSum) {

            this.declaredSum -= paymentTotal;
            transaction.addAll(cart_instance);
            cart_instance.clear();

            return;
        }

    }

    private double payForPartOfCart(Car car, double paymentTotal, double maksSum, LastTransaction transaction) {

        PriceList priceList = PriceList.getPriceList();
        Parameters parameters = priceList.find(car.getEnumType(), car.getName());

        int boughtKilometers = 0;

        double additionToTotal = (hasSubscription && parameters.getPriceWithSubscription()!=null) ? parameters.getPriceWithSubscription() : parameters.getPriceWithoutSubscription();

        while ( paymentTotal + additionToTotal <= maksSum){

            if( parameters.getKmLimit() == null || boughtKilometers <= parameters.getKmLimit()){

                if(hasSubscription && parameters.getPriceWithSubscription() != null){
                    additionToTotal = parameters.getPriceWithSubscription();
                    paymentTotal += parameters.getPriceWithSubscription();
                    boughtKilometers++;
                }
                else{
                    additionToTotal = parameters.getPriceWithoutSubscription();
                    paymentTotal += parameters.getPriceWithoutSubscription();
                    boughtKilometers++;
                }
            }
            else {
                additionToTotal = parameters.getPriceAfterLimit();
                paymentTotal += parameters.getPriceAfterLimit();
                boughtKilometers++;
            }

        }

        car.setMaksKilometers(car.getMaksKilometers() - boughtKilometers);

        this.declaredSum -= paymentTotal;

        transaction.add(car, boughtKilometers);

        return paymentTotal;
    }


    private double provision(double val){
        return val*0.01;
    }

    public double getWallet() {

        double roundedTotal = this.declaredSum;

        roundedTotal *= 100.0;

        return Math.round(roundedTotal) /100.0;

    }

    public void returnPositions(CarType type, String name, int amountOfKilometers) {

        if(LastTransaction.isFound(type, name) && LastTransaction.getKilometers(type,name) >= amountOfKilometers){

            Car car = LastTransaction.find(type,name);

            if ( cart_instance.isFound(car) )
                car.setMaksKilometers( car.getMaksKilometers() + amountOfKilometers );
            else
                cart_instance.add(car);

            PriceList priceList = PriceList.getPriceList();
            Parameters parameters = priceList.find(type, name);

            if ( this.hasSubscription)
                this.declaredSum += parameters.getPriceWithSubscription() * amountOfKilometers;
             else
                this.declaredSum += parameters.getPriceWithoutSubscription() * amountOfKilometers;

             LastTransaction.clear();
        }
        else
            System.out.println("Incorrect data");

    }

    public String getName(){
        return this.name;
    }

    public boolean getSubscription(){
        return this.hasSubscription;
    }
}

package Classes;

public class Parameters {

    private Double priceWithSubscription;
    private Double priceWithoutSubscription;
    private Integer kmLimit;
    private Double priceAfterLimit;

    public Parameters(Double priceWithSubscription, Double priceWithoutSubscription, Integer kmLimit, Double priceAfterLimit) {
        this.priceWithSubscription = priceWithSubscription;
        this.priceWithoutSubscription = priceWithoutSubscription;
        this.kmLimit = kmLimit;
        this.priceAfterLimit = priceAfterLimit;
    }

    public Double getPriceWithSubscription() {
        return priceWithSubscription;
    }

    public Double getPriceWithoutSubscription() {
        return priceWithoutSubscription;
    }

    public Integer getKmLimit() {
        return kmLimit;
    }

    public Double getPriceAfterLimit() {
        return priceAfterLimit;
    }

}

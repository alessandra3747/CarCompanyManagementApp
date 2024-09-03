package Klasy;

import Enumeracje.TypSamochodu;

public class Parametry {

    private Double cenaZAbonamentem;
    private Double cenaPodstawowa;
    private Integer limitKm;
    private Double cenaPoLimicie;

    public Parametry(Double cenaZAbonamentem, Double cenaPodstawowa, Integer limitKm, Double cenaPoLimicie) {
        this.cenaZAbonamentem = cenaZAbonamentem;
        this.cenaPodstawowa = cenaPodstawowa;
        this.limitKm = limitKm;
        this.cenaPoLimicie = cenaPoLimicie;
    }

    public Double getCenaZAbonamentem() {
        return cenaZAbonamentem;
    }

    public Double getCenaPodstawowa() {
        return cenaPodstawowa;
    }

    public Integer getLimitKm() {
        return limitKm;
    }

    public Double getCenaPoLimicie() {
        return cenaPoLimicie;
    }

}

package Klasy;

import Enumeracje.TypSamochodu;

import java.util.HashMap;
import java.util.Map;

public class Cennik {

    private Map<TypSamochodu, Map<String, Parametry>> pozycje;

    private Cennik(){
        this.pozycje = new HashMap<>();
    }


    //WZORZEC SINGLETON
    private static Cennik cennik_instancja = null;

    public static Cennik pobierzCennik() {
        if (cennik_instancja == null) {
            cennik_instancja = new Cennik();
        }
        return cennik_instancja;
    }

    public void dodaj(TypSamochodu typ, String model, Object cenaZAbonamentem, Object cenaPodstawowa, Object limitKm, Object cenaPoLimicie){

        if(cenaZAbonamentem instanceof Integer) cenaZAbonamentem = ((Integer)cenaZAbonamentem).doubleValue();
        if(cenaPodstawowa instanceof Integer) cenaPodstawowa = ((Integer)cenaPodstawowa).doubleValue();
        if(cenaPoLimicie instanceof Integer) cenaPoLimicie = ((Integer)cenaPoLimicie).doubleValue();

        if (!pozycje.containsKey(typ)) {
            pozycje.put(typ, new HashMap<>());
        }

        pozycje.get(typ).put(model, new Parametry((Double)cenaZAbonamentem, (Double)cenaPodstawowa, (Integer)limitKm, (Double)cenaPoLimicie));
    }

    public<T> void dodaj(TypSamochodu typ, String model, T cenaPodstawowa, T limitKm, T cenaPoLimicie){
        this.dodaj(typ,model, null, cenaPodstawowa, limitKm, cenaPoLimicie);
    }

    public<T> void dodaj(TypSamochodu typ, String model, T cenaZAbonamentem, T cenaPodstawowa){
        this.dodaj(typ,model, cenaZAbonamentem, cenaPodstawowa, null, null);
    }

    public<T> void dodaj(TypSamochodu typ, String model, T cenaPodstawowa){
        this.dodaj(typ, model, null, cenaPodstawowa, null, null);
    }

    public<T> void dodaj(TypSamochodu typ, T limitKm, String model){
        this.dodaj(typ, model, 0, null, limitKm, null);
    }

    public Parametry find(TypSamochodu typ, String nazwa){
        return pozycje.get(typ).get(nazwa);
    }

}

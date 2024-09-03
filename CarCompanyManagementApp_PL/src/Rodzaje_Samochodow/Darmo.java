package Rodzaje_Samochodow;

import Enumeracje.TypSamochodu;
import Klasy.Cennik;
import Klasy.Klient;
import Klasy.Parametry;
import KlasyAbstrakcyjne.Samochod;

public class Darmo extends Samochod {

    public Darmo(String nazwa, int maksKilometrow){
        this.nazwa = nazwa;
        this.maksKilometrow = maksKilometrow;
        this.typ = "darmowy";
        this.typEnum = ustalEnum();
    }

    public String obliczCene(Klient klient) {

        Cennik cennik = Cennik.pobierzCennik();
        if(cennik.find(TypSamochodu.DARMO, nazwa) == null)
            return "brak";
        Parametry parametry = cennik.find(TypSamochodu.DARMO, nazwa);


        if ( (parametry.getLimitKm() != null) && (this.maksKilometrow > parametry.getLimitKm()) )
            this.maksKilometrow = parametry.getLimitKm();

        return "0.00";

    }

    public double obliczLacznaCene(Klient klient) {
        return 0.0;
    }

    @Override
    public String toString(){

        Cennik cennik = Cennik.pobierzCennik();

        if(cennik.find(TypSamochodu.DARMO, nazwa) == null)
            return nazwa + ", typ: " + typ + ", ile: --";

        Parametry parametry = cennik.find(TypSamochodu.DARMO, nazwa);

        if(this.maksKilometrow > parametry.getLimitKm())
            return nazwa + ", typ: " + typ + ", ile: " + parametry.getLimitKm();

        return nazwa + ", typ: " + typ + ", ile: " + maksKilometrow;
    }

}

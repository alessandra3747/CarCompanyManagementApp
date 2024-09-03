package Rodzaje_Samochodow;

import Enumeracje.TypSamochodu;
import Klasy.Cennik;
import Klasy.Klient;
import Klasy.Parametry;
import KlasyAbstrakcyjne.Samochod;

public class Osobowy extends Samochod {

    public Osobowy(String nazwa, int maksKilometrow){
        this.nazwa = nazwa;
        this.maksKilometrow = maksKilometrow;
        this.typ = "osobowy";
        this.typEnum = ustalEnum();
    }

    public String obliczCene(Klient klient) {

        return obliczCeneHelper(this.typEnum, klient);

    }

    public double obliczLacznaCene(Klient klient) {

        return obliczLacznaCeneHelper(this.typEnum, klient);

    }

}

package Rodzaje_Samochodow;

import Enumeracje.TypSamochodu;
import Klasy.Cennik;
import Klasy.Klient;
import Klasy.Parametry;
import KlasyAbstrakcyjne.Samochod;

public class Zabytkowy extends Samochod {

    public Zabytkowy(String nazwa, int maksKilometrow){
        this.nazwa = nazwa;
        this.maksKilometrow = maksKilometrow;
        this.typ = "zabytkowy";
        this.typEnum = ustalEnum();
    }

    public String obliczCene(Klient klient) {

        return obliczCeneHelper(this.typEnum, klient);

    }

    public double obliczLacznaCene(Klient klient) {

        return obliczLacznaCeneHelper(this.typEnum, klient);

    }

}

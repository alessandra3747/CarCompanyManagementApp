package Interfejsy;

import Enumeracje.Platnosc;
import Enumeracje.TypSamochodu;
import KlasyAbstrakcyjne.Samochod;
import Klasy.ListaZyczen;
import Klasy.Koszyk;

public interface KlientActions {

    ListaZyczen pobierzListeZyczen();

    void dodaj(Samochod samochod);

    Koszyk pobierzKoszyk();

    void przepakuj();

    void zaplac(Platnosc platnosc, boolean warunek);

    double pobierzPortfel();

    void zwroc(TypSamochodu typ, String nazwa, int iloscKm);

}

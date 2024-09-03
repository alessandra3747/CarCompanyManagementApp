package Komparatory;

import Klasy.Klient;
import KlasyAbstrakcyjne.Samochod;

import java.util.Comparator;

public class SamochodComparator implements Comparator<Samochod> {
    private Klient klient;

    public SamochodComparator(Klient klient){
        this.klient = klient;
    }

    @Override
    public int compare(Samochod s1, Samochod s2) {
        if (s1.obliczLacznaCene(klient) < s2.obliczLacznaCene(klient))
            return -1;
        else if (s1.obliczLacznaCene(klient) < s2.obliczLacznaCene(klient))
            return 0;
        else if (s1.obliczLacznaCene(klient) > s2.obliczLacznaCene(klient))
            return 1;
        return 0;
    }

}

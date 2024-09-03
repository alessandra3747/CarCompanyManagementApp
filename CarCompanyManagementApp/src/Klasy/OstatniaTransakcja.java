package Klasy;

import Enumeracje.TypSamochodu;
import KlasyAbstrakcyjne.Samochod;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OstatniaTransakcja {

    private static Map<Samochod, Integer> pozycje;

    public OstatniaTransakcja(){
        pozycje = new HashMap<>();
    }

    public void add(Samochod s, Integer kilometry){
        pozycje.put(s, kilometry);
    }

    public void addAll(Koszyk koszykInstancja) {
        for(Samochod s : koszykInstancja){
            pozycje.put(s, s.getMaksKilometrow());
        }
    }

    public static boolean czyNalezy (TypSamochodu typ, String nazwa){
        for (Samochod s : pozycje.keySet()){
            if(s.getTypEnum() == typ && s.getNazwa().equals(nazwa))
                return true;
        }
        return false;
    }

    public static Samochod find (TypSamochodu typ, String nazwa){
        for (Samochod s : pozycje.keySet()){
            if(s.getTypEnum() == typ && s.getNazwa().equals(nazwa))
                return s;
        }
        return null;
    }

    public static Integer getKilometry(TypSamochodu typ, String nazwa){
        for (Samochod s : pozycje.keySet()){
            if(s.getTypEnum() == typ && s.getNazwa().equals(nazwa))
                return pozycje.get(s);
        }
        return null;
    }

    public static void clear(){
        pozycje.clear();
    }
}

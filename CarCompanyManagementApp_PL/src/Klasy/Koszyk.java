package Klasy;

import KlasyAbstrakcyjne.Samochod;
import Komparatory.SamochodComparator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Koszyk implements Iterable<Samochod>{

    private List<Samochod> koszyk;
    private Klient klient;

    protected Koszyk(Klient klient){
        koszyk = new LinkedList<>();
        this.klient = klient;

    }


    public void add(Samochod samochod){
        koszyk.add(samochod);
    }

    public void remove(Samochod samochod){
        koszyk.remove(samochod);
    }

    public void clear(){
        koszyk.clear();
    }

    public Samochod getIndex(int i){
        return koszyk.get(i);
    }

    public int getSize(){
        return koszyk.size();
    }

    public Klient getKlient(){
        return this.klient;
    }

    public double wartoscKoszyka(){
        double result = 0.0;

        for(Samochod s : this.koszyk)
            result += s.obliczLacznaCene(this.klient);

        return result;
    }

    public void posortujKoszyk(){
        koszyk.sort(new SamochodComparator(klient));
    }


    public boolean czyNalezy(Samochod samochod) {
        for(Samochod s : koszyk){
            if(samochod == s)
                return true;
        }
        return false;
    }

    @Override
    public String toString(){

        if(koszyk.isEmpty())
            return klient.getNazwa() + " -- pusto";

        StringBuilder result = new StringBuilder(klient.getNazwa() + "\n");

        for(Samochod s : koszyk) {
            result.append(s.toString());
            result.append(", cena: ");
            result.append(s.obliczCene(this.klient));
            result.append("\n");
        }

        return result.toString();
    }


    @Override
    public Iterator<Samochod> iterator() {
        return koszyk.iterator();
    }

}

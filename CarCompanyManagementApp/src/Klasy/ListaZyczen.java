package Klasy;

import KlasyAbstrakcyjne.Samochod;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaZyczen implements Iterable<Samochod>{

    private List<Samochod> listaZyczen = null;
    protected Klient klient;

    public ListaZyczen(Klient klient){
        listaZyczen = new LinkedList<>();
        this.klient = klient;
    }


    public void add(Samochod samochod){
        listaZyczen.add(samochod);
    }

    public void remove(Samochod samochod){
        listaZyczen.remove(samochod);
    }

    public void clear(){
        listaZyczen.clear();
    }

    public Samochod getIndex(int i){
        return listaZyczen.get(i);
    }

    public int getSize(){
        return listaZyczen.size();
    }

    @Override
    public String toString(){

        if(listaZyczen.isEmpty())
            return klient.getNazwa() + " -- pusto";

        StringBuilder result = new StringBuilder(klient.getNazwa() + "\n");

        for(Samochod s : listaZyczen) {
            result.append(s.toString());
            result.append(", cena: ");
            result.append(s.obliczCene(this.klient));
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public Iterator<Samochod> iterator() {
        return listaZyczen.iterator();
    }

}

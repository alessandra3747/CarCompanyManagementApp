package Klasy;

import Enumeracje.Platnosc;
import Enumeracje.TypSamochodu;
import Interfejsy.KlientActions;
import KlasyAbstrakcyjne.Samochod;
import Komparatory.SamochodComparator;

import java.util.Arrays;

import static java.lang.Math.round;

public class Klient implements KlientActions{

    public static int idCounter = 0;

    private int id;
    private String nazwa;
    private double zadeklarowanaKwota;
    private boolean maAbonament;

    private ListaZyczen listaZyczen_instancja;
    private Koszyk koszyk_instancja;


    public Klient(String nazwa, int zadeklarowanaKwota, boolean maAbonament) {
        this.id = ++idCounter;
        this.nazwa = nazwa;
        this.zadeklarowanaKwota = zadeklarowanaKwota;
        this.maAbonament = maAbonament;
        this.listaZyczen_instancja = new ListaZyczen(this);
        this.koszyk_instancja = new Koszyk(this);
    }


    public ListaZyczen pobierzListeZyczen() {
        return listaZyczen_instancja;
    }

    public Koszyk pobierzKoszyk() {
        return koszyk_instancja;
    }

    public void dodaj(Samochod samochod) {
        listaZyczen_instancja.add(samochod);
    }

    public void przepakuj() {

        for (int i = listaZyczen_instancja.getSize()-1; i >= 0; i--) {

            if( !listaZyczen_instancja.getIndex(i).obliczCene(this).equals("brak") ) {

                koszyk_instancja.add(listaZyczen_instancja.getIndex(i));

                listaZyczen_instancja.remove(listaZyczen_instancja.getIndex(i));

            }
        }

    }

    public void zaplac(Platnosc platnosc, boolean warunek) {

        OstatniaTransakcja transakcja = new OstatniaTransakcja();

        double doZaplaty = koszyk_instancja.wartoscKoszyka();
        double maksymalnaKwota = zadeklarowanaKwota;

        if(platnosc == Platnosc.KARTA) {

            doZaplaty += prowizja(doZaplaty);
            maksymalnaKwota -= prowizja(maksymalnaKwota);

        }

        //WARUNEK FALSE
        if ( !warunek && doZaplaty > zadeklarowanaKwota ) {

            koszyk_instancja.clear();
            listaZyczen_instancja.clear();

            return;
        }

        //WARUNEK TRUE
        if ( warunek && doZaplaty > zadeklarowanaKwota ) {

            doZaplaty = 0.0;

            //sortowanie od najtanszych pozycji do najdrozszych
            koszyk_instancja.posortujKoszyk();

            for ( int i = koszyk_instancja.getSize()-1; i >= 0; i-- ){
                Samochod s = koszyk_instancja.getIndex(i);

                //czy mozna kupic cala pozycje
                if ( doZaplaty + s.obliczLacznaCene(this) > maksymalnaKwota ){

                    doZaplaty = zakupCzesciKoszyka(s, doZaplaty, maksymalnaKwota, transakcja);

                } else {
                    koszyk_instancja.remove(s);
                    doZaplaty += s.obliczLacznaCene(this);
                    transakcja.add(s, s.getMaksKilometrow());
                }

            }

        }

        if ( doZaplaty <= zadeklarowanaKwota ) {

            this.zadeklarowanaKwota -= doZaplaty;
            transakcja.addAll(koszyk_instancja);
            koszyk_instancja.clear();

            return;
        }

    }

    private double zakupCzesciKoszyka(Samochod s, double doZaplaty, double maksymalnaKwota, OstatniaTransakcja transakcja) {

        Cennik cennik = Cennik.pobierzCennik();
        Parametry parametry = cennik.find(s.getTypEnum(), s.getNazwa());

        int kupioneKilometry = 0;

        double ileDodajeDoKwoty = (maAbonament && parametry.getCenaZAbonamentem()!=null) ? parametry.getCenaZAbonamentem() : parametry.getCenaPodstawowa();

        while ( doZaplaty + ileDodajeDoKwoty <= maksymalnaKwota){

            if( parametry.getLimitKm() == null || kupioneKilometry <= parametry.getLimitKm()){

                if(maAbonament && parametry.getCenaZAbonamentem() != null){
                    ileDodajeDoKwoty = parametry.getCenaZAbonamentem();
                    doZaplaty += parametry.getCenaZAbonamentem();
                    kupioneKilometry++;
                }
                else{
                    ileDodajeDoKwoty = parametry.getCenaPodstawowa();
                    doZaplaty += parametry.getCenaPodstawowa();
                    kupioneKilometry++;
                }
            }
            else {
                ileDodajeDoKwoty = parametry.getCenaPoLimicie();
                doZaplaty += parametry.getCenaPoLimicie();
                kupioneKilometry++;
            }

        }

        s.setMaksKilometrow(s.getMaksKilometrow() - kupioneKilometry);

        this.zadeklarowanaKwota -= doZaplaty;

        transakcja.add(s, kupioneKilometry);

        return doZaplaty;
    }


    private double prowizja(double val){
        return val*0.01;
    }

    public double pobierzPortfel() {

        double zaokraglonaKwota = this.zadeklarowanaKwota;

        zaokraglonaKwota *= 100.0;

        return Math.round(zaokraglonaKwota) /100.0;

    }

    public void zwroc(TypSamochodu typ, String nazwa, int iloscKm) {

        if(OstatniaTransakcja.czyNalezy(typ, nazwa) && OstatniaTransakcja.getKilometry(typ,nazwa) >= iloscKm){

            Samochod s = OstatniaTransakcja.find(typ,nazwa);

            if ( koszyk_instancja.czyNalezy(s) )
                s.setMaksKilometrow( s.getMaksKilometrow() + iloscKm );
            else
                koszyk_instancja.add(s);

            Cennik cennik = Cennik.pobierzCennik();
            Parametry parametry = cennik.find(typ, nazwa);

            if ( this.maAbonament )
                this.zadeklarowanaKwota += parametry.getCenaZAbonamentem() * iloscKm;
             else
                this.zadeklarowanaKwota += parametry.getCenaPodstawowa() * iloscKm;

             OstatniaTransakcja.clear();
        }
        else
            System.out.println("Niepoprawne dane");

    }

    public String getNazwa(){
        return this.nazwa;
    }

    public boolean getAbonament(){
        return this.maAbonament;
    }
}

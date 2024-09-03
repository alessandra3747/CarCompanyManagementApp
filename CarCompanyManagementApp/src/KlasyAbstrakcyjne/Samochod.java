package KlasyAbstrakcyjne;

import Enumeracje.TypSamochodu;
import Klasy.Cennik;
import Klasy.Klient;
import Klasy.Parametry;

public abstract class Samochod{

    protected String nazwa;
    protected int maksKilometrow;
    protected String typ;
    protected TypSamochodu typEnum;

    public abstract String obliczCene(Klient klient);

    protected String obliczCeneHelper(TypSamochodu typSamochodu, Klient klient){

        Cennik cennik = Cennik.pobierzCennik();

        if(cennik.find(typSamochodu, nazwa) == null)
            return "brak";

        Parametry parametry = cennik.find(typSamochodu, nazwa);

        if ( parametry.getLimitKm() != null ){

            if( this.maksKilometrow <= parametry.getLimitKm() ){

                if ( (klient.getAbonament()) && (parametry.getCenaZAbonamentem() != null) )
                    return parametry.getCenaZAbonamentem() + "";

                else
                    return parametry.getCenaPodstawowa() + "";

            }

            if ( this.maksKilometrow > parametry.getLimitKm() ){

                if( (klient.getAbonament()) && (parametry.getCenaZAbonamentem() != null) )
                    return parametry.getCenaZAbonamentem() + " (do " + parametry.getLimitKm() + "), " + parametry.getCenaPoLimicie() + " (od " + (parametry.getLimitKm() + 1) + ")";

                else
                    return parametry.getCenaPodstawowa() + " (do " + parametry.getLimitKm() + "), " + parametry.getCenaPoLimicie() + " (od " + (parametry.getLimitKm() + 1) + ")";
            }

        }

        if ( parametry.getLimitKm() == null ) {

            if ( (parametry.getCenaZAbonamentem() != null) && (klient.getAbonament()) )
                return parametry.getCenaZAbonamentem() + "";

            else
                return parametry.getCenaPodstawowa() + "";
        }

        return "ERROR";
    }


    public abstract double obliczLacznaCene(Klient klient);

    protected double obliczLacznaCeneHelper(TypSamochodu typSamochodu, Klient klient){

        double lacznaCena = 0.0;

        Cennik cennik = Cennik.pobierzCennik();

        if(cennik.find(typSamochodu, nazwa) == null)
            return 0.0;

        Parametry parametry = cennik.find(typSamochodu, nazwa);

        if ( parametry.getLimitKm() != null ){

            if( this.maksKilometrow <= parametry.getLimitKm() ){

                if ( (klient.getAbonament()) && (parametry.getCenaZAbonamentem() != null) ) {
                    lacznaCena += this.maksKilometrow * parametry.getCenaZAbonamentem();

                    return lacznaCena;
                }

                else {
                    lacznaCena = this.maksKilometrow * parametry.getCenaPodstawowa();

                    return lacznaCena;
                }
            }

            if ( this.maksKilometrow > parametry.getLimitKm() ){

                if( (klient.getAbonament()) && (parametry.getCenaZAbonamentem() != null) ) {
                    lacznaCena += parametry.getLimitKm() * parametry.getCenaZAbonamentem();
                    lacznaCena += (this.maksKilometrow - parametry.getLimitKm()) * parametry.getCenaPoLimicie();

                    return lacznaCena;
                }

                else {
                    lacznaCena += parametry.getLimitKm() * parametry.getCenaPodstawowa();
                    lacznaCena += (this.maksKilometrow - parametry.getLimitKm()) * parametry.getCenaPoLimicie();

                    return lacznaCena;
                }

            }

        }

        if ( parametry.getLimitKm() == null ) {

            if ( (parametry.getCenaZAbonamentem() != null) && (klient.getAbonament()) ) {
                lacznaCena += this.maksKilometrow * parametry.getCenaZAbonamentem();

                return lacznaCena;
            }
            else {
                lacznaCena += this.maksKilometrow * parametry.getCenaPodstawowa();

                return lacznaCena;
            }

        }

        return 0.0;
    }


    public String getNazwa(){
        return this.nazwa;
    }

    public TypSamochodu getTypEnum(){
        return this.typEnum;
    }

    public int getMaksKilometrow(){
        return this.maksKilometrow;
    }


    public void setMaksKilometrow(int maksKilometrow) {
        this.maksKilometrow = maksKilometrow;
    }


    protected TypSamochodu ustalEnum(){

        if(this.typ.equals("osobowy")) return TypSamochodu.OSOBOWY;

        if(this.typ.equals("dostawczy")) return TypSamochodu.DOSTAWCZY;

        if(this.typ.equals("zabytkowy")) return TypSamochodu.ZABYTKOWY;

        return TypSamochodu.DARMO;

    }

    @Override
    public String toString(){
        return nazwa + ", typ: " + typ + ", ile: " + maksKilometrow;
    }

}

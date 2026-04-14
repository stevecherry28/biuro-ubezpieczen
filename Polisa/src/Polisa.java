import java.util.Objects;

public class Polisa {
    private static final double OPLATA_ADMINISTRACYJNA = 100;

    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis;

    Polisa (String numerPolisy, String klient, double skladkaBazowa,
            int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm,
            boolean czyBezszkodowyKlient) {
        setNumerPolisy(numerPolisy);
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }
    public void setNumerPolisy (String numerPolisy) { //setter numerPolisy
        if (numerPolisy == null
                || numerPolisy.isBlank()
                || numerPolisy.startsWith("-")) {
            throw new IllegalArgumentException (
                    "Numer polisy nie może: być pusty, " +
                            "być spacją i zaczynać się liczbą ujmeną. PODANO: " + numerPolisy);
        }

        this.numerPolisy = numerPolisy;
    }

    public String getNumerPolisy () { //getter numerPolisy
        return numerPolisy;
    }
    public String getKlient () {
        return klient;
    }
    public double getSkladkaBazowa () {
        return skladkaBazowa;
    }
    public int getPoziomRyzyka () { //getter Poziom ryzyka
        return poziomRyzyka;
    }
    public double getWartoscPojazdu () {
        return wartoscPojazdu;
    }
    public boolean isCzyMaAlarm () {
        return czyMaAlarm;
    }
    public boolean isCzyBezszkodowyKlient () {
        return czyBezszkodowyKlient;
    }



    public double obliczSkladkeKoncowa() {
        double skladkaKoncowa = skladkaBazowa;
        if (wartoscPojazdu >= 70000.0) {
            skladkaKoncowa += skladkaBazowa * 10.0 / 100.00;
        }  if (czyMaAlarm) {
            skladkaKoncowa -= skladkaBazowa * 5.0 / 100.00 ;
        }  if (czyBezszkodowyKlient) {
            skladkaKoncowa -= skladkaBazowa * 10.0 / 100.00;
        }
        if (skladkaKoncowa < skladkaBazowa) {
            skladkaKoncowa = skladkaBazowa;
        }
        return skladkaKoncowa +  OPLATA_ADMINISTRACYJNA + (poziomRyzyka * 120.0);
    }

    public double obliczSkladkeOdnowieniowa() {
        double kwotaStartowa = this.obliczSkladkeKoncowa();
        double skladkaOdnowieniowa = kwotaStartowa;
        if (wartoscPojazdu >= 70000.0) {
            skladkaOdnowieniowa += 150;
        }
        if (poziomRyzyka == 4) {
            skladkaOdnowieniowa += skladkaOdnowieniowa * 10.0 / 100.00;
        }
        if (poziomRyzyka >= 5) {
            skladkaOdnowieniowa += skladkaOdnowieniowa * 20.0 / 100.00;
        }
        if (czyMaAlarm) {
            skladkaOdnowieniowa -= skladkaOdnowieniowa * 5.0 / 100.00;
        }
        if (czyBezszkodowyKlient) {
            skladkaOdnowieniowa -= skladkaOdnowieniowa * 8.0 / 100.00;
        }
        if (skladkaOdnowieniowa < kwotaStartowa * 90.0 / 100.0) {
            skladkaOdnowieniowa = kwotaStartowa * 90.0 / 100.0;
        }
        if (skladkaOdnowieniowa > kwotaStartowa * 125.0 / 100.0) {
            skladkaOdnowieniowa = kwotaStartowa * 125.0 / 100.0;
        }
        return Math.round(skladkaOdnowieniowa * 100.0) / 100.0;
    }


public String pobierzPodsumowanieRyzyka() {
        if (poziomRyzyka > 0) {
            return "Poziom ryzyka = NISKI";
        } else if (poziomRyzyka > 3 &&  poziomRyzyka < 7) {
            return "Poziom ryzyka = ŚREDNI";
        } else {
            return "Poziom ryzyka = WYSOKI";
        }
}

    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonychPolis;
    }
    @Override
    public String toString() {
        return "Polisa{" +
                "numerPolisy='" + numerPolisy + '\'' +
                ", klient='" + klient + '\'' +
                ", skladkaBazowa=" + skladkaBazowa +
                ", poziomRyzyka=" + poziomRyzyka +
                ", wartoscPojazdu=" + wartoscPojazdu +
                ", czyMaAlarm=" + czyMaAlarm +
                ", czyBezszkodowyKlient=" + czyBezszkodowyKlient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Polisa polisa = (Polisa) o;
        return Objects.equals(numerPolisy, polisa.numerPolisy);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numerPolisy);
    }
}

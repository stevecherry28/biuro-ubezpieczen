import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;

    // Konstruktor
    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }


    public void dodajPolise(Polisa polisa) {
        this.polisy.add(polisa);
    }


    public void wypiszRaport() {
        System.out.println("==== RAPORT BIURA: " + nazwa + " ====");
        for (Polisa p : polisy) {
            System.out.println(p.toString());
            System.out.println("Aktualna składka: " + p.obliczSkladkeKoncowa() + " zł");
            System.out.println("Prognoza odnowienia: " + p.obliczSkladkeOdnowieniowa() + " zł");
            System.out.println("---------------------------------");
        }
    }


    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }


    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeOdnowieniowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }


    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;
        for (Polisa p : polisy) {
            if (p.getPoziomRyzyka() >= 7) {
                licznik++;
            }
        }
        return licznik;
    }


    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numerPolisy)) {
                return p;
            }
        }
        return null;
    }


    public void wypiszTanszeNiz(double prog) {
        System.out.println("==== POLISY TAŃSZE NIŻ " + prog + " zł ====");
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) {
                System.out.println(p.getNumerPolisy() + " | Składka: " + p.obliczSkladkeKoncowa() + " zł");
            }
        }
    }
}
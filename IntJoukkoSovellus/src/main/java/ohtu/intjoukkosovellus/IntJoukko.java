
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono; // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");
        } else if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko väärin");
        }
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
        this.ljono = new int [kapasiteetti]; 
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) return false;
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == ljono.length) {
            int[] taulukkoUusi = new int[ljono.length+kasvatuskoko];
            kopioiTaulukko(ljono, taulukkoUusi);
            ljono = taulukkoUusi;
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                for (int j = i; j < alkioidenLkm; j++) {
                    swap(j);
                }
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void swap(int j) {
        int apu = ljono[j];
        ljono[j] = ljono[j + 1];
        ljono[j + 1] = apu;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i]+ ", ";
        }
        if (alkioidenLkm == 0) return tuotos+= "}";
        return tuotos+ljono[alkioidenLkm - 1]+"}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            x.lisaa(a.toIntArray()[i]);
        }
        for (int i = 0; i < b.toIntArray().length; i++) {
            x.lisaa(b.toIntArray()[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            for (int j = 0; j < b.toIntArray().length; j++) {
                if (a.toIntArray()[i] == b.toIntArray()[j]) {
                    y.lisaa(b.toIntArray()[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            z.lisaa(a.toIntArray()[i]);
        }
        for (int i = 0; i < b.toIntArray().length; i++) {
            z.poista(i);
        }
        return z;
    }
        
}
import java.time.LocalDate;
import java.util.Scanner;

public class PajamuIrasas extends Irasas {
    private int pajamuKategorija;

    public PajamuIrasas(double suma, LocalDate data, String mokejimoBudas, String papildomaInfo, int pajamuKategorija) {
        super(suma, data, mokejimoBudas, papildomaInfo);
        this.pajamuKategorija = pajamuKategorija;
    }

    public PajamuIrasas(Scanner sc) {
        super(sc);
        System.out.print("Įveskite pajamų kategorijos numerį: ");
        try {
            this.pajamuKategorija = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
        }

    }


    @Override
    public String toString() {
        return String.format(Biudzetas.FORMATIRASAS, getId(), getSuma(), getData(), this.pajamuKategorija, getMokejimoBudas(), getPapildomaInfo());
    }

    public int getPajamuKategorija() {
        return pajamuKategorija;
    }

    public void setPajamuKategorija(int pajamuKategorija) {
        this.pajamuKategorija = pajamuKategorija;
    }
}

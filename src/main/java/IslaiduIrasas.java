import java.time.LocalDate;
import java.util.Scanner;

public class IslaiduIrasas extends Irasas {
    private int islaiduKategorija;

    public IslaiduIrasas(double suma, LocalDate data, String mokejimoBudas, String papildomaInfo, int islaiduKategorija) {
        super(suma, data, mokejimoBudas, papildomaInfo);
        this.islaiduKategorija = islaiduKategorija;
        if (getSuma() > 0) {
            this.setSuma(-getSuma());
        }
    }

    public IslaiduIrasas(Scanner sc) {
        super(sc);
        System.out.print("Įveskite išlaidų kategorijos numerį: ");
        try {
            this.islaiduKategorija = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
        }

    }

    @Override
    public String toString() {
        return String.format(Biudzetas.FORMATIRASAS, getId(), getSuma(), getData(), this.islaiduKategorija, getMokejimoBudas(), getPapildomaInfo());
    }

    public int getIslaiduKategorija() {
        return islaiduKategorija;
    }

    public void setIslaiduKategorija(int islaiduKategorija) {
        this.islaiduKategorija = islaiduKategorija;
    }
}

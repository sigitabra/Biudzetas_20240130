import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Irasas {
    private double suma;
    private LocalDate data;
    private String mokejimoBudas;
    private String papildomaInfo;
    private final int id;
    private static int numOfRecords = 0;


    public Irasas(double suma, LocalDate data, String mokejimoBudas, String papildomaInfo) {
        this.suma = suma;
        this.data = data;
        this.mokejimoBudas = mokejimoBudas;
        this.papildomaInfo = papildomaInfo;
        this.id = numOfRecords++;
    }

    public Irasas(int id) {
        this.id = id;
    }

    public Irasas(Scanner sc) {
        System.out.print("Įveskite įrašo sumą: ");
        try {
            this.suma = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
        }

        System.out.print("Įveskite datą (formatu YYYY-MM-DD): ");
        try {
            this.data = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
        }

        System.out.print("Irašo operacija buvo grynais ar pavedimu?");
        this.mokejimoBudas = sc.nextLine();
        System.out.print("Papildoma informacija: ");
        this.papildomaInfo = sc.nextLine();
        this.id = numOfRecords++;

    }

    public Irasas() {
        this.id = numOfRecords++;
    }

    public void redaguotiSumaKonsoleje(Scanner sc) {
        System.out.printf("Suma: %8.2f Eur %n", getSuma());
        System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
        if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
            System.out.print("Įveskite naują reikšmę: ");
            try {
                setSuma(Double.parseDouble(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Neteisingas formatas: " + e.getMessage());
                redaguotiSumaKonsoleje(sc);
            }
        }
    }

    public void redaguotiDataKonsoleje(Scanner sc) {
        System.out.printf("Data: %s %n", getData());
        System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
        if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
            System.out.print("Redaguoti datą (formatu YYYY-MM-DD): ");
            try {
                setData(LocalDate.parse(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Neteisingas formatas: " + e.getMessage());
                redaguotiDataKonsoleje(sc);
            }
        }
    }

    public void redaguotiPiniguFormaKonsoleje(Scanner sc) {
        System.out.printf("Mokėjimo būdas: %s %n", getMokejimoBudas());
        System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
        if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
            System.out.print("Redaguoti mokėjimo būda (grynais/pavedimu): ");
            setMokejimoBudas(sc.nextLine());
        }


    }

    public void redaguotiKategorijaKonsoleje(Scanner sc) {
        if (this instanceof PajamuIrasas) {
            System.out.printf("Pajamų kategorija: %s %n", ((PajamuIrasas) this).getPajamuKategorija());
            System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
            if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
                System.out.print("Redaguoti pajamų kategoriją: ");
                try {
                    ((PajamuIrasas) this).setPajamuKategorija(Integer.parseInt(sc.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Neteisingas formatas: " + e.getMessage());
                    redaguotiKategorijaKonsoleje(sc);
                }
            }

        }

        if (this instanceof IslaiduIrasas) {
            System.out.printf("Išlaidų kategorija: %s %n", ((IslaiduIrasas) this).getIslaiduKategorija());
            System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
            if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
                System.out.print("Redaguoti išlaidų kategoriją: ");
                try {
                    ((IslaiduIrasas) this).setIslaiduKategorija(Integer.parseInt(sc.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Neteisingas formatas: " + e.getMessage());
                    redaguotiKategorijaKonsoleje(sc);
                }
            }

        }
    }

    public void redaguotiPapildomaInfoKonsoleje(Scanner sc) {
        System.out.printf("Papildoma informacija: %s %n", getPapildomaInfo());
        System.out.print("[R] - Redaguoti, [AnyKey] - Toliau: ");
        if (sc.nextLine().toUpperCase().charAt(0) == 'R') {
            System.out.print("Redaguoti papildomą informaciją: ");
            setPapildomaInfo(sc.nextLine());
        }
    }

    @Override
    public String toString() {
        return String.format(Biudzetas.FORMATIRASAS, getId(), getSuma(), getData(), "", getMokejimoBudas(), getPapildomaInfo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Irasas irasas)) return false;
        return getId() == irasas.getId();
    }


    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getMokejimoBudas() {
        return mokejimoBudas;
    }

    public void setMokejimoBudas(String mokejimoBudas) {
        this.mokejimoBudas = mokejimoBudas;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public int getId() {
        return id;
    }

    public static int getNumOfRecords() {
        return numOfRecords;
    }


}

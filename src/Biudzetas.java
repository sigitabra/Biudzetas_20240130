import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Biudzetas {
    private ArrayList<Irasas> irasuSarasas;

    public final static String FORMATIRASAS = " %-3s | %8s Eur | %-10s | %-10s | %-10s | %s %n";

    public Biudzetas() {
        this.irasuSarasas = new ArrayList<>();
    }

    public Biudzetas(ArrayList<Irasas> irasuSarasas) {
        this.irasuSarasas = irasuSarasas;
    }

    public void pridetiIrasa(Irasas irasas) {
        this.irasuSarasas.add(irasas);
    }

    public void pridetiPajamas(PajamuIrasas pajamos) {
        this.irasuSarasas.add(pajamos);
    }

    public void pridetiIslaidas(IslaiduIrasas islaidos) {
        this.irasuSarasas.add(islaidos);
    }

    public void spausdintiVisusIrasus() {
        System.out.printf(FORMATIRASAS, "Id", "Suma", "Data", "Kategorija", "Mokėjimas", "Info");
        for (Irasas i : irasuSarasas) {
            System.out.print(i);
        }
    }


    public ArrayList<PajamuIrasas> gautiPajamuIrasus() {
        ArrayList<PajamuIrasas> pajamos = new ArrayList<>();
        for (Irasas p : irasuSarasas) {
            if (p instanceof PajamuIrasas) {
                pajamos.add((PajamuIrasas) p);
            }
        }
        return pajamos;
    }

    public ArrayList<IslaiduIrasas> gautiIslaiduIrasus() {
        ArrayList<IslaiduIrasas> islaidos = new ArrayList<>();
        for (Irasas i : irasuSarasas) {
            if (i instanceof IslaiduIrasas) {
                islaidos.add((IslaiduIrasas) i);
            }
        }
        return islaidos;
    }

    public void spausdintiVisasPajamas() {
        System.out.printf(FORMATIRASAS, "Id", "Suma", "Data", "Tipas", "Mokėjimas", "Info");
        for (PajamuIrasas p : gautiPajamuIrasus()) {
            System.out.print(p);
        }
    }

    public void spausdintiVisasIslaidas() {
        System.out.printf(FORMATIRASAS, "Id", "Suma", "Data", "Tipas", "Mokėjimas", "Info");
        for (IslaiduIrasas i : gautiIslaiduIrasus()) {
            System.out.print(i);
        }
    }

    public double balansas() {
        double total = 0;
        for (Irasas i : irasuSarasas) {
            total += i.getSuma();
        }
        return total;
    }

    public void trintiIrasa(Scanner sc) {
        spausdintiVisusIrasus();
        System.out.print("Nurodykite įrašo numerį, kurį norite ištrinti: ");
        irasuSarasas.remove(rastiIrasaPagalId(sc));
    }

    public Irasas rastiIrasaPagalId(Scanner sc) {
        try {
            return irasuSarasas.get(irasuSarasas.indexOf(new Irasas(Integer.parseInt(sc.nextLine()))));
        } catch (NumberFormatException e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
            rastiIrasaPagalId(sc);
        }
        return null;
    }

    public void redaguotiIrasaKonsoleje(Scanner sc) {
        spausdintiVisusIrasus();
        System.out.print("Nurodykite įrašo ID, kurį norite redaguoti: ");

        Irasas i = rastiIrasaPagalId(sc);

        i.redaguotiSumaKonsoleje(sc);

        i.redaguotiDataKonsoleje(sc);

        i.redaguotiKategorijaKonsoleje(sc);

        i.redaguotiPiniguFormaKonsoleje(sc);

        i.redaguotiPapildomaInfoKonsoleje(sc);

        System.out.println("Redagavimas baigtas.");

    }

    public void antaujintiIrasa(Scanner sc, Irasas irasas) {
        spausdintiVisusIrasus();
        System.out.print("Nurodykite įrašo ID, kurį norite atnaujinti: ");
        irasuSarasas.set(irasuSarasas.indexOf(rastiIrasaPagalId(sc)), irasas);
    }


    public static void issaugotiDuomenis(ArrayList<Irasas> array, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (Irasas i : array) {
                if (i instanceof PajamuIrasas) {
                    bw.write("P," + ((PajamuIrasas) i).getPajamuKategorija());
                } else if (i instanceof IslaiduIrasas) {
                    bw.write("I," + ((IslaiduIrasas) i).getIslaiduKategorija());
                } else {
                    bw.write(",");
                }
                bw.write("," + i.getId() + "," + i.getSuma() + "," + i.getData() + "," + i.getMokejimoBudas() + "," + i.getPapildomaInfo() + '\n');
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            new File(path).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Irasas> gautiDuomenis(String path) throws IOException {
        ArrayList<Irasas> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals("P")) {
                    array.add(new PajamuIrasas(Double.valueOf(fields[3]), LocalDate.parse(fields[4]), fields[5], fields[5], Integer.valueOf(fields[1])));
                } else if
                (fields[0].equals("I")) {
                    array.add(new IslaiduIrasas(Double.valueOf(fields[3]), LocalDate.parse(fields[4]), fields[5], fields[5], Integer.valueOf(fields[1])));
                } else {
                    array.add(new Irasas(Double.valueOf(fields[3]), LocalDate.parse(fields[4]), fields[5], fields[5]));
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
        return array;
    }

    public static String gautiFailoAdresa(Scanner sc) {
        System.out.print("Įveskite failo adresą: ");
        return sc.nextLine();
    }

    public ArrayList<Irasas> getIrasuSarasas() {
        return irasuSarasas;
    }

    public void setIrasuSarasas(ArrayList<Irasas> irasuSarasas) {
        this.irasuSarasas = irasuSarasas;
    }
}

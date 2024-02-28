import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Programa {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Biudzetas biudzetas = new Biudzetas();
//        biudzetas.pridetiIslaidas(new IslaiduIrasas(450, LocalDate.parse("2024-01-25"), "Grynais", "Komunaliniai", 1));
//        biudzetas.pridetiIslaidas(new IslaiduIrasas(1450, LocalDate.parse("2024-01-05"), "Pavedimu", "Kelione", 2));
//        biudzetas.pridetiPajamas(new PajamuIrasas(3000, LocalDate.parse("2024-01-05"), "Pavedimu", "Atlyginimas", 3));
//        biudzetas.pridetiPajamas(new PajamuIrasas(20, LocalDate.parse("2024-01-25"), "Grynais", "Skola", 4));
//        biudzetas.antaujintiIrasa(sc, new PajamuIrasas(3550, LocalDate.parse("2024-05-25"), "Grynais", "NaujasAtlyginimas", 4));

        try {
            kviestiFunkcijas(sc, biudzetas);
        } catch (Exception e) {
            System.out.println("Neteisingas formatas: " + e.getMessage());
            kviestiFunkcijas(sc, biudzetas);
        }

        sc.close();

    }

    public static void kviestiFunkcijas(Scanner sc, Biudzetas biudzetas) throws IOException {

        boolean until = true;

        while (until) {
            ProgramosFunkcijos.spausdinti();
            String CurrentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            switch (ProgramosFunkcijos.valueOf(sc.nextLine().toUpperCase().substring(0, 2))) {
                case II -> biudzetas.pridetiIslaidas(new IslaiduIrasas(sc));
                case IP -> biudzetas.pridetiPajamas(new PajamuIrasas(sc));
                case AI -> biudzetas.spausdintiVisasIslaidas();
                case AP -> biudzetas.spausdintiVisasPajamas();
                case AB -> System.out.printf("[%s] Balansas: %,.2f Eur%n", CurrentTime, biudzetas.balansas());
                case TI -> biudzetas.trintiIrasa(sc);
                case RI -> biudzetas.redaguotiIrasaKonsoleje(sc);
                case NI -> biudzetas.antaujintiIrasa(sc, new Irasas(sc));
                case SF -> Biudzetas.issaugotiDuomenis(biudzetas.getIrasuSarasas(), Biudzetas.gautiFailoAdresa(sc));
                case GF -> biudzetas.setIrasuSarasas(Biudzetas.gautiDuomenis(Biudzetas.gautiFailoAdresa(sc)));
                case EX -> until = false;
            }
        }

    }
}
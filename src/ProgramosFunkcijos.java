import static java.lang.System.out;

public enum ProgramosFunkcijos {
    IP("Įvesti pajamas"),
    II("Įvesti išlaidas"),
    AP("Peržiūrėti pajamų ataskaitą"),
    AI("Peržiūrėti išlaidų ataskaitą"),
    AB("Peržiūrėti balansą"),
    TI("Trinti įrašą"),
    RI("Redaguoti įrašą"),
    NI("Naujinti įrašą"),
    SF("Saugoti duomenis į failą"),
    GF("Gauti duomenis iš failo"),
    EX("Uždaryti programą");

    private final String aprasymas;

    ProgramosFunkcijos(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public static void spausdinti() {

        out.println("\nPasirinkite norimą veiksmą:");
        for (ProgramosFunkcijos f : ProgramosFunkcijos.values()) {
            out.printf("[%1s] - %s%n", f, f.aprasymas);
        }
    }
}

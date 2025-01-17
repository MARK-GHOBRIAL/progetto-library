package Library;

public class Rivista extends Elemento {
    private String periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public String getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}

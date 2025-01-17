package Library;

import java.util.*;
import java.util.stream.*;

public class Archivio {
    private Map<String, Elemento> catalogo;

    public Archivio() {
        catalogo = new HashMap<>();
    }

    // Aggiunta di un elemento
    public void aggiungiElemento(Elemento elemento) throws Exception {
        if (catalogo.containsKey(elemento.getIsbn())) {
            throw new Exception("Elemento con ISBN " + elemento.getIsbn() + " già presente.");
        }
        catalogo.put(elemento.getIsbn(), elemento);
    }

    // Ricerca per ISBN
    public Elemento ricercaPerIsbn(String isbn) throws Exception {
        Elemento elemento = catalogo.get(isbn);
        if (elemento == null) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        return elemento;
    }

    // Rimozione di un elemento per ISBN
    public void rimuoviElemento(String isbn) throws Exception {
        if (!catalogo.containsKey(isbn)) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        catalogo.remove(isbn);
    }

    // Ricerca per anno di pubblicazione
    public List<Elemento> ricercaPerAnnoPubblicazione(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore
    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiornamento di un elemento
    public void aggiornaElemento(String isbn, Elemento nuovoElemento) throws Exception {
        if (!catalogo.containsKey(isbn)) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        catalogo.put(isbn, nuovoElemento);
    }

    // Statistiche del catalogo
    public void statisticheCatalogo() {
        long numeroLibri = catalogo.values().stream().filter(e -> e instanceof Libro).count();
        long numeroRiviste = catalogo.values().stream().filter(e -> e instanceof Rivista).count();
        Elemento maxPagine = catalogo.values().stream()
                .max(Comparator.comparingInt(Elemento::getNumeroPagine))
                .orElse(null);
        double mediaPagine = catalogo.values().stream()
                .mapToInt(Elemento::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.println("Numero di libri: " + numeroLibri);
        System.out.println("Numero di riviste: " + numeroRiviste);
        System.out.println("Elemento con maggior numero di pagine: " + (maxPagine != null ? maxPagine.toString() : "N/A"));
        System.out.println("Media pagine: " + mediaPagine);
    }
}


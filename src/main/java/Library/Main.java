package Library;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            System.out.println("1. Aggiungi Elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi Elemento");
            System.out.println("4. Ricerca per Anno");
            System.out.println("5. Ricerca per Autore");
            System.out.println("6. Aggiorna Elemento");
            System.out.println("7. Statistiche");
            System.out.println("8. Esci");
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuma la newline

            try {
                switch (scelta) {
                    case 1:
                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Inserisci Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Inserisci Anno di pubblicazione: ");
                        int anno = scanner.nextInt();
                        System.out.print("Inserisci Numero di pagine: ");
                        int pagine = scanner.nextInt();
                        scanner.nextLine();  // Consuma la newline

                        System.out.print("Inserisci tipo (1 = Libro, 2 = Rivista): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();  // Consuma la newline

                        if (tipo == 1) {
                            System.out.print("Inserisci Autore: ");
                            String autore = scanner.nextLine();
                            System.out.print("Inserisci Genere: ");
                            String genere = scanner.nextLine();
                            Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
                            archivio.aggiungiElemento(libro);
                        } else if (tipo == 2) {
                            System.out.print("Inserisci Periodicità (settimanale, mensile, semestrale): ");
                            String periodicita = scanner.nextLine();
                            Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
                            archivio.aggiungiElemento(rivista);
                        }
                        break;

                    case 2:
                        System.out.print("Inserisci ISBN da cercare: ");
                        String ricercaIsbn = scanner.nextLine();
                        Elemento elemento = archivio.ricercaPerIsbn(ricercaIsbn);
                        System.out.println(elemento);
                        break;

                    case 3:
                        System.out.print("Inserisci ISBN da rimuovere: ");
                        String isbnRimuovi = scanner.nextLine();
                        archivio.rimuoviElemento(isbnRimuovi);
                        System.out.println("Elemento rimosso.");
                        break;

                    case 4:
                        System.out.print("Inserisci anno di pubblicazione: ");
                        int annoRicerca = scanner.nextInt();
                        scanner.nextLine();  // Consuma la newline
                        List<Elemento> elementiAnno = archivio.ricercaPerAnnoPubblicazione(annoRicerca);
                        elementiAnno.forEach(System.out::println);
                        break;

                    case 5:
                        System.out.print("Inserisci autore da cercare: ");
                        String autoreRicerca = scanner.nextLine();
                        List<Libro> libriAutore = archivio.ricercaPerAutore(autoreRicerca);
                        libriAutore.forEach(System.out::println);
                        break;

                    case 6:
                        System.out.print("Inserisci ISBN da aggiornare: ");
                        String isbnAggiorna = scanner.nextLine();
                        System.out.print("Inserisci nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();
                        Elemento nuovoElemento = new Libro(isbnAggiorna, nuovoTitolo, 2025, 100, "Nuovo Autore", "Nuovo Genere");
                        archivio.aggiornaElemento(isbnAggiorna, nuovoElemento);
                        break;

                    case 7:
                        archivio.statisticheCatalogo();
                        break;

                    case 8:
                        System.out.println("Arrivederci!");
                        return;

                    default:
                        System.out.println("Opzione non valida.");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}


package it.epicode;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.epicode.dao.CatalogoDAO;
import it.epicode.dao.PrestitoDAO;
import it.epicode.entities.library.Elemento;
import it.epicode.entities.library.Libro;
import it.epicode.entities.library.Periodicità;
import it.epicode.entities.library.Rivista;
import it.epicode.entities.prestito.Prestito;
import it.epicode.entities.prestito.Utente;

public class Program {
	private static final Logger log = LoggerFactory.getLogger(Program.class);

	public static void popolazione() {
		Elemento elemento1 = new Libro("987-543-104-2", "Il signore degli anelli", "1955", 1200, "J.R.R. Tolkien", "Fantasy");
		Elemento elemento2 = new Rivista("009-687-819-4", "Focus", "2021", 60, Periodicità.MENSILE);
		Elemento elemento3 = new Libro("736-014-623-5", "Il giro del mondo in 80 giorni", "1873", 300, "Jules Verne", "Avventura");
		Elemento elemento4 = new Rivista("728-366-990-1", "Topolino", "2019", 100, Periodicità.SETTIMANALE);
		Elemento elemento5 = new Libro("267-892-541-2", "Alla ricerca del tempo perduto", "1927", 3000, "Marcel Proust", "Narrativa");

		var ServiceCatalogo = new CatalogoDAO();

		ServiceCatalogo.aggiungiElemento(elemento1);
		ServiceCatalogo.aggiungiElemento(elemento2);
		ServiceCatalogo.aggiungiElemento(elemento3);
		ServiceCatalogo.aggiungiElemento(elemento4);
		ServiceCatalogo.aggiungiElemento(elemento5);

		Utente utente1 = new Utente("Luca", "Ward", LocalDate.of(1985, 1, 19), 3266732);
		Utente utente2 = new Utente("Gianni", "Morandi", LocalDate.of(1990, 8, 3), 3266733);
		Utente utente3 = new Utente("Walter", "White", LocalDate.of(1999, 12, 21), 3266734);

		Prestito prestito1 = new Prestito(utente1, elemento1, LocalDate.now());
		Prestito prestito2 = new Prestito(utente1, elemento3, LocalDate.of(2000, 5, 21));
		Prestito prestito3 = new Prestito(utente1, elemento2, LocalDate.of(2011, 5, 21), LocalDate.of(2011, 5, 25));

		var ServicePrestito = new PrestitoDAO();

		ServicePrestito.aggiungiUnUtente(utente1);
		ServicePrestito.aggiungiUnUtente(utente2);
		ServicePrestito.aggiungiUnUtente(utente3);
		ServicePrestito.aggiungiUnPrestito(prestito1);
		ServicePrestito.aggiungiUnPrestito(prestito2);
		ServicePrestito.aggiungiUnPrestito(prestito3);

	}

	public static void main(String[] args) {

		popolazione();

		var ServiceCatalogo = new CatalogoDAO();
		var ServicePrestito = new PrestitoDAO();
		
		ServiceCatalogo.rimuoviElemento("267-892-541-2");

		log.info("-----RICERCA PER ISBN-----");
		Elemento el = ServiceCatalogo.ricercaPerISBN("987-543-104-2");
		log.info("{}", el);

		log.info("-----RICERCA PER ANNO-----");
		List<Elemento> lista1 = ServiceCatalogo.ricercaPerAnno("2019");
		for (Elemento e : lista1) {
			log.info("{}", e);
		}

		log.info("-----RICERCA PER AUTORE-----");
		List<Libro> lista2 = ServiceCatalogo.ricercaPerAutore("Marcel Proust");
		for (Libro e : lista2) {
			log.info("{}", e);
		}

		log.info("-----RICERCA PER TITOLO-----");
		List<Elemento> lista3 = ServiceCatalogo.ricercaPerTitolo("Il signore degli anelli");
		for (Elemento e : lista3) {
			log.info("{}", e);
		}

		log.info("-----RICERCA PRESTITO PER N TESSERA-----");
		List<Prestito> listaP1 = ServicePrestito.ricercaPrestitoPerNTessera(3266732);
		for (Prestito p : listaP1) {
			log.info("{}", p);
		}

		log.info("-----RICERCA PRESTITI SCADUTI E NON RESTITUITI-----");
		List<Prestito> listaP2 = ServicePrestito.ricercaPrestitiScadutiNonRestituiti();
		for (Prestito p : listaP2) {
			log.info("{}", p);
		}
	}
}

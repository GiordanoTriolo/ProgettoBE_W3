package it.epicode.entities.prestito;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import it.epicode.entities.library.Elemento;

@Entity
@NamedQuery(name = Prestito.QUERY_SELECT_RICERCA_PRESTITO, query = "SELECT a FROM Prestito a WHERE a.utente.numeroDiTessera = :ntessera "
		+ "AND a.dataRestituzioneEffettiva IS NULL")
@NamedQuery(name = Prestito.QUERY_SELECT_RICERCA_PRESTITO_SCADUTO, query = "SELECT a FROM Prestito a WHERE a.dataRestituzioneEffettiva IS NULL "
		+ "AND a.dataRestituzionePrevista < :data")
public class Prestito {
	public static final String QUERY_SELECT_RICERCA_PRESTITO = "QUERY_SELECT_RICERCA_PRESTITO";
	public static final String QUERY_SELECT_RICERCA_PRESTITO_SCADUTO = "QUERY_SELECT_RICERCA_PRESTITO_SCADUTO";

	private int id;
	private Utente utente;
	private Elemento elementoPrestato;
	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzionePrevista; // (calcolata automaticamente a 30 gg dalla data inizio prestito)
	private LocalDate dataRestituzioneEffettiva;

	public Prestito(Utente utente, Elemento elementoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzioneEffettiva) {
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public Prestito(Utente utente, Elemento elementoPrestato, LocalDate dataInizioPrestito) {
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);

	}

	public Prestito() {
	}

	@ManyToOne
	public Utente getUtente() {
		return utente;
	}

	@OneToOne
	public Elemento getElementoPrestato() {
		return elementoPrestato;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public LocalDate getDataRestituzionePrevista() {
		return dataRestituzionePrevista;
	}

	public LocalDate getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setElementoPrestato(Elemento elementoPrestato) {
		this.elementoPrestato = elementoPrestato;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
		this.dataRestituzionePrevista = dataRestituzionePrevista;
	}

	public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrevista="
				+ dataRestituzionePrevista + ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}

package it.epicode.entities.library;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Catalogo", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = Elemento.QUERY_SELECT_RICERCA_ISBN, query = "SELECT a FROM Elemento a WHERE a.codiceISBN = :isbn")
@NamedQuery(name = Elemento.QUERY_SELECT_RICERCA_ANNO, query = "SELECT a FROM Elemento a WHERE a.annoPublicazione = :anno")
@NamedQuery(name = Elemento.QUERY_SELECT_RICERCA_TITOLO, query = "SELECT a FROM Elemento a WHERE a.titolo LIKE :titolo")
public abstract class Elemento {
	public static final String QUERY_SELECT_RICERCA_ISBN = "QUERY_SELECT_RICERCA_ISBN";
	public static final String QUERY_SELECT_RICERCA_ANNO = "QUERY_SELECT_RICERCA_ANNO";
	public static final String QUERY_SELECT_RICERCA_TITOLO = "QUERY_SELECT_RICERCA_TITOLO";

	protected int id;
	protected String codiceISBN;
	protected String titolo;
	protected String annoPublicazione;
	protected int nPagine;

	public Elemento(String codiceISBN, String titolo, String annoPublicazione, int nPagine) {
		super();
		this.codiceISBN = codiceISBN;
		this.titolo = titolo;
		this.annoPublicazione = annoPublicazione;
		this.nPagine = nPagine;
	}

	public Elemento() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@Column(length = 15, nullable = false)
	public String getCodiceISBN() {
		return codiceISBN;
	}

	@Column(length = 50, nullable = false)
	public String getTitolo() {
		return titolo;
	}

	@Column(length = 5, nullable = false)
	public String getAnnoPublicazione() {
		return annoPublicazione;
	}

	public int getnPagine() {
		return nPagine;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCodiceISBN(String codiceISBN) {
		this.codiceISBN = codiceISBN;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setAnnoPublicazione(String annoPublicazione) {
		this.annoPublicazione = annoPublicazione;
	}

	public void setnPagine(int nPagine) {
		this.nPagine = nPagine;
	}

	@Override
	public String toString() {
		return "Elemento [id=" + id + ", codiceISBN=" + codiceISBN + ", titolo=" + titolo + ", annoPublicazione="
				+ annoPublicazione + ", nPagine=" + nPagine + "]";
	}

}

package it.epicode.entities.library;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends Elemento {

	Periodicità periodicità;

	public Rivista(String codiceISBN, String titolo, String annoPublicazione, int nPagine, Periodicità periodicità) {
		super(codiceISBN, titolo, annoPublicazione, nPagine);
		this.periodicità = periodicità;
	}

	public Rivista() {
	}

	public Rivista(String codiceISBN, String titolo, String annoPublicazione, int nPagine) {
		super(codiceISBN, titolo, annoPublicazione, nPagine);
	}

	public Rivista(Periodicità periodicità) {
		this();
		this.periodicità = periodicità;
	}

	@Column(length = 15, nullable = true)
	public Periodicità getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità(Periodicità periodicità) {
		this.periodicità = periodicità;
	}

	@Override
	public String toString() {
		return "Rivista [periodicità=" + periodicità + ", codiceISBN=" + codiceISBN + ", titolo=" + titolo
				+ ", annoPublicazione=" + annoPublicazione + ", nPagine=" + nPagine + "]@";
	}

}

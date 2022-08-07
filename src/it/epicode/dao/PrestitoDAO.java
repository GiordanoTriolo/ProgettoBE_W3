package it.epicode.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.epicode.entities.prestito.Prestito;
import it.epicode.entities.prestito.Utente;
import it.epicode.utils.JpaUtil;

public class PrestitoDAO {
	private final Logger log = LoggerFactory.getLogger(getClass());
	EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public void aggiungiUnPrestito(Prestito prestito) {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(prestito);
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception try to check aggiungiUnPrestito method");
		} finally {
			em.close();
		}
	}

	public void aggiungiUnUtente(Utente utente) {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(utente);
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception try to check aggiungiUnUtente method");
		} finally {
			em.close();
		}
	}

	public List<Prestito> ricercaPrestitoPerNTessera(int numeroDiTessera) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Prestito.QUERY_SELECT_RICERCA_PRESTITO, Prestito.class);
			query.setParameter("ntessera", numeroDiTessera);
			List<Prestito> lis = query.getResultList();
			return lis;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPrestitoPerNTessera method");
			return new ArrayList<Prestito>();
		} finally {
			em.close();
		}
	}

	public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Prestito.QUERY_SELECT_RICERCA_PRESTITO_SCADUTO, Prestito.class);
			query.setParameter("data", LocalDate.now());
			List<Prestito> lis = query.getResultList();
			return lis;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPrestitiScadutiNonRestituiti method");
			return new ArrayList<Prestito>();
		} finally {
			em.close();
		}
	}
}

package it.epicode.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.epicode.entities.library.Elemento;
import it.epicode.entities.library.Libro;
import it.epicode.utils.JpaUtil;

public class CatalogoDAO {
	private final Logger log = LoggerFactory.getLogger(getClass());
	EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public void aggiungiElemento(Elemento elemento) {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(elemento);
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception try to check aggiungiElemento method");
		} finally {
			em.close();
		}
	}

	public void rimuoviElemento(String isbn) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Elemento.QUERY_SELECT_RICERCA_ISBN, Elemento.class);
			query.setParameter("isbn", isbn);
			Elemento el = query.getSingleResult();
			Elemento i = em.find(Elemento.class, el.getId());
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(i);
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception try to check rimuoviElemento method");
		} finally {
			em.close();
		}

	}

	public Elemento ricercaPerISBN(String isbn) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Elemento.QUERY_SELECT_RICERCA_ISBN, Elemento.class);
			query.setParameter("isbn", isbn);
			Elemento el = query.getSingleResult();
			return el;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPerISBN method");
			return null;
		} finally {
			em.close();
		}
	}

	public List<Elemento> ricercaPerAnno(String anno) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Elemento.QUERY_SELECT_RICERCA_ANNO, Elemento.class);
			query.setParameter("anno", anno);
			List<Elemento> lis = query.getResultList();
			return lis;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPerAnno method");
			return new ArrayList<Elemento>();
		} finally {
			em.close();
		}
	}

	public List<Libro> ricercaPerAutore(String autore) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Libro.QUERY_SELECT_RICERCA_AUTORE, Libro.class);
			query.setParameter("autore", autore);
			List<Libro> lis = query.getResultList();
			return lis;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPerAutore method");
			return new ArrayList<Libro>();
		} finally {
			em.close();
		}
	}

	public List<Elemento> ricercaPerTitolo(String titolo) {
		EntityManager em = emf.createEntityManager();
		try {
			var query = em.createNamedQuery(Elemento.QUERY_SELECT_RICERCA_TITOLO, Elemento.class);
			query.setParameter("titolo", "%" + titolo + "%");
			List<Elemento> lis = query.getResultList();
			return lis;
		} catch (Exception e) {
			log.error("Exception try to check ricercaPerTitolo method");
			return new ArrayList<Elemento>();
		} finally {
			em.close();
		}
	}

}

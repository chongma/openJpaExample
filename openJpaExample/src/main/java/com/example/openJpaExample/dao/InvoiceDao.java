package com.example.openJpaExample.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.example.openJpaExample.entities.Invoice;

@ApplicationScoped
public class InvoiceDao {

	private static final Logger log = Logger.getLogger(InvoiceDao.class.getName());

	@Inject
	private EntityManager em;

	public void create() {
		for (int i = 0; i < 10; i++) {
			log.info("BLAH: " + i);
			Invoice invoice = new Invoice();
			invoice.setName("BLAH " + i);
			em.persist(invoice);
		}
	}

	public List<Invoice> select() {
		return (List<Invoice>) em.createQuery("SELECT i FROM Invoice i").getResultList();
	}

}

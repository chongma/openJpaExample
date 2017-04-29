package com.example.openJpaExample.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.example.openJpaExample.entities.Invoice;
import com.example.openJpaExample.entities.InvoiceItem;

@ApplicationScoped
@Transactional
public class InvoiceDao {

	private static final Logger log = Logger.getLogger(InvoiceDao.class.getName());

	@Inject
	private EntityManager em;

	public void create(String name) {
		log.info(name);
		Invoice invoice = new Invoice();
		invoice.setName(name);
		em.persist(invoice);
	}

	public List<Invoice> selectInvoicesAll() {
		return em.createQuery("SELECT i FROM Invoice i", Invoice.class).getResultList();
	}

	public Invoice findInvoice(long id) {
		return em.find(Invoice.class, id);
	}

	public void create(Invoice invoice, String name, BigDecimal value) {
		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName(name);
		invoiceItem.setValue(value);
		invoiceItem.setInvoice(invoice);
		if (invoice.getInvoiceItem() == null)
			invoice.setInvoiceItem(new ArrayList<>());
		invoice.getInvoiceItem().add(invoiceItem);
		em.persist(invoiceItem);
		em.merge(invoice);
	}

	public void deleteInvoice(Invoice invoice) {
		log.fine("DELETE: " + invoice.getId());
		invoice = em.merge(invoice);
		em.remove(invoice);
	}

	public void deleteInvoiceItem(Invoice invoice, InvoiceItem invoiceItem) {
		log.info("Deleting InvoiceItem: " + invoiceItem.getId());
		invoice = em.merge(invoice);
		invoiceItem = em.merge(invoiceItem);
		invoice.getInvoiceItem().remove(invoiceItem);
		em.remove(invoiceItem);
	}

	public void delete(Invoice invoice, InvoiceItem invoiceItem) {
		invoice.getInvoiceItem().remove(invoiceItem);
		em.merge(invoice);
	}

	public InvoiceItem findInvoiceItem(long id) {
		return em.find(InvoiceItem.class, id);
	}

}

package com.example.openJpaExample.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.openJpaExample.dao.InvoiceDao;
import com.example.openJpaExample.entities.Invoice;
import com.example.openJpaExample.entities.InvoiceItem;

@Named
@ViewScoped
public class InvoiceBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private InvoiceDao invoiceDao;

	private long id;
	private Invoice invoice;
	private InvoiceItem invoiceItem;

	public void onload() {
		update();
	}

	private void update() {
		setInvoice(invoiceDao.findInvoice(id));
	}

	public String create() {
		for (int i = 0; i < 10; i++) {
			invoiceDao.create(invoice, "INVOICE ITEM: " + i, BigDecimal.TEN);
		}
		// update();
		return null;
	}

	public String delete() {
		invoiceDao.deleteInvoiceItem(invoice, invoiceItem);
		update();
		return null;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public InvoiceItem getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

}

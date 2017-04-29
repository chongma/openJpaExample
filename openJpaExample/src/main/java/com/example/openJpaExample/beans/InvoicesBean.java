package com.example.openJpaExample.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.openJpaExample.dao.InvoiceDao;
import com.example.openJpaExample.entities.Invoice;

@Named
@ViewScoped
public class InvoicesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private InvoiceDao invoiceDao;

	private List<Invoice> invoiceList;
	private Invoice invoice;

	public void onload() {
		update();
	}

	private void update() {
		setInvoiceList(invoiceDao.selectInvoicesAll());
	}

	public String create() {
		for (int i = 0; i < 10; i++) {
			invoiceDao.createInvoice("INVOICE: " + i);
		}
		update();
		return null;
	}

	public String delete() {
		invoiceDao.deleteInvoice(invoice);
		update();
		return null;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}

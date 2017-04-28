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
public class IndexBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private InvoiceDao invoiceDao;

	private List<Invoice> invoiceList;

	public void onload() {
		update();
	}

	private void update() {
		setInvoiceList(invoiceDao.select());
	}

	public String create() {
		invoiceDao.create();
		update();
		return null;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

}

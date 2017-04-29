package com.example.openJpaExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Version
	private long version;

	@Lob
	private String name;

	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> invoiceItem;

	public List<InvoiceItem> getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

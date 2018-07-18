package br.com.caelum.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Price 
{
	@Enumerated(EnumType.STRING)
	private BookType booktype;
	@Column(scale=2)
	private BigDecimal price;
	
	public BookType getBooktype() {
		return booktype;
	}
	public void setBooktype(BookType booktype) {
		this.booktype = booktype;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Price [booktype=" + booktype + ", price=" + price + "]";
	}
	
	
}

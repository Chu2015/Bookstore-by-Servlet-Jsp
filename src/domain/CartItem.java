package domain;

public class CartItem {

	private Book book;
	private double price;
	private int quantity;

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		this.price = this.quantity*this.book.getPrice();
		return price;
	}
	
}

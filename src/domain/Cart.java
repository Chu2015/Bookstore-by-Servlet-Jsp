package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//键为书的id号，value为购物车项。
	private Map<String, CartItem> map = new LinkedHashMap<String,CartItem>();
	private double price=0;
	
	public void add(Book book){
		String bookid = book.getId();
		CartItem cartitem = map.get(bookid);
		if(cartitem==null){
			cartitem=new CartItem();
			cartitem.setBook(book);
			cartitem.setQuantity(1);
			map.put(bookid, cartitem);
		}else{
			cartitem.setQuantity(cartitem.getQuantity()+1);
		}
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getPrice() {	
//		for(Map.Entry<String, CartItem> entry : map.entrySet()){
//			this.price += entry.getValue().getPrice();
//			System.out.println(this.price);
//		}
//		return this.price;
		double totalprice = 0;
		for(Map.Entry<String, CartItem> entry : map.entrySet()){
			totalprice += entry.getValue().getPrice();
		}
		this.price = totalprice;
		return price;
	}
}

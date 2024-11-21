package amazonsystem;

import amazonsystem.AmazonProduct.*;

public class AmazonCartItem{
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct newProduct, int newQuantity) {
		this.product = newProduct;
		this.quantity = newQuantity;
	}
	
	public AmazonProduct getItem() {
		return product;
	}
	
	public float calcSubTotal() {
		float total = product.getActualPrice() * quantity;
		return total;
	}
	
	@Override
	public String toString() {
		String returnString;
		
		returnString = quantity + " of " + product.getName();
		return returnString;
	}
}

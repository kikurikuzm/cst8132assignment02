package amazonsystem;

import amazonsystem.AmazonProduct.*;

public class AmazonCartItem{
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct newProduct, int newQuantity) {
		this.product = newProduct;
		this.quantity = newQuantity;
	}
	
	public float calcSubTotal() {
		float total = product.getDiscountPrice() * quantity;
		return total;
	}
	
	@Override
	public String toString() {
		return new String();
	}
}

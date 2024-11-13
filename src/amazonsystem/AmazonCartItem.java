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
		return 0.0f;
	}
	
	@Override
	public String toString() {
		return new String();
	}
}

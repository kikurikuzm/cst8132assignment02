package amazonsystem;

import java.util.Date;
import java.util.ArrayList;

import amazonsystem.AmazonProduct.*;

public class AmazonCart implements AmazonPayable {
	
	private AmazonCustomer customer;
	private Date date;
	private ArrayList<AmazonCartItem> items;
	private float orderValue;
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.customer = customer;
		this.date = date;
	}
	
	public float calcSubTotal() {
		return 0.0f;
	}
	
	public AmazonCartItem getItem(int itemIndex) {
		
	}
	
	public boolean hasItem(AmazonProduct desiredItem) {
		
	}
	
	@Override
	public boolean pay(float amount) {
		return false;//placeholder
	}
	
	public void addItem(AmazonCartItem newItem) {
		
	}
	
	public void removeItem(AmazonProduct desiredItem) {
		
	}
	
	public String getCartDetails() {
		
	}
	
	@Override
	public String toString() {
		
	}
}

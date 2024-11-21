package amazonsystem;

import java.util.Date;
import java.util.ArrayList;

import amazonsystem.AmazonProduct.*;

public class AmazonCart implements AmazonPayable {
	
	private AmazonCustomer customer;
	private Date date;
	private ArrayList<AmazonCartItem> items;
	private float orderValue;
	
	public AmazonCart() {
		this.date = new Date();
		this.items = new ArrayList<AmazonCartItem>();
	}
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.customer = customer;
		this.date = date;
	}
	
	public float calcSubTotal() {
		orderValue = 0.0f;
		
		for(AmazonCartItem curItem: items) {
			orderValue += curItem.calcSubTotal();
		}
		
		return orderValue;
	}
	
	public AmazonCartItem getItem(int itemIndex) throws AmazonException{
		if(itemIndex > items.size() || itemIndex < 0) {
			throw new AmazonException("Item not found in cart.");
		} else {
			return items.get(itemIndex);
		}

	}
	
	public boolean hasItem(AmazonProduct desiredItem) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getItem().equals(desiredItem)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean pay(float amount) {
		if(amount >= orderValue) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addItem(AmazonCartItem newItem) {
		items.add(newItem);
	}
	
	public void removeItem(AmazonProduct desiredItem) throws AmazonException{
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getItem().equals(desiredItem)) {
				items.remove(i);
				return;
			}
		}
		
		throw new AmazonException("Item not found.");
	}
	
	public String[] listItems() {
		ArrayList<String> itemStrings = new ArrayList<String>();
		String[] returnString;
		
		for(AmazonCartItem curCartItem: items) {
			itemStrings.add(curCartItem.toString());
		}
		
		returnString = new String[itemStrings.size()];
		
		for(int i = 0; i < itemStrings.size(); i++) {
			returnString[i] = itemStrings.get(i);
		}
		
		return returnString;
	}
	
	public String getCartDetails() {
		String cartDetails;
		
		cartDetails = String.format("This cart contains %i items, has a sub-total of $%.1f, and was created on %s.", items.size(), this.calcSubTotal(), date.getDate());
		
		//TODO "This cart contains %i items, has a sub-total of %f, and was created on %s (date)."
		return new String();
	}
	
	@Override
	public String toString() {
		return new String();
	}
}

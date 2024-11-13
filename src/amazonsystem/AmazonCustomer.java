package amazonsystem;

import amazonsystem.AmazonMoney.*;
import amazonsystem.AmazonProduct.*;

public class AmazonCustomer {
	private int id;
	private String name;
	private String address;
	
	private AmazonCustomer(int newID, String newName, String newAddress) {
		this.id = newID;
		this.name = newName;
		this.address = newAddress;
	}
	
	public AmazonCustomer createAmazonCustomer(String[] newCustomer) {
		int newID = Integer.valueOf(newCustomer[0]);
		String newName = newCustomer[1];
		String newAddress = newCustomer[2];
		
		return new AmazonCustomer(newID, newName, newAddress);
	}
	
	public void addCredit(AmazonCredit newCredit) {
		
	}
	
	public void showCredits() {
		
	}
	
	public void addProductInWishList(AmazonProduct newProduct) {
		
	}
	
	public void removeProductFromWishList(AmazonProduct desiredProduct) {
		
	}
	
	public boolean isProductInWishList(AmazonProduct desiredProduct) {
		return false; //placeholder
	}
	
	public void showWishList() {
		
	}
	
	public void addItemInCart(AmazonCartItem newItem) {
		
	}
	
	public void removeProductFromCart(AmazonProduct desiredProduct) {
		
	}
	
	public void showCart() {
		
	}
	
	public void pay(AmazonCredit payment) {
		
	}
	
	public void moveFromCartToComments() {
		
	}
	
	public boolean hasProductToComment(AmazonProduct desiredProduct) {
		return true;//placeholder
	}
	
	public void addComment(AmazonComment newComment) {
		
	}
	
	public void setComment(AmazonProduct desiredProduct, String comment, float rating) {
		
	}
	
	public void showComments() {
		
	}
	
	@Override
	public String toString() {
		return new String();//placeholder
	}
}

package amazonsystem;

import java.util.ArrayList;
import amazonsystem.AmazonMoney.*;
import amazonsystem.AmazonProduct.*;

public class AmazonCustomer {
	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> credits;
	private ArrayList<AmazonProduct> wishlist;
	private ArrayList<AmazonComment> comments;
	private AmazonCart cart;
	
	private AmazonCustomer(int newID, String newName, String newAddress) {
		this.id = newID;
		this.name = newName;
		this.address = newAddress;
		
		this.credits = new ArrayList<AmazonCredit>();
		this.wishlist = new ArrayList<AmazonProduct>();
		this.comments= new ArrayList<AmazonComment>();
		this.cart = new AmazonCart();
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] newCustomer) throws AmazonException{
		if(newCustomer.length != 3) {
			throw new AmazonException("Invalid length of String list. Should have only three indices.");
		}
		
		if(AmazonUtil.isValidInt(newCustomer[0]) == false) {
			throw new AmazonException("Invalid int given for ID.");
		}
		
		if(AmazonUtil.isValidString(newCustomer[1]) == false) {
			throw new AmazonException("Invalid sting given for name.");
		}
		
		if(AmazonUtil.isValidString(newCustomer[2]) == false) {
			throw new AmazonException("Invalid string given for address.");
		}
		
		int newID = Integer.valueOf(newCustomer[0]);
		String newName = newCustomer[1];
		String newAddress = newCustomer[2];
		
		return new AmazonCustomer(newID, newName, newAddress);
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public ArrayList<AmazonCredit> getCredits() {
//		for(int i = 0; i < credits.size(); i++) {
//			System.out.printf("- Credit[%i]: %s %n", i, credits.get(i).toString());
//		}
		return credits;
	}
	
	public void addCredit(AmazonCredit newCredit) {
		this.credits.add(newCredit);
	}
	
	public void addProductInWishList(AmazonProduct newProduct) {
		wishlist.add(newProduct);
	}
	
	public void removeProductFromWishList(AmazonProduct desiredProduct) throws AmazonException{
		if(isProductInWishList(desiredProduct) == false) {
			throw new AmazonException("Product not found in wishlist.");
		}
		
		for(int i = 0; i < wishlist.size(); i++) {
			if(wishlist.get(i).equals(desiredProduct)) {
				wishlist.remove(i);
				break;
			}
		}
	}
	
	public boolean isProductInWishList(AmazonProduct desiredProduct) {
		boolean wishlistHasItem = false;
		
		if(wishlist.contains(desiredProduct)) {
			wishlistHasItem = true;
		}
		
		return wishlistHasItem;
	}
	
	public ArrayList<String> showWishList() {
		ArrayList<String> wishlistItems = new ArrayList<String>();
		
		for(AmazonProduct curProduct: wishlist) {
			String newItem;
			
			newItem = new String("- ID: " + curProduct.getID() + ", Name: " + curProduct.getName());
			
			wishlistItems.add(newItem);
		}
		
		return wishlistItems;
	}
	
	public void addItemInCart(AmazonCartItem newItem) {
		cart.addItem(newItem);
	}
	
	public void addItemInCart(AmazonProduct newItem, int quantity) {
		AmazonCartItem newCartItem = new AmazonCartItem(newItem, 1);
		
		cart.addItem(newCartItem);
	}
	
	public void removeProductFromCart(AmazonProduct desiredProduct) throws AmazonException {
		try {
			cart.removeItem(desiredProduct);
		} catch (AmazonException e) {
			throw new AmazonException(e.getMessage());
		}
	}
	
	public void showCart() { //outputs cart items
		cart.listItems();
	}
	
	public ArrayList<AmazonProduct> getCart() { //returns cart items as amazonProduct
		return cart.getItems();
	}
	
	public void pay(AmazonCredit payment) {
		//TODO how should credits be handled?
	}
	
	public void moveFromCartToComments() {
		//TODO idk
	}
	
	public boolean hasProductToComment(AmazonProduct desiredProduct) {
		return cart.hasItem(desiredProduct);
	}
	
	public void addComment(AmazonComment newComment) throws AmazonException {
		comments.add(newComment);
	}
	
	public void setComment(AmazonProduct desiredProduct, String comment, float rating) throws AmazonException{
		//TODO change customer's comment and rating about desiredProduct to comment and rating
	}
	
	public void showComments() {
		//TODO list all comments
	}
	
	@Override
	public String toString() {
		return new String();//placeholder
	}
}

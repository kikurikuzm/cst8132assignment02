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
	
	public static AmazonCustomer createAmazonCustomer(String[] newCustomer) {
		if(newCustomer.length != 3) {
			return null;
		}
		
		if(AmazonUtil.isValidInt(newCustomer[0]) == false) {
			return null;
		}
		
		if(AmazonUtil.isValidString(newCustomer[1]) == false) {
			return null;
		}
		
		if(AmazonUtil.isValidString(newCustomer[2]) == false) {
			return null;
		} else {
			int newID = Integer.valueOf(newCustomer[0]);
			String newName = newCustomer[1];
			String newAddress = newCustomer[2];
			
			return new AmazonCustomer(newID, newName, newAddress);
		}
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
		return credits;
	}
	
	public int getNumberOfCredits() {
		return credits.size();
	}
	
	public ArrayList<AmazonComment> getCustomerComments() {
		return comments;
	}
	
	public int getNumberOfComments() {
		return comments.size();
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
	
	public void showCart() { // outputs cart items
		cart.listItems();
	}
	
	public ArrayList<AmazonProduct> getCart() { // returns cart items as amazonProduct
		return cart.getItems();
	}
	
	public int getCartSize () {
		return cart.getItems().size();
	}
	
	public void pay(int creditIndex) throws AmazonException{
		AmazonCredit creditInstance = credits.get(creditIndex);
		
		if (creditInstance.getAmount() >= cart.calcSubTotal()) {
			creditInstance.setAmount(creditInstance.getAmount() - cart.calcSubTotal());
			
			for(AmazonProduct curProduct: cart.getItems()) {
				try {
					this.moveFromCartToComments(curProduct);
				} catch (AmazonException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new AmazonException("Insufficient funds.");
		}
	}
	
	public void moveFromCartToComments(AmazonProduct desiredProduct) throws AmazonException{
		for(AmazonProduct curProduct: cart.getItems()) {
			if(curProduct.equals(desiredProduct)) {
				this.addComment(new AmazonComment(desiredProduct));
				cart.removeItem(desiredProduct);
				return;
			}
		}
		
		throw new AmazonException("Product not found in cart.");
	}
	
	public boolean hasProductToComment(AmazonProduct desiredProduct) {
		return cart.hasItem(desiredProduct);
	}
	
	public void addComment(AmazonComment newComment) {
		comments.add(newComment);
	}
	
	public void setComment(AmazonProduct desiredProduct, String comment, float rating) throws AmazonException{
		for(AmazonComment curComment: comments) {
			if(curComment.getProduct().equals(desiredProduct)) {
				if(!AmazonUtil.isValidString(comment) || !AmazonUtil.isValidFloat(String.valueOf(rating))) {
					throw new AmazonException("Invalid argument passed for changing comment.");
				}
				curComment.setComment(comment);
				curComment.setRating(rating);
				break;
			}
		}
	}
	
	public String[] showComments() {
		ArrayList<String> commentStrings = new ArrayList<String>();
		
		for(AmazonComment curComment: comments) {
			commentStrings.add(curComment.toString());
		}
		
		return (String[])commentStrings.toArray(); //casting to string array from object
	}
	
	@Override
	public String toString() {
		String customerSignature = "["+id+"] " + "["+name+"] " + "["+address+"]";
		
		return customerSignature;
	}

	public int getWishlistSize() {
		return wishlist.size();
	}
}

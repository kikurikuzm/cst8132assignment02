package amazonsystem;

import java.util.List;
import java.util.Scanner;

import amazonsystem.AmazonProduct.*;
import amazonsystem.AmazonMoney.*;

public class AmazonManager {
	
	private List<AmazonCustomer> customers;
	private List<AmazonProduct> products;
	
	public static void main(String[] args) {
		AmazonManager manager = new AmazonManager();
		manager.showMenu();
	}
	
	public void showMenu() {
		System.out.println("""
===========================================================================
||   ****    ****           ****    ****   *****       ALGONQUIN COLLEGE ||
||  **  ** 	**       **    **  **  **  **  **  **    COURSE: OOP/CST8152 ||
||  ******  **       **    **  **  **  **  *****         PROF: PAULO     ||
||  **  **   ****           ****    ****   **          TERM: FALL / 2024 ||
===========================================================================
||                       [Menu A2 - Amazon Manager]                      ||
===========================================================================
||									|| USER                              ||
||                                  || Credit options .................. ||
|| ADMIN                            || [F] Add credit to customer        ||
||                                  || [G] Show credits from customer    ||
|| Product options ................ || Wishlist options ................ ||
|| [A] Load product list            || [H] Add product in wishlist       ||
|| [B] Show product list            || [I] Remove product from wishlist  ||
|| [C] Search products              || [J] Show products from wishlist   ||
||                                  || Cart options .................... ||
|| Customer options ............... || [K] Add product in cart           ||
|| [D] Add customer                 || [L] Remove product from cart      ||
|| [E] Show customers               || [M] Show products from cart       ||
||                                  || [N] Buy products from cart        ||
||                                  || Comment options ................. ||
|| ................................ || [O] Comment products bought       ||
||            [Q] Exit              || [P] List comments from products   ||
===========================================================================
                            Choose an option: Q                           
===========================================================================
||   [End of Application (Authors: Matthias Kuzma, Courtney Hammond)]    ||
===========================================================================
				""");//TODO remove end 'choose an option' part and put it into main/exit func
	}
	
	public void loadProductList() {
		
	}
	
	public void showProductList() {
		
	}
	
	public void searchInProducts() {
		
	}
	
	public void addCustomer() {
		Scanner input = new Scanner(System.in);
		String customerID;
		String customerName;
		String customerAddress;
		String[] customerVariables = new String[3];
		AmazonCustomer newCustomer;
		
		System.out.println("Enter the Customer ID: ");
		customerID = input.next();
		
		System.out.println("Enter the Customer name: ");
		customerName = input.next();
		
		System.out.println("Enter the Customer address: ");
		customerAddress = input.next();
		
		int convertedID = Integer.valueOf(customerID);
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getID() == convertedID) {
				System.out.println("Customer ID already exists!");
				return;
			}
		}
		
		customerVariables[0] = customerID;
		customerVariables[1] = customerName;
		customerVariables[2] = customerAddress;
		
		newCustomer = AmazonCustomer.createAmazonCustomer(customerVariables);
		
		if(newCustomer == null) {
			System.out.println("Result: Customer failed to add. Invalid parameters.");
			return;
		}
		
		customers.add(newCustomer);
		
		System.out.println("Result: Customer added with success!");
	}
	
	public void showCustomers() {
		
	}
	
	public void addCreditToCustomer() {
		
	}
	
	public void showCreditFromCustomer() {
		
	}
	
	public void addProductInWishList() {
		
	}
	
	public void removeProductFromWishList() {
		
	}
	
	public void showWishList() {
		
	}
	
	public void addProductInCart() {
		
	}
	
	public void removeProductFromCart() {
		
	}
	
	public void showProductsInCart() {
		
	}
	
	public void payCart() {
		
	}
	
	public void addCommentToProduct() {
		
	}
	
	public void showComments() {
		
	}

	
	public AmazonCustomer findCustomerById(int customerId) {
		
	}
	
	public AmazonProduct findProductById(int productId) {
		
	}
	
	public void exit() {
		
	}
}

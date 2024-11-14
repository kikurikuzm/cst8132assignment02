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
		
		for(AmazonCustomer curCustomer: customers) {
			if(curCustomer.getID() == convertedID) {
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
		System.out.println("[Printing customers ......]");
		for(AmazonCustomer curCustomer: customers) {
			System.out.print("Customer: ");
			System.out.print("[Id: " + curCustomer.getID() + "], ");
			System.out.print("[Name: " + curCustomer.getName() + "], ");
			System.out.print("[Address: " + curCustomer.getAddress() + "]\n");
		}
	}
	
	public void addCreditToCustomer() {
		Scanner input = new Scanner(System.in);
		String customerID;
		String cashType;
		String cashValue;
		AmazonCustomer selectedCustomer;
		AmazonCredit newCredit;
		
		System.out.println("Enter the Customer ID: ");
		customerID = input.next();
		
		selectedCustomer = getCustomerIfValid(Integer.valueOf(customerID));
		if(selectedCustomer == null) {
			System.out.println("Invalid Customer ID.");
			return;
		}
		
		
		System.out.println("Enter the Type of Credit ([1] - Cash, [2] - Check, [3] - Card): ");
		cashType = input.next();
		
		int convertedCashType = Integer.valueOf(cashType);
		
		if(convertedCashType != 1 && convertedCashType != 2 && convertedCashType != 3) {
			System.out.println("Invalid Credit type.");
			return;
		}
		
		
		System.out.println("Enter the Cash value: ");
		cashValue = input.next();
		
		if(AmazonUtil.isValidFloat(cashValue) == false) {
			System.out.println("Invalid value.");
			return;
		}
		
		
		switch(convertedCashType) {
			case 1:
				newCredit = AmazonCash.createCash(cashValue);
			case 2:
				System.out.println("Please provide an account number: ");
				String newAccountNumber = input.next();
				
				String[] checkDetails = {cashValue, newAccountNumber};
				newCredit = AmazonCheck.createCheck(checkDetails);
			case 3:
				System.out.println("Please provide a card number: ");
				String cardNumber = input.next();
				
				System.out.println("Please provide the corresponding expiration date: ");
				String expirationDate = input.next();
				
				String[] cardDetails = {cardNumber, expirationDate, cashValue};
				newCredit = AmazonCard.createCredit(cardDetails);
			default:
				newCredit = AmazonCash.createCash("0");
		}
		
		selectedCustomer.addCredit(newCredit);
		
		System.out.println("Result: Credit added with success!");
	}
	
	public void showCreditFromCustomer() {
		Scanner input = new Scanner(System.in);
		String customerID;
		AmazonCustomer selectedCustomer;
		
		System.out.println("Enter the Customer ID:");
		customerID = input.next();
		
		selectedCustomer = getCustomerIfValid(Integer.valueOf(customerID));
		if(selectedCustomer == null) {
			System.out.println("Invalid Customer ID.");
			return;
		}
		
		System.out.println("[Printing Customer credit ......]");
		selectedCustomer.showCredits();
		
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
	
	public AmazonCustomer getCustomerIfValid(int customerID) {
		for(AmazonCustomer curCustomer: customers) {
			if(curCustomer.getID() == customerID) {
				return curCustomer;
			}
		}
		
		return null;
	}
}

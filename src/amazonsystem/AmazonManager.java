package amazonsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import amazonsystem.AmazonProduct.*;
import amazonsystem.AmazonMoney.*;

public class AmazonManager {
	
	private List<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	private List<AmazonProduct> products = new ArrayList<AmazonProduct>();
	private boolean keepRunning = true;
	
	public static void main(String[] args) {
		AmazonManager manager = new AmazonManager();
		manager.showMenu();
		while (manager.keepRunning == true) {
			
			manager.requestUserCommand();
			System.out.println("===========================================================================");
			//manager.awaitCustomerContinue();
		}
	}
	
	public void showMenu() {
		System.out.println("""
===========================================================================
||   ****    ****           ****    ****   *****       ALGONQUIN COLLEGE ||
||  **  **  **       **    **  **  **  **  **  **    COURSE: OOP/CST8152 ||
||  ******  **       **    **  **  **  **  *****         PROF: PAULO     ||
||  **  **   ****           ****    ****   **          TERM: FALL / 2024 ||
===========================================================================
||                       [Menu A2 - Amazon Manager]                      ||
===========================================================================
||                                  || USER                              ||
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
""");
	}
	
	public void loadProductList() {
		String fileName = "";
		
		fileName = requestUserInput("Enter name of file to load (\"default\" = default file): ", "string");
		
		try {
			if (fileName.equals("default")||fileName.equals("d")) { // can't accept no chars for default because it wouldn't be considered a valid string
				products = AmazonProductList.createList("Sample-Amazon-Products-v2.csv");
				System.out.println("Loaded default list successfully.");
			} else {
				products = AmazonProductList.createList(fileName);
				System.out.printf("Loaded list \"%s\" successfully.", fileName);
			}
		} catch (AmazonException e) {
			System.out.println("List loading failed!");
		}
	}
	
	public void showProductList() {
		if(products.isEmpty()) {
			System.out.println("No product list loaded!");
			return;
		}
		
		System.out.println("PRODUCT LIST ......");
		
		for (AmazonProduct i: products) {
			System.out.println(i);
		}
		System.out.println("...................");
	}
	
	public void searchInProducts() {
		String stringQuery = "";
		
		stringQuery = requestUserInput("Enter search query:","string");
		
		try {
			AmazonProductList.search(stringQuery);
			} catch (AmazonException e) {
			System.out.println("List search failed!");
		}
	}
	
	public void addCustomer() {
		String customerID;
		String customerName;
		String customerAddress;
		String[] customerVariables = new String[3];
		AmazonCustomer newCustomer;
		
		customerID = requestUserInput("Enter the Customer ID: ", "int");
		customerName = requestUserInput("Enter the Customer Name: ", "string");
		customerAddress = requestUserInput("Enter the Customer's Address: ", "string");
		
		customerVariables[0] = customerID;
		customerVariables[1] = customerName;
		customerVariables[2] = customerAddress;
		
		try {
			newCustomer = AmazonCustomer.createAmazonCustomer(customerVariables);
		} catch (AmazonException e) {
			System.out.println(e);
			System.out.println("Result: Customer failed to create. Invalid parameters.");
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
		String cashType;
		String cashValue;
		AmazonCustomer selectedCustomer;
		AmazonCredit newCredit;

		selectedCustomer = requestCustomer();
		
		cashType = requestUserInput("Enter the Type of Credit ([1] - Cash, [2] - Check, [3] - Card): ", "int");
		
		int convertedCashType = Integer.valueOf(cashType);
		
		if(convertedCashType != 1 && convertedCashType != 2 && convertedCashType != 3) {
			System.out.println("Invalid Credit type.");
			return;
		}
		
		cashValue = requestUserInput("Enter the cash value: ", "float");
		
		switch(convertedCashType) {
			case 1:
				newCredit = AmazonCash.createCash(cashValue);
				break;
			case 2:
				String newAccountNumber = requestUserInput("Please provide an account number: ", "string");
				
				String[] checkDetails = {cashValue, newAccountNumber};
				newCredit = AmazonCheck.createCheck(checkDetails);
				break;
			case 3:
				String cardNumber = requestUserInput("Please provide a card number: ", "string");
				String expirationDate = requestUserInput("Please provide the corresponding expiration date: ", "string");
				
				String[] cardDetails = {cardNumber, expirationDate, cashValue};
				newCredit = AmazonCard.createCredit(cardDetails);
				break;
			default:
				newCredit = AmazonCash.createCash("0");
		}
		
		selectedCustomer.addCredit(newCredit);
		
		System.out.println("Result: Credit added with success!");
	}
	
	public void showCreditFromCustomer() {
		String customerID;
		AmazonCustomer selectedCustomer;
		ArrayList<AmazonCredit> customerCredits;
		
		selectedCustomer = requestCustomer();
		
		System.out.println("[Printing Customer credit ......]");
		
		customerCredits = selectedCustomer.getCredits();
		
		for(int i = 0; i < customerCredits.size(); i++) {
			AmazonCredit currentCredit = customerCredits.get(i);
			System.out.printf("- Credit[%d]: %s%n", i, currentCredit.toString());
		}
	}
	
	public void addProductInWishList() {
		AmazonCustomer selectedCustomer;
		AmazonProduct desiredProduct;
		
		selectedCustomer = requestCustomer();
		
		desiredProduct = getProductIfValid(requestUserInput("Enter the Product ID to include in the Wishlist: ", "int"));
		
		selectedCustomer.addProductInWishList(desiredProduct);
		
		System.out.printf("Added \"%s...\" into %s's wishlist.%n", desiredProduct.getName().substring(0, 25), selectedCustomer.getName());
	}
	
	public void removeProductFromWishList() {
		AmazonCustomer selectedCustomer;
		ArrayList<String> customerWishlist;
		AmazonProduct productToRemove;
		
		selectedCustomer = requestCustomer();
		
		customerWishlist = selectedCustomer.showWishList();
		
		System.out.printf("Wishlist of customer %s contains: %n", selectedCustomer.getName());
		for(String wishlistProd: customerWishlist) {
			System.out.println(wishlistProd);
		}
		
		productToRemove = getProductIfValid(requestUserInput("Enter the Product ID to remove from the wishlist: ", "int"));
		
		try {
			selectedCustomer.removeProductFromWishList(productToRemove);
		} catch (AmazonException e) {
			System.out.println(e);
		}
		
		System.out.printf("Removed \"%s...\" from %s's wishlist.%n", productToRemove.getName().substring(0,25), selectedCustomer.getName());
	}
	
	public void showWishList() {
		AmazonCustomer selectedCustomer;
		ArrayList<String> customerWishlist;
		
		selectedCustomer = requestCustomer();
		
		customerWishlist = selectedCustomer.showWishList();
		
		System.out.printf("Wishlist of customer %s contains: %n", selectedCustomer.getName());
		for(String wishlistProd: customerWishlist) {
			System.out.println(wishlistProd);
		}
	}
	
	public void addProductInCart() {
		AmazonCustomer selectedCustomer;
		AmazonProduct desiredProduct;
		String productQuantity;
		
		selectedCustomer = requestCustomer();
		
		desiredProduct = getProductIfValid(requestUserInput("Enter the desired product ID: ", "product"));
		productQuantity = requestUserInput("Enter the desired quantity of product to add: ", "int");
		
		selectedCustomer.addItemInCart(desiredProduct, Integer.valueOf(productQuantity));
		
		System.out.printf("Added %s \"%s...\" to %s's cart.%n", productQuantity, desiredProduct.getName().substring(0, 25), selectedCustomer.getName());
	}
	
	public void removeProductFromCart() {
		AmazonCustomer selectedCustomer;
		ArrayList<AmazonProduct> customerCart;
		AmazonProduct productToRemove;
		
		selectedCustomer = requestCustomer();
		
		customerCart = selectedCustomer.getCart();
		
		System.out.printf("Cart of customer %s contains: %n", selectedCustomer.getName());
		for(AmazonProduct curCartItem: customerCart) {
			System.out.println(curCartItem.toString());
		}
		
		productToRemove = getProductIfValid(requestUserInput("Enter the ID of the product to remove: ", "int"));
		
		try {
			selectedCustomer.removeProductFromCart(productToRemove);
		} catch (AmazonException e) {
			System.out.println(e);
		}
		
		System.out.printf("Removed \"%s...\" from %s's cart.%n", productToRemove.getName().substring(0, 25), selectedCustomer.getName());
	}
	
	public void showProductsInCart() {
		AmazonCustomer selectedCustomer;
		ArrayList<AmazonProduct> customerCart;
		
		selectedCustomer = requestCustomer();
		
		customerCart = selectedCustomer.getCart();
		
		System.out.printf("Cart of customer %s contains: %n", selectedCustomer.getName());
		for(AmazonProduct currentCartItem: customerCart) {
			System.out.println(currentCartItem.toString());
		}
	}
	
	public void payCart() {
		AmazonCustomer selectedCustomer;
		int creditIndex;
		
		selectedCustomer = requestCustomer();
		creditIndex = Integer.valueOf(requestUserInput("Enter the number for the credit from the customer: ", "int"));
		
		try {
			selectedCustomer.pay(creditIndex);
		} catch (AmazonException e) {
			System.out.println(e);
		}
	}
	
	public void addCommentToProduct() {
		AmazonCustomer selectedCustomer;
		AmazonProduct desiredProduct;
		
		selectedCustomer = requestCustomer();
		
		desiredProduct = getProductIfValid(requestUserInput("Enter the product ID to comment on: ", "product"));
		AmazonComment newComment = new AmazonComment(desiredProduct);
		
		System.out.println("Commenting on product: " + desiredProduct.toString());
		
		newComment.setComment(requestUserInput("Enter the comment: ","string"));
		newComment.setRating(Integer.parseInt(requestUserInput("Enter the star rating: ","stars")));
		
		selectedCustomer.addComment(newComment);
		
		System.out.println("Comment from customer: " + selectedCustomer);
		System.out.printf("%.1f stars -- \"%s\"\n", newComment.getStarRating(), newComment.getCommentDesc());
	}
	
	public void showComments() {
		AmazonCustomer selectedCustomer;
		selectedCustomer = requestCustomer();
		
		for (AmazonComment i : selectedCustomer.getCustomerComments()) {
			System.out.println(i.toString());
		}
	}

	
	public AmazonCustomer findCustomerById(int customerID) {
		for(AmazonCustomer curCustomer: customers) {
			if(curCustomer.getID() == customerID) {
				return curCustomer;
			}
		}
		return null;
	}
	
	public AmazonProduct findProductById(int productID) {
		for(AmazonProduct curProduct: products) {
			if(curProduct.getID() == productID) {
				return curProduct;
			}
		}
		return null;
		
	}
	
	public void exit() {
		System.out.println("""
===========================================================================
||   [End of Application (Authors: Matthias Kuzma, Courtney Hammond)]    ||
===========================================================================
				""");
		keepRunning = false;
	}
	
	public AmazonCustomer getCustomerIfValid(String customerID) {//returns customer instance if valid, otherwise returns null
		if(AmazonUtil.isValidInt(customerID) != true) {
			return null;
		}
		
		int customerIDInteger = Integer.valueOf(customerID);
		for(AmazonCustomer curCustomer: customers) {
			if(curCustomer.getID() == customerIDInteger) {
				return curCustomer;
			}
		}
		
		return null;
	}
	
	public AmazonProduct getProductIfValid(String productID) {
		if(AmazonUtil.isValidInt(productID) != true) {
			return null;
		}
		
		int productIDInteger = Integer.valueOf(productID);
		for(AmazonProduct curProduct: products) {
			if(curProduct.getID() == productIDInteger) {
				return curProduct;
			}
		}
		
		return null;
	}
	
	public String requestUserInput(String question, String inputType) {//requests user input and desired type, and provides the response if it passes error checking
		boolean responseInvalid = true;
		Scanner input = new Scanner(System.in);
		String userResponse = "";
		
		while(responseInvalid == true) {
			
			System.out.println(question);
			userResponse = input.nextLine();
			
			if(userResponse.equalsIgnoreCase("quit") || userResponse.equalsIgnoreCase("exit")) {
				responseInvalid = false;
				System.out.println("Returning to menu...");
				//need to figure out how to actually exit function and return to menu
			}
			switch(inputType) { //error checking
				case("product"):
					if(getProductIfValid(userResponse) != null) {
						responseInvalid = false;
					} else {
						System.out.println("Product ID invalid.");
					}
					break;
				case("customer"):
					if(getCustomerIfValid(userResponse) != null) {
						responseInvalid = false;
					} else {
						System.out.println("Customer ID does not exist.");
					}
					break;
				case("newCustomer"):
					if(getCustomerIfValid(userResponse) == null) {
						responseInvalid = false;
					} else {
						System.out.println("Customer ID already exists.");
					}
					break;
				case("int"):
					if(AmazonUtil.isValidInt(userResponse) == true) {
						responseInvalid = false;
					} else {
						System.out.println("Invalid integer.");
					}
					break;
				case("string"):
					if(AmazonUtil.isValidString(userResponse) == true) {
						responseInvalid = false;
					} else {
						System.out.println("Invalid string.");
					}
					break;
				case("float"):
					if(AmazonUtil.isValidFloat(userResponse) == true) {
						responseInvalid = false;
					} else {
						System.out.println("Invalid float.");
					}
					break;
				case("stars"):
					if(AmazonUtil.isValidInt(userResponse) == true) {
						if (Integer.parseInt(userResponse) > 0 && Integer.parseInt(userResponse) <= 5)
						responseInvalid = false;
					} else {
						System.out.println("Invalid star rating.");
					}
					break;
				default:
					System.out.println("Invalid input type requested.");
					return null;
			}
		}
		
		return userResponse;
	}
	
	public AmazonCustomer requestCustomer() {
		return getCustomerIfValid(requestUserInput("Enter the Customer ID: ", "customer"));
	}
	
	public void requestUserCommand() {
		Scanner input = new Scanner(System.in);
		String userOption;
		
		System.out.println("Choose an option (\"menu\" to see options): ");
		userOption = input.next();
		
		userOption = userOption.toLowerCase();
		
		switch(userOption) {
			case("a"):
				loadProductList();
				break;
			case("b"):
				showProductList();
				break;
			case("c"):
				searchInProducts();
				break;			
			case("d"):
				addCustomer();
				break;
			case("e"):
				showCustomers();
				break;
			case("f"):
				addCreditToCustomer();
				break;
			case("g"):
				showCreditFromCustomer();
				break;
			case("h"):
				addProductInWishList();
				break;
			case("i"):
				removeProductFromWishList();
				break;
			case("j"):
				showWishList();
				break;
			case("k"):
				addProductInCart();
				break;
			case("l"):
				removeProductFromCart();
				break;
			case("m"):
				showProductsInCart();
				break;
			case("n"):
				payCart();
				break;
			case("o"):
				addCommentToProduct();
				break;
			case("p"):
				showComments();
				break;
			case("q"):
				exit();
				break;
			case("menu"):
				showMenu();
				break;
		}
	}
	
	public void awaitCustomerContinue() {
		Scanner input = new Scanner(System.in);
		System.out.println("Press enter to continue...");
		
		input.nextLine();
	}
}

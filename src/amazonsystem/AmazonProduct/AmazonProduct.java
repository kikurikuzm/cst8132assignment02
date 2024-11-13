package amazonsystem.AmazonProduct;

public class AmazonProduct {
	private int id;
	private String name;
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	private String imageURL;
	private String link;
	private float rating;
	private int numberOfRatings;
	private float discountPrice;
	private float actualPrice;
	
	private AmazonProduct(int id, String name, AmazonProductCategory category, AmazonProductSubCategory subCategory, String imageURL, String link, float rating, int numberOfRatings, float discountPrice, float actualPrice) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.imageURL = imageURL;
		this.link = link;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
		this.discountPrice = discountPrice;
		this.actualPrice = actualPrice;
	}
	
	public AmazonProduct(String[] productVariables) {
		return new AmazonProduct(Integer.valueOf(productVar
		return new AmazonProduct(Integer.valueOf(productVariables[0]), productVariables[1], productVariables[2], AmazonProductCategory(), productVariables[4], productVariables[5], productVariables[6], productVariables[7], productVariables[8], productVariables[9]);
	}
	
	public AmazonProduct createAmazonProduct(String[] productVariables) {
		return new AmazonProduct(productVariables);
	}
	
	public void setTitle(String[] newTitle) {
		
	}
	
	public String getProductDetails() {
		return "";
	}
	
	public String toString() {
		return "";
	}
}

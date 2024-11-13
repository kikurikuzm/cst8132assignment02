package amazonsystem.AmazonProduct;

import amazonsystem.*;

public class AmazonProduct {
	private int id;
	private String name;
	private String[] variableArray;
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	private String imageURL;
	private String link;
	private float rating;
	private int numberOfRatings;
	private float discountPrice;
	private float actualPrice;
	
	AmazonProduct(int id, String name, AmazonProductCategory category, AmazonProductSubCategory subCategory,
String imageURL, String link, float rating, int numberOfRatings, float discountPrice, float actualPrice){
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

	AmazonProduct(String[] productVariables) {
		this.variableArray = productVariables;
		this.id = Integer.parseInt(productVariables[0]);
		this.name = productVariables[1];
		this.category = new AmazonProductCategory(productVariables[2]);
		this.subCategory = new AmazonProductSubCategory(productVariables[3],this.category);
		this.imageURL = productVariables[4];
		this.link = productVariables[5];
		this.rating = AmazonUtil.convertStringToFloat(productVariables[6]);
		this.numberOfRatings = Integer.parseInt(productVariables[7].replace(",", ""));
		this.discountPrice = AmazonUtil.convertStringToFloat(productVariables[8]);
		this.actualPrice = AmazonUtil.convertStringToFloat(productVariables[9]);

	}
	
	public float getActualPrice() {
		return this.actualPrice;
	}
	
	public float getDiscountPrice() {
		return this.discountPrice;
	}
	
	public int getID() {	
		return this.id;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public AmazonProductCategory getMainCategory() {
		return this.category;
	}
	
	public AmazonProductSubCategory getSubCategory() {
		return this.subCategory;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumberOfRatings() {
		return this.numberOfRatings;
	}
	public float getRatings() {
		return this.rating;
	}
	
	public String getImageURL() {
		return this.imageURL;
	}
	
	public String[] getTitle() {
		return this.variableArray;
	}
	
	public void setTitle(String[] title) {
		this.variableArray = title;
	}
	
	public String toString() {
		return (String.valueOf(this.id) + ",\""+this.name+"\","+this.category.getCategory()+
				","+this.subCategory.getSubCategory()+","+this.imageURL+","+this.link+
				","+String.valueOf(this.rating)+","+String.valueOf(this.numberOfRatings)+","+String.valueOf(this.discountPrice)+
				","+String.valueOf(this.actualPrice));
	}
}
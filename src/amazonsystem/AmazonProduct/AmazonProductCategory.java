package amazonsystem.AmazonProduct;

public class AmazonProductCategory {
	private String categoryName;
	
	public AmazonProductCategory(String givenName){
		this.categoryName = givenName;
	}
	
	public void setCategory(String setName){
		this.categoryName = setName;
	}
	
	public String getCategory() {
		return this.categoryName;
	}
}

package amazonsystem;

import amazonsystem.AmazonProduct.*;

public class AmazonComment {
	private String comment;
	private AmazonProduct product;
	private float stars;
	
	public AmazonComment(AmazonProduct product) {
		this.product = product;
	}
	
	public AmazonProduct getProduct() {
		return product;
	}
	
	public float getStarRating() {
		return stars;
	}
	
	public String getCommentDesc() {
		return comment;
	}
	
	public void setRating(float stars) {
		this.stars = stars;
	}
	
	public void setComment(String desc) {
		this.comment = desc;
	}
	
	@Override
	public String toString() {
		return String.format("- ProductID: [%d, %s] - Comment: %s - Rating: %.1f", product.getID(), product.getName().substring(0, 20), comment, stars);
	}
	
}

package amazonsystem;

import amazonsystem.AmazonProduct.*;

public class AmazonComment {
	private String comment;
	private AmazonProduct product;
	private float stars;
	
	public AmazonComment(AmazonProduct product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return new String();//placeholder
	}
	
}

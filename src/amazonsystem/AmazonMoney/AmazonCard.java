package amazonsystem.AmazonMoney;

public class AmazonCard extends AmazonCredit{
	String number;
	String expiration;
	
	private AmazonCard(String number, String expiration, float amount) {
		super(amount);
		this.number = number;
		this.expiration = expiration;
		
		this.type = AmazonCredit.PaymentType.Card;
	}
	
	public static AmazonCard createCredit(String[] cardVariables) {
		return new AmazonCard(cardVariables[0], cardVariables[1], Float.valueOf(cardVariables[2]));
	}
	
	@Override
	public String toString() {
		return new String("Type: " + this.type + ", Amount: " + this.amount + ", Account Number: " + this.number + ", Expiration: " + this.expiration);
	}
}

package amazonsystem.AmazonMoney;

public class AmazonCard extends AmazonCredit{
	String number;
	String expiration;
	
	private AmazonCard(String number, String expiration, float amount) {
		super(amount);
		this.type = AmazonCredit.PaymentType.Card;
		this.number = number;
		this.expiration = expiration;
	}
	
	public static AmazonCard createCredit(String[] cardVariables) {
		return new AmazonCard(cardVariables[0], cardVariables[1], Float.valueOf(cardVariables[2]));
	}
}

package amazonsystem.AmazonMoney;

public class AmazonCheck extends AmazonCredit{
	private String accountNumber;
	
	private AmazonCheck(String accountNumber, float amount) {
		super(amount);
		this.type = AmazonCredit.PaymentType.Check;
		this.accountNumber = accountNumber;
	}
	
	public static AmazonCheck createCheck(String[] checkVariables) {
		return new AmazonCheck(checkVariables[0], Float.valueOf(checkVariables[1]));
	}
}

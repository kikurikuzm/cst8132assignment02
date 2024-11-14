package amazonsystem.AmazonMoney;

public class AmazonCheck extends AmazonCredit{
	private String accountNumber;
	
	private AmazonCheck(String accountNumber, float amount) {
		super(amount);
		this.accountNumber = accountNumber;
		
		this.type = AmazonCredit.PaymentType.Check;
	}
	
	public static AmazonCheck createCheck(String[] checkVariables) {
		return new AmazonCheck(checkVariables[0], Float.valueOf(checkVariables[1]));
	}
	
	@Override
	public String toString() {
		return new String("Type: " + this.type + ", Amount: " + this.amount + ", Account Number: " + this.accountNumber);
	}
}

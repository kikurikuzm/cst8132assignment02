package amazonsystem.AmazonMoney;

public class AmazonCash extends AmazonCredit{
	private AmazonCash(float amount) {
		super(amount);
		
		this.type = AmazonCredit.PaymentType.Cash;
	}
	
	public static AmazonCash createCash(String cashVariables) {
		return new AmazonCash(Float.valueOf(cashVariables));
	}
	
	public static AmazonCash createCash(String[] cashVariables) {
		float sum = 0;
		
		for (String i : cashVariables) {
			sum += Float.valueOf(i);
		}
		return new AmazonCash(sum);
	}
}

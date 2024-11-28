package amazonsystem.AmazonMoney;

public abstract class AmazonCredit {
	enum PaymentType { Cash, Check, Card };
	float amount;
	PaymentType type;
	
	public AmazonCredit(float amount) {
		this.amount = amount;
	}
	
	public float getAmount () {
		return this.amount;
	}
	
	public void setAmount (float setValue) {
		
	}
			
	@Override
	public String toString() {
		return new String("Type: " + this.type + ", Amount: " + this.amount);
	}
	
}

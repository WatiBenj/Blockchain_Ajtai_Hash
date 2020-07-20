
public class Transaction {
	
	private String id;
	@SuppressWarnings("unused")
	private String sender;
	@SuppressWarnings("unused")
	private String receiver;
	@SuppressWarnings("unused")
	private double amount;
	
	public Transaction(String id, String sender, String receiver, double amount) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}
	
}

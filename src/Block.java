import java.util.Date;

public class Block {
	
	private String previousHash;
	private String hash;
	private String merkleRoot;
	private Date timeStamp = new Date();
	private int nonce = 0;
	private boolean proof = false;
	private String miner;
	
	/* GENESIS BLOCK */
	public Block(String previousHash, String hash) {
		this.previousHash = previousHash;
		this.hash = hash;
		this.merkleRoot = "None";
		this.miner = "None";
	}
	
	public Block(String previousHash) {
		this.previousHash = previousHash;
	}
	
	public Block() {}
	
	public String getPreviousHash() {
		return previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMerkleRoot() {
		return merkleRoot;
	}

	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public boolean getProof() {
		return proof;
	}

	public void setProof(boolean proof) {
		this.proof = proof;
	}

	public String getMiner() {
		return miner;
	}

	public void setMiner(String miner) {
		this.miner = miner;
	}

}

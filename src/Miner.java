import java.util.ArrayList;
import java.util.Random;

public class Miner {
	
	private String name;
	private ArrayList<Block> blockchain = new ArrayList<Block>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private int[][] AjtaiKey;
	private int reward = 0; /* BITCOINS */
	
	public Miner(String name, Block genesis, int[][] AjtaiKey) {
		this.name = name;
		this.blockchain.add(genesis);
		this.AjtaiKey = AjtaiKey;
	}
	
	public void fetchTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/* ATTEMPT TO MINE A NEW BLOCK */
	public Block mineBlock(int difficulty) {
		
		Utility tool = new Utility(AjtaiKey);
		Random random = new Random();
		
		Block block = new Block(blockchain.get(blockchain.size() - 1).getHash());
		block.setMerkleRoot(tool.computeMerkleRoot(transactions));
		block.setNonce(random.nextInt(500000));
		block.setHash(tool.AjtaiHash(block.getPreviousHash() + block.getMerkleRoot() + Integer.toString(block.getNonce())));
		
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		/* VERIFY PROOF OF WORK */
		if(block.getHash().substring(0, difficulty).equals(target)) {
			block.setProof(true);
			block.setMiner(name);
		}
		
		return block;
	}
	
	public String checkChainValidity(int difficulty) {
		
		Utility tool = new Utility(AjtaiKey);
		
		Block currentBlock; 
		Block previousBlock;
		
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1; i<blockchain.size(); i++) {
			
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
		
			/* COMPARE ACTUAL AND REGISTERED PREVIOUS HASHES */
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				return "INVALID BLOCKCHAIN (Previous hashes not equal)";
			}
			
			/* COMPARE REGISTERED AND COMPUTED HASH */
			if(!currentBlock.getHash().equals(tool.AjtaiHash(currentBlock.getPreviousHash() 
				+ currentBlock.getMerkleRoot() + Integer.toString(currentBlock.getNonce())))) {		
				return "INVALID BLOCKCHAIN (Hashes not equal)";
			}
			
			/* VERIFY PROOF OF WORK */
			if(!currentBlock.getHash().substring(0, difficulty).equals(target)) {
				return "INVALID BLOCKCHAIN (Proof of work)";
			}
			
		}
		
		return "VALID BLOCKCHAIN";
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Block> getBlockchain() {
		return blockchain;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
		
}

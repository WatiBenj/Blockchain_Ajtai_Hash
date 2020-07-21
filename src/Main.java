
import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	private static ArrayList<Block> blockchain = new ArrayList<Block>();
	private static int n = 64; /* LENGTH OF OUTPUT HASH VALUE (HEXADECIMAL REPRESENTATION)*/
	private static int m = 536; /* MAXIMAL LENGTH OF INPUT STRING (BINARY REPRESENTATION) */
	private static int q = 16; /* MODULUS */
	private static int[][] AjtaiKey = new int[n][m];
	private static int difficulty = 4; /* NUMBER OF ZEROS STARTING HASH VALUE */
	
	public static void main(String[] args) {
		
		/* AJTAI HASH FUNCTION KEY */
		Random random = new Random();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				AjtaiKey[i][j] = random.nextInt(Integer.MAX_VALUE) % q;
			}
		}
		
		Utility tool = new Utility(AjtaiKey);
		
		/* INITIAL BLOCK */
		Block genesis = new Block(tool.getRandomHexString(64), tool.getRandomHexString(64));
		blockchain.add(genesis);
		
		/* INITIAL BLOCKCHAIN */
		displayChain(blockchain);
		
		/* MINERS */
		ArrayList<Miner> miners = new ArrayList<Miner>();
		
		Miner miner_1 = new Miner("Miner_1", genesis, AjtaiKey);
		miners.add(miner_1);
		Miner miner_2 = new Miner("Miner_2", genesis, AjtaiKey);
		miners.add(miner_2);
		Miner miner_3 = new Miner("Miner_3", genesis, AjtaiKey);
		miners.add(miner_3);
		Miner miner_4 = new Miner("Miner_4", genesis, AjtaiKey);
		miners.add(miner_4);
		
		/* AVAILABLE TRANSACTIONS */
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		Transaction t1 = new Transaction("1", "Alice", "Bob", 20.00);
		transactions.add(t1);
		Transaction t2 = new Transaction("2", "Bob", "Eve", 10.25);
		transactions.add(t2);
		Transaction t3 = new Transaction("3", "Dan", "Alice", 25.5);
		transactions.add(t3);
		Transaction t4 = new Transaction("4", "Eve", "Dan", 15.00);
		transactions.add(t4);
		Transaction t5 = new Transaction("5", "Alice", "Eve", 10.75);
		transactions.add(t5);
		Transaction t6 = new Transaction("6", "Bob", "Dan", 30.00);
		transactions.add(t6);
		Transaction t7 = new Transaction("7", "Dan", "Bob", 20.10);
		transactions.add(t7);
		Transaction t8 = new Transaction("8", "Eve", "Alice", 15.25);
		transactions.add(t8);
		
		miner_1.fetchTransactions(transactions);
		miner_2.fetchTransactions(transactions);
		miner_3.fetchTransactions(transactions);
		miner_4.fetchTransactions(transactions);
		
		/* NEW BLOCK TO BE MINED */
		Block newBlock = new Block();
		
		Long start = System.currentTimeMillis();
		
		/* MINING PROCESS */
		while(true) {
			newBlock = miner_1.mineBlock(difficulty);
			if(newBlock.getProof()) {break;}
			newBlock = miner_2.mineBlock(difficulty);
			if(newBlock.getProof()) {break;}
			newBlock = miner_3.mineBlock(difficulty);
			if(newBlock.getProof()) {break;}
			newBlock = miner_4.mineBlock(difficulty);
			if(newBlock.getProof()) {break;}
		}
		
		Long end = System.currentTimeMillis();

		System.out.println("--------------------------");
		System.out.println("|    NEW MINED BLOCK     |");
		System.out.println("--------------------------");
		System.out.println("|	Mined by         : " + newBlock.getMiner());
		System.out.println("|	Hash             : " + newBlock.getHash());
		System.out.println("|	Nonce            : " + newBlock.getNonce());
		System.out.println("|	Merkle Root      : " + newBlock.getMerkleRoot());
		System.out.println("|	Computation Time : " + ((end - start) / 1000) + " [s]");
		System.out.println("--------------------------");
		System.out.print("\n");
		
		/* ADDITION OF NEW BLOCK */
		blockchain.add(newBlock);
		miner_1.getBlockchain().add(newBlock);
		miner_2.getBlockchain().add(newBlock);
		miner_3.getBlockchain().add(newBlock);
		miner_4.getBlockchain().add(newBlock);
		
		/* BLOCKCHAIN VALIDITY CHECK */
		System.out.println("-----------------------");
		System.out.println("|    VALIDITY CHECK   |");
		System.out.println("-----------------------");
		for(Miner miner : miners) {
			System.out.println("|       " + miner.getName() + "       : " + miner.checkChainValidity(difficulty));
		}
		System.out.println("-----------------------");
		System.out.print("\n");
		
		/* BITCOIN REWARD */
		for(Miner miner : miners) {
			if(newBlock.getMiner().equals(miner.getName())) {
				miner.setReward(miner.getReward() + 1);
				System.out.println("-----------------------");
				System.out.println("|    BITCOIN REWARD   : " + miner.getName() + " receives 1 Bitcoin");
				System.out.println("-----------------------");
			}
		}
		System.out.print("\n");
		
		/* NEW BLOCKCHAIN */
		displayChain(blockchain);
	}
	
	public static void displayChain(ArrayList<Block> blockchain) {
		System.out.println("-----------------------");
		System.out.println("|      BLOCKCHAIN     |");
		System.out.println("-----------------------");
		for(Block block : blockchain) {
			System.out.println("|	Previous Hash : " + block.getPreviousHash());
			System.out.println("|	Hash          : " + block.getHash());
			System.out.println("|	Nonce         : " + block.getNonce());
			System.out.println("|	Merkle Root   : " + block.getMerkleRoot());
			System.out.println("|	Time          : " + block.getTimeStamp());
			System.out.println("|	Miner         : " + block.getMiner());
			System.out.println("-----------------------");
		}
		System.out.print("\n");
	}

}

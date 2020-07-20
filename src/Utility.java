import java.util.ArrayList;
import java.util.Random;

public class Utility {
	
	private int[][] AjtaiKey;
	
	public Utility(int[][] AjtaiKey) {
		this.AjtaiKey = AjtaiKey;
	}

	public String computeMerkleRoot(ArrayList<Transaction> transactions) {
		
		int count = transactions.size();
		ArrayList<String> previousLayer = new ArrayList<String>();
		
		for(Transaction transaction : transactions) {
			previousLayer.add(AjtaiHash(transaction.getId()));
		}
		
		ArrayList<String> currentLayer = previousLayer;
		
		/* BUILD MERKLE TREE */
		while(count > 1) {
			currentLayer = new ArrayList<String>();
			
			for(int i=0; i<previousLayer.size(); i+=2) {
				currentLayer.add(AjtaiHash(previousLayer.get(i) + previousLayer.get(i+1)));
			}
			
			count = currentLayer.size();
			previousLayer = currentLayer;
		}
		
		/* MERKLE ROOT */
		return (currentLayer.size() == 1) ? currentLayer.get(0) : "";
	}

	/* AJTAI HASH FUNCTION */
	public String AjtaiHash(String string) {
		
		int n = AjtaiKey.length; /* DESIRED OUTPUT LENGTH : 64 (HEXADECIMAL) */
		int q = 16;
		
		/* LIST OF BINARY REPRESENTATION OF EACH CHARACTER */
		ArrayList<String> binary = new ArrayList<String>();
		
		for(int i=0; i<string.length(); i++){
			char c = string.charAt(i);        
			binary.add(Integer.toBinaryString(c));
		}
		
		/* BINARY REPRESENTATION SIZE */
		int m = binary.size() * 4;
		
		/* BINARY REPRESENTATION OF ENTIRE STRING */
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<binary.size(); i++) {
			for(int j=0; j<4; j++) {
				x.add(Character.getNumericValue(binary.get(i).charAt(j+2)));
			}
		}
		
		/* ARRAY OF BINARY REPRESENTATION OF STRING */
		int y[] = new int[m];
		
		for(int i=0; i<m; i++) {
			y[i] = x.get(i);
		}
		
		String hash = new String();
		
		for(int row=0; row<n; row++) {
			double sum = 0;
			for(int column=0; column<m; column++) {
				/* MATRIX - VECTOR PRODUCT */
				sum += AjtaiKey[row][column] * y[column];
			}
			/* CONVERSION TO HEXADECIMAL BASE (q = 16) */
			hash = hash + Integer.toHexString((int) sum % q);
		}
	    
		return hash;
	}
	
    public String getRandomHexString(int length) {
    	
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        
        while(buffer.length() < length){
        	buffer.append(Integer.toHexString(random.nextInt()));
        }
        
        return buffer.toString().substring(0, length);
    }
	
}

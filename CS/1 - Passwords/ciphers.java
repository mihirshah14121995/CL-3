import java.util.Arrays; 
import java.util.Scanner; 
public class ciphers { 
	public String encryption(String plaintext, String key, String sortedkey){ 
		System.out.println("\n===================== ENCRYPTION ==========================\n"); 
		 
		plaintext = plaintext.replaceAll(" ", ""); 
		 
		System.out.println("\nPlaintext is\t: " + plaintext); 
		 
		int n = key.length(); 
		int m = 0; 
		 
		if((plaintext.length()%key.length())>0) 
			m = plaintext.length()/key.length() + 1; 
		else 
			m = plaintext.length()/key.length(); 
		 
		 
		char enmatrix[][] = new char[m][n]; 
		char ciphmatrix[][] = new char[m][n]; 
		 
		 
		int index = 0; 
		for (int i = 0; i < m; i++) { 
			for (int j = 0; j < n; j++) { 
				if(index<plaintext.length()) 
				{ 
					enmatrix[i][j] = plaintext.charAt(index); 
					index++; 
				} 
				else 
					enmatrix[i][j] = 'X'; 
			} 
			if(index>=plaintext.length()) 
				break; 
		} 
		 
		System.out.println("\n\t\tMatrix Representation is : \n"); 
		 
		String header = key.toUpperCase(); 
		 
		for (int i = 0; i < key.length(); i++) { 
			System.out.print(header.charAt(i) + "\t"); 
		} 
		 
		System.out.print("\n---------------------------------------"); 
		 
		for (int i = 0; i < m; i++) { 
			System.out.print("\n"); 
			for (int j = 0; j < n; j++) { 
				System.out.print(enmatrix[i][j] + "\t"); 
			} 
		} 
		 
		for (int j = 0; j < sortedkey.length(); j++) { 
			for (int i = 0; i < m ; i++) { 
				ciphmatrix[i][j] = enmatrix[i][key.indexOf(sortedkey.charAt(j))]; 
			} 
		} 
		 
		System.out.println("\n\n\n\t\tEncrypted Matrix is : \n\n"); 
		 
		header = sortedkey.toUpperCase(); 
		 
		 
		for (int i = 0; i < key.length(); i++) { 
			System.out.print(header.charAt(i) + "\t"); 
		} 
		 
		System.out.print("\n---------------------------------------"); 
		 
		for (int i = 0; i < m; i++) { 
			System.out.print("\n"); 
			for (int j = 0; j < n; j++) { 
				System.out.print(ciphmatrix[i][j] + "\t"); 
			} 
		} 
		 
		String ciphertext = ""; 
		 
		for (int j = 0; j < sortedkey.length(); j++) { 
			for (int i = 0; i < m ; i++) { 
				ciphertext = ciphertext + ciphmatrix[i][j]; 
			} 
		} 
		System.out.println("\n\nCiphertext by columnar transposition algorithm is\t: " + ciphertext); 
		return ciphertext; 
	} 
	 
	public void decryption(String ciphertext, String key, String sortedkey){ 
		System.out.println("\n===================== DECRYPTION ==========================\n"); 
		int row = 0,col = 0; 
		col = key.length(); 
		row = ciphertext.length()/key.length(); 
		 
		char[][] dciphmatrix = new char[row][col]; 
		char[][] decrymatrix = new char[row][col]; 
		 
		int index = 0; 
		for (int j = 0; j < col; j++) { 
			for (int i = 0; i < row; i++) { 
					dciphmatrix[i][j] = ciphertext.charAt(index); 
					index++; 
			} 
			if(index>=ciphertext.length()) 
				break; 
		} 
		 
		System.out.println("\nCiphertext Matrix Representation is : \n\n"); 
		 
		String header = sortedkey.toUpperCase(); 
		 
		for (int i = 0; i < key.length(); i++) { 
			System.out.print(header.charAt(i) + "\t"); 
		} 
		 
		System.out.print("\n---------------------------------------"); 
		 
		for (int i = 0; i < row; i++) { 
			System.out.print("\n"); 
			for (int j = 0; j < col; j++) { 
				System.out.print(dciphmatrix[i][j] + "\t"); 
			} 
		} 
		 
		 
		 
		for (int j = 0; j < sortedkey.length(); j++) { 
			for (int i = 0; i < row ; i++) { 
				decrymatrix[i][j] = dciphmatrix[i][sortedkey.indexOf(key.charAt(j))]; 
			} 
		} 
		 
		 
		System.out.println("\nDecrypted Matrix is : \n  "); 
		 
		header = key.toUpperCase(); 
		 
		for (int i = 0; i < key.length(); i++) { 
			System.out.print(header.charAt(i) + "\t"); 
		} 
		 
		System.out.print("\n---------------------------------------"); 
		 
		for (int i = 0; i < row; i++) { 
			System.out.print("\n"); 
			for (int j = 0; j < col; j++) { 
				System.out.print(decrymatrix[i][j] + "\t"); 
			} 
		} 
		 
		String decryptedtext = ""; 
		 
		for (int i = 0; i < row; i++) { 
			for (int j = 0; j < col ; j++) { 
				decryptedtext = decryptedtext + decrymatrix[i][j]; 
			} 
		} 
		 
		decryptedtext = decryptedtext.replaceAll("X", ""); 
		 
		System.out.println("\nDecrypted Ciphertext is\t: " + decryptedtext); 
	} 
	 
	public String encryption(String plaintext, int key){ 
		System.out.println("\n===================== CAESAR ENCRYPTION ==========================\n"); 
		 
		String ciphertext = ""; 
		int temp; 
		char cipherchar; 
		for (int i = 0; i < plaintext.length(); i++) { 
			if(plaintext.charAt(i)==' ') 
			{ 
				ciphertext = ciphertext + " "; 
				continue; 
			} 
			temp = plaintext.charAt(i); 
			temp = ((temp - 97) + key)%26; 
			cipherchar = (char)(temp+97); 
			ciphertext = ciphertext + String.valueOf(cipherchar); 
		} 
		System.out.println("\nCiphertext by caeser encryption algorithm\t: " + ciphertext); 
		 
		return ciphertext; 
	} 
	 
	public void decryption(String ciphertext, int key){ 
		System.out.println("\n===================== CAESAR DECRYPTION ==========================\n"); 
		String plaintext = ""; 
		int temp; 
		char plainchar; 
		for (int i = 0; i < ciphertext.length(); i++) { 
			if(ciphertext.charAt(i)==' ') 
			{ 
				plaintext = plaintext + " "; 
				continue; 
			} 
			temp = ciphertext.charAt(i); 
			if(((temp - 97) - key)<0) 
				temp = 26 + ((temp - 97)-key); 
			else 
				temp = ((temp - 97) - key)%26; 
			plainchar = (char)(temp+97); 
			plaintext = plaintext + String.valueOf(plainchar); 
		} 
		System.out.println("\nDecrypted Ciphertext is\t: " + plaintext); 
	} 
	/*public String remove_rep(String to_remove)
	{   
	    String removed = to_remove;
	    String temp = "abcdefghijklmnopqrstuvwxyz";
	    char[] charArray = temp.toCharArray();
	    for (int i = 0; i < to_remove.length(); i++)
	    { 
	        for (int j = 0; j < temp.length(); j++)
			{
			    if(to_remove.charAt(i)==charArray[j])
			}
		} 
	    return removed;
	}
	*/
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in); 
		String key,plaintext="",ciphertext=""; 
		int caeserkey=0; 
		ciphers obj = new ciphers(); 
		 
		 
		 
		System.out.print("\n\nEnter plaintext to encrypt\t: "); 
		plaintext = sc.nextLine(); 
		 
		System.out.print("\n\nSelect Encryption algorithm : \n1.Caeser Encryption Algorithm.\n2.Columnar Transposition Encryption Algorithm.\n -->   "); 
		int choice = sc.nextInt(); 
		 
		switch (choice) { 
			case 1: 
				System.out.println("\n\nEnter the caeser key (integer value)\t: "); 
				caeserkey = sc.nextInt(); 
				ciphertext = obj.encryption(plaintext, caeserkey); 
				obj.decryption(ciphertext, caeserkey); 
				break; 
			case 2: 
				System.out.print("\n\nEnter the columnar traspose key (String value)\t: "); 
				key = sc.next(); 
				char[] temp = key.toCharArray(); 
				Arrays.sort(temp); 
				String sortedkey = new String(temp); 
				
				//String newplain = obj.remove_rep(plaintext); 
 
				ciphertext = obj.encryption(plaintext, key, sortedkey); 
				obj.decryption(ciphertext, key, sortedkey); 
				break; 
				 
			default: 
				System.out.println("\n\nPlease enter valid input"); 
				break; 
		} 
		 
		 
	} 
} 

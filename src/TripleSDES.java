import java.util.ArrayList;

/*
 * 2. TripleSDES
 
The DES algorithm uses keys of length 56 bits, which, when DES was originally designed, was thought to be secure enough to meet most needs. However, 
the increase in computing power makes it more tractable to brute-force crack a 56-bit key. Thus, an alternative version of DES using longer keys 
was desirable. The result, known as Triple DES uses two 56-bit raw keys k1 and k2 and is implemented by composing DES with itself three times in the 
following way:
E3DES(p) = EDES(k1,DDES(k2,EDES(k1, p)))
Here, p is the plaintext to encrypt, EDES is the usual DES encryption algorithm and DDES is the DES decryption algorithm. This strategy doubles 
the number of bits in the key, at the expense of performing three times as many calculations. This approach was shown to offer only the security 
of a 57-bit key rather than 112 bits as you might expect.)
The TripleDES decryption algorithm is just the reverse:
D3DES(c) = DDES(k1,EDES(k2,DDES(k1, c)))
For this part of the project, implement a class called TripleSDES that provides the following methods and calculates the TripleSDES encryption.
 
public static byte[] Encrypt( byte[] rawkey1, byte[] rawkey2, byte[] plaintext )
public static byte[] Decrypt( byte[] rawkey1, byte[] rawkey2, byte[] ciphertext )
 

 */

/* Test Cases
 Use your implementation to complete the following table:
 
Raw Key 1        Raw Key        Plaintext         Ciphertext
 
0000000000     0000000000     00000000         ?
1000101110     0110101110     11010111         ?
1000101110     0110101110     10101010         ?
1111111111     1111111111     10101010         ?
1000101110     0110101110     ?                     11100110
1011101111     0110101110     ?                     01010000
0000000000     0000000000     ?                     10000000
1111111111     1111111111     ?                     10010010
 
 */

public class TripleSDES {	
	static String[][] __s0 = { {"01", "00", "11", "10"}, 
		{"11", "10", "01", "00"}, 
		{"00", "10", "01", "11"},
		{"11", "01", "11", "10"} };
	static String[][] __s1 = { {"00", "01", "10", "11"},
		{"10", "00", "01", "11"},
		{"11", "00", "01", "00"},
		{"10", "01", "00", "11"} };

	static boolean debug = false;
	
	public static void main(String[] args) {
		byte[] rk1_1 = {0,0,0,0,0, 0,0,0,0,0};
		byte[] rk1_2 = {1,0,0,0,1, 0,1,1,1,0};
		byte[] rk1_3 = {1,0,0,0,1, 0,1,1,1,0};
		byte[] rk1_4 = {1,1,1,1,1, 1,1,1,1,1};
		byte[] rk1_5 = {1,0,0,0,1, 0,1,1,1,0};
		byte[] rk1_6 = {1,0,1,1,1, 0,1,1,1,1};
		byte[] rk1_7 = {0,0,0,0,0, 0,0,0,0,0};
		byte[] rk1_8 = {1,1,1,1,1, 1,1,1,1,1};
		
		byte[] rk2_1 = {0,0,0,0,0, 0,0,0,0,0};
		byte[] rk2_2 = {0,1,1,0,1, 0,1,1,1,0};
		byte[] rk2_3 = {0,1,1,0,1, 0,1,1,1,0};
		byte[] rk2_4 = {1,1,1,1,1, 1,1,1,1,1};
		byte[] rk2_5 = {0,1,1,0,1, 0,1,1,1,0};
		byte[] rk2_6 = {0,1,1,0,1, 0,1,1,1,0};
		byte[] rk2_7 = {0,0,0,0,0, 0,0,0,0,0};
		byte[] rk2_8 = {1,1,1,1,1, 1,1,1,1,1};
		
		byte[] pt1 = {0,0,0,0, 0,0,0,0};
		byte[] pt2 = {1,1,0,1, 0,1,1,1};
		byte[] pt3 = {1,0,1,0, 1,0,1,0};
		byte[] pt4 = {1,0,1,0, 1,0,1,0};
		byte[] pt5 = {1,1,1,1, 1,1,0,1};
		byte[] pt6 = {0,1,0,0, 1,1,1,1};
		byte[] pt7 = {0,1,0,1, 0,0,1,0};
		byte[] pt8 = {0,0,1,0, 0,1,0,1};
		
		byte[] ct1 = {1,1,1,1, 0,0,0,0};
		byte[] ct2 = {1,0,1,1, 1,0,0,1};
		byte[] ct3 = {1,1,1,0, 0,1,0,0};
		byte[] ct4 = {0,0,0,0, 0,1,0,0};
		byte[] ct5 = {1,1,1,0, 0,1,1,0};
		byte[] ct6 = {0,1,0,1, 0,0,0,0};
		byte[] ct7 = {1,0,0,0, 0,0,0,0};
		byte[] ct8 = {1,0,0,1, 0,0,1,0};
		
		
		byte[] r1 = rk1_8;
		byte[] r2 = rk2_8;
		byte[] p = pt8;
		byte[] c = ct8;
		
		//Encryption
		printArray(Encrypt(r1, r2, p));
		
		// Decryption
		printArray(Decrypt(r1, r2, c));
	}

	public static byte[] Encrypt( byte[] rawkey1, byte[] rawkey2, byte[] plaintext) {
		// Encrypt like = encrypt(k1, decrypt(k2, encrypt(k1, p) ) ) 
		byte[] encrypted = null;
		
		encrypted = encryptMini(rawkey1, decryptMini(rawkey2, encryptMini(rawkey1, plaintext)));
		System.out.println("Triple Encrypted Text:");
		return encrypted;
	}
	
	public static byte[] Decrypt(byte[] rawkey1, byte[] rawkey2, byte[] ciphertext) {
		// Decrypt like = decrypt(k1, encrypt(k2, decrpyt(k1, c) ) ) 
		byte[] decrypted = null;
		
		decrypted = decryptMini(rawkey1, encryptMini(rawkey2, decryptMini(rawkey1, ciphertext)));
		System.out.println("Triple Decrypted Text:");
		return decrypted;
	}
	
	public static byte[] encryptMini(byte[] rawkey, byte[] plaintext) {
		byte[] encrypted = null;
		// Method consisting of P10, Left Shifts, and P8 to generate keys
		ArrayList<byte[]> keys = GenerateKeys(rawkey);
		byte[] k1 = keys.get(0);
		byte[] k2 = keys.get(1);
		
		// Initial Permutation
		byte[] ip = InitialPermutation(plaintext);
		
		// Round consisting of Expand/Permutate, XOR, S0 and S1, P4, and final XOR
		byte[] round1 = round(ip, k1);
		
		// Swap
		byte[] combined = combineBytes(subArray(ip, 4, 7), round1);
		
		// Round 2
		byte[] round2 = round(combined, k2);
		
		// Swap 2
		byte[] combined2 = combineBytes(round2, subArray(combined, 4, 7));
		
		// Inverse Permutation
		encrypted = InversePermutation(combined2);
		return encrypted;
	}
	
	public static byte[] decryptMini(byte[] rawkey, byte[] ciphertext) {
		byte[] decrypted = null;
		
		ArrayList<byte[]> keys = GenerateKeys(rawkey);
		byte[] k1 = keys.get(0);
		byte[] k2 = keys.get(1);
		
		//IP
		byte[] ip = InitialPermutation(ciphertext);
		
		//fk w/ K2
		byte[] round1 = round(ip, k2);
		
		//SW
		byte[] combined = combineBytes(subArray(ip, 4, 7), round1);
		
		//fk w/ K1
		byte[] round2 = round(combined, k1);
		byte[] combined2 = combineBytes(round2, subArray(combined, 4, 7));		
		
		//InvP
		decrypted = InversePermutation(combined2);	
		return decrypted;
	}
	
	public static byte[] P10(byte[] rawkey) {
		//P10
		// 3 5 2 7 4 - 10 1 9 8 6
		int[] p10Arrangement = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
		byte[] p10 = new byte[10]; 
		for (int i=0; i<rawkey.length; i++) {
			p10[i] = rawkey[p10Arrangement[i]-1];
		}
		if (debug) {
			System.out.println("P10");
			printArray(p10);			
		}
		
		return p10;
	}
	public static byte[] LeftShift(byte[] array, int count) {
		// Left Shift the halves
		byte[] shifted = new byte[array.length];

		for (int i=0; i<array.length/2; i++) {
			shifted[i] = array[(i+count) % (array.length/2)];
		}
		
		for (int i=array.length/2; i<array.length; i++) {
			if ( ((i+count) % (array.length)) == 0 ) {
				count+=5;
			}
			shifted[i] = array[(i+count) % (array.length)];
		}
		if (debug) {
			System.out.println("Left Shift");
			printArray(shifted);			
		}
		
		return shifted;
	}
	public static byte[] P8(byte[] array) {
		// P8 (first 2 discarded)
		// x x 6 3 7 4 8 5 10 9
		int[] p8Arrangement = {6, 3, 7, 4, 8, 5, 10, 9};
		// AKA K1
		byte[] key = new byte[8];
		for (int i=0; i< key.length; i++) {
			key[i] = array[p8Arrangement[i]-1];
		}
		if (debug) {
			System.out.println("Key");
			printArray(key);			
		}
		
		return key;
	}
	public static byte[] InitialPermutation(byte[] array) {
		// Initial Permutation
		// 2 6 3 1 4 8 5 7
		int[] permutationArrangement = {2, 6, 3, 1, 4, 8, 5, 7};
		byte[] ip = new byte[8];
		
		for (int i=0; i<ip.length; i++) {
			ip[i] = array[permutationArrangement[i]-1];
		}
		if (debug) {
			System.out.println("Initial Permutation");
			printArray(ip);				
		}	
		
		return ip;
	}
	public static byte[] ExpandAndPermutate(byte[] array) {
		// Expand / Permutate
		// 4 1 2 3 2 3 4 1
		int[] eAndPArrangement = {4, 1, 2, 3, 2, 3, 4, 1};
		byte[] ep = new byte[8];
		for (int i=0; i<ep.length; i++) { 
			ep[i] = array[eAndPArrangement[i]-1];
		}
		if (debug) {
			System.out.println("Expand and Permutate");
			printArray(ep);			
		}
		
		return ep;
	}
	public static byte[] xor(byte[] array, byte[] key) {
		// XOR with Key
		byte[] xor = new byte[array.length];
		for (int i=0; i<array.length; i++) {
			if (array[i] == key[i]) {
				xor[i] = 0;
			} else {
				xor[i] = 1;
			}
		}
		if (debug) {
			System.out.println("XOR");
			printArray(xor);			
		}
		
		return xor;
	}
	public static String sBox(byte[] array, String[][] sBox) {
		int row; //array[0]array[3]
		if (array[0] == 0 && array[3] == 0) {
			row = 0;
		} else if (array[0] == 0 && array[3] == 1) {
			row = 1;
		} else if (array[0] == 1 && array[3] == 0) {
			row = 2;
		} else {
			row = 3;
		}
		int col; //array[1]array[2]
		if (array[1] == 0 && array[2] == 0) {
			col = 0;
		} else if (array[1] == 0 && array[2] == 1) {
			col = 1;
		} else if (array[1] == 1 && array[2] == 0) {
			col = 2;
		} else {
			col = 3;
		}
		
		String s0 = sBox[row][col];
		if (debug) {
			System.out.println("S Box");
			System.out.println(s0);			
		}
		
		return s0;
	}
	public static byte[] subArray(byte[] array, int start, int stop) {
		byte[] newArray = new byte[stop-start+1];
		
		for (int i=0; i<newArray.length; i++) {
			newArray[i] = array[start+i];
		}
		
		return newArray;
	}
	public static byte[] P4(String s0, String s1) {
		// P4
		// 2 4 3 1
		byte[] p4 = new byte[4];
		p4[0] = (byte)(s0.charAt(1)-48);
		p4[1] = (byte)(s1.charAt(1)-48);
		p4[2] = (byte)(s1.charAt(0)-48);
		p4[3] = (byte)(s0.charAt(0)-48);
		if (debug) {
			System.out.println("P4");
			printArray(p4);			
		}
		
		return p4;
	}
	public static byte[] combineBytes(byte[] array1, byte[] array2) {
		// Combine the 2 bytes
		byte[] combined = new byte[8];
		combined[0] = array1[0];
		combined[1] = array1[1];
		combined[2] = array1[2];
		combined[3] = array1[3];
		combined[4] = array2[0];
		combined[5] = array2[1];
		combined[6] = array2[2];
		combined[7] = array2[3];
		if (debug) {
			System.out.println("Swapped");
			printArray(combined);			
		}
		
		return combined;
	}
	public static byte[] round(byte[] ip, byte[] key) {
		// Expand / Permutate
		byte[] ep = ExpandAndPermutate(subArray(ip, 4, 7));
		
		// XOR with K1
		byte[] xor = xor(ep, key);
		
		// S0
		String s0 = sBox(subArray(xor, 0, 3), __s0);
		
		// S1
		String s1 = sBox(subArray(xor, 4, 7), __s1);
		
		// P4
		byte[] p4 = P4(s0, s1);
				
		// XOR with 1st left 4 bits
		byte[] xor2 = xor(p4, subArray(ip, 0, 4));
		
		return xor2;
	}
	public static byte[] InversePermutation(byte[] array) {
		//Inverse Permutation
		// 4 1 3 5 7 2 8 6
		int[] invPermArrangement = {4, 1, 3, 5, 7, 2, 8, 6};
		byte[] invP = new byte[8];
		
		for (int i=0; i<invP.length; i++) {
			invP[i] = array[invPermArrangement[i]-1];
		}
		if (debug) {
			System.out.println("Inverse Permutation");
			printArray(invP);			
		}
		
		return invP;
	}
	public static ArrayList<byte[]> GenerateKeys(byte[] rawkey) {
		//P10
		byte[] p10 = P10(rawkey);
		// Left Shift the halves
		rawkey = LeftShift(p10, 1);
		// P8 (first 2 discarded) AKA K1
		byte[] k1 = P8(rawkey);
		// Left Shift by 2
		// 4 5 1 2 3 - 9 10 6 7 8 
		rawkey = LeftShift(rawkey,2);
		// P8-2 (first 2 discarded) AKA K2
		byte[] k2 = P8(rawkey);
		
		ArrayList<byte[]> keys = new ArrayList<byte[]>();
		keys.add(k1);
		keys.add(k2);
		return keys;
	}
	public static void printArray(byte[] array) {
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
	
}

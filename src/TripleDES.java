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


public class TripleDES {

}

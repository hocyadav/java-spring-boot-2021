package sep17th;

import java.nio.charset.Charset;
import java.security.MessageDigest;
/**
 * 
 * @author hariomyadav
 * https://www.youtube.com/watch?v=dh8ura4rVUM
 * https://www.baeldung.com/java-string-to-byte-array
 */
public class MessageDigest_halodoc {
	public static void main(String[] args) {
		String str = "hariom";
		
		String sha512Hash = getSha512Hash(str, "ABCD");
		System.out.println(sha512Hash);
	}
	
	public static String getSha512Hash(final String input, final String salt) {
        MessageDigest md;
        StringBuilder out = new StringBuilder();
        try {
            String algorithm = "SHA-512";//we can make it global
			md = MessageDigest.getInstance(algorithm);
            System.out.println(md.toString());
            
            md.update(salt.getBytes(Charset.defaultCharset()));
            byte[] mb2 = md.digest();
            printByteArray(mb2);
            
            md.update(input.getBytes(Charset.defaultCharset()));
            byte[] mb3 = md.digest();
            printByteArray(mb3);
            
            byte[] mb = md.digest();
            for (byte temp : mb) {
                String hexString = Integer.toHexString(temp);
				final StringBuilder stringBuilder = new StringBuilder(hexString);
				
                while (stringBuilder.length() < 2) {
                    stringBuilder.insert(0, "0");
                }
                String s = stringBuilder.toString();
                s = s.substring(s.length() - 2);
                out.append(s);
            }
        } catch (Exception e) {
            System.out.println("error in generating SHA 512 of String : {}, {}"+ input + e.getMessage());
        }
        return out.toString();
    }

	private static void printByteArray(byte[] bytes) {
		for (byte b : bytes) {
			System.out.print(b+" ");
		}
		System.out.println();
	}

}
/**
 * 
SHA-512 Message Digest from SUN, <initialized>

73 -20 85 -67 -125 -4 -42 120 56 -29 -45 -123 -50 -125 22 105 -29 -8 21 -89 -12 75 122 -91 -8 -43 43 93 66 53 76 70 -40 -100 -117 -99 6 -28 122 121 122 -28 -5 -46 34 -111 -66 21 -68 -61 91 7 115 92 74 111 -110 53 127 -109 -43 -93 61 -101 
-33 -101 84 7 21 76 95 86 -73 87 -14 89 -77 55 95 26 87 -91 -8 120 12 -116 55 -106 78 -35 -96 -62 109 64 -22 -24 83 -39 106 -65 -91 -93 -52 68 18 72 37 -107 17 -17 -39 -79 87 -16 67 -70 -97 15 -67 -116 115 64 -58 117 31 57 58 -111 
cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e


 */

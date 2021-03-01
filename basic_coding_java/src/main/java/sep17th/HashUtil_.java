package sep17th;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class HashUtil_ {
	public String getSha512Hash(final String input, final String salt) {
        MessageDigest md;
        StringBuilder out = new StringBuilder();
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(Charset.defaultCharset()));
            md.update(input.getBytes(Charset.defaultCharset()));
            byte[] mb = md.digest();
            for (byte temp : mb) {
                final StringBuilder stringBuilder = new StringBuilder(Integer.toHexString(temp));
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
	

}

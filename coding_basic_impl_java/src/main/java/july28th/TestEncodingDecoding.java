package july28th;

public class TestEncodingDecoding {
	public static void main(String[] args) {
		Encoding_Decode obj = new Encoding_Decode();
		
		String encode_ = obj.encode_("open", "123");
		System.out.println(encode_);
		
		String decode_ = obj.decode_("oppeeen", "123");
		System.out.println(decode_);
		
		String decode_2 = obj.decode_(obj.encode_("open", "123"), "123");
		System.out.println(decode_2);
	}
}
/**
oppeeen
open
open


*/
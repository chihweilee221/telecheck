import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import serviceRequester.QryTelnumOpCodeService;


public class TeleCheckTest {
	

	public static void main(String[] args) {
		
		String isvAccount = "367f7deaa1ce47b185a0c91cb6d8f714";
        String isvKey = "n+ABj+1w6e1Ht2A2ziBh0Q==";
        String telNum = "0960579426";
        
        System.out.println("start.");
        QryTelnumOpCodeService qryTelnumOpCodeService = new QryTelnumOpCodeService(isvAccount, isvKey);
        System.out.println("got service instance.");
        
        System.out.println("start to query.");
        String result = qryTelnumOpCodeService.getQryResult(telNum);
//        String result = "";
        System.out.println(result);
        
        System.out.println("finish query.");

//        String msgStr = result.substring(result.indexOf("<Msg>") + 5, result.indexOf("</Msg>"));
//        System.out.println(msgStr);
//
//        
//        String resultUtf;
//		try {
//	        String str = getHexString(msgStr.getBytes());
//
//	        System.out.println(new String(msgStr.getBytes("iso-8859-1"), "big5"));
//	        System.out.println(new String(msgStr.getBytes("iso-8859-1"), "utf8"));
//
//	        System.out.println(str);
//			
//	        String success = "成功";
//	        String sucOrgHex = getHexString(success.getBytes());
//	        System.out.println("orgi:\t" + sucOrgHex);
//	        String sucBig5Hex = getHexString(success.getBytes("big5"));
//	        System.out.println("big5:\t" + sucBig5Hex);
//	        String sucMS950Hex = getHexString(success.getBytes("ms950"));
//	        System.out.println("m950:\t" + sucMS950Hex);
//	        String sucUtf8Hex = getHexString(success.getBytes("utf8"));
//	        System.out.println("utf8:\t" + sucUtf8Hex);
//	        String sucUtf16Hex = getHexString(success.getBytes("utf16"));
//	        System.out.println("utf16:\t" + sucUtf16Hex);
//	        String suc8859Hex = getHexString(success.getBytes("iso-8859-1"));
//	        System.out.println("8859-1:\t" + suc8859Hex);
//	        
//	        System.out.println();
//	        byte[] sucBinary = hexStringToByteArray(msgStr);
//	        String reverse = getHexString(sucBinary);
//	        System.out.println("revs:" + reverse);
//	        String big5Str = new String(sucBinary, "big5");
//	        System.out.println(big5Str);
//	        
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
	}

	
	static final byte[] HEX_CHAR_TABLE = {
	    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
	    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
	    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
	    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
	  };    

	  public static String getHexString(byte[] raw) 
	    throws UnsupportedEncodingException 
	  {
	    byte[] hex = new byte[2 * raw.length];
	    int index = 0;

	    for (byte b : raw) {
	      int v = b & 0xFF;
	      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
	      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
	    }
	    return new String(hex, "ASCII");
	  }

	  public static String getStringToHex(byte[] byteData) {
		     int intHex = 0;
		     String strHex = "";
		     String strReturn = "";
		     try {
		     for (int intI=0;intI<byteData.length;intI++)
		     {
		     intHex = (int)byteData[intI];
		     if (intHex<0)
		     intHex += 256;
		     if (intHex<16)
		     strHex += "0" + Integer.toHexString(intHex).toUpperCase();
		     else
		     strHex += Integer.toHexString(intHex).toUpperCase();
		     }
		     strReturn = strHex;

		     }
		     catch (Exception ex) {
		     ex.printStackTrace();
		     }
		     return strReturn;
		   }
	  
	  public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
	  
/*	  
	  public static void main(String[] args) throws Exception {
	        byte[] binary = "測試".getBytes("UTF-16");
	        System.out.printf("UTF-16\t%h %h %h %h %h %h%n", 
	            binary[0] & 0x00FF, binary[1] & 0x00FF,
	            binary[2] & 0x00FF, binary[3] & 0x00FF,
	            binary[4] & 0x00FF, binary[5] & 0x00FF);
	        
	        binary = "測試".getBytes("UTF-8");
	        System.out.printf("UTF-8\t%h %h %h %h %h %h%n", 
	            binary[0] & 0x00FF, binary[1] & 0x00FF, binary[2] & 0x00FF,
	            binary[3] & 0x00FF, binary[4] & 0x00FF, binary[5] & 0x00FF);
	        
	        binary = "測試".getBytes("Big5");
	        System.out.printf("Big5\t%h %h %h %h%n", 
	            binary[0] & 0x00FF, binary[1] & 0x00FF,
	            binary[2] & 0x00FF, binary[3] & 0x00FF);

	        binary = "測試".getBytes();
	        System.out.printf("Default\t%h %h%n", 
	            binary[0] & 0x00FF, binary[1] & 0x00FF,
	            binary[2] & 0x00FF, binary[3] & 0x00FF);
	    }
*/	    
}



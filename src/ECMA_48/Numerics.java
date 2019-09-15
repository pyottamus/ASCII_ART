package ECMA_48;

public class Numerics {
	public static final byte INTERMEDIATE_BYTE_START = 0x20;
	public static final byte INTERMEDIATE_BYTE_END = 0x2F;
	
	public static final byte NUMERIC_START = 0x30;
	public static final byte NUMERIC_END = 0x39;
	public static final byte COLON = 0x3A;
	public static final byte SEMICOLON = 0x3B;
	
	public static final byte FINAL_BYTE_START = 0x40;
	public static final byte FINAL_BYTE_END = 0x7E;
	
	public static boolean isIntermediate(byte x){
	  return x>=INTERMEDIATE_BYTE_START && x<=INTERMEDIATE_BYTE_END;
	}
	
	public static boolean isNumeric(byte x){
	  return x>=NUMERIC_START && x<=NUMERIC_END;
	}
	
	public static boolean isColon(byte x){
	  return x==COLON;
	}
	
	public static boolean isSemicolon(byte x){
	  return x==SEMICOLON;
	}
	
	
	public static boolean isFinal(byte x){
	  return x>=FINAL_BYTE_START && x<=FINAL_BYTE_END;
	}

}


package pyoUtil; 

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class Bytes extends ByteArrayOutputStream{


  protected void writeSafe(byte[] x){
    try{
      super.write(x);
    } catch(IOException e){ // should never happen
      throw new UncheckedIOException(e);
    }
  }

  protected void writeSafe(byte[] x, int off, int len){
   
      super.write(x, off, len);

  }

  protected void writeSafe(byte x){

      super.write(x);

  }

  
  public void write(byte...bytes){
    for(byte x : bytes){
      this.writeSafe(x);
    }
  } 

  public void write(Bytes...bytes){
    for(Bytes x : bytes){
      this.writeSafe(x.buf, 0, x.count);
    }
  }

  public void write(byte[]...bytes){
    for(byte[] x : bytes){
      this.writeSafe(x);
    }
  } 

  /**
   * Convert to UTF-8 string representation, then append bytes to buffer
   * @param data data to be converted and appended
   */
  public <T> void append(T data){
    this.write(this.convert(data));
  } 

  /**
   * Convert to UTF-8 string representation, then return bytes
   * @return data converted to string as UTF-8 byte array
   */
  public static <T>  byte[] convert(T data){
    return data.toString().getBytes(StandardCharsets.UTF_8);
  }

  public static byte[] concat(byte...bytes){
    Bytes byteArr = new Bytes();
    byteArr.write(bytes);
    return byteArr.getBytes();

  }
  
  public static byte[] concat(byte[]...bytes){
    
    Bytes byteArr = new Bytes();
    byteArr.write(bytes);

    return byteArr.getBytes();

  }
  
  public static Bytes construct(){
    return new Bytes();
  }

  public static Bytes construct(byte...x){
    Bytes byteArr = new Bytes();
    byteArr.write(x);
    return byteArr;
  }

  public static Bytes construct(byte[]...x){
    Bytes byteArr = new Bytes();
    byteArr.write(x);
    return byteArr;
  }

  public byte[] getBytes(){
    return this.toByteArray();
  }

  @Override 
  public String toString(){
    return new String(buf, 0, count, StandardCharsets.UTF_8);
  }

  public int length(){
    return count;
  }

}
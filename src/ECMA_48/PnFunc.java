package ECMA_48;
public class PnFunc{
  public final byte finalByte;
  public final int numParams;
  public PnFunc(char finalByte, int numParams){
    this.finalByte = (byte)finalByte;
    this.numParams = numParams;
  }
  public PnFunc(byte finalByte, int numParams){
    this.finalByte = finalByte;
    this.numParams = numParams;
  }

}

package ECMA_48;
import ECMA_48.ControlSequenceBase;
import ECMA_48.PnFunc;
import java.util.ArrayList;

public class ControlSequence{
  public final ArrayList<Integer> params;
  public final byte[] intermidiateBytes;
  public final byte finalByte;

  ControlSequence(ArrayList<Integer> params, byte[] intermidiateBytes, byte finalByte){
    this.params = params;
    this.intermidiateBytes = intermidiateBytes;
    this.finalByte = finalByte;
  }
}
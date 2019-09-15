package ECMA_48;
import ECMA_48.ControlSequenceBase;
import ECMA_48.*;
import ECMA_48.PnFunc;
import pyoUtil.Bytes;
import ECMA_48.ControlSequence;

import java.io.InputStream;
import java.text.ParseException;
import java.io.IOException;

public class ControlSequenceCodec{
  //final char[] intermediateBytes;
  final PnFunc func;
  
    

  public ControlSequenceCodec(PnFunc func){
    this.func = func;
  }


  public Bytes encode(int... params){
    assert params.length == this.func.numParams : "Too Few Parameters";

    return ControlSequenceBase.encode(this.func.finalByte, params);
  }

  public static ControlSequence decode(InputStream seq) throws ParseException, IOException{
    ControlSequence x = ControlSequenceBase.decode(seq);
    return x;
  }

}
class Cords{
  int row;
  int column;
  public Cords(int row, int column){
    this.row = row;
    this.column = column;
  }
}

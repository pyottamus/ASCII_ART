package ECMA_48;

import ECMA_48.*;
import pyoUtil.Bytes;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

class ControlSequenceBase{
  
  public static Bytes parameterize(int...params){
    final int end = params.length - 1;
    Bytes str = new Bytes(); 
    
    if(params.length > 0){
      //append all non-last parameters with a semicolon terminator
      //if numParams==1, end=0 and the for loop will be skipped.
      for(int x = 0; x<end; x++){
        str.append(params[x]);
        str.write((byte)';');
      }

      //always append last one without semicolon
      str.append(params[end]); 
    }

    
    return str;     
  }

  public static Bytes encode(byte finalByte, int... params){
    Bytes str = new Bytes(); 
    str.write(C1.CSI);
    str.write(ControlSequenceBase.parameterize(params));    
    str.write(finalByte);
    return str;
  }

  public static ControlSequence decode(InputStream stream) throws ParseException, IOException{
    ControlSequenceParser x = new ControlSequenceParser(stream);
    x.parse();
    return x.getControlSequence();
  }

}
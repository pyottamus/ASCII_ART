package ECMA_48;

import pyoUtil.Bytes;

public class Functional {
    public static final ControlSequenceCodec CUU = new ControlSequenceCodec(Pn.CUU);
    public static final ControlSequenceCodec CUD = new ControlSequenceCodec(Pn.CUD);
    public static final ControlSequenceCodec CUF = new ControlSequenceCodec(Pn.CUF);
    public static final ControlSequenceCodec CUB = new ControlSequenceCodec(Pn.CUB);
    public static final ControlSequenceCodec CNL = new ControlSequenceCodec(Pn.CNL);
    public static final ControlSequenceCodec CPL = new ControlSequenceCodec(Pn.CPL);
    public static final ControlSequenceCodec CHA = new ControlSequenceCodec(Pn.CHA);
    public static final ControlSequenceCodec CPR = new ControlSequenceCodec(Pn.CPR);
    public static final ControlSequenceCodec CUP = new ControlSequenceCodec(Pn.CUP);

    public static class DSR{
      private static ControlSequenceCodec DSR = new ControlSequenceCodec(Pn.DSR);

      public static Bytes RequestCursorPos(){
        return DSR.encode(6);
      }
    
    }
}

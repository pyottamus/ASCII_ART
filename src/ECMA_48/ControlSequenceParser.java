package ECMA_48;
import ECMA_48.*;
import ECMA_48.ControlSequence;
import pyoUtil.Bytes;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.text.ParseException;

enum state{
    PARAM_PARSE,
    CHECK_BUFFER,
    SEMICOLON,
    COLON,
    INTERMEDIATE,
    INTERMEDIATE_FINAL,
    FINAL,
    OTHER;
  }

public class ControlSequenceParser{

  private final InputStream stream;
  private ArrayList<Integer> params;
  private byte[] intermidiateBytes;
  private byte finalByte;
  private int readcount = 0;
  private byte termbuffer = 0x00; // storage for terminater/input buffer
  private state parserState;

  public ControlSequenceParser(InputStream stream){
    this.stream = stream; 
    this.params = new ArrayList<Integer>();
  }

  public void parse() throws ParseException, IOException{
    hasCSI(); // verify stream starts with CSI

    System.out.println("Has CSI");
    parserState = state.PARAM_PARSE; // set state to PARAM_PARSE and check for a number

    while(true){
      switch(parserState){
        case PARAM_PARSE:
          parseNum();
          break;
        case INTERMEDIATE:
          parseIntermediate();
          break;
        case INTERMEDIATE_FINAL:
          parseFinal();
          return;
        case FINAL:
          parseFinal();
          return;
        case OTHER:
          throw new ParseException("Malformed Command Sequence", readcount);
        
      }
      
      
    }


  }
  
  public ControlSequence getControlSequence(){
    return new ControlSequence(params, intermidiateBytes, finalByte);
  }
  /**
   * Determine why parseNum terminated, and figure out next state to transition into
   */
  protected void checkTerminator(){
    if(Numerics.isSemicolon(termbuffer)){
      // more numbers to come
      parserState = state.SEMICOLON;
    } else if(Numerics.isColon(termbuffer)){
      parserState = state.COLON;
    } else if(Numerics.isIntermediate(termbuffer)){
      parserState = state.INTERMEDIATE;
    } else if(Numerics.isFinal(termbuffer)){
      parserState = state.FINAL;
    }else if(Numerics.isNumeric(termbuffer)){
      // Masively unexpected behavior.
      throw new AssertionError("Parser not in valid state: A number apeared after consuming all number.");
    } else{
      parserState = state.OTHER;
    }
  }
  
  
  protected byte read() throws ParseException, IOException{
    int x = stream.read();
    if(x == -1){
      throw new ParseException("Unexpected EOF", readcount);
    }
    readcount++;
    return (byte)x;
  }

  protected boolean hasCSI() throws IOException, ParseException{    
    for(int x=0; x<C1.CSI.length; x++){
      boolean  charEqual = read()==C1.CSI[x];
      if(!charEqual){
        return false;
      }      
    }

    return true;
  }

  protected void parseIntermediate() throws IOException, ParseException{
    Bytes buffer = new Bytes();
    while(Numerics.isIntermediate(termbuffer)){
      buffer.write(termbuffer);
      termbuffer = read();
    }
    intermidiateBytes = buffer.getBytes();
    parserState = state.INTERMEDIATE_FINAL;
  }

  protected void parseFinal() throws ParseException{
    if(Numerics.isFinal(termbuffer)){
      finalByte = termbuffer;
    } else if(parserState == state.INTERMEDIATE_FINAL){
      throw new ParseException("Command sequence with intermediate bytes not terminated with a final byte", readcount);
    } else if(parserState == state.FINAL){
      throw new ParseException("Command sequence not terminated with a final byte", readcount);
    } else{
      throw new AssertionError("Parser not in valid state: command sequence not terminated and parser in invalid state.");
    }
  }

  protected void convertNum(Bytes num) throws ParseException{
    int x = 0;
    boolean lookaheadDefault = false;

    if(num.length() > 0){ //has a defined value
      x = Integer.parseInt(num.toString());

    } else{
      // if next is a semicolon or colon, this argument is a default, which is unsoported. otherwise, its fine, since it just means its the end
      lookaheadDefault = true;
    }


    switch(parserState){
      case SEMICOLON:
        // well defined if not lookaheadDefault. just add number to buffer
        if(lookaheadDefault){
          throw new ParseException("Default parameters not supported", readcount);
        }
        params.add(x);
        parserState = state.PARAM_PARSE;
        break;
      case COLON:
        throw new ParseException("Colon in parameters not supported", readcount);
      case INTERMEDIATE:
        // Unknowable until defualt parameters are added and the Command sequence is determined, since the last semicolon can be omited if last param is default.
        params.add(x);
        break;
      case FINAL:
        // Same as above
        params.add(x);
        break;
      case OTHER:
        throw new ParseException("Malformed Command Sequence", readcount);
    }

  }
  protected void parseNum() throws ParseException, IOException{
    Bytes num = new Bytes();
    boolean terminated = false;

    while(!terminated){
      termbuffer = read();
      if(Numerics.isNumeric(termbuffer)){
        num.write(termbuffer);
      } else{
        terminated = true;
      }
    }

    checkTerminator(); // the charactor in the termbuffer has an effect on the interpretation of this num, so state must be determined now
    convertNum(num);
  }


}
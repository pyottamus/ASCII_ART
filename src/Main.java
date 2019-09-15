import java.util.concurrent.TimeUnit;
import java.lang.reflect.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import ECMA_48.*;
import pyoConsole.*;

import java.text.ParseException;
class Main extends Thread { 

  private static int longPause = 1000;
  private static int shortPause = 500;
  private static int miniPause = 100;
  private static int microPause = 30;
  
  public static void main(String[] args) throws IOException, ParseException{
    Superglyph x = new Sun();
    System.out.write(Functional.CUP.encode(1,1).getBytes());
    x.print();



    //System.out.println(seq.params.get(1));

  }

  private static void slowtype(String s, int pauselen){
    for(int i = 0; i < s.length(); i++){
      System.out.print(s.charAt(i));
      Main.sleep(pauselen);
    }
  }
  private static void sleep(int mili){
    try{
      TimeUnit.MILLISECONDS.sleep(mili);
    } catch(InterruptedException ex){
      Thread.currentThread().interrupt();
    }
  }
}
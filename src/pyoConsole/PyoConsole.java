package pyoConsole;
//import java.util.function.*;
//import ECMA_48;
//import ECMA_48.*;

//import ECMA_48.ControlSequenceCodec;
//import pyoUtil.Bytes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PyoConsole{
  
  public static int[] getConsoleSize() throws InterruptedException, IOException{
	final String pattern = "([0-9]+)\\s+([0-9]+)";
    Pattern r = Pattern.compile(pattern);
    
    String cmd2[] = { "/bin/sh", "-c", "stty size </dev/tty" };
    Process p = Runtime.getRuntime().exec(cmd2);

    BufferedReader cmdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader cmdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    int exitCode=p.waitFor();
    
    if(exitCode != 0){
      throw new IOException("Test");
    }
    
    Matcher m = r.matcher(cmdOut.readLine());
    
    if(m.find()) {
    	int[] ret = new int[2];
    	ret[0] = Integer.parseInt(m.group(0));
    	ret[1] = Integer.parseInt(m.group(1));
    	return ret;
     } else {
    	 throw new IOException("Failed to parse 2 integers from input");
     }
    
  
  }


  
  
  
}
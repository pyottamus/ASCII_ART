package ECMA_48;
import ECMA_48.PnFunc;
public class Pn {
	public static final PnFunc CUU = new PnFunc('A', 1); //Cursor up
	public static final PnFunc CUD = new PnFunc('B', 1); //Cursor down
	public static final PnFunc CUF = new PnFunc('C', 1); //Cursor right
	public static final PnFunc CUB = new PnFunc('D', 1); //Cursor left
	public static final PnFunc CNL = new PnFunc('E', 1); //Cursor Next line
	public static final PnFunc CPL = new PnFunc('F', 1); //Cursor preceeding line
	public static final PnFunc CHA = new PnFunc('G', 1);//Cursor charactor absolute
	public static final PnFunc CUP = new PnFunc('H', 2);//Cursor position
	public static final PnFunc CPR = new PnFunc('R', 2);//Device status report
	public static final PnFunc DSR = new PnFunc('n', 1);//Device status report
}

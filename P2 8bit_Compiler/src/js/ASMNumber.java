package eightBit.js;
import java.io.*;
public class ASMNum extends ASMAtom<String>{
   public ASMNum(String value){
      super(value);
   }
   @Override
   public void genCode(PrintStream out){
	   out.print("[");
       super.genCode(out);
	   out.print("]");
   }
}
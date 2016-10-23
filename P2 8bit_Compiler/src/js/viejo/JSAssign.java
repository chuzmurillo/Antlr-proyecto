package eightBit.js;
import java.io.*;
public class JSAssign extends  JSOperation{
   final public static JSId EQ = new JSId("=");
   public JSAssign(JSAst left, JSAst right){
      super(EQ, left, right);
   }
   public void genCode(PrintStream out){
      super.genCode(out);
	  out.print(";");
   }
   public void genData(PrintStream out){
      super.genData(out);
	  out.print(";");
   }
}
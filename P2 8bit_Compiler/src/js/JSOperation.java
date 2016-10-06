package eightBit.js;
import java.io.*;
public class JSOperation implements  JSAst{
   private JSAst oper;
   private JSAst left, right;
   public JSOperation(JSAst oper, JSAst left, JSAst right){
      this.oper = oper;
	  this.left = left;
	  this.right = right;
   }
   public void genCode(PrintStream out){
      left.genCode(out);
	  oper.genCode(out);
	  right.genCode(out);
   }
}
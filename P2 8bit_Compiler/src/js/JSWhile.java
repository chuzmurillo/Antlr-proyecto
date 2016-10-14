package eightBit.js;
import java.util.*;
import java.io.PrintStream;

public class JSWhile implements JSAst{
  
   private JSAst c, t;
   public JSWhile(JSAst c, JSAst t){
      this.c = c;
	  this.t = t;
   }
   @Override
   public void genCode(PrintStream out){
     out.format("while("); 
	 this.c.genCode(out); 
	 out.format("){"); 
	 this.t.genCode(out); 
	 out.format("}"); 
   }
}
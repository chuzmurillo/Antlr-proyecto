package eightBit.js;
import java.util.*;
import java.io.PrintStream;

public class JSLet implements JSAst{
  
   private JSAst c, t;
   public JSLet(JSAst c, JSAst t){
      this.c = c;
	  this.t = t;
   }
   @Override
   public void genCode(PrintStream out){
     out.format("let {"); 
	 this.c.genCode(out); 
	 out.format("}"); 
	 this.t.genCode(out);  
   }
   @Override
   public void genData(PrintStream out){
     out.format("let {"); 
	 this.c.genData(out); 
	 out.format("}"); 
	 this.t.genData(out);  
   }
}
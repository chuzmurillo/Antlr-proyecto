package eightBit.js;
import java.util.*;
import java.io.PrintStream;

public class JSRelMas implements JSAst{
  
   private JSAst c, t;
   public JSRelMas(JSAst c, JSAst t){
      this.c = c;
	  this.t = t;
   }
   @Override
   public void genCode(PrintStream out){
	 this.c.genCode(out); 
	 this.t.genCode(out); 
	}
	
   @Override
   public void genData(PrintStream out){
	 this.c.genData(out); 
	 this.t.genData(out); 
	}
}
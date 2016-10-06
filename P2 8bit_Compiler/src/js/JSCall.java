package eightBit.js;
import java.util.*;
import java.io.*;
public class JSCall implements JSAst{
   
   private JSAst f;
   private List<JSAst> args;
   
   public JSCall(JSAst f, JSAst e){
      this(f, Arrays.asList(e));
   }
   public JSCall(JSAst f, List<JSAst> args){
      this.f = f;
      this.args = args;
	  
   }
   public void genCode(PrintStream out){
	  this.f.genCode(out);
	  out.print("(");
	  if (this.args != null)
	      this.args
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> f.genCode(out));
	  out.print(");");
	}
}
package eightBit.js;
import java.util.*;
import java.io.*;
public class JSArith implements JSAst{
   
   private JSAst f;
   private List<JSAst> args;
   
   public JSArith(JSAst f, JSAst e){
      this(f, Arrays.asList(e));
   }
   public JSArith(JSAst f, List<JSAst> args){
      this.f = f;
      this.args = args;
	  
   }
   public void genCode(PrintStream out){
	  //this.f.genCode(out);
	 if (this.args != null)
	      this.args
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> {
				  out.print("\n	DB	");
				  f.genCode();
				  out.print("\n");
			  
			  });
	}
	
	public void genData(PrintStream out){
	  //this.f.genData(out);
	  if (this.args != null){
		  args.get(0).genCode();
	      this.args
	          .stream()
	          .filter(f -> f != null)
			  .skip(1)
	          .forEach(f -> {
				  out.print("\n	DB	");
				  f.genCode();
				  out.print("\n");
			  
	  });
	  }
	}
}
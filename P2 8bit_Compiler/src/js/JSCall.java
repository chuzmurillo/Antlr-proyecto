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
   public void genData(PrintStream out){


   }

   public void genCode(PrintStream out){
   if (this.args != null)
       this.args
           .stream()
           .filter(x -> x != null)
           .forEach(x -> { 
        out.print(".");
        f.genData(out);
        out.print("_"++":\n DB "); 
        x.genCode(out); 
        out.println("\n DB 0");
        });
   //out.print(" CALL .");
   this.f.genCode(out);
 }

   public void genCode(PrintStream out){
	  if (this.args != null)
	      this.args
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> {out.print("."); this.f.genCode(out); out.print("_data:\n	DB "); f.genCode(out); out.println("\n	DB 0");});
	 // if(this.f.getValue().equals("print_string")){
		  printString(out);
	  //}
	  //out.print("	CALL .");
	  //this.f.genCode(out);
	}
	

	
}
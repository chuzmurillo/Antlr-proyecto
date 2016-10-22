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
   if (this.args != null){
	   if(parametros.size()!= 0){
		   JSAst id = parametros.get(0);
       this.args
           .stream()
           .filter(x -> x != null)
           .forEach(x -> {  
				out.print(".");
				this.f.genData(out);  
				out.print("_data_01:\n	");
				id.genCode(out);
				out.print("_");
				parametros.get(1).genCode(out);
				out.print(": DB ");
				x.genData(out); 
				out.println("");
				
				id.genCode(out);
				out.print("_");
				parametros.get(2).genCode(out);
				out.print(": DB ");
				x.genData(out); 
				out.println("");
				});
		out.print("\n	DB	0");
   }else{
	   this.args
           .stream()
           .filter(x -> x != null)
           .forEach(x -> {  
				out.print(".");
				this.f.genData(out);  
				out.print("_data_01:\n	DB	");
				x.genData(out); 
				});
		out.print("\n	DB	0"); 
   }
   }
  }
   public void genCode(PrintStream out){
	   
		out.print("	\n	PUSH .");
		this.f.genCode(out);
		out.print("_data_01\n	CALL ");
	   	this.f.genCode(out);
			out.print("\n	POP A");
   }
	

	
}
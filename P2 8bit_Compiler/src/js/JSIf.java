package eightBit.js;
import java.util.*;
import java.io.PrintStream;

public class JSIf implements JSAst{
  
   private JSAst c, t, e;
   public JSIf(JSAst c, JSAst t, JSAst e){
      this.c = c;
	  this.t = t;
	  this.e = e;
   }
   @Override
   public void genCode(PrintStream out){
     printString(out);

	}
	
	public void printString(PrintStream out){
		//TRUE
		out.println(".compare_numberes_01:");
		out.println("DB \"true\"");
		out.println("DB 0;");
		//FALSE
		out.println(".compare_numberes_02:");
		out.println("DB \"false\"");
		out.println("DB 0;");
		//COPARE
		out.println("compare:");
		out.println("POP A");
		out.println("POP C");
		out.println("POP B");
		out.println("CMP B, C");
		out.println("JA .print_bool_loop_01");
		out.println("JB .print_bool_loop_02");
		//END
	}
}
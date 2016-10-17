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
	
    public void printString(PrintStream out){
	   out.println("print_string:");
	   out.println("	POP C");
	   out.println("	POP B");
	   out.println("	PUSH C");
	   out.println(".print_string_loop_01:");
	   out.println("	MOV C, [B]");
	   out.println("	CMP C, 0");
	   out.println("	CMP C, 0");
	   out.println("	JE .print_string_exit");
	   out.println("	JE .print_string_exit");
	   out.println("	MOV [D], C");
	   out.println("	INC D");
	   out.println("	INC B");
	   out.println("	JMP .print_string_loop_01");
	   out.println(".print_string_exit:");
	   out.println("	POP C ");
	   out.println("	PUSH .UNDEF");
	   out.println("	PUSH C");
	   out.println("	RET");
   }
	
	
}
package eightBit.js;
import java.util.*;
import java.io.*;
public class JSProgram implements JSAst{
   static private JSId UNK = new JSId("");
   private JSId name;
   private List<JSAst> functions;
   private JSAst body;
   public JSProgram(List<JSAst> functions){
      this(UNK, functions);
   }
   public JSProgram(JSId name, List<JSAst> functions){
      this.functions = functions;
	  this.name = name;
   }
   public void genData(PrintStream out){
    out.println(".init:");
    out.println("   MOV D, 232");
    out.println("   JMP main");
    out.println(".main_data:");
    out.println(".UNDEF: DB 255\n");
    functions.stream().forEach( t -> t.genData(out));
	printString(out);
   }
    public void genCode(PrintStream out){
     
	functions.stream().forEach( t -> t.genCode(out));
   }
   
   
   public void printString(PrintStream out){
	   out.println("print_string:");
	   out.println("	POP C");
	   out.println("	POP B");
	   out.println("	PUSH C");
	   out.println(".print_string_loop_01:");
	   out.println("	MOV C, [B]");
	   out.println("	CMP C, 0");
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
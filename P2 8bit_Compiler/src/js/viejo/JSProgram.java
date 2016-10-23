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
   // out.println(".main_data:");
   
    functions.stream().forEach( t -> t.genData(out));
	printString(out);
	printNumber(out);
   }
    public void genCode(PrintStream out){
     
	functions.stream().forEach( t -> t.genCode(out));
		out.print("\n	HLT");
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
   
   public void printNumber(PrintStream out){
	   out.println("print_number:");
	   out.println("	POP C");
       out.println("	POP A");
       out.println("	PUSH C ");
	   out.println(".number_to_Stack:");
       out.println("	MOV B,A;");
	   out.println("	DIV 10;");
  	   out.println("	MUL 10;");
	   out.println("	SUB B, A;");
	   out.println("	PUSH B;");
	   out.println(" 	CMP A, 0;");
	   out.println("	JE .number_to_display;");
	   out.println("	DIV 10;");
	   out.println("	JMP .number_to_Stack;");
       out.println(".number_to_display:");
       out.println("	POP A;");
	   out.println("	CMP A,C;");
	   out.println("	JE .exit;");
       out.println("	ADD A, 0x30;");
	   out.println("	MOV [D], A;");
	   out.println("	INC D;");
	   out.println("	JMP .number_to_display;");
	   out.println(".exit:	");
       out.println("	PUSH .UNDEF");
       out.println("	PUSH C");
       out.println("	RET");
	   
   }
}
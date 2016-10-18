package eightBit.js;
import java.io.*;
public class JSAtom<T> implements JSAst{
   private T value;
   public T getValue(){return this.value;}
   
   public JSAtom(T value){
      this.value = value;
   }
   public void genCode(PrintStream out){
	   if (this.value.equals("print_string")){
		printString(out);
	   }else{
	   out.print(this.value);
	   }
	   
   }
   public void genData(PrintStream out){
      out.print(this.value);
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
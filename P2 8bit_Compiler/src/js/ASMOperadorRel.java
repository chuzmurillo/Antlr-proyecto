package eightBit.js;
import java.io.*;

public class ASMOperadorRel implements  ASMAst{
   private String oper;

   
   public ASMOperadorRel(String oper){
      this.oper = oper;
   }
   public void genCode(PrintStream out){
		out.print("	POP B");
		out.print("\n	POP A");
		
		if(this.oper.getText().equals("==")){
		out.println("\n	CMP A, B");
		out.println("	JZ");
		}
		if(this.oper.getText().equals("!=")){
		out.println("\n	CMP A, B");
		out.println("	JNZ");
		}
		if(this.oper.getText().equals("<=")){
		out.println("\n	SUB A, B");
		out.println("	JBE");
		}
		if(this.oper.getText().equals(">=")){
		out.println("\n	SUB A, B");
		out.println("	JAE");
		}
		if(this.oper.getText().equals("<")){
		out.println("\n	SUB A, B");
		out.println("	JB");
		}
		if(this.oper.getText().equals(">")){
		out.println("\n	SUB A, B");
		out.println("	JA");
		}
		out.print("	PUSH A\n");

     }
   
}
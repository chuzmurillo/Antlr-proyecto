package eightBit.js;
import java.io.*;

public class ASMOperador implements  ASMAst{
   private String oper;

   
   public ASMOperador(String oper){
      this.oper = oper;
   }
   public void genCode(PrintStream out){
        out.print("	POP B");
		out.print("\n	POP A");
		if(this.oper.getText().equals("+"))
		out.println("\n	ADD A,B");
		if(this.oper.getText().equals("-"))
		out.println("	SUB A,B");
		if(this.oper.getText().equals("*"))
		out.println("	MUL B");
		if(this.oper.getText().equals("/"))
		out.println("	DIV B");
		out.print("	PUSH A\n");

        }
	  

 }

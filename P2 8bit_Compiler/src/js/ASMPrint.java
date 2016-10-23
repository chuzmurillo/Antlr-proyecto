package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMPrint implements ASMAst{
   
   private int  p_string;
   private int p_number;
   private int p_boolean;
   
   public ASMPrint(int s,int n,int b){
	this.p_string=s;
	this.p_number=n;
	this.p_boolean=b;
   }
   
   public void genCode(PrintStream out){
		if(p_string==1) printString(out);
		
		if(p_number==1) printNumber(out);
		
		if(p_string==0 && p_boolean==1 ){ printString(out);  printBoolean(out);  this.p_boolean=0;  }  
		//El print_boolean utiliza el printString para pintar TRUE o False. Prevenir que el programa se caiga si solo se hace el codigo del boolean sin print
		//p_boolean=0 para que no lo imprima dos veces
		
		if(p_boolean==1) printBoolean(out);
  
   
   }
   
   
   private void printString(PrintStream out){
	 out.println("");
	 out.println(".print_string:");
	 out.println	("POP C");
	 out.println	("POP B");
	 out.println	("PUSH C") ;
	 out.println(".print_string_loop_01:");
	 out.println	("MOV C, [B]");
	 out.println	("CMP C, 0");
	 out.println	("JE .print_string_exit");
	 out.println	("MOV [D], C");
	 out.println	("INC D");
	 out.println	("INC B");
	 out.println	("JMP .print_string_loop_01");
	 out.println(".print_string_exit:");
	 out.println	("POP C ");
	 out.println	("PUSH .UNDEF");
	 out.println	("PUSH C");
	 out.println	("RET");	 
	out.println("");	 
   }
   
   private void printNumber(PrintStream out){
	 out.println("");
	 out.println(".print_number:");
	 out.println	("POP C");
	 out.println	("POP A");
	 out.println	("PUSH C") ;
	 out.println(".number_to_Stack:");
	 out.println	("MOV B,A;");
	 out.println	("DIV 10;");
	 out.println	("MUL 10;");
	 out.println	("SUB B, A;");
	 out.println	("PUSH B;");
	 out.println	("CMP A, 0;");
	 out.println	("JE .number_to_display;");
	 out.println	("DIV 10;");
	 out.println	("JMP .number_to_Stack;");
	 out.println	(".number_to_display:");
	 out.println	("POP A;");
	 out.println	("CMP A,C;");
	 out.println	("JE .exit;");
	 out.println	("ADD A, 0x30;");
	 out.println	("MOV [D], A;");
	 out.println	("INC D");	
	 out.println	("JMP .number_to_display;");	
	 out.println	(".exit:");
	 out.println	("PUSH .UNDEF\n");	
	 out.println	("PUSH C");	
	 out.println	("RET");	 
	 out.println("");	 	    
   }
   
   private void printBoolean(PrintStream out){
	out.println("");	
	out.println(".string_false: DB \"False \" "); 
	out.println("DB 0");
	out.println(".string_true: DB \"True \" ");  
	out.println("DB 0");
	out.println(".print_boolean:");
	out.println("POP C");
	out.println("POP B");
	out.println("PUSH C");
	out.println(".print_bool_01:");
	out.println("CMP B, 1");
	out.println("JZ .print_bool_true");
	out.println("CMP B, 0");
	out.println("JZ .print_bool_false");
	out.println(".print_bool_false:");
	out.println("PUSH .string_false");
	out.println("call .print_string");
	out.println("POP A");
	out.println("JMP .print_bool_exit");
	out.println(".print_bool_true:");
	out.println("PUSH .string_true");
	out.println("call .print_string");
	out.println("POP A");
	out.println("JMP .print_bool_exit");
	out.println(".print_bool_exit:");
	out.println("POP C");
	out.println("PUSH .UNDEF");
	out.println("PUSH C");
	out.println("RET");
 	out.println("");	
   }
   
   
	

}
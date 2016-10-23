package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMWhile implements ASMAst{

   private ASMAst expr; //Condicion del while
   private ASMAst close; //Lo que hace el while
   private String funcionActual;

   
   public ASMWhile(ASMAst expr,ASMAst close,String funcionActual){
      this.expr=expr;
	  this.close=close;
	  this.funcionActual=funcionActual;
   }

   public void genCode(PrintStream out){
	    out.println(".loop_"+funcionActual+":");
		this.expr.genCode(out);
		out.println(".loop_cs");
		out.println("JMP .exit_loop:" );  //Puedar conflicto si hay mas de un while en todo el programa
		out.println(".loop_cs:");
		this.close.genCode(out);
		out.println("JMP .loop_"+funcionActual);
		out.println(".exit_loop:");
   }
}
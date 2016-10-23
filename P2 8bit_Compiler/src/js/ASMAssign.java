package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMAssign implements ASMAst{
   private ASMAst expr;
   private ASMAst id; 
   public ASMAssign(ASMAst expr,ASMAst id){
      this.expr=expr;
	  this.id=id;
   }

   public void genCode(PrintStream out){
		this.expr.genCode(out);
		out.println("POP A");
		out.print("MOV ");
		out.print("[");
		this.id.genCode(out);
		out.print("]");
		out.print(",");
		out.println("A");
   }
}
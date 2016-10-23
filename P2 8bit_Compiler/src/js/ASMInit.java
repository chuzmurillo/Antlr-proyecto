package eightBit.js;

import java.util.*;
import java.io.*;

public class ASMInit implements ASMAst{
   
   private List<ASMAst> f;
   private HashMap<String,List<String>> datos;
   private ASMPrint print; 
   
   public ASMInit(List<ASMAst> f, HashMap<String,List<String>> args, ASMPrint print){
      this.f = f;
      this.datos = args;
	  this.print=print; 
   }
   
   public void genCode(PrintStream out){
	out.println(".init:");
	out.println("MOV D,232");
	out.println("JMP .main");
	out.println(".main_data:");
	out.println(".UNDEF: DB 255");
	   if (this.datos != null)
	      this.datos
	          .forEach(
			   (k,e) ->e.stream().forEach(var->printVariable(var,out))	  
			  );
		if(f!=null)
		f.stream().forEach(e->genCode());	
		if(print!=null)
		print.genCode(out);
   }
   
   private void printVariable(String var,PrintStream out){
		out.format("%s "+"\n",var );
		out.println(" DB 0");
   }
   
   
}

package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMCall implements ASMAst{
   
   private ASMAst f;
   private List<ASMAst> args;
   private String funAct;
   
   public ASMCall(ASMAst f, ASMAst e,String funAct){
      this(f, Arrays.asList(e),funAct);
   }
   
   public ASMCall(ASMAst f, List<ASMAst> args,String funAct){
      this.f = f;
      this.args = args;
	  this.funAct=funAct;
   }
   public void genCode(PrintStream out){
	  this.args.stream().forEach( t -> pushCode(out,t)); //EL DEBE HACE LOS POP de eso
	   out.print("\n call .");
	   this.f.genCode(out);
	   out.println("");
   }
   private void pushCode(PrintStream out,ASMAst t){
	   if(!this.funAct.equals("main")){
		   		//out.print("push ");
				//out.print("[");
				t.genCode(out);	
			   //out.print("]");
	   }
	   else {
		  //out.print("push ");  TALVEZ PONER, en el main sale doble push
		  t.genCode(out);	
	   }

   }
   
}
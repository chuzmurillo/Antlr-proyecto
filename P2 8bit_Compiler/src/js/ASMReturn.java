package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMReturn implements ASMAst{
   
   private List<ASMAst> exprs;
   private String funcionActual;
   
   public ASMReturn( ASMAst e,String funcionActual){
      this(Arrays.asList(e),funcionActual);
   }
   
   public ASMReturn( List<ASMAst> exprs,String funcionActual){
      this.exprs = exprs;
	  this.funcionActual=funcionActual;
   }
   public void genCode(PrintStream out){
	  this.exprs.stream().forEach( t -> t.genCode(out)); //HACE LO DE LA EXPR AQUI, despues llama al return
	  out.print("\n JMP ."+funcionActual+"_return");
	  
   }

 
   
}
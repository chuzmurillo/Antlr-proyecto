package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMProgram implements ASMAst{

   private ASMInit dataArea;
   private List<ASMAst> functions;

   public ASMProgram(ASMInit dataArea,List<ASMAst> functions){
      this.dataArea=dataArea;
	  this.functions=functions;
   }

   public void genCode(PrintStream out){
	   dataArea.genCode(out);
       functions.stream().forEach( t -> t.genCode(out));
   }
}
package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMIfstatement implements ASMAst{
   private String funAct;
   private ASMAst closedStm, ifStm, elseStm;
   
   public ASMIfSTM(ASMAst ifStm, ASMAst closedStm, ASMAst elseStm, String funAct){ 
      this.ifStm = ifStm;
	  this.closedStm = closedStm;
	  this.elseStm = elseStm;
	  this.funAct= funAct;
   }
   @Override
   public void genCode(PrintStream out){
	 this.ie.genCode(out); 
	 out.println("."+funAct+"_if");
	 if(ec!=null)  out.println("JMP ."+funAct+"_else");	
	 out.println("\n ."+funAct+"_if:");
	 this.c.genCode(out); 
	if(ec!=null){
	 out.println("\n ."+funAct+"_else:");
	 this.ec.genCode(out);	 
	 }
   }
}
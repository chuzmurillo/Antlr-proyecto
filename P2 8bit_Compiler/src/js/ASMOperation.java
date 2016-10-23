package eightBit.js;
import java.io.*;
public class ASMOperation implements  ASMAst{
	
   private ASMAst oper;
   private ASMAst left, right;
   public ASMAst getOper(){return this.oper;}
   public ASMAst getLeft(){return this.left;}
   public ASMAst getRight(){return this.right;}
   public ASMOperation(ASMAst oper, ASMAst left, ASMAst right){
      this.oper = oper;
	  this.left = left;
	  this.right = right;
   }
   
   public void genCode(PrintStream out){
	  // out.print("	POP B");
	   //out.print("	POP A")
      left.genCode(out);
	  right.genCode(out);
	  oper.genCode(out);
	  //out.print("	PUSH A\n");
   }
}
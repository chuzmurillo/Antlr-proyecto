package eightBit.js;
import java.util.*;
import java.io.*;

public class ASMPush implements ASMAst{
   
   private ASMAst atom;
   
   public ASMPush(ASMAst a){
      this.atom = a;
   }
   
   public void genCode(PrintStream out){
	   out.print("\n push ");
	   atom.genCode(out);
	   out.println("");
   }
}
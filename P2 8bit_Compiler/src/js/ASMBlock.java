package eightBit.js;
import java.util.*;
import java.io.*;
public class ASMBlock implements ASMAst {
   protected List<ASMAst> members;
   public ASMBlock(List<ASMAst> members){
      this.members = members;
   }
   public List<ASMAst> getMembers(){
	   return this.members;   
   }

   public void genCode(PrintStream out){
       this.members.stream().forEach( t -> t.genCode(out));
   }
   
}
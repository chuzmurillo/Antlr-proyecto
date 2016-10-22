package eightBit.js;

import java.util.*;
import java.io.*;

public class JSFunction implements JSAst{
   static private JSId UNK = new JSId("");
   private JSId name;
   private List<JSAst> formals;
   private JSAst body;
   
   public JSFunction(List<JSAst> formals, JSAst body){
      this(UNK, formals, body);
   }

   public JSFunction(JSId name, List<JSAst> formals, JSAst body){
      this.formals = formals;
	  this.body = body;
	  this.name = name;
   }

   public void genData(PrintStream out){
	out.format(".%s_data:\n	", this.name.getValue());
   if(this.name.getValue().equals("main")){
       out.print(".UNDEF: DB 255\n");
    }
	   if (this.body != null)
	   this.body.genData(out);
   }

   public void genCode(PrintStream out){
	out.format("%s:\n	", this.name.getValue());
	   if (this.body != null)
	      this.body.genCode(out);
   }
   
   
  
   
}
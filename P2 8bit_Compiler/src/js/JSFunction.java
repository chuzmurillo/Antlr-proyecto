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
	else{
		out.println("."+this.name.getValue()+"_ra:"); 
		out.println(" DB 0"); 
		formals.stream().forEach(f ->{
			out.print("."+this.name.getValue()+"_"); 
			f.genCode(out);
			out.println(":\n DB 0");
		});
	}
	 if (this.body != null)
	   this.body.genData(out);
   }

   public void genCode(PrintStream out){
		
	  /*if(parametros.size()!=0){
	  parametros.get(0).genCode(out);
	  out.print("tamaño de la vara -------------------------->"+parametros.size());
	  }*/
	out.format("%s:\n	", this.name.getValue());
	if(!this.name.getValue().equals("main")){
		 /*out.print(".:\n");

 .add_Prolog:
POP C
POP A
POP B
PUSH [add_y]
PUSH [add_x]
PUSH [add_ra]
MOV [add_ra], C
MOV [add_x], B
MOV [add_y], A
 .add_Body:*/
	  out.print("POP C\n");
	  // if (this.body != null)
	      this.body.genCode(out);
	  out.print("	PUSH C\n");
	  out.print("	RET\n");
		}else{
			
		this.body.genCode(out);
	}
   }
   
   
  
   
}
package eightBit.js;

import java.util.*;
import java.io.*;

public class JSFunction implements JSAst{
   static private JSId UNK = new JSId("");
   private JSId name;
   private List<JSAst> formals;
   private JSAst body;
   int i;
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
		 out.print("."+this.name.getValue()+"_prolog:\n");
		 out.print("POP C\n");
		 out.print("POP A\n");
		 out.print("POP B\n");	
		 formals.stream().forEach(f ->{
			out.print("PUSH ["); 
			f.genCode(out);
			out.println("]");
		});
		 out.print("PUSH ["+this.name.getValue()+"_ra]\n");	
		 out.print("MOV ["+this.name.getValue()+"_ra], C\n");
		 i=0;
		 formals.stream().forEach(f ->{
			out.print("MOV ["+this.name.getValue()+"_"); 
			f.genCode(out);
			out.print("],");
			if(i==0){
				out.print(" B");
			}
			else{
				out.print(" A");
			}
			out.print("\n");
			i++;
		});	
		out.print("."+this.name.getValue()+"_body:\n");
	  ////////////////////////////
	   if (this.body != null)
	    this.body.genCode(out);
		///////////////////////
		out.print("."+this.name.getValue()+"_return:\n");
		out.println("POP A");
		out.print("MOV C, ["+this.name.getValue()+"_ra]\n");
		out.println("POP B");
		out.print("MOV ["+this.name.getValue()+"_ra], B\n");
		 formals.stream().forEach(f ->{
			out.print("POP B\n");
			out.print("MOV ["+this.name.getValue()+"_"); 
			f.genCode(out);
			out.println("], B");
			out.println("PUSH A");
		});	
	  out.print("	PUSH C\n");
	  out.print("	RET\n");
		}else{			
		this.body.genCode(out);
	}
   }
   
   
  
   
}
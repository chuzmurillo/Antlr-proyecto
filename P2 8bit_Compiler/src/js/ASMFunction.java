package eightBit.js;

import java.util.*;
import java.io.*;
import java.util.stream.*;


public class ASMFunction implements ASMAst{
  
   private ASMAtom name;
   
   private List<ASMAst> formals; //SON LOS POPS DESPUES DE LLAMAR A LA FUNCION O MOVES eso xd
   
   private ASMAst body;
   private List<String> register; 
   
   public ASMFunction(ASMAtom name, List<ASMAst> formals, ASMAst body){
      this.formals = formals;
	  this.body = body;
	  this.name = name;
	  this.register= new ArrayList<>();
	  initregister();
   }

   
   public void genCode(PrintStream out){

	   
	   String value=(String)this.name.getValue();   		
       out.format(".%s:\n",value);
		
	   if(!name.getValue().equals("main")){
	   	   if (this.formals != null) {
			   out.println(" ."+value+"_Prolog:");

			   
			 this.formals			//POPs		        	
	          .stream()			                          
	          .filter(f -> f != null)
	          .forEach( f -> printPrologPOP(f,out));  							

			  
			   this.formals.stream()  	//PUSHs
						  .collect(Collectors.toCollection(LinkedList::new)) //Invierte la lista
						  .descendingIterator().forEachRemaining(f-> printPrologPUSH(f,out));
			  
			  
			 if(this.formals.size()>2) initregisterInvert(); else initregister();   
		   this.formals			             //MOVEs
	          .stream()			                          
	          .filter(f -> f != null)
	          .forEach(f -> printPrologMOVE(f,out));  	 
		}   
		
		
		else out.println("");		
		
		if (this.body != null){
		   out.println(" ."+value+"_Body:");	
		   this.body.genCode(out);	
	   }
		out.println("\n ."+value+"_return:");
		
		printReturn("["+value+"_ra]",out);
		
	   }
	   else{
		   	if (this.body != null){
		   out.println(" ."+value+"_Body:");	
		   this.body.genCode(out);	
		   out.println("\n hlt");
	   }   	
		   
	   }

   }
   private void printPrologPOP(ASMAst f,PrintStream out){
		out.print("POP ");
		if(this.register.get(0)!=null){out.println(this.register.get(0)); this.register.remove(0);  } 
		
   }
   
  private void printPrologPUSH(ASMAst f,PrintStream out){
		out.print("PUSH ");
		out.print("[");
		f.genCode(out);
		out.println("]");  
   }
   
   private void printPrologMOVE(ASMAst f,PrintStream out){
		out.print("MOV ");
		out.print("[");
		f.genCode(out);
		out.print("]");
		out.print(",");
		out.print(" ");

		if(this.register.get(0)!=null){out.println(this.register.get(0)); this.register.remove(0); }  		
   }
   private void printReturn(String raValue,PrintStream out){	   
		out.println("POP A");
		out.format("MOV C, %s \n",raValue);
		out.println("POP B");
		out.format("MOV %s, B \n",raValue);
		if(this.formals !=null){
			if(this.formals.size()>1){
							this.formals.remove(0);
			this.formals		             
						.stream()			                          
						.filter(f -> f != null)
						.forEach(f -> printRetunLoop(f,out));  	
				
			}
			else  out.println("PUSH A ");

			
		}
		out.println("PUSH C");
		out.println("RET");
   }
   
   private void printRetunLoop(ASMAst f,PrintStream out){
	   out.println("POP B");
	   out.print("MOV ");
	   out.print("[");
	   f.genCode(out);
	   out.print("]");
	   out.println(", B");
	   out.println("PUSH A ");
	   
   }
   
   private void initregister(){
	 this.register.add(0,"C");
	 this.register.add(1,"A");
	 this.register.add(2,"B");
	   
   }
   
   private void initregisterInvert(){
	 this.register.clear();
	 this.register.add(0,"C");
	 this.register.add(1,"B");
	 this.register.add(2,"A");
	   
   }
   

   
   
}
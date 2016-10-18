package eightBit.js;
import java.util.*;
import java.io.*;
public class JSProgram implements JSAst{
   static private JSId UNK = new JSId("");
   private JSId name;
   private List<JSAst> functions;
   private JSAst body;
   public JSProgram(List<JSAst> functions){
      this(UNK, functions);
   }
   public JSProgram(JSId name, List<JSAst> functions){
      this.functions = functions;
	  this.name = name;
   }
   public void genData(PrintStream out){
    out.println(".init:");
    out.println("   MOV D, 232");
    out.println("   JMP main");
    out.println(".main_data:");
    out.println(".UNDEF: DB 255\n");
    functions.stream().forEach( t -> t.genData(out));
   }
    public void genCode(PrintStream out){
     
	functions.stream().forEach( t -> t.genCode(out));
   }
}
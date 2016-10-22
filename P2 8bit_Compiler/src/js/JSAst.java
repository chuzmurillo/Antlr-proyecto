package eightBit.js;
import java.io.*;
import java.util.*;

public interface JSAst{
    default void genData(){
      genData(System.out);
   }
   default void genData(PrintStream out){

   }

   default void genCode(){
      genCode(System.out);
   }
   default void genCode(PrintStream out){
   }

   default String getText(){
     return  "***DEFAULT TEXT***";
   }
   List<JSAst> parametros = new LinkedList<JSAst>();
}

package eightBit.js;
import java.io.*;

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
}
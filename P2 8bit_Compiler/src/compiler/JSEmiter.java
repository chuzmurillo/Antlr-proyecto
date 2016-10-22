package eightBit.compile;

import eightBit.js.*;
import java.util.*;

public interface JSEmiter{
	
   default JSAst PROGRAM(List<JSAst> functions){ return new JSProgram(functions);} 
   
   default JSAst BLOCK(List<JSAst> members){ return new JSBlock(members);}
   default JSAst BLOCK(){ return new JSBlock(Arrays.asList());}
   
   default JSAst EMPTY(){
	   return new JSEmpty();
   }
   default JSAst FOLD_LEFT(JSAst left, JSAst right){
	   JSOperation rightOperation = (JSOperation)right;
	   return new JSOperation(rightOperation.getOper(), left, rightOperation.getRight());
   }
   default JSAst LET(JSAst asst, JSAst lisaast){
	   return new JSLet(asst, lisaast);
   }
   
   default JSNum NUM(double value){ return new JSNum(value);}
   
   default JSId  ID(String value){return new JSId(value);}
   
   default JSFunction FUNCTION(JSId id, List<JSAst> formals, JSAst body){
           return new JSFunction(id, formals, body);
   }
   
   default JSRelMas REL(JSAst a, JSAst b){
	   return new JSRelMas(a,b);
   }
   
   default JSIf IF(JSAst c, JSAst t, JSAst e){
       return new JSIf(c,t,e);
   }
   default JSWhile WHILE(JSAst c, JSAst t){
       return new JSWhile(c, t);
   }
   default JSCall CALL(JSAst f, List<JSAst> args){
       return new JSCall(f, args);
   }
   default JSAst OPERATION(JSAst oper, JSAst left, JSAst right){
	   return new JSOperation(oper, left, right);
   }
   default JSAst ASSIGN(JSAst left, JSAst right){
	   return new JSAssign(left, right);
   }
   default JSAst ARITH(JSAst f, List<JSAst> args){
       return new JSArith(f, args);
   }
   
   default List<JSAst> ARGS(JSAst... args){ return Arrays.asList(args);}
   default List<JSAst> ARGS(List<JSAst> args){ return args;}
   default List<JSAst> FORMALS(JSAst... args){ return Arrays.asList(args);}
    default List<JSAst> FORMALS(List<JSAst> args){ return args;}
   
   
   default JSAst RET(JSAst e){ return new JSReturn(e);}
   default JSAst OPER(String op){return new JSId(op);}
   final JSBool TRUE = new JSBool(true);
   final JSBool FALSE = new JSBool(false);
   final JSAst ADD = new JSId("+");
   final JSAst MINUS = new JSId("-");
   final JSAst MUL = new JSId("*");
   final JSAst DIV = new JSId("/");
   
   
   
}
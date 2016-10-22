/*
 loriacarlos@gmail.com EIF400 II-2016
 EightBit starting compiler
*/
package eightBit.compile;


import eightBit.js.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;
import java.util.stream.*;


public class Compiler extends EightBitBaseVisitor<JSAst> implements JSEmiter{
   protected JSAst program;
   public JSAst getProgram(){
	   return this.program;
   }
   protected List<JSAst> statements = new ArrayList<>();
   
  

   public void genCode(){
     this.program.genData();
	 this.program.genCode();
   }
   public JSAst compile(ParseTree tree){
      return visit(tree);
   }
   @Override
   public JSAst visitEightProgram(EightBitParser.EightProgramContext ctx){
	   ctx.eightFunction().stream()
	                      .forEach( fun -> visit(fun) );
	   return this.program = PROGRAM(this.statements);
   }
   
   
   
   @Override
   public JSAst visitEightFunction(EightBitParser.EightFunctionContext ctx){
      
    JSId id = (JSId)visit(ctx.id());
	  JSAst f = visit(ctx.formals());
	  JSAst body = visit(ctx.funBody());
	  JSAst function = FUNCTION(id, FORMALS(f), body);
	  this.statements.add(function);
	  return function;
   }
   @Override
   public JSAst visitEmptyStatement(EightBitParser.EmptyStatementContext ctx){
      return EMPTY();
	                
   }
   
   @Override
   public JSAst visitCallStatement(EightBitParser.CallStatementContext ctx){
	   JSAst id = ID(ctx.ID().getText());
	   JSAst args = visit(ctx.arguments());
	   return CALL(id, ARGS(args));
   }
   
   @Override
   public JSAst visitReturnStatement(EightBitParser.ReturnStatementContext ctx){
      return RET(visit(ctx.expr()));
	                
   }
   @Override
   public JSAst visitAssignStatement(EightBitParser.AssignStatementContext ctx){
	  return ASSIGN(visit(ctx.id()), visit(ctx.expr()));
	                
   }
   @Override
   public JSAst visitBlockStatement(EightBitParser.BlockStatementContext ctx){
	  EightBitParser.ClosedListContext closedList = ctx.closedList();
      return (closedList == null ) ? BLOCK() 
	                               : visit(closedList);
   }
   @Override
   public JSAst visitClosedList(EightBitParser.ClosedListContext ctx){					  
					   return  BLOCK(ctx.closedStatement().stream()
	                                                      .map( c -> visit(c))
										                  .collect(Collectors.toList()));
	                
   }
   @Override
   public JSAst visitFormals(EightBitParser.FormalsContext ctx){
	   EightBitParser.IdListContext idList = ctx.idList();
	   return (idList == null ) ? BLOCK()
	                            : visit(idList);
   }
   @Override
   public JSAst visitIdList(EightBitParser.IdListContext ctx){
	   return  BLOCK(ctx.id().stream()
						     .map( c -> visit(c))
						     .collect(Collectors.toList()));
	
   } 
   @Override
   public JSAst visitId(EightBitParser.IdContext ctx){
	  return  ID(ctx.ID().getText());
   }
   
   
   @Override
    public JSAst visitArithOperation(EightBitParser.ArithOperationContext ctx) {
	   if (ctx.oper == null)
		   return visit(ctx.arithMonom().get(0));
	   JSAst oper = ( ctx.oper.getType() == EightBitParser.ADD ) ? ADD : MINUS;
       List<JSAst> exprs = ctx.arithMonom().stream()
	                                       .map( c -> visit(c) )
										   .collect(Collectors.toList());
	   return exprs.stream()
	               .skip(1)
				   .reduce(exprs.get(0), (opers, expr) ->
	                              OPERATION(oper, opers , expr));
	   
    }
   @Override
    public JSAst visitArithMonom(EightBitParser.ArithMonomContext ctx){
	JSAst left = visit(ctx.arithSingle());
		return (ctx.operTDArithSingle() == null) 
		       ? left
		       :ctx.operTDArithSingle().stream()
	                                   .map( c -> visit(c) )
									   .reduce(left, (opers, expr) 
									                      -> FOLD_LEFT(opers , expr));
	}
   @Override
   public JSAst visitArithIdSingle(EightBitParser.ArithIdSingleContext ctx){
	  JSAst id = visit(ctx.id());
	  if(ctx.arguments()!=null){
	  JSBlock args = (JSBlock)visit(ctx.arguments());
	  List <JSAst> mas = ARGS(args.getMembers());
	  System.err.print("id = "+ ctx.id().getText()+ " ,mas = "+ ctx.arguments().getText());
	  return ARITH(id,mas);
	  }
	  return id;
   }
   
   
      @Override
		public JSAst visitOperTDArithSingle(EightBitParser.OperTDArithSingleContext ctx){
	   JSAst oper = ( ctx.oper.getType() == EightBitParser.MUL ) ? MUL : DIV;
	   JSAst right = visit(ctx.arithSingle());
	   return OPERATION(oper, null, right);
   }
   
   
   @Override
   public JSAst visitArithConstantSingle(EightBitParser.ArithConstantSingleContext ctx){
		   JSAst num = visit(ctx.constant());
		   return num;
	   
   }
   
   
   @Override
   public JSAst visitExprString(EightBitParser.ExprStringContext ctx){
	   return OPER(ctx.getText());
   }
   @Override
   public JSAst visitExprNum(EightBitParser.ExprNumContext ctx){
      return NUM(Double.valueOf(ctx.NUMBER().getText()));
   }
   @Override
   public JSAst visitExprTrue(EightBitParser.ExprTrueContext ctx){
      return TRUE;
   }
   @Override
   public JSAst visitExprFalse(EightBitParser.ExprFalseContext ctx){
      return FALSE;
   }
   @Override
   public JSAst visitArguments(EightBitParser.ArgumentsContext ctx){
	   EightBitParser.ArgsContext args = ctx.args();
	   return (args == null ) ? BLOCK()
	                            : visit(args);
   }
   @Override
   public JSAst visitArgs(EightBitParser.ArgsContext ctx){
	   return BLOCK(ctx.expr().stream()
						     .map( c -> visit(c))
						     .collect(Collectors.toList()));
   }
   
   @Override
   public JSAst visitIfStatement(EightBitParser.IfStatementContext ctx){
		JSAst expr = visit(ctx.expr());
		if(ctx.closedStatement().size() > 1){
		return IF(expr,visit(ctx.closedStatement().get(0)),visit(ctx.closedStatement().get(1)));
		}
		return IF(expr,visit(ctx.closedStatement().get(0)),null);
	   
	   
   }
   
   @Override
   public JSAst visitLetStatement(EightBitParser.LetStatementContext ctx){
	   EightBitParser.AssignStmtListContext assC = ctx.assignStmtList();
	   JSAst assT = null;
	   if (assC == null ) {
		   assT = BLOCK();
		}
	   else {
		   assT = visit(assC);
		}
		return LET(assT,visit(ctx.closedStatement()));
   }
   
   
   @Override
   public JSAst visitAssignStmtList(EightBitParser.AssignStmtListContext ctx){
	   return  BLOCK(ctx.assignStatement().stream()
	                                      .map( c -> visit(c))
						                  .collect(Collectors.toList()));
	   
   }
   
   @Override
   public JSAst visitWhileStatement(EightBitParser.WhileStatementContext ctx){
		JSAst expr = visit(ctx.expr());
		JSAst block = visit(ctx.closedStatement());
		return WHILE(expr,block);
   }
   
   @Override
   public JSAst visitRelOperator(EightBitParser.RelOperatorContext ctx){
	   return  OPER(ctx.getText());
	   
   }
   
   @Override
   public JSAst visitRelMonom(EightBitParser.RelMonomContext ctx){
	   return  BLOCK(ctx.relOperation().stream()
	                                      .map( c -> visit(c))
						                  .collect(Collectors.toList()));
   }
   
   
   @Override
   public JSAst visitRelOperation(EightBitParser.RelOperationContext ctx){
	   return  REL(visit(ctx.arithOperation()),BLOCK(ctx.relMas().stream()
	                                      .map( c -> visit(c))
						                  .collect(Collectors.toList())));
   }
   
   @Override
   public JSAst visitRelMas(EightBitParser.RelMasContext ctx){
	   return REL(visit(ctx.relOperator()),visit(ctx.arithOperation()));
   }
   
}
  
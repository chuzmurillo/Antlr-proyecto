package eightBit.js;
import java.io.*;
public class JSOperation implements  JSAst{
  private JSAst oper;
  private JSAst left, right;
  public JSAst getOper(){return this.oper;}
  public JSAst getLeft(){return this.left;}
  public JSAst getRight(){return this.right;}
  public JSOperation(JSAst oper, JSAst left, JSAst right){
    this.oper = oper;
    this.left = left;
    this.right = right;
  }
  public void genCode(PrintStream out){

    left.genCode(out);
    if(oper.getText().equals("+"))
      out.println("ADD A,B");
    if(oper.getText().equals("-"))
      out.println("SUB A,B");
    if(oper.getText().equals("*"))
      out.println("MUL A,B");
    if(oper.getText().equals("/"))
      out.println("DIV A,B");
    right.genCode(out);
  }

  public void genData(PrintStream out){
    left.genData(out);
    oper.genData(out);
    right.genData(out);
  }
}

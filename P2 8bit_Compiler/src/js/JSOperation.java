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

		//left.genCode(out);
		//right.genCode(out);
      out.print("	POP B");
      out.print("\n	POP A");
    if(oper.getText().equals("+"))
      out.println("\n	ADD A,B");
    if(oper.getText().equals("-"))
      out.println("	SUB A,B");
    if(oper.getText().equals("*"))
      out.println("	MUL B");
    if(oper.getText().equals("/"))
      out.println("	DIV B");
	  out.print("	PUSH A\n");
  }

  public void genData(PrintStream out){
	left.genData(out);
    out.print(": DB 0 \n	");
    right.genData(out);
    out.print(": DB 0 \n");
    //oper.genData(out);
  }
}

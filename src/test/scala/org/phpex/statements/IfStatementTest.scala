package org.phpex.statements

import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Variable
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.concrete.StringValue
import org.phpex.values.symbolic.Choice
import org.scalatest.FlatSpec
import org.phpex.expressions.bool.And

object IfStatementTest extends App {
  
  def ifStatement1(b:Boolean): Statement = {
    IfStatement(BooleanConstant(b),
        BlockStatement(EchoStatement("A")), 
        BlockStatement(EchoStatement("B")))
  }
  
  def ifStatement2(s:String): Statement = {
    IfStatement(Variable(s),
        BlockStatement(AssignStatement("a", IntegerConstant(1))), 
        BlockStatement(AssignStatement("a", IntegerConstant(0))))
  }
  
  println(IfStatementTest.ifStatement2("x").symbolicExecute(SimpleEnvironment()))

}

class IfStatementTest extends FlatSpec {
  
  "IfStatement simply executed" should "either print \"A\" or \"B\"" in {
    assert(IfStatementTest.ifStatement1(true).execute(SimpleEnvironment()).getOutput().top.equals(StringValue("A")))
    assert(IfStatementTest.ifStatement1(false).execute(SimpleEnvironment()).getOutput().top.equals(StringValue("B")))   
  }
   
  /*it should "either assign 1 or 0 to $a" in {
    assert(IfStatementTest.ifStatement2("b").execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(1)))
    assert(IfStatementTest.ifStatement2("b").execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(0)))
  }*/
  
  "IfStatement symbolically executed" should "both print \"A\" or \"B\"" in {
    assert(IfStatementTest.ifStatement1(true).symbolicExecute(SimpleEnvironment()).getOutput().top.equals(Choice(And(BooleanConstant(true), BooleanConstant(true)), StringValue("A"), StringValue("B"))) )
  }

  it should "assign a CHOICE(b, 1, 0) to $a" in {
    assert(IfStatementTest.ifStatement2("b").symbolicExecute(SimpleEnvironment()).lookup("a").equals(Choice( And(BooleanConstant(true), Variable("b")), IntegerValue(1), IntegerValue(0))))
  }
}
import org.phpex.environments.SimpleEnvironment
import scala.collection.immutable.Stack
import org.phpex.values.Value
import org.phpex.environments.Environment
import org.scalatest.FunSuite
import org.phpex.examples.Statements
import org.phpex.values.concrete.IntegerValue
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.bool.LessThan
import org.phpex.values.symbolic.Choice
import org.phpex.expressions.Variable
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.values.concrete.StringValue


object SymbolicExecutionTest {
  def getEnvironment(): Environment = new SimpleEnvironment(Map[String, Value](), new Stack[Value]());
}

class SymbolicExecutionTest extends FunSuite {
  
  test("IfStatement with assignments") {
    val env = ExecutionTest.getEnvironment()
    assert(Choice(BooleanConstant(true), IntegerValue(42), IntegerValue(43)) == Statements.ifStatement1().symbolicExecute(env).lookup("a"))
  }
  
  test("IfStatement with echo statements") {
    val env = ExecutionTest.getEnvironment()
    assert(Stack(StringValue("A"), Choice(Variable("b"), StringValue("B"), StringValue("CD"))) == Statements.printStatement2().symbolicExecute(env).getOutput())
  }
}
import org.scalatest.FunSuite
import org.phpex.environments.SimpleEnvironment
import org.phpex.examples.Statements
import org.phpex.values.Value
import scala.collection.immutable.Stack
import org.phpex.environments.Environment

object ExecutionTest {
  def getEnvironment(): Environment = new SimpleEnvironment(Map[String, Value](), new Stack[Value]());
}

/**
 * Tests for simple execution
 */
class ExecutionTest extends FunSuite {

  test("AssignmentStatement") {
    val env = ExecutionTest.getEnvironment()
    assert(42 == Statements.assignStatement1().execute(env).lookup("a").intValue);
  }

  test("WhileStatement") {
    val env = ExecutionTest.getEnvironment()
    assert(10 == Statements.whileStatement1().execute(env).lookup("i").intValue)
  }

  test("IfStatement") {
    val env = ExecutionTest.getEnvironment()
    assert(42 == Statements.ifStatement1().execute(env).lookup("a").intValue)
    assert(43 == Statements.ifStatement2().execute(env).lookup("a").intValue)
  }

  test("EchoStatement") {
    val env = new SimpleEnvironment(Map[String, Value](), new Stack[Value]())
    assert("ABC".equals(Statements.printStatement1().execute(env).getOutput().reverse.foldLeft("")((a, b) => a + b)))  
  }

}


package org.phpex.statements

import scala.collection.immutable.Stack

import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Expression
import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import org.phpex.values.symbolic.Choice
import org.phpex.values.concrete.BooleanValue
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.expressions.bool.Not

case class IfStatement(e: Expression, s1: BlockStatement, s2: BlockStatement) extends Statement {

  def execute(env: Environment): Environment = {
    if (e.evaluate(env).asInstanceOf[BooleanValue].value) {
      return s1.execute(env)
    } else {
      return s2.execute(env)
    }
  }

  def symbolicExecute(env: Environment): Environment = {

    /*
     * TODO Integrate path condition & path condition checks
     * + compute pi_ = whenEquals(e.evaluate(..), TRUE)
     * + compute isSatisfiable(pi' && pass)
     */
    joinStates(env, s1.execute(env), s2.execute(env))
  }

  def joinStates(env: Environment, env1: Environment, env2: Environment): Environment = {
    
    var joinedMap = joinStateMaps(env1.addPathConstraint(e), env2.addPathConstraint(Not(e)))
    var joinedOutput = joinStacks(env1.addPathConstraint(e), env2.addPathConstraint(Not(e)))
    
    // Take old path condition since this is a join
    return SimpleEnvironment(joinedMap, joinedOutput, env.getCalls(), env.getPathCondition())
  }

  /**
   * Merges output stacks of two program states.
   */
  private def joinStacks(env1:Environment, env2:Environment): Stack[Value] = {
    
    val s1 = env1.getOutput()
    val s2 = env2.getOutput()
    
    val prefix = pref(s1.reverse, s2.reverse)
    val output1 = s1.reverse.slice(prefix.size, s1.size)
    val output2 = s2.reverse.slice(prefix.size, s2.size)

    if (compareStacks(output1, output2)) {
      return prefix ++ output1
    } else {
      var stv1 = StringValue(output1.foldLeft("")((a, b) => a + b))
      var stv2 = StringValue(output2.foldLeft("")((a, b) => a + b))
      return prefix.push(Choice( env1.getPathCondition() , stv1, stv2)).reverse
    }
  }

  /**
   * Finds the longest common prefix/sequence of elements on two given stacks.
   */
  private def pref(s: Stack[Value], t: Stack[Value], out: Stack[Value] = Stack[Value]()): Stack[Value] = {
    if (s.isEmpty || t.isEmpty || !s(0).equals(t(0))) {
      return out
    } else {
      pref(s.slice(1, s.size), t.slice(1, t.size), out.push(s(0)))
    }
  }

  /**
   * Merges two program states variable store. Variables
   * will refer to symbolic values if their definition is ambiguous or
   * they're only defined once.
   */
  private def joinStateMaps(env1:Environment, env2:Environment): Map[String, Value] = {
    val m1 = env1.getMap()
    val m2 = env2.getMap()
    /**
     * Map for joined program states
     *  - get all keys that are in m1 but not in m2
     *  - get all keys that are in m2 but not in m1
     *  - get all keys that are in both m1 and m2
     */
    var smap = Map[String, Value]()

    (m1.keySet -- m2.keySet).foreach {
      key => smap += key -> Choice( env1.getPathCondition(), m1.get(key).get, null )
    }

    (m2.keySet -- m1.keySet).foreach {
      key => smap += key -> Choice( env2.getPathCondition() , m2.get(key).get, null)
    }

    (m1.keySet intersect m2.keySet).foreach { key =>
      if (!m1.get(key).get.toString().equals(m2.get(key).get.toString())) {
        smap += key -> Choice( env1.getPathCondition() , m1.get(key).get, m2.get(key).get)
      } else {
        smap += key -> m1.get(key).get
      }
    }
    return smap
  }

  /**
   * Recursively compares two stacks of Value objects. Two stacks are
   * equal if their size is equal, their top element is equal and the
   * stack.pop stack are equal (-> recursive definition).
   */
  private def compareStacks(s1: Stack[Value], s2: Stack[Value]): Boolean = {
    var a = s1
    var b = s2
    if (a.isEmpty != b.isEmpty) {
      return false
    }
    if (a.isEmpty && b.isEmpty) {
      return true
    }
    val element_a = a.top
    a = a.pop
    val element_b = b.top
    b = b.pop
    try {
      if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
        return false
      return compareStacks(a, b)
    } finally {
      a = a.push(element_a)
      b = b.push(element_b)
    }
  }

  override def toString(): String = {
    "if (" + e + ") {" + s1 + "} else { " + s2 + "}"
  }

}
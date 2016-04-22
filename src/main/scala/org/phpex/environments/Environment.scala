package org.phpex.environments

import scala.collection.immutable.Map
import scala.collection.immutable.Stack

import org.phpex.expressions.Expression
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.values.Value

trait Environment {
  def update(name:String, value:Value): Environment
  def lookup(name:String): Value
  def addOutput(s:String): Environment
  
  def getMap(): Map[String, Value]
  def getOutput(): Stack[Value]
  def getPathCondition(): Expression
  def addPathConstraint(s: Expression): Environment
  def getCalls(): Stack[String]
}


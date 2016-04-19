package org.phpex.environments

import org.phpex.values.Value
import scala.collection.immutable.Map
import scala.collection.immutable.Stack

trait Environment {
  def update(name:String, value:Value): Environment
  def lookup(name:String): Value
  def addOutput(s:String): Environment
  
  def getMap(): Map[String, Value]
  def getOutput(): Stack[Value] // Not only Strings, since symbolic outputs may occur
}
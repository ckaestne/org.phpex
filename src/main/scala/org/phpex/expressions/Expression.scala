package org.phpex.expressions

import org.phpex.values.Value
import org.phpex.environments.Environment

trait Expression {
  def evaluate(env:Environment): Value
}
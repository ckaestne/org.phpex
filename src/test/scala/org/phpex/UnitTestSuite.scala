package org.phpex

import org.scalatest.Suites
import org.phpex.expressions.ExpressionTest
import org.phpex.statements.StatementTestSuite

class UnitTestSuite extends Suites(
    new ExpressionTest,
    new StatementTestSuite    
)
package org.phpex.statements

import org.scalatest.Suites

class StatementTestSuite extends Suites (
    new AssignStatementTest,
    new IfStatementTest
)
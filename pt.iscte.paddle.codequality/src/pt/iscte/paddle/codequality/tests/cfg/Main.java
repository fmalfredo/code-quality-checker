package pt.iscte.paddle.codequality.tests.cfg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestMax.class,
	TestArrayFind.class,
//	TestBinarySearch.class, // These is an issue in the Statements, i guess.
//	TestSummation.class,
	TestMax.class,
//	TestArrayCount.class,
	TestDeadNodes.class,
})

public class Main {
}
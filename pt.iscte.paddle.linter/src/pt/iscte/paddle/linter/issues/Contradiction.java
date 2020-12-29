package pt.iscte.paddle.linter.issues;

import pt.iscte.paddle.linter.cases.base.SingleOcurrenceIssue;
import pt.iscte.paddle.linter.misc.Classification;
import pt.iscte.paddle.linter.misc.IssueType;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;

public class Contradiction extends SingleOcurrenceIssue {

	public Contradiction(String explanation, IProcedure procedure, IProgramElement element) {
		super(IssueType.CONTRADICTION, Classification.SERIOUS, procedure, element);
	}
}

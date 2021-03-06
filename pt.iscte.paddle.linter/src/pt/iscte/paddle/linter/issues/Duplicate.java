package pt.iscte.paddle.linter.issues;

import java.util.List;
import pt.iscte.paddle.linter.cases.base.MultipleOccurrencesIssue;
import pt.iscte.paddle.linter.misc.Classification;
import pt.iscte.paddle.linter.misc.IssueType;
import pt.iscte.paddle.model.IProgramElement;

public class Duplicate extends MultipleOccurrencesIssue {

	public Duplicate(List<IProgramElement> occurrences) {
		super(IssueType.DUPLICATE_CODE, Classification.SERIOUS, occurrences);
	}

	public List<IProgramElement> getDuplicates() {
		return occurrences;
	}

}

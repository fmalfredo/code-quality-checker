package pt.iscte.paddle.quality.cases.base;

import java.util.ArrayList;
import java.util.List;

public abstract class CodeAnalyser {

	protected ArrayList<QualityIssue> issues = new ArrayList<QualityIssue>();
	
	public List<QualityIssue> getQualityIssues(){
		return issues;
	}
	
}

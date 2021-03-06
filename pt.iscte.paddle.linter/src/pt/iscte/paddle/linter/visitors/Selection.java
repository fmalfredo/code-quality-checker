package pt.iscte.paddle.linter.visitors;

import pt.iscte.paddle.linter.cases.base.CodeAnalyser;
import pt.iscte.paddle.linter.cases.base.QualityIssue;
import pt.iscte.paddle.linter.issues.BooleanCheck;
import pt.iscte.paddle.linter.issues.EmptySelection;
import pt.iscte.paddle.linter.issues.SelectionMisconception;
import pt.iscte.paddle.linter.linter.Linter;
import pt.iscte.paddle.linter.misc.Explanations;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.IOperator.OperationType;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;

public class Selection extends CodeAnalyser implements IVisitor {

	@Override
	public boolean visit(IBinaryExpression exp) {
		if(exp.getOperationType().equals(OperationType.RELATIONAL) 
				&& (exp.getOperator().equals(IBinaryOperator.EQUAL) || exp.getOperator().equals(IBinaryOperator.DIFFERENT))
				&& exp.getLeftOperand().getType().isBoolean() 
				&& exp.getRightOperand().getType().isBoolean()
				&& ((exp.getRightOperand().isSame(IType.BOOLEAN.literal(false)) || exp.getRightOperand().isSame(IType.BOOLEAN.literal(true)))
						||  (exp.getLeftOperand().isSame(IType.BOOLEAN.literal(false)) || exp.getLeftOperand().isSame(IType.BOOLEAN.literal(true))))) {
			issues.add(new BooleanCheck(Explanations.FAULTY_BOOLEAN_CHECK, exp));
		}

		return true;
	}

	@Override
	public boolean visit(ISelection selection) {
		if(selection.isEmpty()) {
			issues.add(new EmptySelection(Explanations.EMPTY_SELECTION, selection));

			if(selection.hasAlternativeBlock() && !selection.getAlternativeBlock().isEmpty()) {
				issues.add(new SelectionMisconception(Explanations.SELECTION_MISCONCEPTION, selection));
			}
		}
		return true;
	}

	@Override
	public boolean visitAlternative(ISelection selection) {
		if(selection.getAlternativeBlock().isEmpty()) {
			issues.add(new EmptySelection(Explanations.EMPTY_SELECTION, selection.getAlternativeBlock()));
		}

		return true;
	}
}

package pt.iscte.paddle.codequality.cases;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

import pt.iscte.paddle.codequality.misc.Category;
import pt.iscte.paddle.codequality.misc.Explanations;
import pt.iscte.paddle.javardise.service.IClassWidget;
import pt.iscte.paddle.javardise.service.ICodeDecoration;
import pt.iscte.paddle.javardise.service.IJavardiseService;
import pt.iscte.paddle.javardise.service.IWidget;
import pt.iscte.paddle.javardise.util.HyperlinkedText;
import pt.iscte.paddle.model.IProgramElement;

public class DuplicateGuard extends BadCodeCase{
	
	List<IProgramElement> guards;

	public DuplicateGuard(List<IProgramElement> guards) {
		super(Category.DUPLICATE_SELECTION_GUARD, Explanations.DUPLICATE_SELECTION_GUARD);
		this.guards = guards;
	}
	
	@Override
	public void generateComponent(IClassWidget widget, Composite comp, int style) {
		generateMark(widget, comp, style, guards);
		generateExplanation(widget, comp, style);
	}
	
	@Override
	protected void generateExplanation(IClassWidget widget, Composite comp, int style) {
		IWidget w = IJavardiseService.getWidget(guards.get(0));
		if(w != null) {
			Link link = new HyperlinkedText(null)
					.words("The condition ").link(guards.get(1).toString(), () -> {
						ICodeDecoration<Text> d2 = w.addNote("Does this need \n to be duplicated?", ICodeDecoration.Location.RIGHT);
						d2.show();
						getDecorations().add(d2);
					})
					.words(" was found duplicated inside the code block.")
					.words("\n\n - The variables used in the condition don't change values.")
					.create(comp, SWT.WRAP | SWT.V_SCROLL);

			link.requestLayout();
		}
	}

}

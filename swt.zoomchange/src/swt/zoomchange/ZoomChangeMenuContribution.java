package swt.zoomchange;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

public class ZoomChangeMenuContribution extends CompoundContributionItem {

	@Override
	protected IContributionItem[] getContributionItems() {
		ArrayList<IContributionItem> menuList = new ArrayList<>();
		menuList.add(createItem(100));
		menuList.add(createItem(125));
		menuList.add(createItem(150));
		menuList.add(createItem(175));
		menuList.add(createItem(200));
		return menuList.toArray(new IContributionItem[menuList.size()]);
	}

	@SuppressWarnings("unchecked")
	private IContributionItem createItem(Integer zoom) {
		CommandContributionItemParameter p = new CommandContributionItemParameter(PlatformUI.getWorkbench(), null,
				ZoomChangeHandler.ID, CommandContributionItem.STYLE_PUSH);
		p.parameters = new HashMap<>();
		p.parameters.put(ZoomChangeHandler.PARM_EDITOR, zoom.toString());
		p.style = CommandContributionItem.STYLE_RADIO;
		p.label = "Zoom to " + zoom; // $NON-NLS-1$
		return new CommandContributionItem(p);
	}
}
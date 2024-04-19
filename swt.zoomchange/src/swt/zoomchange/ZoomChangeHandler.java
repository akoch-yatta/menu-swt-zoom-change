package swt.zoomchange;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.DPIUtil;
import org.eclipse.swt.internal.DPIZoomChangeRegistry;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class ZoomChangeHandler extends AbstractHandler {
	public static final String ID = "swt.zoomchange.change"; //$NON-NLS-1$
	public static final String PARM_EDITOR = "swt.zoomchange.change.zoom"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		try {			
			Object zoom = event.getObjectParameterForExecution(PARM_EDITOR);
			int newSWTZoom = ((Integer) zoom).intValue();
			Shell shell = window.getShell();
			float scalingFactor = 1f * newSWTZoom/shell.nativeZoom;

			DPIUtil.setDeviceZoom(newSWTZoom);
			DPIZoomChangeRegistry.applyChange(window.getShell(), newSWTZoom, scalingFactor);
		} catch (Exception e) {
			throw new ExecutionException("Failed to activate editor", e); //$NON-NLS-1$
		}
		return null;
	}
}
package org.goko.core.gcode.rs274ngcv3.jogl.internal;

import org.goko.core.gcode.rs274ngcv3.IRS274NGCService;
import org.goko.core.workspace.service.IWorkspaceService;
import org.goko.tools.viewer.jogl.service.IJoglViewerService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private static Activator instance;
	private IRS274NGCService rs274ngcService;
	private IWorkspaceService workspaceService;
	private IJoglViewerService joglViewerService;
	
	public Activator() {
		instance = this;
	}
	
	/** (inheritDoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		ServiceReference<IRS274NGCService> rs274Servicereference = context.getServiceReference(IRS274NGCService.class);
		rs274ngcService = context.getService(rs274Servicereference);
		
	}

	/** (inheritDoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public static IRS274NGCService getRS274NGCService(){
		return getInstance().rs274ngcService;
	}

	public static IWorkspaceService getWorkspaceService(){
		return getInstance().workspaceService;
	}
	
	public static IJoglViewerService getJoglViewerService(){
		return getInstance().joglViewerService;
	}

	/**
	 * @return the instance of this activator
	 */
	public static Activator getInstance() {
		return instance;
	}
}

package org.goko.core.workspace.handlers;

import java.io.File;
import java.net.URI;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.goko.core.common.exception.GkException;
import org.goko.core.workspace.element.GkProject;
import org.goko.core.workspace.io.IProjectLocation;
import org.goko.core.workspace.service.IWorkspaceService;

/**
 * Handler for project save
 * @author Psyko
 */
public class SaveProjectHandler {

	@CanExecute
	public boolean canExecute(IWorkspaceService workspaceService) throws GkException{
		GkProject project = workspaceService.getProject();
		return project != null && project.isDirty();
	}

	@Execute
	public boolean saveProject(Shell shell, IWorkspaceService workspaceService) throws GkException{
		boolean saveDone = false;
		GkProject project = workspaceService.getProject();
		IProjectLocation projectLocation = project.getLocation();
		
		if(!projectLocation.isLocationDefined()){
			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setText("Save Goko project...");
			dialog.setFilterNames(new String[]{"Goko projects (*.goko) "});
			dialog.setFilterExtensions(new String[]{"*.goko"});
			String targetFile = dialog.open();
			
			if(StringUtils.isNotEmpty(targetFile)){
				String fileName 	= FilenameUtils.getBaseName(targetFile);
				String projectName  = FilenameUtils.removeExtension(fileName);
				String path 		= FilenameUtils.getPathNoEndSeparator(targetFile);
				projectLocation.setName( projectName );
				
				URI projectUri = null;
				if(StringUtils.endsWith(path, projectName)){
					projectUri = new File(targetFile).getParentFile().toURI();
				}else{
					projectUri = URIUtil.append(new File(targetFile).getParentFile().toURI(), projectName);
				}
				new File(projectUri).mkdirs();
				projectLocation.setLocation(projectUri);
			}
		}
		
		if(projectLocation.isLocationDefined()){
			workspaceService.saveProject(projectLocation, new NullProgressMonitor());
			saveDone = true;
		}
		return saveDone;
	}
}

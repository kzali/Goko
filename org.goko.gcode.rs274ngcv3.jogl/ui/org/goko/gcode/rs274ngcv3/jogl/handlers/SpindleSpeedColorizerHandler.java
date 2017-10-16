/**
 * 
 */
package org.goko.gcode.rs274ngcv3.jogl.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.goko.core.common.exception.GkException;
import org.goko.core.gcode.rs274ngcv3.jogl.RS274NGCV3JoglService;
import org.goko.core.gcode.rs274ngcv3.jogl.renderer.colorizer.SpindleSpeedColorizer;

/**
 * @author Psyko
 * @date 19 juil. 2017
 */
public class SpindleSpeedColorizerHandler {

	@CanExecute
	public boolean canExecute(){
		return true;
	}

	@Execute
	public void execute(RS274NGCV3JoglService service){
		try {
			service.changeColorizer(new SpindleSpeedColorizer());
		} catch (GkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	- gerer les exceptions dans les colorizer handlers
//	- ajouter le by feedrate
//	- ajouter la legende
}

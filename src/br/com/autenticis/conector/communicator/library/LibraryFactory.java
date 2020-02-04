package br.com.autenticis.conector.communicator.library;

import br.com.autenticis.conector.util.OSValidator;
import br.com.autenticis.conector.util.StringPath;

public class LibraryFactory {

	public static ICLibrary getLibraryFactory() {
		
		if (OSValidator.isUnix()) {
			return ILinuxCLibrary.ICOMConector;
		} else if (OSValidator.isWindows()) {
			return IWindowsCLibrary.ICOMConector;
		}
		
		return null;
	}
	
	public static String getIniICOMFactory() {
		
		if (OSValidator.isUnix()) {
			return StringPath.PATH_LINUX;
		} else if (OSValidator.isWindows()) {
			return StringPath.PATH_WINDOWS;
		}
		
		return null;
	}
	
}

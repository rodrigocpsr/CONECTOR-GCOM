package br.com.autenticis.conector.communicator.library;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ILinuxCLibrary extends Library,ICLibrary{
	
	ILinuxCLibrary ICOMConector = (ILinuxCLibrary)
        
	Native.loadLibrary(("C:\\ICOM\\icom-64bits\\ConectorT.dll"), ILinuxCLibrary.class);

}

package br.com.autenticis.conector.communicator.library;

import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface IWindowsCLibrary extends StdCallLibrary,ICLibrary{
	
    HashMap<?, ?> methodMapper = new HashMap<Object, Object>(1,1)
    {
    	private static final long serialVersionUID = 1L;

	    { put(OPTION_FUNCTION_MAPPER, FUNCTION_MAPPER); }
	};

	IWindowsCLibrary ICOMConector = (IWindowsCLibrary)
    
	Native.loadLibrary(("C:\\ICOM\\icom-64bits\\ConectorT.dll"), IWindowsCLibrary.class, methodMapper);	

}

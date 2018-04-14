package pos.tools;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface JNIWindowFactory extends Library{
	JNIWindowFactory instanceDll  = (JNIWindowFactory)Native.loadLibrary(JNIWindowFactory.class.getResource("JNIWindowUtilProxy.dll").getPath(),JNIWindowFactory.class);
}

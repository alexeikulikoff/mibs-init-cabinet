package mibs.init.cabinet;

import java.io.InputStream;
import java.io.OutputStream;

public interface Compressor {
	  void compress (InputStream source, OutputStream target);
}

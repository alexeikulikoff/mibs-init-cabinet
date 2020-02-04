package mibs.init.cabinet;

import java.io.InputStream;
import java.io.OutputStream;

public interface Decompressor {
	 void decompress (InputStream source, OutputStream target);
}

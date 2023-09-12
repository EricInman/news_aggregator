package code.datasources;

import java.io.IOException;
import java.io.InputStream;

public interface DataSource {
  /**
   * Opens the provided source, either a file or a url.
   *
   * @return an InputStream connected to the source.
   * @throws IOException if file is not found, or the urls doesn't exist.
   */
  InputStream openSource() throws IOException;
}

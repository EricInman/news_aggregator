package code.datasources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FileSource implements DataSource {
  private final String name;

  public FileSource(String name) {
    this.name = name;
  }

  public InputStream openSource() throws IOException {
    return new FileInputStream(this.name);
  }

  /**
   * Determines if a response object has the same articles.
   *
   * @param other the comparison response object.
   * @return True if all the articles are the same, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof FileSource)) {
      return false;
    }

    FileSource obj = (FileSource) other;

    return this.name.equals(obj.name);
  }

  /**
   * Overrides the hashCode method.
   *
   * @return a new hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}

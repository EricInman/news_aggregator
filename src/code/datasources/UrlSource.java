package code.datasources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class UrlSource implements DataSource {
  private final String url;

  public UrlSource(String url) {
    this.url = url;
  }

  public InputStream openSource() throws IOException {
    return new URL(this.url).openStream();
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

    if (!(other instanceof UrlSource)) {
      return false;
    }

    UrlSource obj = (UrlSource) other;

    return this.url.equals(obj.url);
  }

  /**
   * Overrides the hashCode method.
   *
   * @return a new hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(url);
  }
}

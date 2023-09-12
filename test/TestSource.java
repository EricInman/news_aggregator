import code.datasources.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestSource implements DataSource {
  private String contents;

  public TestSource(String source) {
    this.contents = source;
  }

  public InputStream openSource() throws IOException {
    return new ByteArrayInputStream(contents.getBytes());
  }

  public void newContents(String newContents) {
    this.contents = newContents;
  }
}

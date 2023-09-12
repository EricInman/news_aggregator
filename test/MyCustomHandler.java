import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyCustomHandler extends Handler {

  private final List<String> logs = new ArrayList<>();

  @Override
  public void publish(LogRecord record) {
    StringBuilder sb = new StringBuilder();
    sb.append(record.getSourceClassName())
      .append("#")
      .append(record.getSourceMethodName())
      .append(" - ")
        .append(record.getMessage());
    logs.add(sb.toString());
  }

  @Override
  public void flush() {
    // just need this to conform to Handler interface
  }

  @Override
  public void close() throws SecurityException {
    // just need this to conform to Handler interface
  }

  public List<String> getLogs() {
    return Collections.unmodifiableList(logs);
  }
}

package code.misc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

  private final DateTimeFormatter[] formatter = new DateTimeFormatter[] {
    DateTimeFormatter.ISO_OFFSET_DATE_TIME,
    DateTimeFormatter.ofPattern("y-M-d H:m:s[.SSSSSS]")
  };

  /**
   * Create a deserializer for the LocalDateTime from a "simple" format.
   */
  public LocalDateTimeDeserializer() {
    super(LocalDateTime.class);
  }

  @Override
  public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException {

    String date = jsonparser.getText();
    LocalDateTime time = null;

    for (DateTimeFormatter format : formatter) {
      try {
        time = LocalDateTime.parse(date, format);
      } catch (Exception e) {
        // do nothing to try and use the other formats
      }
    }
    return time;
  }
}

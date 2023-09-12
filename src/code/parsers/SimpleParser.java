package code.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import code.apiobjects.Article;
import code.apiobjects.NewsResponseObject;
import code.misc.LocalDateTimeDeserializer;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleParser implements Parser {

  @Override
  public NewsResponseObject createResponse(InputStream stream, Logger logger) {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    mapper.registerModule(module);
    List<Article> articles = new ArrayList<>();
    StringBuilder jsonString = new StringBuilder();


    try {
      JsonNode root;
      int data = stream.read();

      while (data != -1) {
        char c = (char) data;
        jsonString.append(c);
        data = stream.read();
      }

      root = mapper.readTree(jsonString.toString());
      articles.add(mapper.convertValue(root, Article.class));
    } catch (Exception e) {
      logger.log(Level.WARNING, "Error when reading in the JSON to a JSON Node", e);
    }

    return new NewsResponseObject("Ok", 1, articles);
  }
}

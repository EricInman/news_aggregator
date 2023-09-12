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

public class ArticleParser implements Parser {
  /**
   * Creates a NewsResponseObject from a JSON file.
   *
   * @param stream The an input stream to be parsed.
   * @param logger The logger that holds warning messages.
   * @return returns a NewsResponseObject.
   */
  public NewsResponseObject createResponse(InputStream stream, Logger logger) {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    mapper.registerModule(module);
    JsonNode root = null;
    List<Article> articles;
    StringBuilder jsonString = new StringBuilder();

    try {
      int data = stream.read();

      while (data != -1) {
        char c = (char) data;
        jsonString.append(c);
        data = stream.read();
      }

      root = mapper.readTree(jsonString.toString());
    } catch (Exception e) {
      logger.log(Level.WARNING, "Error when reading in the JSON to a JSON Node", e);
    }

    if (root == null) {
      return null;
    }

    String status = root.get("status").asText();
    int totalResults = root.get("totalResults").asInt();
    articles = parseArticles(mapper, root, logger);

    return new NewsResponseObject(status, totalResults, articles);
  }

  /**
   * Parses the list of articles and creates Article objects.
   *
   * @param mapper The object mapper needed to convert JsonNodes to Articles.
   * @param root The root Json node.
   * @param logger The logger to record any problems.
   * @return a list of Articles.
   */
  private List<Article> parseArticles(ObjectMapper mapper, JsonNode root, Logger logger) {
    List<Article> articles = new ArrayList<>();

    for (JsonNode node : root.get("articles")) {
      try {
        Article a = mapper.convertValue(node, Article.class);

        if (checkNecessaryFields(a)) {
          throw new NullPointerException();
        } else {
          articles.add(a);
        }
      } catch (Exception e) {
        logger.log(Level.WARNING, "Article is missing one of the necessary fields.", e);
      }
    }

    return articles;
  }

  private boolean checkNecessaryFields(Article a) {
    String blank = "BLANK";
    return a.getTitle() == null || a.getTitle().equals(blank)
      || a.getDescription() == null || a.getDescription().equals(blank)
      || a.getPublishedAt() == null || a.getPublishedAt().toString().equals(blank)
      || a.getUrl() == null || a.getUrl().equals(blank);
  }
}
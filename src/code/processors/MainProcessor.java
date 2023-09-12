package code.processors;

import code.apiobjects.NewsResponseObject;
import code.datasources.DataSource;
import code.parsers.Parser;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainProcessor implements Processor {

  private final DataSource source;
  private final Parser parser;
  private final Logger logger;

  /**
   * Creates a processor that takes a parser, a source (either a File or URL),
   * and a logger specified by the user for potential warnings.
   *
   * @param parser the parser that may perform actions on a list of articles, but
   *               will return the list of articles.
   * @param source the source of the articles, either a File or URL.
   * @param logger the logger to store warning or potential errors.
   */
  public MainProcessor(Parser parser, DataSource source, Logger logger) {
    this.parser = parser;
    this.source = source;
    this.logger = logger;
  }

  /**
   * Extracts the articles from the provided source.
   *
   * @return a NewsResponseObject with a list of articles.
   */
  public NewsResponseObject extractArticles() {
    NewsResponseObject obj = null;

    try {
      obj = this.parser.createResponse(this.source.openSource(), this.logger);
    } catch (Exception e) {
      this.logger.log(Level.WARNING, e.getMessage());
    }

    return obj;
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

    if (!(other instanceof MainProcessor)) {
      return false;
    }

    MainProcessor obj = (MainProcessor) other;

    return this.source.equals(obj.source)
        && this.parser.getClass().equals(obj.parser.getClass());
  }

  /**
   * Overrides the hashCode method.
   *
   * @return a new hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(logger);
  }
}

package code.parsers;

import code.apiobjects.NewsResponseObject;
import java.io.InputStream;
import java.util.logging.Logger;

public interface Parser {

  /**
   * Creates the NewsResponseObject that contains a list of articles.
   *
   * @param stream the stream to read data from.
   * @param logger the logger to log any problems or warnings.
   * @return a new NewsResponseObject
   */
  NewsResponseObject createResponse(InputStream stream, Logger logger);
}

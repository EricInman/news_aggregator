package code.processors;

import code.apiobjects.NewsResponseObject;

public interface Processor {
  /**
   * Extracts all the articles from a given source.
   *
   * @return returns a NewsResponseObject containing a list of articles
   */
  NewsResponseObject extractArticles();
}

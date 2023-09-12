package code.processors;

import code.apiobjects.Article;
import code.apiobjects.NewsResponseObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CacheFilterDecorator implements Processor {
  private final Processor processor;
  private List<Article> articleCache;

  public CacheFilterDecorator(Processor processor) {
    this.processor = processor;
    this.articleCache = new ArrayList<>();
  }

  /**
   * Calls the base parser implementation of extract articles.
   * It also filters out recently seen articles based on the article cache.
   *
   * @return a NewsResponseObject with an array of new Articles.
   */
  public NewsResponseObject extractArticles() {
    NewsResponseObject obj = this.processor.extractArticles();

    List<Article> recentArticles = obj.getArticles();
    List<Article> notSeenArticles = recentArticles;

    for (Article article : articleCache) {
      notSeenArticles = notSeenArticles.stream()
                                       .filter(a -> !a.equals(article))
                                       .collect(Collectors.toList());
    }

    articleCache = recentArticles;
    return new NewsResponseObject("Ok", notSeenArticles.size(), notSeenArticles);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof CacheFilterDecorator)) {
      return false;
    }

    CacheFilterDecorator obj = (CacheFilterDecorator) other;

    return this.processor.equals(obj.processor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processor);
  }
}

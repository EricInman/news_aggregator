package code.processors;

import code.apiobjects.Article;
import code.apiobjects.NewsResponseObject;
import code.expressions.Expression;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilteringProcessor implements Processor {
  private final Processor processor;
  private final Expression filter;

  public FilteringProcessor(Processor proc, Expression filter) {
    this.processor = proc;
    this.filter = filter;
  }

  /**
   * Calls the base parser implementation of extract articles and applies a filter.
   *
   * @return a NewsResponseObject with a filtered array of articles.
   */
  public NewsResponseObject extractArticles() {
    NewsResponseObject obj = this.processor.extractArticles();

    List<Article> filteredArticles = obj.getArticles()
                                        .stream()
                                        .filter(filter::evaluate)
                                        .collect(Collectors.toList());

    return new NewsResponseObject("Ok", filteredArticles.size(), filteredArticles);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof FilteringProcessor)) {
      return false;
    }

    FilteringProcessor obj = (FilteringProcessor) other;

    return this.processor.equals(obj.processor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processor, filter);
  }
}

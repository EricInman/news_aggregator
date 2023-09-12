package code.apiobjects;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NewsResponseObject {
  private final String status;
  private final int totalResults;
  private final List<Article> articles;

  /**
   * Creates a represenation of the response gotten from NewsAPI site.
   *
   * @param status Determines if the request was successful or not.
   * @param totalResults The total number of results available from the request.
   * @param articles  The results of the request.
   */
  public NewsResponseObject(String status, int totalResults, List<Article> articles) {
    this.status = status;
    this.totalResults = totalResults;
    this.articles = articles;
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

    if (!(other instanceof NewsResponseObject)) {
      return false;
    }

    NewsResponseObject obj = (NewsResponseObject) other;

    if (this.articles.size() != obj.articles.size()) {
      return false;
    }

    for (var i = 0; i < this.articles.size(); i++) {
      if (!this.articles.get(i).equals(obj.articles.get(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Overrides the hashCode method.
   *
   * @return a new hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(status, totalResults);
  }

  /**
   * Gets the list of articles.
   *
   * @return the list of articles.
   */
  public List<Article> getArticles() {
    return Collections.unmodifiableList(this.articles);
  }
}

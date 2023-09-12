package code.expressions;

import code.apiobjects.Article;
import java.util.Objects;

public class KeywordExpression implements Expression {
  private final String keyword;

  public KeywordExpression(String keyword) {
    this.keyword = keyword;
  }

  /**
   * Determines if the key word appears anywhere in the article.
   *
   * @param article the article object to look through.
   * @return True if the article contains the keyword, false otherwise.
   */
  @Override
  public boolean evaluate(Article article) {
    String lowerKeyWord = keyword.toLowerCase();

    return article.getTitle().toLowerCase().contains(lowerKeyWord)
      || article.getDescription().toLowerCase().contains(lowerKeyWord)
      || article.getUrl().toLowerCase().contains(lowerKeyWord)
      || article.getPublishedAt().toString().toLowerCase().contains(lowerKeyWord);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof KeywordExpression)) {
      return false;
    }

    KeywordExpression obj = (KeywordExpression) other;

    return this.keyword.equals(obj.keyword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyword);
  }
}

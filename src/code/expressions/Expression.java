package code.expressions;

import code.apiobjects.Article;

public interface Expression {
  /**
   * Evaluates the result of the current expression.
   *
   * @param article the article to be evaluated.
   * @return True if a keyword is in an article, false otherwise.
   */
  boolean evaluate(Article article);
}

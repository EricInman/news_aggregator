package code.expressions;

import code.apiobjects.Article;
import java.util.Objects;

public class OrExpression implements Expression {
  private final Expression left;
  private final Expression right;

  public OrExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Evaluates the left and right expressions.
   *
   * @param article the article to be evaluated.
   * @return True if one of the expressions returns true, false otherwise.
   */
  @Override
  public boolean evaluate(Article article) {
    return left.evaluate(article) || right.evaluate(article);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof OrExpression)) {
      return false;
    }

    OrExpression obj = (OrExpression) other;

    return this.left.equals(obj.left)
      && this.right.equals(obj.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }
}

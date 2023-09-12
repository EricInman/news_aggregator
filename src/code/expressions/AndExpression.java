package code.expressions;

import code.apiobjects.Article;
import java.util.Objects;

public class AndExpression implements Expression {
  private final Expression left;
  private final Expression right;

  public AndExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Evaluates the left and right expressions.
   *
   * @param article the article to be evaluated.
   * @return True only if both expressions return true, false otherwise.
   */
  @Override
  public boolean evaluate(Article article) {
    return left.evaluate(article) && right.evaluate(article);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof AndExpression)) {
      return false;
    }

    AndExpression obj = (AndExpression) other;

    return this.left.equals(obj.left)
        && this.right.equals(obj.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }
}

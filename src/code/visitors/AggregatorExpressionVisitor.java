package code.visitors;

import code.expressions.AndExpression;
import code.expressions.Expression;
import code.expressions.KeywordExpression;
import code.expressions.OrExpression;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;

public class AggregatorExpressionVisitor
    extends AggregatorConfigParserBaseVisitor<Expression> {

  /**
   * Visit a parse tree produced by a file source type.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */

  @Override
  public Expression visitFilter(AggregatorConfigParser.FilterContext ctx) {
    if (ctx.selector() != null) {
      return ctx.selector().accept(this);
    }

    return null;
  }

  @Override
  public Expression visitNestedExpression(AggregatorConfigParser.NestedExpressionContext ctx) {
    return ctx.selector().accept(this);
  }

  @Override
  public Expression visitAndExpression(AggregatorConfigParser.AndExpressionContext ctx) {
    Expression left = ctx.lft.accept(this);
    Expression right = ctx.rht.accept(this);

    return new AndExpression(left, right);
  }

  @Override
  public Expression visitKeywordExpression(AggregatorConfigParser.KeywordExpressionContext ctx) {
    return new KeywordExpression(ctx.KEYWORD().toString().trim());
  }

  @Override
  public Expression visitOrExpression(AggregatorConfigParser.OrExpressionContext ctx) {
    Expression left = ctx.lft.accept(this);
    Expression right = ctx.rht.accept(this);

    return new OrExpression(left, right);
  }
}

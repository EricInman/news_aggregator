package code.visitors;

import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;

public class AggregatorDelayVisitor
    extends AggregatorConfigParserBaseVisitor<Integer> {

  /**
   * Visits a parse tree to obtain a delay number.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public Integer visitDelay(AggregatorConfigParser.DelayContext ctx) {
    return Integer.parseInt(ctx.NUM().toString());
  }
}

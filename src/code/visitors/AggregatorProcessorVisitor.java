package code.visitors;

import code.datasources.DataSource;
import code.expressions.Expression;
import code.parsers.Parser;
import code.processors.CacheFilterDecorator;
import code.processors.DataAggregator;
import code.processors.FilteringProcessor;
import code.processors.MainProcessor;
import code.processors.Processor;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;
import java.util.logging.Logger;

public class AggregatorProcessorVisitor
    extends AggregatorConfigParserBaseVisitor<DataAggregator> {

  private final Logger logger;

  public AggregatorProcessorVisitor(Logger logger) {
    this.logger = logger;
  }

  /**
   * Visit a parse tree produced by a file source type.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public DataAggregator visitFileSourceType(
      AggregatorConfigParser.FileSourceTypeContext ctx) {

    DataSource source = ctx.sourceLocation().accept(new AggregatorSourceVisitor());
    Parser parser = ctx.format().accept(new AggregatorParserVisitor());
    Expression filter = ctx.filter().accept(new AggregatorExpressionVisitor());

    Integer delay = null;
    if (ctx.delay() != null) {
      delay = ctx.delay().accept(new AggregatorDelayVisitor());
    }

    Processor processor = new MainProcessor(parser, source, this.logger);

    if (filter != null) {
      processor = new FilteringProcessor(processor, filter);
    }

    processor = new CacheFilterDecorator(processor);

    return new DataAggregator(processor, delay != null ? delay : 0);
  }

  /**
   * Visit a parse tree produced by a url source type.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public DataAggregator visitUrlSourceType(
      AggregatorConfigParser.UrlSourceTypeContext ctx) {

    DataSource source = ctx.sourceAddress().accept(new AggregatorSourceVisitor());
    Parser parser = ctx.format().accept(new AggregatorParserVisitor());
    Expression filter = ctx.filter().accept(new AggregatorExpressionVisitor());

    Integer delay = null;
    if (ctx.delay() != null) {
      delay = ctx.delay().accept(new AggregatorDelayVisitor());
    }

    Processor processor = new MainProcessor(parser, source, this.logger);

    if (filter != null) {
      processor = new FilteringProcessor(processor, filter);
    }

    processor = new CacheFilterDecorator(processor);

    return new DataAggregator(processor, delay != null ? delay : 0);
  }

}

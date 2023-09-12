package code.visitors;

import code.processors.DataAggregator;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AggregatorVisitor
    extends AggregatorConfigParserBaseVisitor<List<DataAggregator>> {

  private final Logger logger;

  public AggregatorVisitor(Logger logger) {
    this.logger = logger;
  }

  /**
   * Visits a parse tree and creates a list of Processors from each source.
   *
   * @param ctx the parse tree
   * @return the list of processors.
   */
  @Override
  public List<DataAggregator> visitSources(AggregatorConfigParser.SourcesContext ctx) {
    List<DataAggregator> sources = new ArrayList<>();

    for (AggregatorConfigParser.SourceTypeContext sourceType : ctx.sourceType()) {
      sources.add(sourceType.accept(new AggregatorProcessorVisitor(this.logger)));
    }

    return sources;
  }
}

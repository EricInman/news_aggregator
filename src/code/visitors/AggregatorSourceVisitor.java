package code.visitors;

import code.datasources.DataSource;
import code.datasources.FileSource;
import code.datasources.UrlSource;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;

public class AggregatorSourceVisitor
    extends AggregatorConfigParserBaseVisitor<DataSource> {

  /**
   * Visits a parse tree to obtain a file's location.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public DataSource visitSourceLocation(AggregatorConfigParser.SourceLocationContext ctx) {
    return new FileSource(ctx.LINE().toString().trim());
  }

  /**
   * Visits a parse tree to obtain a url's address.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public DataSource visitSourceAddress(AggregatorConfigParser.SourceAddressContext ctx) {
    return new UrlSource(ctx.LINE().toString().trim());
  }
}

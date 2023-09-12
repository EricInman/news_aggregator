package code.visitors;

import code.parsers.ArticleParser;
import code.parsers.Parser;
import code.parsers.SimpleParser;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;

public class AggregatorParserVisitor
    extends AggregatorConfigParserBaseVisitor<Parser> {

  /**
   * Visits a parse tree to get the specific format of the source.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public Parser visitFormat(AggregatorConfigParser.FormatContext ctx) {
    return ctx.format_option().accept(this);
  }

  /**
   * Visits a parse tree to obtain the parser for the newsapi format.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public Parser visitNewsApiFormat(AggregatorConfigParser.NewsApiFormatContext ctx) {
    return new ArticleParser();
  }

  /**
   * Visits a parse tree to get the parser for the simple format.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  @Override
  public Parser visitSimpleFormat(AggregatorConfigParser.SimpleFormatContext ctx) {
    return new SimpleParser();
  }
}

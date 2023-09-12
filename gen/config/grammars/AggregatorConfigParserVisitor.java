// Generated from /Users/ericinman/Downloads/news_aggregator/src/config/grammars/AggregatorConfigParser.g4 by ANTLR 4.12.0
package config.grammars;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AggregatorConfigParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AggregatorConfigParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#sources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSources(AggregatorConfigParser.SourcesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#sourceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceType(AggregatorConfigParser.SourceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#fileSourceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileSourceType(AggregatorConfigParser.FileSourceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#urlSourceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrlSourceType(AggregatorConfigParser.UrlSourceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#sourceName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceName(AggregatorConfigParser.SourceNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#format}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormat(AggregatorConfigParser.FormatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewsApiFormat}
	 * labeled alternative in {@link AggregatorConfigParser#format_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewsApiFormat(AggregatorConfigParser.NewsApiFormatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleFormat}
	 * labeled alternative in {@link AggregatorConfigParser#format_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFormat(AggregatorConfigParser.SimpleFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#sourceLocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceLocation(AggregatorConfigParser.SourceLocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#sourceAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceAddress(AggregatorConfigParser.SourceAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(AggregatorConfigParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NestedExpression}
	 * labeled alternative in {@link AggregatorConfigParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpression(AggregatorConfigParser.NestedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link AggregatorConfigParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(AggregatorConfigParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KeywordExpression}
	 * labeled alternative in {@link AggregatorConfigParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordExpression(AggregatorConfigParser.KeywordExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpression}
	 * labeled alternative in {@link AggregatorConfigParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(AggregatorConfigParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AggregatorConfigParser#delay}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelay(AggregatorConfigParser.DelayContext ctx);
}
package code.parsers;

import code.processors.DataAggregator;
import code.visitors.AggregatorVisitor;
import config.grammars.AggregatorConfigLexer;
import config.grammars.AggregatorConfigParser;
import config.grammars.AggregatorConfigParserBaseVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class AntlerParserContainer implements AntlerParser {

  /**
   * Creates and returns a list of processors.
   *
   * @param filename the name of the config file.
   * @param logger the logger to both create the processor and log errors.
   * @return a list of processors to extract articles.
   */
  @Override
  public List<DataAggregator> gatherProcessors(String filename, Logger logger) {

    List<ParseTree> parseTrees = parseConfigFile(filename, logger);
    return gatherVisitorResults(parseTrees, new AggregatorVisitor(logger));
  }

  private List<ParseTree> parseConfigFile(String filename, Logger logger) {
    List<ParseTree> parseTrees = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromFileName(filename)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        parseTrees.add(parseTree);
      }
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }

    return parseTrees;
  }

  private <T> T gatherVisitorResults(List<ParseTree> parseTrees,
                                                  AggregatorConfigParserBaseVisitor<T> visitor) {
    T results = null;

    for (ParseTree parseTree : parseTrees) {
      results = parseTree.accept(visitor);
    }

    return results;
  }
}
package code.parsers;

import code.processors.DataAggregator;
import java.util.List;
import java.util.logging.Logger;

public interface AntlerParser {
  /**
   * Gathers a list of Processors with a source and a logger.
   *
   * @param filename the name of the source.
   * @param logger the logger to log errors.
   * @return a list of Processors.
   */
  List<DataAggregator> gatherProcessors(String filename, Logger logger);
}

package code.misc;

import code.apiobjects.Article;
import code.parsers.AntlerParser;
import code.parsers.AntlerParserContainer;
import code.parsers.ArticleParser;
import code.processors.DataAggregator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ArticleParserDriver {

  /**
   * Checks for config files and schedules processors to run.
   *
   * @param args the command line arguments
   * @param logger the logger to log errors
   * @param scheduler the scheduler used to schedule processors
   * @param queue the shared queue between all threads
   */
  private static void parseConfigFiles(String[] args, Logger logger,
                                       ScheduledExecutorService scheduler,
                                       BlockingQueue<Article> queue) {
    for (String arg : args) {
      if (arg.contains("config")) {
        AntlerParser container = new AntlerParserContainer();
        List<DataAggregator> procs = container.gatherProcessors(arg, logger);

        for (DataAggregator data : procs) {
          if (data.getDelay() > 0) {
            scheduler.scheduleWithFixedDelay(
                new ProcessorRunner(data.getProcessor(), queue),
                0, data.getDelay(), TimeUnit.SECONDS);
          } else {
            scheduler.schedule(new ProcessorRunner(
                    data.getProcessor(), queue), 0, TimeUnit.SECONDS);
          }
        }

        scheduler.schedule(new PrinterThread(queue), 0, TimeUnit.SECONDS);
      }
    }
  }

  /**
   * Runs the Article Parser to go through all sources.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    BlockingQueue<Article> q = new LinkedBlockingDeque<>();
    ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    Logger logger = Logger.getLogger(ArticleParser.class.getName());
    parseConfigFiles(args, logger, scheduler, q);
  }
}

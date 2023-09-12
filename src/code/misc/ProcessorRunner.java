package code.misc;

import code.apiobjects.Article;
import code.processors.Processor;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ProcessorRunner implements Runnable {
  private final Processor processor;
  private final BlockingQueue<Article> queue;

  public ProcessorRunner(Processor processor, BlockingQueue<Article> queue) {
    this.processor = processor;
    this.queue = queue;
  }

  /**
   * Runs the given processor in a thread and stores the articles in a blocking queue.
   */
  @Override
  public void run() {
    List<Article> articles = this.processor.extractArticles().getArticles();
    for (Article article : articles) {
      try {
        queue.put(article);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

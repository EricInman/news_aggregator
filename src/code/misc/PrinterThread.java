package code.misc;

import code.apiobjects.Article;
import java.util.concurrent.BlockingQueue;

public class PrinterThread implements Runnable {

  private final BlockingQueue<Article> articleQueue;

  public PrinterThread(BlockingQueue<Article> articles) {
    this.articleQueue = articles;
  }

  /**
   * Prints the title, description, time, and url of an article.
   */
  private void printArticle(Article article) {
    System.out.println("Title: " + article.getTitle());
    System.out.println("Description: " + article.getDescription());
    System.out.println("Time: " + article.getPublishedAt());
    System.out.println("URL: " + article.getUrl());
    System.out.println();
  }

  /**
   * Prints the articles one by one as they appear in the queue.
   */
  public void run() {
    Article article;
    try {
      while (!Thread.interrupted()) {
        article = articleQueue.take();
        printArticle(article);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

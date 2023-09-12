package code.apiobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

  private final String title;
  private final String description;
  private final String url;
  private final LocalDateTime publishedAt;

  /**
   * Creates a representation of an edu.calpoly.csc305.assignment4.newsApiObjects.Article.
   *
   * @param title The title of the article.
   * @param description A description or snippet from the article.
   * @param url The direct URL to the article.
   * @param publishedAt The date and time the article was published.
   */
  @JsonCreator
  public Article(@JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("url") String url,
                 @JsonProperty("publishedAt") LocalDateTime publishedAt) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.publishedAt = publishedAt;
  }

  /**
   * Determines if two Articles are equal to each other.
   *
   * @param other the other article to compare.
   * @return True if they're the same, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Article)) {
      return false;
    }

    Article article = (Article) other;

    return title.equals(article.title) && description.equals(article.description)
        && url.equals(article.url) && publishedAt.equals(article.publishedAt);
  }

  /**
   * Overrides the hashCode method.

   * @return returns a hashcode for the Article object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, description, url);
  }

  /**
   * Gets the title of the Article.

   * @return the title.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Gets the description of the Article.

   * @return the description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the url of the Article.

   * @return the url.
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * Gets the time published of the Article.

   * @return the time published.
   */
  public LocalDateTime getPublishedAt() {
    return this.publishedAt;
  }
}

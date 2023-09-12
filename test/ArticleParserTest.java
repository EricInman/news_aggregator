import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import code.apiobjects.Article;
import code.apiobjects.NewsResponseObject;
import code.datasources.DataSource;
import code.datasources.FileSource;
import code.datasources.UrlSource;
import code.expressions.AndExpression;
import code.expressions.Expression;
import code.expressions.KeywordExpression;
import code.expressions.OrExpression;
import code.misc.LocalDateTimeDeserializer;
import code.parsers.ArticleParser;
import code.parsers.SimpleParser;
import code.processors.CacheFilterDecorator;
import code.processors.DataAggregator;
import code.processors.FilteringProcessor;
import code.processors.MainProcessor;
import code.processors.Processor;
import code.visitors.AggregatorVisitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import config.grammars.AggregatorConfigLexer;
import config.grammars.AggregatorConfigParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ArticleParserTest {

  @Test
  void testParsingAllArticles() {
    // setting up Handler and logger
    MyCustomHandler handler = new MyCustomHandler();
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testParsingAllArticles");

    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    // mock json response with two articles
    String jsonString = "{\"status\":\"ok\",\"totalResults\":38,\"articles"
        + "\":[{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author"
        + "\":\"some author\",\"title\":\"a title here\",\"description\":\"some description\",\"url"
        + "\":\"someurl.com\",\"publishedAt\":\"2021-03-24T22:32:00Z\",\"content"
        + "\":\"some content\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},"
        + "\"author\":\"Eric Inman\","
        + "\"title\":\"Man fights bear with bear hands\","
        + "\"description\":\"Bears beats battle royale"
        + "\",\"url\":\"www.bears.com\",\"urlToImage\":\"image of bear\","
        + "\"publishedAt\":\"2021-03-24T22:20:12Z\",\"content\":null}]}";

    // testing if all the articles in the list get processed.
    ArticleParser parser = new ArticleParser();
    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    List<Article> test = parser.createResponse(testStream, logger).getArticles();

    assertEquals(2, test.size());

    // checking if the log file contains nothing.
    assertEquals(0, handler.getLogs().size());
  }

  @Test
  void testCorrectOutput() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testCorrectOutput");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "{\"status\":\"ok\",\"totalResults\":38,\"articles"
        + "\":[{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author"
        + "\":\"some author\",\"title\":\"a title here\",\"description\":\"some description\",\"url"
        + "\":\"someurl.com\",\"publishedAt\":\"2021-03-24T22:32:00Z\",\"content"
        + "\":\"some content\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},"
        + "\"author\":\"Eric Inman\","
        + "\"title\":\"Man fights bear with bear hands\","
        + "\"description\":\"Bears beats battle royale"
        + "\",\"url\":\"www.bears.com\",\"urlToImage\":\"image of bear\","
        + "\"publishedAt\":\"2021-03-24T22:20:12Z\",\"content\":null}]}";

    Article comparisonArticle1 = new Article("a title here",
        "some description", "someurl.com",
        LocalDateTime.parse("2021-03-24T22:32:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    Article comparisonArticle2 = new Article("Man fights bear with bear hands",
        "Bears beats battle royale", "www.bears.com",
          LocalDateTime.parse("2021-03-24T22:20:12Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    ArticleParser parser = new ArticleParser();
    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    List<Article> articles = parser.createResponse(testStream, logger).getArticles();

    assertEquals(2, articles.size());
    assertEquals(comparisonArticle1, articles.get(0));
    assertEquals(comparisonArticle2, articles.get(1));
  }

  @Test
  void testMalformedInput() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testMalformedInput");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    // mock json file where the second article is missing a title.
    String jsonString = "{\"status\":\"ok\",\"totalResults\":38,\"articles"
        + "\":[{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author"
        + "\":\"some author\",\"title\":\"a title here\",\"description\":\"some description\",\"url"
        + "\":\"someurl.com\",\"publishedAt\":\"2021-03-24T22:32:00Z\",\"content"
        + "\":\"some content\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},"
        + "\"author\":\"Eric Inman\","
        + "\"description\":\"Bears beats battle royale"
        + "\",\"url\":\"www.bears.com\",\"urlToImage\":\"image of bear\","
        + "\"publishedAt\":\"2021-03-24T22:20:12Z\",\"content\":null}]}";

    Article comparisonArticle1 = new Article("a title here",
        "some description", "someurl.com",
        LocalDateTime.parse("2021-03-24T22:32:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    Article comparisonArticle2 = new Article("Man fights bear with bear hands",
        "Bears beats battle royale", "www.bears.com",
        LocalDateTime.parse("2021-03-24T22:20:12Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    ArticleParser parser = new ArticleParser();
    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    List<Article> articles = parser.createResponse(testStream, logger).getArticles();
    final String expectedError = "code."
        + "parsers.ArticleParser#parseArticles - "
        + "Article is missing one of the necessary fields.";

    // These two lines check that the first article is parsed correctly.
    assertEquals(1, articles.size());
    assertEquals(comparisonArticle1, articles.get(0));

    // This asserts that the handler gets the error and prints the right message.
    assertEquals(1, handler.getLogs().size());
    assertEquals(expectedError, handler.getLogs().get(0));
  }

  @Test
  void testNewDateFormat() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    mapper.registerModule(module);

    // defining a test class for holding the LocalDateTime Object
    TestDate actual = null;

    try {
      actual = mapper.readValue(
        "{\"date\":\"2021-04-16 09:53:23.709229\"}",
        TestDate.class);
    } catch (Exception e) {
      fail("Failed due to exception thrown when reading the date to LocalDateTime object.");
    }

    LocalDateTime expectedValue = LocalDateTime.parse("2021-04-16 09:53:23.709229",
        DateTimeFormatter.ofPattern("y-M-d H:m:s[.SSSSSS]"));
    assertEquals(expectedValue, actual.getDate());
  }

  @Test
  void testSimpleParser() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testSimpleParser");
    SimpleParser sp = new SimpleParser();

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "{\"description\":\"Extend Assignment #1 to support"
        + " multiple sources and to introduce source processor.\","
        + "\"publishedAt\":\"2021-04-16 09:53:23.709229\","
        + "\"title\":\"Assignment #2\","
        + "\"url\":\"https://canvas.calpoly.edu/courses/55411/assignments/274503\"}";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    NewsResponseObject response = sp.createResponse(testStream, logger);

    Article expected = new Article("Assignment #2",
        "Extend Assignment #1 to support multiple sources and to introduce source processor.",
        "https://canvas.calpoly.edu/courses/55411/assignments/274503",
        LocalDateTime.parse("2021-04-16 09:53:23.709229",
          DateTimeFormatter.ofPattern("y-M-d H:m:s[.SSSSSS]")));

    assertEquals(expected, response.getArticles().get(0));
  }

  @Test
  void testProcessor() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testProcessor");
    SimpleParser sp = new SimpleParser();

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "{\"description\":\"Extend Assignment #1 to support"
        + " multiple sources and to introduce source processor.\","
        + "\"publishedAt\":\"2021-04-16 09:53:23.709229\","
        + "\"title\":\"Assignment #2\","
        + "\"url\":\"https://canvas.calpoly.edu/courses/55411/assignments/274503\"}";

    DataSource testSource = new TestSource(jsonString);
    MainProcessor processor = new MainProcessor(sp, testSource, logger);

    Article expected = new Article("Assignment #2",
        "Extend Assignment #1 to support multiple sources and to introduce source processor.",
        "https://canvas.calpoly.edu/courses/55411/assignments/274503",
        LocalDateTime.parse("2021-04-16 09:53:23.709229",
          DateTimeFormatter.ofPattern("y-M-d H:m:s[.SSSSSS]")));

    assertEquals(expected, processor.extractArticles().getArticles().get(0));
  }

  @Test
  void testVisitorTrivial() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testVisitorTrivial");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "file\n"
        + "name: Simple Fixed\n"
        + "format: simple\n"
        + "location: inputs/simple.json\n"
        + "filter:\n";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    DataSource fileSource = new FileSource("inputs/simple.json");
    Processor expectedProcessor = new CacheFilterDecorator(
            new MainProcessor(new SimpleParser(), fileSource, logger));
    List<DataAggregator> testResults = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromStream(testStream)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        testResults = parseTree.accept(new AggregatorVisitor(logger));
      }
    } catch (IOException e) {
      fail("Parse tree could not be created.");
    }

    assertEquals(expectedProcessor, testResults.get(0).getProcessor());
  }

  @Test
  void testVisitorNonTrivial() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testSimpleParser");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "file\n"
        + "name: Simple Fixed\n"
        + "format: simple\n"
        + "location: inputs/simple.json\n"
        + "filter:\n"
        + "\n"
        + "url\n"
        + "name: Some url name\n"
        + "format: newsapi\n"
        + "address: www.baseballiscool.com\n"
        + "filter:\n";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    DataSource fileSource = new FileSource("inputs/simple.json");
    DataSource urlSource = new UrlSource("www.baseballiscool.com");
    Processor expectedProcessor1 = new CacheFilterDecorator(
            new MainProcessor(new SimpleParser(), fileSource, logger));
    Processor expectedProcessor2 = new CacheFilterDecorator(
            new MainProcessor(new ArticleParser(), urlSource, logger));
    List<DataAggregator> testResults = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromStream(testStream)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        testResults = parseTree.accept(new AggregatorVisitor(logger));
      }
    } catch (IOException e) {
      fail("Parse tree could not be created.");
    }

    assertEquals(expectedProcessor1, testResults.get(0).getProcessor());
    assertEquals(expectedProcessor2, testResults.get(1).getProcessor());
  }

  @Test
  void testVisitorWithFilter() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testVisitorWithFilter");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "file\n"
        + "name: Simple Fixed\n"
        + "format: simple\n"
        + "location: inputs/simple.json\n"
        + "filter: stuff\n";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    DataSource fileSource = new FileSource("inputs/simple.json");
    Expression keyword = new KeywordExpression("stuff");
    Processor expectedProcessor = new CacheFilterDecorator(
            new FilteringProcessor(
              new MainProcessor(new SimpleParser(), fileSource, logger), keyword));
    List<DataAggregator> testResults = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromStream(testStream)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        testResults = parseTree.accept(new AggregatorVisitor(logger));
      }
    } catch (IOException e) {
      fail("Parse tree could not be created.");
    }

    assertEquals(expectedProcessor, testResults.get(0).getProcessor());
  }

  @Test
  void testVisitorMoreComplexFilter() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName() + ".testVisitorWithFilter");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "file\n"
        + "name: Simple Fixed\n"
        + "format: simple\n"
        + "location: inputs/simple.json\n"
        + "filter: (Dodgers | Giants) & (Padres | Angels)\n";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    DataSource fileSource = new FileSource("inputs/simple.json");

    Expression dodgers = new KeywordExpression("Dodgers");
    Expression giants = new KeywordExpression("Giants");
    Expression padres = new KeywordExpression("Padres");
    Expression angels = new KeywordExpression("Angels");
    Expression or1 = new OrExpression(dodgers, giants);
    Expression or2 = new OrExpression(padres, angels);
    Expression expected = new AndExpression(or1, or2);

    Processor expectedProcessor = new CacheFilterDecorator(
            new FilteringProcessor(
              new MainProcessor(new SimpleParser(), fileSource, logger), expected));
    List<DataAggregator> testResults = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromStream(testStream)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        testResults = parseTree.accept(new AggregatorVisitor(logger));
      }
    } catch (IOException e) {
      fail("Parse tree could not be created.");
    }

    assertEquals(expectedProcessor, testResults.get(0).getProcessor());
  }

  @Test
  void testDataAggregatorCreation() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName()
        + ".testScheduledDecoratorCreation");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String jsonString = "file\n"
        + "name: Eric Inman\n"
        + "format: simple\n"
        + "location: inputs/hidden.file\n"
        + "filter:\n"
        + "delay: 10\n";

    InputStream testStream = new ByteArrayInputStream(jsonString.getBytes());
    DataSource fileSource = new FileSource("inputs/hidden.file");

    CacheFilterDecorator proc = new CacheFilterDecorator(
            new MainProcessor(new SimpleParser(), fileSource, logger));

    DataAggregator expectedProcessor = new DataAggregator(proc, 10);
    List<DataAggregator> testResults = new ArrayList<>();

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromStream(testStream)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);
      ParseTree parseTree = parser.sources();
      if (parser.getNumberOfSyntaxErrors() == 0) {
        testResults = parseTree.accept(new AggregatorVisitor(logger));
      }
    } catch (IOException e) {
      fail("Parse tree could not be created.");
    }

    assertEquals(expectedProcessor, testResults.get(0));
  }

  @Test
  void testScheduledDecoratorOutput() {
    Logger logger = Logger.getLogger(ArticleParserTest.class.getName()
        + ".testScheduledDecoratorOutput");

    MyCustomHandler handler = new MyCustomHandler();
    logger.setUseParentHandlers(false);
    logger.addHandler(handler);

    String fileContents = "{\"status\":\"ok\",\"totalResults\":38,"
        + "\"articles\":[{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\""
        + "Eric Inman\",\"title\":\"Why the Earth is flat.\",\""
        + "description\":\"this is a description\","
        + "\"url\":\"www.flatearthers.com\","
        + "\"urlToImage\":\"someImageHere\","
        + "\"publishedAt\":\"2021-03-24T22:32:00Z\","
        + "\"content\":\"something something content.\"}]}";

    DataSource fileSource = new TestSource(fileContents);

    CacheFilterDecorator proc = new CacheFilterDecorator(
            new MainProcessor(new ArticleParser(), fileSource, logger));

    DataAggregator expectedProcessor = new DataAggregator(proc, 10);

    Article expected = new Article("Why the Earth is flat.",
        "this is a description",
        "www.flatearthers.com",
        LocalDateTime.parse("2021-03-24T22:32:00Z",
        DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    NewsResponseObject obj = expectedProcessor.getProcessor().extractArticles();

    assertEquals(expected, obj.getArticles().get(0));

    String newFileContents = "{\"status\":\"ok\",\"totalResults\":38,"
        + "\"articles\":[{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\""
        + "Eric Inman\",\"title\":\"Why the Earth is flat.\",\""
        + "description\":\"this is a description\","
        + "\"url\":\"www.flatearthers.com\","
        + "\"urlToImage\":\"someImageHere\","
        + "\"publishedAt\":\"2021-03-24T22:32:00Z\","
        + "\"content\":\"something something content.\"}, "
        + "{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\""
        + "Eric Inman\",\"title\":\"Wyoming Doesn't Exist.\",\""
        + "description\":\"Wyoming Doesn't Exist\","
        + "\"url\":\"www.whatIsWyoming.com\","
        + "\"urlToImage\":\"someImageHere\","
        + "\"publishedAt\":\"2021-03-24T22:32:00Z\","
        + "\"content\":\"something something content.\"}]}";

    Article newExpected = new Article("Wyoming Doesn't Exist.",
        "Wyoming Doesn't Exist",
        "www.whatIsWyoming.com",
        LocalDateTime.parse("2021-03-24T22:32:00Z",
        DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    ((TestSource) fileSource).newContents(newFileContents);

    NewsResponseObject obj2 = expectedProcessor.getProcessor().extractArticles();

    assertEquals(newExpected, obj2.getArticles().get(0));
    assertEquals(1, obj2.getArticles().size());
  }
}


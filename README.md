# News Aggregator

This is a news aggregator that can collect articles from a news API (https://newsapi.org/) or local JSON file sources.

This is probably the easiest way to run the project:
1. Download IntelliJ (or another Java IDE you are familiar with)
2. Install all of the dependencies
3. Run ArticleParserDriver with the "config" file from the "inputs" folder as a command line argument
4. If successful, you should see news article summaries in the terminal of your IDE
   (The program will also be waiting for new articles to be posted, in which case you can just end the program)

Trying to run directly from the command line will be a lot more involved as you will have to install the dependencies manually, add them to the right paths, 
and then compile the entire project. I recommend getting it to work with the above method. 


## Dependencies
ANTLR version >= 4.9.2\
Jackson-Databind version >= 2.12.3\
Junit (for testing) >= 5.5.2

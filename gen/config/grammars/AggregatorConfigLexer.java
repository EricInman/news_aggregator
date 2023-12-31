// Generated from /Users/ericinman/Downloads/news_aggregator/src/config/grammars/AggregatorConfigLexer.g4 by ANTLR 4.12.0
package config.grammars;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AggregatorConfigLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FILE_SOURCE_TYPE=1, URL_SOURCE_TYPE=2, FORMAT=3, NEWSAPI_FORMAT=4, SIMPLE_FORMAT=5, 
		NAME=6, LOCATION=7, ADDRESS=8, FILTER=9, DELAY=10, NUM=11, NEWLINE=12, 
		WS=13, COMMENT=14, LINE=15, AND=16, OR=17, LPAREN=18, RPAREN=19, KEYWORD=20, 
		NL=21, SPACE=22;
	public static final int
		lineMode=1, filterMode=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "lineMode", "filterMode"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FILE_SOURCE_TYPE", "URL_SOURCE_TYPE", "FORMAT", "NEWSAPI_FORMAT", "SIMPLE_FORMAT", 
			"NAME", "LOCATION", "ADDRESS", "FILTER", "DELAY", "NUM", "NEWLINE", "WS", 
			"COMMENT", "LINE", "AND", "OR", "LPAREN", "RPAREN", "KEYWORD", "NL", 
			"SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'file'", "'url'", "'format:'", "'newsapi'", "'simple'", "'name:'", 
			"'location:'", "'address:'", "'filter:'", "'delay:'", null, null, null, 
			null, null, "'&'", "'|'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FILE_SOURCE_TYPE", "URL_SOURCE_TYPE", "FORMAT", "NEWSAPI_FORMAT", 
			"SIMPLE_FORMAT", "NAME", "LOCATION", "ADDRESS", "FILTER", "DELAY", "NUM", 
			"NEWLINE", "WS", "COMMENT", "LINE", "AND", "OR", "LPAREN", "RPAREN", 
			"KEYWORD", "NL", "SPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public AggregatorConfigLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AggregatorConfigLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0016\u00c1\u0006\uffff\uffff\u0006\uffff\uffff\u0006\uffff"+
		"\uffff\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007"+
		"\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007"+
		"\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b"+
		"\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007"+
		"\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002"+
		"\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002"+
		"\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0004\n\u0081\b\n\u000b\n\f\n\u0082"+
		"\u0001\u000b\u0003\u000b\u0086\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u008a\b\u000b\u0001\f\u0004\f\u008d\b\f\u000b\f\f\f\u008e\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0005\r\u0095\b\r\n\r\f\r\u0098\t\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\u000e\u0004\u000e\u009f\b\u000e\u000b\u000e\f\u000e"+
		"\u00a0\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0004"+
		"\u0013\u00ae\b\u0013\u000b\u0013\f\u0013\u00af\u0001\u0014\u0003\u0014"+
		"\u00b3\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00b7\b\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0004\u0015\u00bc\b\u0015\u000b\u0015\f"+
		"\u0015\u00bd\u0001\u0015\u0001\u0015\u0001\u0096\u0000\u0016\u0003\u0001"+
		"\u0005\u0002\u0007\u0003\t\u0004\u000b\u0005\r\u0006\u000f\u0007\u0011"+
		"\b\u0013\t\u0015\n\u0017\u000b\u0019\f\u001b\r\u001d\u000e\u001f\u000f"+
		"!\u0010#\u0011%\u0012\'\u0013)\u0014+\u0015-\u0016\u0003\u0000\u0001\u0002"+
		"\u0004\u0001\u000009\u0002\u0000\t\t  \u0002\u0000\n\n\r\r\u0006\u0000"+
		"\n\n\r\r  &&()||\u00c8\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0002!\u0001\u0000\u0000"+
		"\u0000\u0002#\u0001\u0000\u0000\u0000\u0002%\u0001\u0000\u0000\u0000\u0002"+
		"\'\u0001\u0000\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0002+\u0001"+
		"\u0000\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u0003/\u0001\u0000\u0000"+
		"\u0000\u00054\u0001\u0000\u0000\u0000\u00078\u0001\u0000\u0000\u0000\t"+
		"@\u0001\u0000\u0000\u0000\u000bH\u0001\u0000\u0000\u0000\rO\u0001\u0000"+
		"\u0000\u0000\u000fW\u0001\u0000\u0000\u0000\u0011c\u0001\u0000\u0000\u0000"+
		"\u0013n\u0001\u0000\u0000\u0000\u0015x\u0001\u0000\u0000\u0000\u0017\u0080"+
		"\u0001\u0000\u0000\u0000\u0019\u0089\u0001\u0000\u0000\u0000\u001b\u008c"+
		"\u0001\u0000\u0000\u0000\u001d\u0092\u0001\u0000\u0000\u0000\u001f\u009e"+
		"\u0001\u0000\u0000\u0000!\u00a4\u0001\u0000\u0000\u0000#\u00a6\u0001\u0000"+
		"\u0000\u0000%\u00a8\u0001\u0000\u0000\u0000\'\u00aa\u0001\u0000\u0000"+
		"\u0000)\u00ad\u0001\u0000\u0000\u0000+\u00b6\u0001\u0000\u0000\u0000-"+
		"\u00bb\u0001\u0000\u0000\u0000/0\u0005f\u0000\u000001\u0005i\u0000\u0000"+
		"12\u0005l\u0000\u000023\u0005e\u0000\u00003\u0004\u0001\u0000\u0000\u0000"+
		"45\u0005u\u0000\u000056\u0005r\u0000\u000067\u0005l\u0000\u00007\u0006"+
		"\u0001\u0000\u0000\u000089\u0005f\u0000\u00009:\u0005o\u0000\u0000:;\u0005"+
		"r\u0000\u0000;<\u0005m\u0000\u0000<=\u0005a\u0000\u0000=>\u0005t\u0000"+
		"\u0000>?\u0005:\u0000\u0000?\b\u0001\u0000\u0000\u0000@A\u0005n\u0000"+
		"\u0000AB\u0005e\u0000\u0000BC\u0005w\u0000\u0000CD\u0005s\u0000\u0000"+
		"DE\u0005a\u0000\u0000EF\u0005p\u0000\u0000FG\u0005i\u0000\u0000G\n\u0001"+
		"\u0000\u0000\u0000HI\u0005s\u0000\u0000IJ\u0005i\u0000\u0000JK\u0005m"+
		"\u0000\u0000KL\u0005p\u0000\u0000LM\u0005l\u0000\u0000MN\u0005e\u0000"+
		"\u0000N\f\u0001\u0000\u0000\u0000OP\u0005n\u0000\u0000PQ\u0005a\u0000"+
		"\u0000QR\u0005m\u0000\u0000RS\u0005e\u0000\u0000ST\u0005:\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UV\u0006\u0005\u0000\u0000V\u000e\u0001\u0000"+
		"\u0000\u0000WX\u0005l\u0000\u0000XY\u0005o\u0000\u0000YZ\u0005c\u0000"+
		"\u0000Z[\u0005a\u0000\u0000[\\\u0005t\u0000\u0000\\]\u0005i\u0000\u0000"+
		"]^\u0005o\u0000\u0000^_\u0005n\u0000\u0000_`\u0005:\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000ab\u0006\u0006\u0000\u0000b\u0010\u0001\u0000\u0000"+
		"\u0000cd\u0005a\u0000\u0000de\u0005d\u0000\u0000ef\u0005d\u0000\u0000"+
		"fg\u0005r\u0000\u0000gh\u0005e\u0000\u0000hi\u0005s\u0000\u0000ij\u0005"+
		"s\u0000\u0000jk\u0005:\u0000\u0000kl\u0001\u0000\u0000\u0000lm\u0006\u0007"+
		"\u0000\u0000m\u0012\u0001\u0000\u0000\u0000no\u0005f\u0000\u0000op\u0005"+
		"i\u0000\u0000pq\u0005l\u0000\u0000qr\u0005t\u0000\u0000rs\u0005e\u0000"+
		"\u0000st\u0005r\u0000\u0000tu\u0005:\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"vw\u0006\b\u0001\u0000w\u0014\u0001\u0000\u0000\u0000xy\u0005d\u0000\u0000"+
		"yz\u0005e\u0000\u0000z{\u0005l\u0000\u0000{|\u0005a\u0000\u0000|}\u0005"+
		"y\u0000\u0000}~\u0005:\u0000\u0000~\u0016\u0001\u0000\u0000\u0000\u007f"+
		"\u0081\u0007\u0000\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u0001\u0000\u0000\u0000\u0083\u0018\u0001\u0000\u0000\u0000\u0084"+
		"\u0086\u0005\r\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u008a"+
		"\u0005\n\u0000\u0000\u0088\u008a\u0005\r\u0000\u0000\u0089\u0085\u0001"+
		"\u0000\u0000\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u001a\u0001"+
		"\u0000\u0000\u0000\u008b\u008d\u0007\u0001\u0000\u0000\u008c\u008b\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008c\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0006\f\u0002\u0000\u0091\u001c\u0001\u0000"+
		"\u0000\u0000\u0092\u0096\u0005#\u0000\u0000\u0093\u0095\t\u0000\u0000"+
		"\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000"+
		"\u0000\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0005\n\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\u0006\r\u0002\u0000\u009c\u001e\u0001\u0000\u0000\u0000\u009d"+
		"\u009f\b\u0002\u0000\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0006\u000e\u0003\u0000\u00a3 \u0001\u0000\u0000\u0000\u00a4\u00a5\u0005"+
		"&\u0000\u0000\u00a5\"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005|\u0000"+
		"\u0000\u00a7$\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005(\u0000\u0000\u00a9"+
		"&\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005)\u0000\u0000\u00ab(\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ae\b\u0003\u0000\u0000\u00ad\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0*\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b3\u0005\r\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b7\u0005\n\u0000\u0000\u00b5\u00b7\u0005\r\u0000\u0000\u00b6"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0006\u0014\u0003\u0000\u00b9"+
		",\u0001\u0000\u0000\u0000\u00ba\u00bc\u0007\u0001\u0000\u0000\u00bb\u00ba"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00bb"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c0\u0006\u0015\u0002\u0000\u00c0.\u0001"+
		"\u0000\u0000\u0000\r\u0000\u0001\u0002\u0082\u0085\u0089\u008e\u0096\u00a0"+
		"\u00af\u00b2\u00b6\u00bd\u0004\u0005\u0001\u0000\u0005\u0002\u0000\u0006"+
		"\u0000\u0000\u0004\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
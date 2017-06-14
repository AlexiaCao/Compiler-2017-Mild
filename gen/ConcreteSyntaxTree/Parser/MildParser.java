// Generated from /home/alexia/Compiler-2017-Mild/src/FrontEnd.ConcreteSyntaxTree/Parser/Mild.g4 by ANTLR 4.6
package FrontEnd.ConcreteSyntaxTree.Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MildParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, ID=50, INTEGER=51, STRING=52, 
		LineComment=53, BlockComment=54, WhiteSpace=55;
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_functionDeclaration = 2, 
		RULE_variableDeclarationStatement = 3, RULE_statement = 4, RULE_blockStatement = 5, 
		RULE_expressionStatement = 6, RULE_selectionStatement = 7, RULE_iterationStatement = 8, 
		RULE_jumpStatement = 9, RULE_expression = 10, RULE_type = 11, RULE_constant = 12;
	public static final String[] ruleNames = {
		"program", "classDeclaration", "functionDeclaration", "variableDeclarationStatement", 
		"statement", "blockStatement", "expressionStatement", "selectionStatement", 
		"iterationStatement", "jumpStatement", "expression", "type", "constant"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'{'", "'}'", "'~'", "'('", "','", "')'", "'='", "';'", 
		"'if'", "'else'", "'while'", "'for'", "'continue'", "'break'", "'return'", 
		"'++'", "'--'", "'['", "']'", "'.'", "'+'", "'-'", "'!'", "'new'", "'[]'", 
		"'*'", "'/'", "'%'", "'<<'", "'>>'", "'<'", "'>'", "'<='", "'>='", "'=='", 
		"'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'void'", "'int'", "'string'", 
		"'bool'", "'true'", "'false'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "ID", "INTEGER", "STRING", "LineComment", "BlockComment", 
		"WhiteSpace"
	};
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

	@Override
	public String getGrammarFileName() { return "Mild.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MildParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(29);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(26);
					classDeclaration();
					}
					break;
				case 2:
					{
					setState(27);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(28);
					variableDeclarationStatement();
					}
					break;
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MildParser.ID, 0); }
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__0);
			setState(34);
			match(ID);
			setState(35);
			match(T__1);
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(38);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(36);
					functionDeclaration();
					}
					break;
				case 2:
					{
					setState(37);
					variableDeclarationStatement();
					}
					break;
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << ID))) != 0) );
			setState(42);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(MildParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MildParser.ID, i);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(44);
				match(T__3);
				}
			}

			setState(47);
			type(0);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(48);
				match(ID);
				}
			}

			setState(51);
			match(T__4);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << ID))) != 0)) {
				{
				setState(52);
				type(0);
				setState(53);
				match(ID);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(54);
					match(T__5);
					setState(55);
					type(0);
					setState(56);
					match(ID);
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(65);
			match(T__6);
			setState(66);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MildParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			type(0);
			setState(69);
			match(ID);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(70);
				match(T__7);
				setState(71);
				expression(0);
				}
			}

			setState(74);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				blockStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				selectionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				iterationStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				jumpStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				variableDeclarationStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_blockStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__1);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				{
				setState(85);
				statement();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				setState(93);
				expression(0);
				}
			}

			setState(96);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitSelectionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitSelectionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__9);
			setState(99);
			match(T__4);
			setState(100);
			expression(0);
			setState(101);
			match(T__6);
			setState(102);
			statement();
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(103);
				match(T__10);
				setState(104);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterIterationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitIterationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitIterationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_iterationStatement);
		int _la;
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(T__11);
				setState(108);
				match(T__4);
				setState(109);
				expression(0);
				setState(110);
				match(T__6);
				setState(111);
				statement();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(T__12);
				setState(114);
				match(T__4);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(115);
					expression(0);
					}
				}

				setState(118);
				match(T__8);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(119);
					expression(0);
					}
				}

				setState(122);
				match(T__8);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(123);
					expression(0);
					}
				}

				setState(126);
				match(T__6);
				setState(127);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jumpStatement);
		int _la;
		try {
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(T__13);
				setState(131);
				match(T__8);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(T__14);
				setState(133);
				match(T__8);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(T__15);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(135);
					expression(0);
					}
				}

				setState(138);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token operator;
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode ID() { return getToken(MildParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
			case T__47:
			case T__48:
			case INTEGER:
			case STRING:
				{
				setState(142);
				constant();
				}
				break;
			case ID:
				{
				setState(143);
				match(ID);
				}
				break;
			case T__4:
				{
				setState(144);
				match(T__4);
				setState(145);
				expression(0);
				setState(146);
				match(T__6);
				}
				break;
			case T__3:
			case T__16:
			case T__17:
			case T__21:
			case T__22:
			case T__23:
				{
				setState(148);
				((ExpressionContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
					((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(149);
				expression(13);
				}
				break;
			case T__24:
				{
				setState(150);
				match(T__24);
				setState(151);
				type(0);
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(152);
						match(T__18);
						setState(153);
						expression(0);
						setState(154);
						match(T__19);
						}
						} 
					}
					setState(160);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				setState(164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(161);
						match(T__25);
						}
						} 
					}
					setState(166);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(225);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(170);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(171);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(173);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(174);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(176);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__29 || _la==T__30) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(177);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(179);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(180);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(182);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__35 || _la==T__36) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(183);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(185);
						match(T__37);
						setState(186);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(188);
						match(T__38);
						setState(189);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(191);
						match(T__39);
						setState(192);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(194);
						match(T__40);
						setState(195);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(197);
						match(T__41);
						setState(198);
						expression(3);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(200);
						match(T__7);
						setState(201);
						expression(1);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(203);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(205);
						match(T__4);
						setState(214);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__16) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << ID) | (1L << INTEGER) | (1L << STRING))) != 0)) {
							{
							setState(206);
							expression(0);
							setState(211);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__5) {
								{
								{
								setState(207);
								match(T__5);
								setState(208);
								expression(0);
								}
								}
								setState(213);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(216);
						match(T__6);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(218);
						match(T__18);
						setState(219);
						expression(0);
						setState(220);
						match(T__19);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(223);
						match(T__20);
						setState(224);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MildParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__42:
				{
				setState(231);
				match(T__42);
				}
				break;
			case T__43:
				{
				setState(232);
				match(T__43);
				}
				break;
			case T__44:
				{
				setState(233);
				match(T__44);
				}
				break;
			case T__45:
				{
				setState(234);
				match(T__45);
				}
				break;
			case ID:
				{
				setState(235);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(238);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(239);
					match(T__25);
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MildParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(MildParser.STRING, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MildListener ) ((MildListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MildVisitor ) return ((MildVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_constant);
		int _la;
		try {
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
			case T__47:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				_la = _input.LA(1);
				if ( !(_la==T__46 || _la==T__47) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
				match(STRING);
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 4);
				{
				setState(248);
				match(T__48);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 11:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 17);
		case 12:
			return precpred(_ctx, 16);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 14);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\39\u00fe\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\3\3\3\3"+
		"\3\3\3\3\3\6\3)\n\3\r\3\16\3*\3\3\3\3\3\4\5\4\60\n\4\3\4\3\4\5\4\64\n"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4=\n\4\f\4\16\4@\13\4\5\4B\n\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\5\5K\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6U\n"+
		"\6\3\7\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\3\b\5\ba\n\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\nw\n\n\3\n\3\n\5\n{\n\n\3\n\3\n\5\n\177\n\n\3\n\3\n\5\n\u0083\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u008b\n\13\3\13\5\13\u008e\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u009f\n\f\f\f"+
		"\16\f\u00a2\13\f\3\f\7\f\u00a5\n\f\f\f\16\f\u00a8\13\f\5\f\u00aa\n\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\7\f\u00d4\n\f\f\f\16\f\u00d7\13\f\5\f\u00d9\n\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e4\n\f\f\f\16\f\u00e7\13\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\5\r\u00ef\n\r\3\r\3\r\7\r\u00f3\n\r\f\r\16\r\u00f6"+
		"\13\r\3\16\3\16\3\16\3\16\5\16\u00fc\n\16\3\16\2\4\26\30\17\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\2\n\5\2\6\6\23\24\30\32\3\2\35\37\3\2\30\31\3"+
		"\2 !\3\2\"%\3\2&\'\3\2\23\24\3\2\61\62\u0128\2\37\3\2\2\2\4#\3\2\2\2\6"+
		"/\3\2\2\2\bF\3\2\2\2\nT\3\2\2\2\fV\3\2\2\2\16`\3\2\2\2\20d\3\2\2\2\22"+
		"\u0082\3\2\2\2\24\u008d\3\2\2\2\26\u00a9\3\2\2\2\30\u00ee\3\2\2\2\32\u00fb"+
		"\3\2\2\2\34 \5\4\3\2\35 \5\6\4\2\36 \5\b\5\2\37\34\3\2\2\2\37\35\3\2\2"+
		"\2\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#$\7\3\2"+
		"\2$%\7\64\2\2%(\7\4\2\2&)\5\6\4\2\')\5\b\5\2(&\3\2\2\2(\'\3\2\2\2)*\3"+
		"\2\2\2*(\3\2\2\2*+\3\2\2\2+,\3\2\2\2,-\7\5\2\2-\5\3\2\2\2.\60\7\6\2\2"+
		"/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\63\5\30\r\2\62\64\7\64\2\2\63"+
		"\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65A\7\7\2\2\66\67\5\30\r\2\67"+
		">\7\64\2\289\7\b\2\29:\5\30\r\2:;\7\64\2\2;=\3\2\2\2<8\3\2\2\2=@\3\2\2"+
		"\2><\3\2\2\2>?\3\2\2\2?B\3\2\2\2@>\3\2\2\2A\66\3\2\2\2AB\3\2\2\2BC\3\2"+
		"\2\2CD\7\t\2\2DE\5\f\7\2E\7\3\2\2\2FG\5\30\r\2GJ\7\64\2\2HI\7\n\2\2IK"+
		"\5\26\f\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\7\13\2\2M\t\3\2\2\2NU\5\f\7"+
		"\2OU\5\16\b\2PU\5\20\t\2QU\5\22\n\2RU\5\24\13\2SU\5\b\5\2TN\3\2\2\2TO"+
		"\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\13\3\2\2\2VZ\7\4\2\2"+
		"WY\5\n\6\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2"+
		"\2]^\7\5\2\2^\r\3\2\2\2_a\5\26\f\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\13"+
		"\2\2c\17\3\2\2\2de\7\f\2\2ef\7\7\2\2fg\5\26\f\2gh\7\t\2\2hk\5\n\6\2ij"+
		"\7\r\2\2jl\5\n\6\2ki\3\2\2\2kl\3\2\2\2l\21\3\2\2\2mn\7\16\2\2no\7\7\2"+
		"\2op\5\26\f\2pq\7\t\2\2qr\5\n\6\2r\u0083\3\2\2\2st\7\17\2\2tv\7\7\2\2"+
		"uw\5\26\f\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2xz\7\13\2\2y{\5\26\f\2zy\3\2"+
		"\2\2z{\3\2\2\2{|\3\2\2\2|~\7\13\2\2}\177\5\26\f\2~}\3\2\2\2~\177\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0081\7\t\2\2\u0081\u0083\5\n\6\2\u0082m\3"+
		"\2\2\2\u0082s\3\2\2\2\u0083\23\3\2\2\2\u0084\u0085\7\20\2\2\u0085\u008e"+
		"\7\13\2\2\u0086\u0087\7\21\2\2\u0087\u008e\7\13\2\2\u0088\u008a\7\22\2"+
		"\2\u0089\u008b\5\26\f\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008e\7\13\2\2\u008d\u0084\3\2\2\2\u008d\u0086\3"+
		"\2\2\2\u008d\u0088\3\2\2\2\u008e\25\3\2\2\2\u008f\u0090\b\f\1\2\u0090"+
		"\u00aa\5\32\16\2\u0091\u00aa\7\64\2\2\u0092\u0093\7\7\2\2\u0093\u0094"+
		"\5\26\f\2\u0094\u0095\7\t\2\2\u0095\u00aa\3\2\2\2\u0096\u0097\t\2\2\2"+
		"\u0097\u00aa\5\26\f\17\u0098\u0099\7\33\2\2\u0099\u00a0\5\30\r\2\u009a"+
		"\u009b\7\25\2\2\u009b\u009c\5\26\f\2\u009c\u009d\7\26\2\2\u009d\u009f"+
		"\3\2\2\2\u009e\u009a\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a6\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\7\34"+
		"\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u008f\3\2"+
		"\2\2\u00a9\u0091\3\2\2\2\u00a9\u0092\3\2\2\2\u00a9\u0096\3\2\2\2\u00a9"+
		"\u0098\3\2\2\2\u00aa\u00e5\3\2\2\2\u00ab\u00ac\f\r\2\2\u00ac\u00ad\t\3"+
		"\2\2\u00ad\u00e4\5\26\f\16\u00ae\u00af\f\f\2\2\u00af\u00b0\t\4\2\2\u00b0"+
		"\u00e4\5\26\f\r\u00b1\u00b2\f\13\2\2\u00b2\u00b3\t\5\2\2\u00b3\u00e4\5"+
		"\26\f\f\u00b4\u00b5\f\n\2\2\u00b5\u00b6\t\6\2\2\u00b6\u00e4\5\26\f\13"+
		"\u00b7\u00b8\f\t\2\2\u00b8\u00b9\t\7\2\2\u00b9\u00e4\5\26\f\n\u00ba\u00bb"+
		"\f\b\2\2\u00bb\u00bc\7(\2\2\u00bc\u00e4\5\26\f\t\u00bd\u00be\f\7\2\2\u00be"+
		"\u00bf\7)\2\2\u00bf\u00e4\5\26\f\b\u00c0\u00c1\f\6\2\2\u00c1\u00c2\7*"+
		"\2\2\u00c2\u00e4\5\26\f\7\u00c3\u00c4\f\5\2\2\u00c4\u00c5\7+\2\2\u00c5"+
		"\u00e4\5\26\f\6\u00c6\u00c7\f\4\2\2\u00c7\u00c8\7,\2\2\u00c8\u00e4\5\26"+
		"\f\5\u00c9\u00ca\f\3\2\2\u00ca\u00cb\7\n\2\2\u00cb\u00e4\5\26\f\3\u00cc"+
		"\u00cd\f\23\2\2\u00cd\u00e4\t\b\2\2\u00ce\u00cf\f\22\2\2\u00cf\u00d8\7"+
		"\7\2\2\u00d0\u00d5\5\26\f\2\u00d1\u00d2\7\b\2\2\u00d2\u00d4\5\26\f\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d0\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00e4\7\t\2\2\u00db\u00dc\f\21"+
		"\2\2\u00dc\u00dd\7\25\2\2\u00dd\u00de\5\26\f\2\u00de\u00df\7\26\2\2\u00df"+
		"\u00e4\3\2\2\2\u00e0\u00e1\f\20\2\2\u00e1\u00e2\7\27\2\2\u00e2\u00e4\7"+
		"\64\2\2\u00e3\u00ab\3\2\2\2\u00e3\u00ae\3\2\2\2\u00e3\u00b1\3\2\2\2\u00e3"+
		"\u00b4\3\2\2\2\u00e3\u00b7\3\2\2\2\u00e3\u00ba\3\2\2\2\u00e3\u00bd\3\2"+
		"\2\2\u00e3\u00c0\3\2\2\2\u00e3\u00c3\3\2\2\2\u00e3\u00c6\3\2\2\2\u00e3"+
		"\u00c9\3\2\2\2\u00e3\u00cc\3\2\2\2\u00e3\u00ce\3\2\2\2\u00e3\u00db\3\2"+
		"\2\2\u00e3\u00e0\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\27\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\b\r\1"+
		"\2\u00e9\u00ef\7-\2\2\u00ea\u00ef\7.\2\2\u00eb\u00ef\7/\2\2\u00ec\u00ef"+
		"\7\60\2\2\u00ed\u00ef\7\64\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00ea\3\2\2\2"+
		"\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f4"+
		"\3\2\2\2\u00f0\u00f1\f\3\2\2\u00f1\u00f3\7\34\2\2\u00f2\u00f0\3\2\2\2"+
		"\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\31"+
		"\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00fc\t\t\2\2\u00f8\u00fc\7\65\2\2"+
		"\u00f9\u00fc\7\66\2\2\u00fa\u00fc\7\63\2\2\u00fb\u00f7\3\2\2\2\u00fb\u00f8"+
		"\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fa\3\2\2\2\u00fc\33\3\2\2\2\37\37"+
		"!(*/\63>AJTZ`kvz~\u0082\u008a\u008d\u00a0\u00a6\u00a9\u00d5\u00d8\u00e3"+
		"\u00e5\u00ee\u00f4\u00fb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
package br.ufpe.cin.if688.minijava.antlr;
// Generated from MiniJava.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, COMMENT=36, LINE_COMMENT=37, WHITESPACE=38, 
		INTEGER_LITERAL=39, IDENTIFIER=40;
	public static final int
		RULE_goal = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_classSimpleDeclaration = 3, 
		RULE_classExtendsDeclaration = 4, RULE_varDeclaration = 5, RULE_methodDeclaration = 6, 
		RULE_type = 7, RULE_statement = 8, RULE_expression = 9, RULE_identifier = 10, 
		RULE_integerLiteral = 11;
	public static final String[] ruleNames = {
		"goal", "mainClass", "classDeclaration", "classSimpleDeclaration", "classExtendsDeclaration", 
		"varDeclaration", "methodDeclaration", "type", "statement", "expression", 
		"identifier", "integerLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", 
		"'String'", "'['", "']'", "')'", "'}'", "'extends'", "';'", "','", "'return'", 
		"'int'", "'boolean'", "'if'", "'else'", "'while'", "'System.out.println'", 
		"'='", "'&&'", "'<'", "'+'", "'-'", "'*'", "'.'", "'length'", "'true'", 
		"'false'", "'this'", "'new'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"COMMENT", "LINE_COMMENT", "WHITESPACE", "INTEGER_LITERAL", "IDENTIFIER"
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
	public String getGrammarFileName() { return "MiniJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GoalContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MiniJavaParser.EOF, 0); }
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitGoal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			mainClass();
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(25);
				classDeclaration();
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
			match(EOF);
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

	public static class MainClassContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMainClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__0);
			setState(34);
			identifier();
			setState(35);
			match(T__1);
			setState(36);
			match(T__2);
			setState(37);
			match(T__3);
			setState(38);
			match(T__4);
			setState(39);
			match(T__5);
			setState(40);
			match(T__6);
			setState(41);
			match(T__7);
			setState(42);
			match(T__8);
			setState(43);
			match(T__9);
			setState(44);
			identifier();
			setState(45);
			match(T__10);
			setState(46);
			match(T__1);
			setState(47);
			statement();
			setState(48);
			match(T__11);
			setState(49);
			match(T__11);
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
		public ClassSimpleDeclarationContext classSimpleDeclaration() {
			return getRuleContext(ClassSimpleDeclarationContext.class,0);
		}
		public ClassExtendsDeclarationContext classExtendsDeclaration() {
			return getRuleContext(ClassExtendsDeclarationContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDeclaration);
		try {
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				classSimpleDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				classExtendsDeclaration();
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

	public static class ClassSimpleDeclarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassSimpleDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classSimpleDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterClassSimpleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitClassSimpleDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassSimpleDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassSimpleDeclarationContext classSimpleDeclaration() throws RecognitionException {
		ClassSimpleDeclarationContext _localctx = new ClassSimpleDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classSimpleDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__0);
			setState(56);
			identifier();
			setState(57);
			match(T__1);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(58);
				varDeclaration();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(64);
				methodDeclaration();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(T__11);
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

	public static class ClassExtendsDeclarationContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassExtendsDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classExtendsDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterClassExtendsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitClassExtendsDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassExtendsDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassExtendsDeclarationContext classExtendsDeclaration() throws RecognitionException {
		ClassExtendsDeclarationContext _localctx = new ClassExtendsDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classExtendsDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__0);
			setState(73);
			identifier();
			setState(74);
			match(T__12);
			setState(75);
			identifier();
			setState(76);
			match(T__1);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(77);
				varDeclaration();
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(83);
				methodDeclaration();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(T__11);
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

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			type();
			setState(92);
			identifier();
			setState(93);
			match(T__13);
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

	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__2);
			setState(96);
			type();
			setState(97);
			identifier();
			setState(98);
			match(T__6);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(99);
				type();
				setState(100);
				identifier();
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__14) {
					{
					{
					setState(101);
					match(T__14);
					setState(102);
					type();
					setState(103);
					identifier();
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(112);
			match(T__10);
			setState(113);
			match(T__1);
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(114);
					varDeclaration();
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(120);
				statement();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(T__15);
			setState(127);
			expression(0);
			setState(128);
			match(T__13);
			setState(129);
			match(T__11);
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

	public static class TypeContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(T__16);
				setState(132);
				match(T__8);
				setState(133);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(T__17);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				match(T__16);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				identifier();
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

	public static class StatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		int _la;
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				match(T__1);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(140);
					statement();
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(T__11);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(T__18);
				setState(148);
				match(T__6);
				setState(149);
				expression(0);
				setState(150);
				match(T__10);
				setState(151);
				statement();
				setState(152);
				match(T__19);
				setState(153);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				match(T__20);
				setState(156);
				match(T__6);
				setState(157);
				expression(0);
				setState(158);
				match(T__10);
				setState(159);
				statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				match(T__21);
				setState(162);
				match(T__6);
				setState(163);
				expression(0);
				setState(164);
				match(T__10);
				setState(165);
				match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				identifier();
				setState(168);
				match(T__22);
				setState(169);
				expression(0);
				setState(170);
				match(T__13);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(172);
				identifier();
				setState(173);
				match(T__8);
				setState(174);
				expression(0);
				setState(175);
				match(T__9);
				setState(176);
				match(T__22);
				setState(177);
				expression(0);
				setState(178);
				match(T__13);
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

	public static class ExpressionContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpression(this);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(183);
				integerLiteral();
				}
				break;
			case 2:
				{
				setState(184);
				match(T__30);
				}
				break;
			case 3:
				{
				setState(185);
				match(T__31);
				}
				break;
			case 4:
				{
				setState(186);
				identifier();
				}
				break;
			case 5:
				{
				setState(187);
				match(T__32);
				}
				break;
			case 6:
				{
				setState(188);
				match(T__33);
				setState(189);
				match(T__16);
				setState(190);
				match(T__8);
				setState(191);
				expression(0);
				setState(192);
				match(T__9);
				}
				break;
			case 7:
				{
				setState(194);
				match(T__33);
				setState(195);
				identifier();
				setState(196);
				match(T__6);
				setState(197);
				match(T__10);
				}
				break;
			case 8:
				{
				setState(199);
				match(T__34);
				setState(200);
				expression(2);
				}
				break;
			case 9:
				{
				setState(201);
				match(T__6);
				setState(202);
				expression(0);
				setState(203);
				match(T__10);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(236);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(234);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(207);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(208);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(211);
						match(T__8);
						setState(212);
						expression(0);
						setState(213);
						match(T__9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(216);
						match(T__28);
						setState(217);
						match(T__29);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(218);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(219);
						match(T__28);
						setState(220);
						identifier();
						setState(221);
						match(T__6);
						setState(230);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << INTEGER_LITERAL) | (1L << IDENTIFIER))) != 0)) {
							{
							setState(222);
							expression(0);
							setState(227);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__14) {
								{
								{
								setState(223);
								match(T__14);
								setState(224);
								expression(0);
								}
								}
								setState(229);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(232);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MiniJavaParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(IDENTIFIER);
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

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(MiniJavaParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_integerLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(INTEGER_LITERAL);
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
		case 9:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00f6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\5\48\n\4\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\5\7\5D\n\5\f\5\16"+
		"\5G\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6Q\n\6\f\6\16\6T\13\6\3\6\7"+
		"\6W\n\6\f\6\16\6Z\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\5\bq\n\b\3\b\3\b\3\b\7\bv\n\b"+
		"\f\b\16\by\13\b\3\b\7\b|\n\b\f\b\16\b\177\13\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u008c\n\t\3\n\3\n\7\n\u0090\n\n\f\n\16\n\u0093"+
		"\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u00b7\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d0"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00e4\n\13\f\13\16\13\u00e7\13\13\5\13"+
		"\u00e9\n\13\3\13\3\13\7\13\u00ed\n\13\f\13\16\13\u00f0\13\13\3\f\3\f\3"+
		"\r\3\r\3\r\2\3\24\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\3\2\32\36\2\u010a"+
		"\2\32\3\2\2\2\4#\3\2\2\2\6\67\3\2\2\2\b9\3\2\2\2\nJ\3\2\2\2\f]\3\2\2\2"+
		"\16a\3\2\2\2\20\u008b\3\2\2\2\22\u00b6\3\2\2\2\24\u00cf\3\2\2\2\26\u00f1"+
		"\3\2\2\2\30\u00f3\3\2\2\2\32\36\5\4\3\2\33\35\5\6\4\2\34\33\3\2\2\2\35"+
		" \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3\2\2\2!\"\7\2\2"+
		"\3\"\3\3\2\2\2#$\7\3\2\2$%\5\26\f\2%&\7\4\2\2&\'\7\5\2\2\'(\7\6\2\2()"+
		"\7\7\2\2)*\7\b\2\2*+\7\t\2\2+,\7\n\2\2,-\7\13\2\2-.\7\f\2\2./\5\26\f\2"+
		"/\60\7\r\2\2\60\61\7\4\2\2\61\62\5\22\n\2\62\63\7\16\2\2\63\64\7\16\2"+
		"\2\64\5\3\2\2\2\658\5\b\5\2\668\5\n\6\2\67\65\3\2\2\2\67\66\3\2\2\28\7"+
		"\3\2\2\29:\7\3\2\2:;\5\26\f\2;?\7\4\2\2<>\5\f\7\2=<\3\2\2\2>A\3\2\2\2"+
		"?=\3\2\2\2?@\3\2\2\2@E\3\2\2\2A?\3\2\2\2BD\5\16\b\2CB\3\2\2\2DG\3\2\2"+
		"\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7\16\2\2I\t\3\2\2\2JK\7\3"+
		"\2\2KL\5\26\f\2LM\7\17\2\2MN\5\26\f\2NR\7\4\2\2OQ\5\f\7\2PO\3\2\2\2QT"+
		"\3\2\2\2RP\3\2\2\2RS\3\2\2\2SX\3\2\2\2TR\3\2\2\2UW\5\16\b\2VU\3\2\2\2"+
		"WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7\16\2\2\\\13\3"+
		"\2\2\2]^\5\20\t\2^_\5\26\f\2_`\7\20\2\2`\r\3\2\2\2ab\7\5\2\2bc\5\20\t"+
		"\2cd\5\26\f\2dp\7\t\2\2ef\5\20\t\2fm\5\26\f\2gh\7\21\2\2hi\5\20\t\2ij"+
		"\5\26\f\2jl\3\2\2\2kg\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2nq\3\2\2\2"+
		"om\3\2\2\2pe\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7\r\2\2sw\7\4\2\2tv\5\f\7\2"+
		"ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x}\3\2\2\2yw\3\2\2\2z|\5\22\n"+
		"\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2"+
		"\2\2\u0080\u0081\7\22\2\2\u0081\u0082\5\24\13\2\u0082\u0083\7\20\2\2\u0083"+
		"\u0084\7\16\2\2\u0084\17\3\2\2\2\u0085\u0086\7\23\2\2\u0086\u0087\7\13"+
		"\2\2\u0087\u008c\7\f\2\2\u0088\u008c\7\24\2\2\u0089\u008c\7\23\2\2\u008a"+
		"\u008c\5\26\f\2\u008b\u0085\3\2\2\2\u008b\u0088\3\2\2\2\u008b\u0089\3"+
		"\2\2\2\u008b\u008a\3\2\2\2\u008c\21\3\2\2\2\u008d\u0091\7\4\2\2\u008e"+
		"\u0090\5\22\n\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u00b7\7\16\2\2\u0095\u0096\7\25\2\2\u0096\u0097\7\t\2\2\u0097\u0098\5"+
		"\24\13\2\u0098\u0099\7\r\2\2\u0099\u009a\5\22\n\2\u009a\u009b\7\26\2\2"+
		"\u009b\u009c\5\22\n\2\u009c\u00b7\3\2\2\2\u009d\u009e\7\27\2\2\u009e\u009f"+
		"\7\t\2\2\u009f\u00a0\5\24\13\2\u00a0\u00a1\7\r\2\2\u00a1\u00a2\5\22\n"+
		"\2\u00a2\u00b7\3\2\2\2\u00a3\u00a4\7\30\2\2\u00a4\u00a5\7\t\2\2\u00a5"+
		"\u00a6\5\24\13\2\u00a6\u00a7\7\r\2\2\u00a7\u00a8\7\20\2\2\u00a8\u00b7"+
		"\3\2\2\2\u00a9\u00aa\5\26\f\2\u00aa\u00ab\7\31\2\2\u00ab\u00ac\5\24\13"+
		"\2\u00ac\u00ad\7\20\2\2\u00ad\u00b7\3\2\2\2\u00ae\u00af\5\26\f\2\u00af"+
		"\u00b0\7\13\2\2\u00b0\u00b1\5\24\13\2\u00b1\u00b2\7\f\2\2\u00b2\u00b3"+
		"\7\31\2\2\u00b3\u00b4\5\24\13\2\u00b4\u00b5\7\20\2\2\u00b5\u00b7\3\2\2"+
		"\2\u00b6\u008d\3\2\2\2\u00b6\u0095\3\2\2\2\u00b6\u009d\3\2\2\2\u00b6\u00a3"+
		"\3\2\2\2\u00b6\u00a9\3\2\2\2\u00b6\u00ae\3\2\2\2\u00b7\23\3\2\2\2\u00b8"+
		"\u00b9\b\13\1\2\u00b9\u00d0\5\30\r\2\u00ba\u00d0\7!\2\2\u00bb\u00d0\7"+
		"\"\2\2\u00bc\u00d0\5\26\f\2\u00bd\u00d0\7#\2\2\u00be\u00bf\7$\2\2\u00bf"+
		"\u00c0\7\23\2\2\u00c0\u00c1\7\13\2\2\u00c1\u00c2\5\24\13\2\u00c2\u00c3"+
		"\7\f\2\2\u00c3\u00d0\3\2\2\2\u00c4\u00c5\7$\2\2\u00c5\u00c6\5\26\f\2\u00c6"+
		"\u00c7\7\t\2\2\u00c7\u00c8\7\r\2\2\u00c8\u00d0\3\2\2\2\u00c9\u00ca\7%"+
		"\2\2\u00ca\u00d0\5\24\13\4\u00cb\u00cc\7\t\2\2\u00cc\u00cd\5\24\13\2\u00cd"+
		"\u00ce\7\r\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00b8\3\2\2\2\u00cf\u00ba\3\2"+
		"\2\2\u00cf\u00bb\3\2\2\2\u00cf\u00bc\3\2\2\2\u00cf\u00bd\3\2\2\2\u00cf"+
		"\u00be\3\2\2\2\u00cf\u00c4\3\2\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00cb\3\2"+
		"\2\2\u00d0\u00ee\3\2\2\2\u00d1\u00d2\f\17\2\2\u00d2\u00d3\t\2\2\2\u00d3"+
		"\u00ed\5\24\13\20\u00d4\u00d5\f\16\2\2\u00d5\u00d6\7\13\2\2\u00d6\u00d7"+
		"\5\24\13\2\u00d7\u00d8\7\f\2\2\u00d8\u00ed\3\2\2\2\u00d9\u00da\f\r\2\2"+
		"\u00da\u00db\7\37\2\2\u00db\u00ed\7 \2\2\u00dc\u00dd\f\f\2\2\u00dd\u00de"+
		"\7\37\2\2\u00de\u00df\5\26\f\2\u00df\u00e8\7\t\2\2\u00e0\u00e5\5\24\13"+
		"\2\u00e1\u00e2\7\21\2\2\u00e2\u00e4\5\24\13\2\u00e3\u00e1\3\2\2\2\u00e4"+
		"\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e9\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\7\r\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00d1\3\2"+
		"\2\2\u00ec\u00d4\3\2\2\2\u00ec\u00d9\3\2\2\2\u00ec\u00dc\3\2\2\2\u00ed"+
		"\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\25\3\2\2"+
		"\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\7*\2\2\u00f2\27\3\2\2\2\u00f3\u00f4"+
		"\7)\2\2\u00f4\31\3\2\2\2\24\36\67?ERXmpw}\u008b\u0091\u00b6\u00cf\u00e5"+
		"\u00e8\u00ec\u00ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
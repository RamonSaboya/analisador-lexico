package br.ufpe.cin.if688.minijava.visitor;

import java.util.Enumeration;

import br.ufpe.cin.if688.minijava.ast.And;
import br.ufpe.cin.if688.minijava.ast.ArrayAssign;
import br.ufpe.cin.if688.minijava.ast.ArrayLength;
import br.ufpe.cin.if688.minijava.ast.ArrayLookup;
import br.ufpe.cin.if688.minijava.ast.Assign;
import br.ufpe.cin.if688.minijava.ast.Block;
import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.Call;
import br.ufpe.cin.if688.minijava.ast.ClassDeclExtends;
import br.ufpe.cin.if688.minijava.ast.ClassDeclSimple;
import br.ufpe.cin.if688.minijava.ast.False;
import br.ufpe.cin.if688.minijava.ast.Formal;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierExp;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.If;
import br.ufpe.cin.if688.minijava.ast.IntArrayType;
import br.ufpe.cin.if688.minijava.ast.IntegerLiteral;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.LessThan;
import br.ufpe.cin.if688.minijava.ast.MainClass;
import br.ufpe.cin.if688.minijava.ast.MethodDecl;
import br.ufpe.cin.if688.minijava.ast.Minus;
import br.ufpe.cin.if688.minijava.ast.NewArray;
import br.ufpe.cin.if688.minijava.ast.NewObject;
import br.ufpe.cin.if688.minijava.ast.Not;
import br.ufpe.cin.if688.minijava.ast.Plus;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.This;
import br.ufpe.cin.if688.minijava.ast.Times;
import br.ufpe.cin.if688.minijava.ast.True;
import br.ufpe.cin.if688.minijava.ast.Type;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.While;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.BadBinaryOperandsException;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.BadUnaryOperandException;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.IncompatibleTypesException;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.MethodParamsMismatchException;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.SymbolNotFoundException;
import br.ufpe.cin.if688.minijava.exceptions.typecheck.TypeMismatchException;
import br.ufpe.cin.if688.minijava.symboltable.Class;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;

public class TypeCheckVisitor implements IVisitor<Type> {

	private SymbolTable symbolTable;
	private Class currClass;
	private Method currMethod;
	private boolean fromVariable;
	private boolean fromMethod;

	private StringBuilder errors;

	public TypeCheckVisitor(SymbolTable st, StringBuilder errors) {
		this.symbolTable = st;
		this.currClass = null;
		this.currMethod = null;
		this.fromVariable = false;
		this.fromMethod = false;

		this.errors = errors;
	}

	public StringBuilder getErrors() {
		return errors;
	}

	// Gambiarra para tratar exeções,
	// visto que não é possível alterar a implementação da AST do professor
	private void appendError(String message) {
		if (this.errors.length() > 0) {
			this.errors.append(System.lineSeparator());
		}

		this.errors.append(message);
	}

	// MainClass m;
	// ClassDeclList cl;
	public Type visit(Program n) {
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Type visit(MainClass n) {
		this.currClass = this.symbolTable.getClass(n.i1.toString());
		this.currMethod = this.symbolTable.getMethod("main", this.currClass.getId());
		n.i1.accept(this);
		this.fromVariable = true;
		n.i2.accept(this);
		this.fromVariable = false;
		n.s.accept(this);
		this.currMethod = null;
		this.currClass = null;
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclSimple n) {
		this.currClass = symbolTable.getClass(n.i.toString());
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		this.currClass = null;
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclExtends n) {
		this.currClass = symbolTable.getClass(n.i.toString());
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		this.currClass = null;
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n) {
		Type type = n.t.accept(this);
		this.fromVariable = true;
		n.i.accept(this);
		this.fromVariable = false;
		return type;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Type visit(MethodDecl n) {
		this.currMethod = this.symbolTable.getMethod(n.i.toString(), this.currClass.getId());
		Type expected = n.t.accept(this);
		this.fromMethod = true;
		n.i.accept(this);
		this.fromMethod = false;
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		Type actual = n.e.accept(this);
		if (!this.symbolTable.compareTypes(expected, actual)) {
			try {
				throw new IncompatibleTypesException(this.getTypeName(expected), this.getTypeName(actual), "METHOD RETURN");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		Type method = this.currMethod.type();
		this.currMethod = null;
		return method;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n) {
		Type type = n.t.accept(this);
		this.fromVariable = true;
		n.i.accept(this);
		this.fromVariable = false;
		return type;
	}

	public Type visit(IntArrayType n) {
		return n;
	}

	public Type visit(BooleanType n) {
		return n;
	}

	public Type visit(IntegerType n) {
		return n;
	}

	// String s;
	public Type visit(IdentifierType n) {
		if (!this.symbolTable.containsClass(n.s)) {
			try {
				throw new SymbolNotFoundException(n.s, "CLASS");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return n;
	}

	// StatementList sl;
	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		Type expression = n.e.accept(this);
		if (!(expression instanceof BooleanType)) {
			try {
				throw new IncompatibleTypesException("BooleanType", this.getTypeName(expression), "IF");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		Type expression = n.e.accept(this);
		if (!(expression instanceof BooleanType)) {
			try {
				throw new IncompatibleTypesException("BooleanType", this.getTypeName(expression), "WHILE");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n) {
		Type identifier = this.symbolTable.getVarType(this.currMethod, this.currClass, n.i.toString());
		this.fromVariable = true;
		n.i.accept(this);
		this.fromVariable = false;
		Type expression = n.e.accept(this);
		if (!this.symbolTable.compareTypes(identifier, expression)) {
			try {
				throw new IncompatibleTypesException(this.getTypeName(identifier), this.getTypeName(expression), "ASSIGN");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n) {
		this.fromVariable = true;
		Type array = n.i.accept(this);
		this.fromVariable = false;
		Type index = n.e1.accept(this);
		Type expression = n.e2.accept(this);
		if (!(array instanceof IntArrayType)) {
			try {
				throw new TypeMismatchException("IntArrayType", this.getTypeName(array), "ARRAYASSIGN");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		if (!(index instanceof IntegerType)) {
			try {
				throw new IncompatibleTypesException("IntegerType", this.getTypeName(index), "ARRAYASSIGN");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		if (!(expression instanceof IntegerType)) {
			try {
				throw new IncompatibleTypesException("IntegerType", this.getTypeName(expression), "ARRAYASSIGN");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if (!(expression1 instanceof BooleanType) || !(expression2 instanceof BooleanType)) {
			try {
				throw new BadBinaryOperandsException("BooleanType", "BooleanType", this.getTypeName(expression1), this.getTypeName(expression2), "And(&&)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if (!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			try {
				throw new BadBinaryOperandsException("IntegerType", "IntegerType", this.getTypeName(expression1), this.getTypeName(expression2), "LessThan(<)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if (!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			try {
				throw new BadBinaryOperandsException("IntegerType", "IntegerType", this.getTypeName(expression1), this.getTypeName(expression2), "Plus(+)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if (!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			try {
				throw new BadBinaryOperandsException("IntegerType", "IntegerType", this.getTypeName(expression1), this.getTypeName(expression2), "Minus(-)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if (!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			try {
				throw new BadBinaryOperandsException("IntegerType", "IntegerType", this.getTypeName(expression1), this.getTypeName(expression2), "Times(*)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		Type array = n.e1.accept(this);
		Type index = n.e2.accept(this);
		if (!(array instanceof IntArrayType)) {
			try {
				throw new TypeMismatchException("IntArrayType", this.getTypeName(array), "ARRAYLOOKUP");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		if (!(index instanceof IntegerType)) {
			try {
				throw new IncompatibleTypesException("IntegerType", this.getTypeName(index), "ARRAYLOOKUP");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntegerType();
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		Type expression = n.e.accept(this);
		if (!(expression instanceof IntArrayType)) {
			try {
				throw new TypeMismatchException("IntArrayType", this.getTypeName(expression), "ARRAYLENGTH");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntegerType();
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		Type classType = n.e.accept(this);
		Type type = null;
		if (classType instanceof IdentifierType) {
			Class classCall = this.symbolTable.getClass(((IdentifierType) classType).s);
			if (classCall == null) {
				try {
					throw new SymbolNotFoundException(((IdentifierType) classType).s, "CLASS");
				} catch (Exception e) {
					this.appendError(e.getMessage());
				}
			} else {
				Method methodCall = this.symbolTable.getMethod(n.i.toString(), ((IdentifierType) classType).s);
				if (methodCall == null) {
					try {
						throw new SymbolNotFoundException(n.i.toString(), "METHOD");
					} catch (Exception e) {
						this.appendError(e.getMessage());
					}
				} else {
					this.fromMethod = true;
					Class previousClass = this.currClass;
					this.currClass = classCall;
					n.i.accept(this);
					this.currClass = previousClass;
					this.fromMethod = false;
					type = methodCall.type();
					if (this.sameNumberArgs(methodCall.getParams(), n.el.size())) {
						for (int i = 0; i < n.el.size(); i++) {
							if (!this.symbolTable.compareTypes(n.el.elementAt(i).accept(this), methodCall.getParamAt(i).type())) {
								try {
									throw new IncompatibleTypesException(this.getTypeName(methodCall.getParamAt(i).type()), this.getTypeName(n.el.elementAt(i).accept(this)),
											methodCall.getId(), "METHOD ARGS");
								} catch (Exception e) {
									this.appendError(e.getMessage());
								}
								i = n.el.size();
							}
						}
					} else {
						try {
							throw new MethodParamsMismatchException(classCall.getId(), methodCall.getId());
						} catch (Exception e) {
							this.appendError(e.getMessage());
						}
					}
				}
			}
		} else {
			try {
				throw new TypeMismatchException("IdentifierType", this.getTypeName(classType), "NO CLASS");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return type;
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		return new IntegerType();
	}

	public Type visit(True n) {
		return new BooleanType();
	}

	public Type visit(False n) {
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		Type identifierExp = this.symbolTable.getVarType(this.currMethod, this.currClass, n.s);
		if (identifierExp == null) {
			try {
				throw new SymbolNotFoundException(n.s, "VARIABLE");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return identifierExp;
	}

	public Type visit(This n) {
		return this.currClass.type();
	}

	// Exp e;
	public Type visit(NewArray n) {
		Type expression = n.e.accept(this);
		if (!(expression instanceof IntegerType)) {
			try {
				throw new IncompatibleTypesException("IntegerType", this.getTypeName(expression), "NEWARRAY");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new IntArrayType();
	}

	// Identifier i;
	public Type visit(NewObject n) {
		n.i.accept(this);
		return this.symbolTable.getClass(n.i.toString()).type();
	}

	// Exp e;
	public Type visit(Not n) {
		Type expression = n.e.accept(this);
		if (!(expression instanceof BooleanType)) {
			try {
				throw new BadUnaryOperandException("BooleanType", this.getTypeName(expression), "Not(!)");
			} catch (Exception e) {
				this.appendError(e.getMessage());
			}
		}
		return new BooleanType();
	}

	// String s;
	public Type visit(Identifier n) {
		if (this.fromVariable) {
			Type identifier = this.symbolTable.getVarType(this.currMethod, this.currClass, n.toString());
			if (identifier == null) {
				try {
					throw new SymbolNotFoundException(n.toString(), "VARIABLE");
				} catch (Exception e) {
					this.appendError(e.getMessage());
				}
			}
			return identifier;
		} else if (this.fromMethod) {
			if (this.symbolTable.getMethod(n.toString(), this.currClass.getId()) != null) {
				return this.symbolTable.getMethod(n.toString(), this.currClass.getId()).type();
			} else {
				try {
					throw new SymbolNotFoundException(n.toString(), "METHOD");
				} catch (Exception e) {
					this.appendError(e.getMessage());
				}
			}
		} else {
			if (this.symbolTable.containsClass(n.toString())) {
				return this.symbolTable.getClass(n.toString()).type();
			} else {
				try {
					throw new SymbolNotFoundException(n.toString(), "CLASS");
				} catch (Exception e) {
					this.appendError(e.getMessage());
				}
			}
		}
		return new IdentifierType(n.toString());
	}

	public String getTypeName(Type t) {
		if (t instanceof BooleanType)
			return "BooleanType";
		else if (t instanceof IdentifierType)
			return ((IdentifierType) t).s;
		else if (t instanceof IntArrayType)
			return "IntArrayType";
		else if (t instanceof IntegerType)
			return "IntegerType";
		else
			return "Null";
	}

	@SuppressWarnings("rawtypes")
	public boolean sameNumberArgs(Enumeration e, int size) {
		int counter = 0;
		while (e.hasMoreElements()) {
			e.nextElement();
			counter++;
		}
		return counter == size;
	}
}

package br.ufpe.cin.if688.minijava.visitor;

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
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.Class;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;

public class TypeCheckVisitor implements IVisitor<Type> {

	private SymbolTable symbolTable;
	private Class currClass;
	private Class currParent;
	private Method currMethod;
	private boolean fromVariable;
	
	
	public TypeCheckVisitor(SymbolTable st) {
		this.symbolTable = st;
		this.currClass = null;
		this.currParent = null;
		this.currMethod = null;
		this.fromVariable = false;
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
		n.i2.accept(this);
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
		this.currParent = symbolTable.getClass(n.j.toString());
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		this.currParent = null;
		this.currClass = null;
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n) {
		this.fromVariable = true;
		Type type = n.t.accept(this);
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
		n.i.accept(this);
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
		if(!this.symbolTable.compareTypes(expected, actual)) {
			System.out.println("Incompatible types: " + this.getTypeName(actual) + " cannot be converted to " + this.getTypeName(expected) + ".");
		}
		Type method = this.currMethod.type();
		this.currMethod = null;
		return method;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n) {
		this.fromVariable = true;
		Type type = n.t.accept(this);
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
		if(!this.symbolTable.containsClass(n.s)) {
			System.out.println("Cannot find symbol " + n.s + ".");
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
		if(!(expression instanceof BooleanType)) {
			System.out.println("Incompatible types: " + this.getTypeName(expression) + " cannot be converted to BooleanType.");
		}
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		Type expression = n.e.accept(this);
		if(!(expression instanceof BooleanType)) {
			System.out.println("Incompatible types: " + this.getTypeName(expression) + " cannot be converted to BooleanType.");
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
		n.i.accept(this);
		Type expression = n.e.accept(this);
		if(!this.symbolTable.compareTypes(identifier,expression)) {
			System.out.println("Incompatible types: " + this.getTypeName(expression) + " cannot be converted to " + this.getTypeName(identifier) + ".");
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
		if(!(array instanceof IntArrayType)) {
			System.out.println("IntArrayType required, but " + this.getTypeName(array) + " found.");
		}
		if(!(index instanceof IntegerType)) {
			System.out.println("Incompatible types: " + this.getTypeName(index) + " cannot be converted to IntegerType.");
		}
		if(!(expression instanceof IntegerType)) {
			System.out.println("Incompatible types: " + this.getTypeName(expression) + " cannot be converted to IntegerType.");
		}
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if(!(expression1 instanceof BooleanType) || !(expression2 instanceof BooleanType)) {
			System.out.println("Bad operand types " + this.getTypeName(expression1) + " and " + this.getTypeName(expression2) +
					" for binary operator 'And(&&)'. Expected BooleanType and BooleanType expressions.");
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if(!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			System.out.println("Bad operand types " + this.getTypeName(expression1) + " and " + this.getTypeName(expression2) +
					" for binary operator 'LessThan(<)'. Expected IntegerType and IntegerType expressions.");
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if(!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			System.out.println("Bad operand types " + this.getTypeName(expression1) + " and " + this.getTypeName(expression2) +
					" for binary operator 'Plus(+)'. Expected IntegerType and IntegerType expressions.");
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if(!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			System.out.println("Bad operand types " + this.getTypeName(expression1) + " and " + this.getTypeName(expression2) +
					" for binary operator 'Minus(-)'. Expected IntegerType and IntegerType expressions.");
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		Type expression1 = n.e1.accept(this);
		Type expression2 = n.e2.accept(this);
		if(!(expression1 instanceof IntegerType) || !(expression2 instanceof IntegerType)) {
			System.out.println("Bad operand types " + this.getTypeName(expression1) + " and " + this.getTypeName(expression2) +
					" for binary operator 'Times(*)'. Expected IntegerType and IntegerType expressions.");
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		Type array = n.e1.accept(this);
		Type index = n.e2.accept(this);
		if(!(array instanceof IntArrayType)) {
			System.out.println("IntArrayType required, but " + this.getTypeName(array) + " found.");
		}
		if(!(index instanceof IntegerType)) {
			System.out.println("Incompatible types: " + this.getTypeName(index) + " cannot be converted to IntegerType.");
		}
		return new IntegerType();
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		Type expression = n.e.accept(this);
		if(!(expression instanceof IntArrayType)) {
			System.out.println("IntArrayType required, but " + this.getTypeName(expression) + " found.");
		}
		return new IntegerType();
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		Type classType = n.e.accept(this);
		Type type = null;
		if(classType instanceof IdentifierType) {
			Method methodCall = this.symbolTable.getMethod(n.i.toString(), ((IdentifierType) classType).s);
			if(methodCall == null) {
				System.out.println("Cannot find symbol " + n.i.toString());
			} else {
				n.i.accept(this);
				type = methodCall.type();
				for (int i = 0; i < n.el.size(); i++) {
					if(!this.symbolTable.compareTypes(n.el.elementAt(i).accept(this),methodCall.getParamAt(i).type())) {
						System.out.println("Incompatible types: " + this.getTypeName(n.el.elementAt(i).accept(this)) + 
								" cannot be converted to " + this.getTypeName(methodCall.getParamAt(i).type()) + ". Some messages may been simplified.");
						i = n.el.size();
					}
				}
			}
		} else {
			System.out.println("IdentifierType required, but " + this.getTypeName(classType) + " found. Cannot find class.");
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
		if(identifierExp == null) {
			System.out.println("Cannot find symbol "+n.s);
		}
		return identifierExp;
	}

	public Type visit(This n) {
		return this.currClass.type();
	}

	// Exp e;
	public Type visit(NewArray n) {
		Type expression = n.e.accept(this);
		if(!(expression instanceof IntegerType)) {
			System.out.println("Incompatible types: " + this.getTypeName(expression) + " cannot be converted to IntegerType.");
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
		if(!(expression instanceof BooleanType)) {
			System.out.println("Bad operand type " + this.getTypeName(expression) + " for unary operator 'Not(!)'. Expected BooleanType expression.");
		}
		return new BooleanType();
	}

	// String s;
	public Type visit(Identifier n) {
		if(this.fromVariable) {
			Type identifier = this.symbolTable.getVarType(this.currMethod, this.currClass, n.toString());
			if(identifier == null) {
				System.out.println("Cannot find symbol " + n.toString() + ".");
			}
			return identifier;
		} else {
			if(this.symbolTable.containsClass(n.toString())) {
				return this.symbolTable.getClass(n.toString()).type();
			}
		}
		return new IdentifierType(n.toString());
	}
	
	public String getTypeName(Type t) {
		if(t instanceof BooleanType) return "BooleanType";
		else if(t instanceof IdentifierType) return ((IdentifierType)t).s;
		else if(t instanceof IntArrayType) return "IntArrayType";
		else if(t instanceof IntegerType) return "IntegerType";
		else return "Null";
	}
}
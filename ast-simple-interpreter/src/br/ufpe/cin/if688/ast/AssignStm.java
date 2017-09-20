package br.ufpe.cin.if688.ast;

import br.ufpe.cin.if688.visitor.IVisitor;

public class AssignStm extends Stm {

	// x = y
	// x = 2
	String id;
	Exp exp;

	public Exp getExp() {
		return exp;
	}

	public String getId() {
		return id;
	}

	public AssignStm(String i, Exp e) {
		id = i;
		exp = e;
	}

	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
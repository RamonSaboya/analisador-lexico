package br.ufpe.cin.if688.ast;

import br.ufpe.cin.if688.visitor.IVisitor;

public class PrintStm extends Stm {
	// print(1,2,3,4)
	ExpList exps;

	public PrintStm(ExpList e) {
		exps = e;
	}

	public ExpList getExps() {
		return exps;
	}

	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
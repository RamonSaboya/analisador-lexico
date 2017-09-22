package br.ufpe.cin.if688.visitor;

import br.ufpe.cin.if688.ast.AssignStm;
import br.ufpe.cin.if688.ast.CompoundStm;
import br.ufpe.cin.if688.ast.EseqExp;
import br.ufpe.cin.if688.ast.Exp;
import br.ufpe.cin.if688.ast.ExpList;
import br.ufpe.cin.if688.ast.IdExp;
import br.ufpe.cin.if688.ast.LastExpList;
import br.ufpe.cin.if688.ast.NumExp;
import br.ufpe.cin.if688.ast.OpExp;
import br.ufpe.cin.if688.ast.PairExpList;
import br.ufpe.cin.if688.ast.PrintStm;
import br.ufpe.cin.if688.ast.Stm;
import br.ufpe.cin.if688.symboltable.IntAndTable;
import br.ufpe.cin.if688.symboltable.Table;

public class IntAndTableVisitor implements IVisitor<IntAndTable> {

	private Table t;

	public IntAndTableVisitor(Table t) {
		this.t = t;
	}

	@Override
	public IntAndTable visit(Stm s) {
		return null;
	}

	@Override
	public IntAndTable visit(AssignStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(CompoundStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(PrintStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(Exp e) {
		return e.accept(this);
	}

	@Override
	public IntAndTable visit(EseqExp e) {
		Stm stm = e.getStm();
		Exp exp = e.getExp();

		Table table = new Interpreter(t).visit(stm);
		this.t = table;

		return exp.accept(this);
	}

	@Override
	public IntAndTable visit(IdExp e) {
		String id = e.getId();

		int result = lookup(this.t, id);

		return new IntAndTable(result, this.t);
	}

	@Override
	public IntAndTable visit(NumExp e) {
		int num = e.getNum();

		return new IntAndTable(num, this.t);
	}

	@Override
	public IntAndTable visit(OpExp e) {
		Exp left = e.getLeft();
		int op = e.getOper();
		Exp right = e.getRight();

		int leftValue = left.accept(this).result;
		int rightValue = right.accept(this).result;
		int result = 0;

		switch (op) {
		case OpExp.Plus:
			result = leftValue + rightValue;
			break;
		case OpExp.Minus:
			result = leftValue - rightValue;
			break;
		case OpExp.Times:
			result = leftValue * rightValue;
			break;
		case OpExp.Div:
			result = leftValue / rightValue;
			break;
		}

		return new IntAndTable(result, t);
	}

	@Override
	public IntAndTable visit(ExpList el) {
		return null;
	}

	@Override
	public IntAndTable visit(PairExpList el) {
		return null;
	}

	@Override
	public IntAndTable visit(LastExpList el) {
		return null;
	}

	private int lookup(Table t, String key) {
		if (t.id == key) {
			return t.value;
		} else {
			return lookup(t.tail, key);
		}
	}

}

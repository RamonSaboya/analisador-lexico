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

public class Interpreter implements IVisitor<Table> {

	// a=8;b=80;a=7;
	// a->7 ==> b->80 ==> a->8 ==> NIL
	private Table t;

	public Interpreter(Table t) {
		this.t = t;
	}

	@Override
	public Table visit(Stm s) {
		return interpStm(s, null);
	}

	@Override
	public Table visit(AssignStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(CompoundStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(PrintStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(Exp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(EseqExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(IdExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(NumExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(OpExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(ExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(PairExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(LastExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	static void interp(Stm s) {
		/* you write this part */
		interpStm(s, null);
	}

	static Table interpStm(Stm s, Table t) {
		if (s.getClass() == CompoundStm.class) {
			Table cs1 = interpStm(((CompoundStm) s).getStm1(), t);
			return interpStm(((CompoundStm) s).getStm2(), cs1);
		} else if (s.getClass() == AssignStm.class) {
			IntAndTable as1 = interpExp(((AssignStm) s).getExp(), t);
			return new Table(((AssignStm) s).getId(), as1.result, as1.table);
		} else if (s.getClass() == PrintStm.class) {
			return print(((PrintStm) s).getExps(), t);
		}

		return null;
	}

	static Table print(ExpList e, Table t) {
		if (e.getClass() == PairExpList.class) {
			IntAndTable p1 = interpExp(((PairExpList) e).getHead(), t);
			System.out.print(p1.result + " ");
			return print(((PairExpList) e).getTail(), p1.table);
		} else {
			IntAndTable p2 = interpExp(((LastExpList) e).getHead(), t);
			System.out.println(p2.result + " ");
			return p2.table;
		}
	}

	static IntAndTable interpExpList(ExpList e, Table t) {
		if (e.getClass() == PairExpList.class) {
			IntAndTable pe1 = interpExp(((PairExpList) e).getHead(), t);
			return interpExpList(((PairExpList) e).getTail(), pe1.table);
		} else if (e.getClass() == LastExpList.class) {
			return interpExp(((LastExpList) e).getHead(), t);
		}

		// this shouldn't happen...
		return null;
	}

	static IntAndTable interpExp(Exp e, Table t) {
		if (e.getClass() == IdExp.class) {
			return new IntAndTable(lookup(t, ((IdExp) e).getId()), t);
		} else if (e.getClass() == NumExp.class) {
			return new IntAndTable(((NumExp) e).getNum(), t);
		} else if (e.getClass() == OpExp.class) {
			IntAndTable oe1 = interpExp(((OpExp) e).getLeft(), t);
			IntAndTable oe2 = interpExp(((OpExp) e).getRight(), oe1.table);
			switch (((OpExp) e).getOper()) {
			case 1:
				return new IntAndTable(oe1.result + oe2.result, oe2.table);
			case 2:
				return new IntAndTable(oe1.result - oe2.result, oe2.table);
			case 3:
				return new IntAndTable(oe1.result * oe2.result, oe2.table);
			case 4:
				return new IntAndTable(oe1.result / oe2.result, oe2.table);
			}
		} else if (e.getClass() == EseqExp.class) {
			Table es1 = interpStm(((EseqExp) e).getStm(), t);
			return interpExp(((EseqExp) e).getExp(), es1);
		}

		return null;
	}

	static int lookup(Table t, String key) {
		if (t.id == key) {
			return t.value;
		} else {
			return lookup(t.tail, key);
		}
	}

}

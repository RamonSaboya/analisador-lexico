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

public class MaxArgsVisitor implements IVisitor<Integer> {
	
	@Override
	public Integer visit(Stm s) {
		return s.accept(this);
	}

	@Override
	public Integer visit(AssignStm s) {
		Exp exp = s.getExp();
		
		return Math.max(1, exp.accept(this));
	}

	@Override
	public Integer visit(CompoundStm s) {
		Stm stm1 = s.getStm1();
		Stm stm2 = s.getStm2();
		
		return Math.max(stm1.accept(this), stm2.accept(this));
	}

	@Override
	public Integer visit(PrintStm s) {
		ExpList expList = s.getExps();
		
		return expList.accept(this);
	}

	@Override
	public Integer visit(Exp e) {
		return e.accept(this);
	}

	@Override
	public Integer visit(EseqExp e) {
		Exp exp = e.getExp();
		Stm stm = e.getStm();
		
		return Math.max(exp.accept(this), stm.accept(this));
	}

	@Override
	public Integer visit(IdExp e) {
		return 1;
	}

	@Override
	public Integer visit(NumExp e) {
		return 1;
	}

	@Override
	public Integer visit(OpExp e) {
		return 2;
	}

	@Override
	public Integer visit(ExpList el) {
		return el.accept(this);
	}

	@Override
	public Integer visit(PairExpList el) {
		Exp exp = el.getHead();
		ExpList expList = el.getTail();
		
		return Math.max(exp.accept(this), expList.accept(this));
	}

	@Override
	public Integer visit(LastExpList el) {
		Exp exp = el.getHead();
		
		return exp.accept(this);
	}
	

}

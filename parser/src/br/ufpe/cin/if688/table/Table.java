package br.ufpe.cin.if688.table;

import br.ufpe.cin.if688.parsing.analysis.*;
import br.ufpe.cin.if688.parsing.grammar.*;
import java.util.*;

public final class Table {

	private Table() {
	}

	public static Map<LL1Key, List<GeneralSymbol>> createTable(Grammar g) throws NotLL1Exception {
        if (g == null) {
        	throw new NullPointerException();
        }

        Map<Nonterminal, Set<GeneralSymbol>> first = SetGenerator.getFirst(g);
        Map<Nonterminal, Set<GeneralSymbol>> follow = SetGenerator.getFollow(g, first);

        Map<LL1Key, List<GeneralSymbol>> parsingTable = new HashMap<LL1Key, List<GeneralSymbol>>();

        Map<Nonterminal, Map<Terminal, LL1Key>> mapping = new HashMap<Nonterminal, Map<Terminal, LL1Key>>();
        for(Nonterminal nonterminal : g.getNonterminals()) {
        	mapping.put(nonterminal, new HashMap<Terminal, LL1Key>());
        	
        	for(Terminal terminal : g.getTerminals()) {
        		LL1Key key = new LL1Key(nonterminal, terminal);
        		
        		Map<Terminal, LL1Key> row = new HashMap<Terminal, LL1Key>();
        		row.put(terminal, key);
        	}
        }
        
        for(Production production : g.getProductions()) {
        	Nonterminal nonterminal = production.getNonterminal();
        	
        	List<GeneralSymbol> symbols = production.getProduction();
        	
        	for(int c = 0; c < symbols.size(); c++) {
        		GeneralSymbol firstSymbol = symbols.get(c);
        		
        		if(firstSymbol instanceof Terminal) {
            		Map<Terminal, LL1Key> row = mapping.get(nonterminal);
        			parsingTable.put(row.get(firstSymbol), symbols);
        		} else if (firstSymbol instanceof Nonterminal) {
        			
        		}
        	}
        }
        
        return parsingTable;
    }
}

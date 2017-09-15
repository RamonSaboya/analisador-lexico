package br.ufpe.cin.if688.parsing.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.ufpe.cin.if688.parsing.grammar.Grammar;
import br.ufpe.cin.if688.parsing.grammar.Nonterminal;
import br.ufpe.cin.if688.parsing.grammar.Production;
import br.ufpe.cin.if688.parsing.grammar.Terminal;

public final class SetGenerator {

	private static Map<Nonterminal, Set<GeneralSymbol>> finalFirstSet;

	public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
		if (g == null) {
			throw new NullPointerException("g nao pode ser nula.");
		}

		Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);

		Map<Nonterminal, List<Production>> nonterminalToProductions = new HashMap<Nonterminal, List<Production>>();

		for (Production production : g.getProductions()) {
			Nonterminal nonterminal = production.getNonterminal();

			if (!nonterminalToProductions.containsKey(nonterminal)) {
				nonterminalToProductions.put(nonterminal, new ArrayList<Production>());
			}

			List<Production> productions = nonterminalToProductions.get(nonterminal);

			productions.add(production);
		}

		finalFirstSet = new HashMap<Nonterminal, Set<GeneralSymbol>>();

		for (Nonterminal nonterminal : g.getNonterminals()) {
			first.put(nonterminal, getFirstSet(nonterminal, nonterminalToProductions));

			finalFirstSet.put(nonterminal, first.get(nonterminal));
		}

		return first;
	}

	private static Set<GeneralSymbol> getFirstSet(Nonterminal nonterminal,
			Map<Nonterminal, List<Production>> nonterminalToProductions) {
		Set<GeneralSymbol> first = new HashSet<GeneralSymbol>();

		List<Production> productions = nonterminalToProductions.get(nonterminal);

		for (Production production : productions) {
			List<GeneralSymbol> productionSymbols = production.getProduction();

			int size = productionSymbols.size();

			Set<GeneralSymbol> internalFirstSet = new HashSet<GeneralSymbol>();

			boolean epsilonPresent = false;
			boolean jump = false;

			for (int c = 0; c < size && !jump; c++) {
				GeneralSymbol generalSymbol = productionSymbols.get(c);

				if (generalSymbol == SpecialSymbol.EPSILON) {
					first.add(generalSymbol);

					jump = true;
				} else if (generalSymbol instanceof Terminal) {
					first.add(generalSymbol);

					jump = true;
				} else if (generalSymbol instanceof Nonterminal) {
					Nonterminal internalNonterminal = (Nonterminal) generalSymbol;

					Set<GeneralSymbol> nonterminalFirstSet;
					if (finalFirstSet.containsKey(internalNonterminal)) {
						nonterminalFirstSet = finalFirstSet.get(internalNonterminal);
					} else {
						nonterminalFirstSet = getFirstSet(internalNonterminal, nonterminalToProductions);
					}

					internalFirstSet.addAll(nonterminalFirstSet);

					if (!nonterminalFirstSet.contains(SpecialSymbol.EPSILON)) {
						jump = true;
					} else if (c + 1 == size) {
						epsilonPresent = true;
					}
				}
			}

			if (!epsilonPresent) {
				internalFirstSet.remove(SpecialSymbol.EPSILON);
			}

			first.addAll(internalFirstSet);
		}

		return first;
	}

	public static Map<Nonterminal, Set<GeneralSymbol>> getFollow(Grammar g,
			Map<Nonterminal, Set<GeneralSymbol>> first) {
		if (g == null || first == null) {
			throw new NullPointerException();
		}

		Map<Nonterminal, Set<GeneralSymbol>> follow = initializeNonterminalMapping(g);

		Nonterminal startSymbol = g.getStartSymbol();
		Set<GeneralSymbol> startFollow = follow.get(startSymbol);
		startFollow.add(SpecialSymbol.EOF);

		Map<Nonterminal, Set<GeneralSymbol>> copy = null;
		
		boolean equals = true;
		
		Set<GeneralSymbol> trailer = new HashSet<GeneralSymbol>();
		while(equals) {
			copy = new HashMap<Nonterminal, Set<GeneralSymbol>>();
			for(Entry<Nonterminal, Set<GeneralSymbol>> entry : follow.entrySet()) {
				Set<GeneralSymbol> symbolsSet = new HashSet<GeneralSymbol>();
				for(GeneralSymbol setSymbol : entry.getValue()) {
					symbolsSet.add(setSymbol);
				}
				copy.put(entry.getKey(), symbolsSet);
			}
			
			for(Production production : g.getProductions()) {
				Nonterminal nonterminal = production.getNonterminal();
				
				Set<GeneralSymbol> nonterminalFollow = follow.get(nonterminal);
				
				trailer.addAll(nonterminalFollow);
				
				List<GeneralSymbol> symbols = production.getProduction();
				for(int c = symbols.size() - 1; c >= 0; c--) {
					GeneralSymbol symbol = symbols.get(c);
					
					if(symbol instanceof Nonterminal) {
						nonterminalFollow.addAll(trailer);
						
						if(nonterminalFollow.contains(SpecialSymbol.EPSILON)) {
							if(trailer.contains(SpecialSymbol.EPSILON)) {
								trailer.addAll(nonterminalFollow);
							} else {
								trailer.addAll(nonterminalFollow);
								trailer.remove(SpecialSymbol.EPSILON);
							}
						} else {
							trailer.clear();
							trailer.addAll(nonterminalFollow);
						}
					} else {
						trailer.addAll(nonterminalFollow);
					}
				}
			}

			for(Entry<Nonterminal, Set<GeneralSymbol>> entry : follow.entrySet()) {
				if(!copy.containsKey(entry.getKey())) {
					equals = false;
				} else {
					for(GeneralSymbol setSymbol : entry.getValue()) {
						if(!copy.get(entry.getKey()).contains(setSymbol)) {
							equals = false;
						}
					}
				}
			}
		}

		return follow;
	}

	// método para inicializar mapeamento nãoterminais -> conjunto de símbolos
	private static Map<Nonterminal, Set<GeneralSymbol>> initializeNonterminalMapping(Grammar g) {
		Map<Nonterminal, Set<GeneralSymbol>> result = new HashMap<Nonterminal, Set<GeneralSymbol>>();

		for (Nonterminal nt : g.getNonterminals()) {
			result.put(nt, new HashSet<GeneralSymbol>());
		}

		return result;
	}

}

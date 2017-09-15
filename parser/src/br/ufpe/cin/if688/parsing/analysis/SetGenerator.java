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

					Set<GeneralSymbol> nonterminalFirstSet = new HashSet<GeneralSymbol>();
					if (finalFirstSet.containsKey(internalNonterminal)) {
						nonterminalFirstSet.addAll(finalFirstSet.get(internalNonterminal));
					} else {
						nonterminalFirstSet.addAll(getFirstSet(internalNonterminal, nonterminalToProductions));
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

		Set<GeneralSymbol> trailer = new HashSet<GeneralSymbol>();
		while (!follow.equals(copy)) {
			copy = new HashMap<Nonterminal, Set<GeneralSymbol>>();
			for (Entry<Nonterminal, Set<GeneralSymbol>> entry : follow.entrySet()) {
				copy.put(entry.getKey(), new HashSet<GeneralSymbol>(entry.getValue()));
			}

			for (Production production : g.getProductions()) {
				Nonterminal nonterminal = production.getNonterminal();

				Set<GeneralSymbol> nonterminalFollow = follow.get(nonterminal);

				Set<GeneralSymbol> symbolFirst;
				Set<GeneralSymbol> symbolFollow;

				trailer = new HashSet<GeneralSymbol>();
				trailer.addAll(nonterminalFollow);

				List<GeneralSymbol> symbols = production.getProduction();
				for (int c = symbols.size() - 1; c >= 0; c--) {
					GeneralSymbol symbol = symbols.get(c);

					symbolFirst = first.get(symbol);
					symbolFollow = follow.get(symbol);

					if (symbol instanceof Nonterminal) {
						symbolFollow.addAll(trailer);

						if (symbolFirst.contains(SpecialSymbol.EPSILON)) {
							if (trailer.contains(SpecialSymbol.EPSILON)) {
								trailer.addAll(symbolFirst);
							} else {
								trailer.addAll(symbolFirst);
								trailer.remove(SpecialSymbol.EPSILON);
							}
						} else {
							trailer = new HashSet<GeneralSymbol>();
							trailer.addAll(symbolFirst);
						}
					} else {
						trailer = new HashSet<GeneralSymbol>();
						trailer.add(symbol);
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

package br.ufpe.cin.if688.parsing.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ufpe.cin.if688.parsing.grammar.Grammar;
import br.ufpe.cin.if688.parsing.grammar.Nonterminal;
import br.ufpe.cin.if688.parsing.grammar.Production;
import br.ufpe.cin.if688.parsing.grammar.Terminal;

public final class SetGenerator {

	private static Map<Nonterminal, Set<GeneralSymbol>> finalFirstSet;

	private static Map<Nonterminal, List<Production>> getNonterminalToProductions(Grammar g) {
		Map<Nonterminal, List<Production>> nonterminalToProductions = new HashMap<Nonterminal, List<Production>>();

		for (Production production : g.getProductions()) {
			Nonterminal nonterminal = production.getNonterminal();

			if (!nonterminalToProductions.containsKey(nonterminal)) {
				nonterminalToProductions.put(nonterminal, new ArrayList<Production>());
			}

			List<Production> productions = nonterminalToProductions.get(nonterminal);

			productions.add(production);
		}

		return nonterminalToProductions;
	}

	public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
		if (g == null) {
			throw new NullPointerException("g nao pode ser nula.");
		}

		Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);

		Map<Nonterminal, List<Production>> nonterminalToProductions = getNonterminalToProductions(g);

		finalFirstSet = new HashMap<Nonterminal, Set<GeneralSymbol>>();

		for (Nonterminal nonterminal : g.getNonterminals()) {
			first.put(nonterminal, getFirstSet(nonterminal, nonterminalToProductions));

			finalFirstSet.put(nonterminal, first.get(nonterminal));
		}

		return first;
	}

	private static Set<GeneralSymbol> getFirstSet(Nonterminal nonterminal, Map<Nonterminal, List<Production>> nonterminalToProductions) {
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

	public static Map<Nonterminal, Set<GeneralSymbol>> getFollow(Grammar g, Map<Nonterminal, Set<GeneralSymbol>> first) {
		if (g == null || first == null) {
			throw new NullPointerException();
		}

		Map<Nonterminal, Set<GeneralSymbol>> follow = initializeNonterminalMapping(g);

		Map<Nonterminal, List<Production>> nonterminalToProductions = getNonterminalToProductions(g);

		Nonterminal startSymbol = g.getStartSymbol();

		Set<GeneralSymbol> startFollow = follow.get(startSymbol);
		startFollow.add(SpecialSymbol.EOF);

		getFollowSet(startSymbol, follow, nonterminalToProductions, first);

		return follow;
	}

	private static void getFollowSet(Nonterminal nonterminal, Map<Nonterminal, Set<GeneralSymbol>> follow,
			Map<Nonterminal, List<Production>> nonterminalToProductions, Map<Nonterminal, Set<GeneralSymbol>> first) {
		List<Production> productions = nonterminalToProductions.get(nonterminal);

		for (Production production : productions) {
			List<GeneralSymbol> productionSymbols = production.getProduction();

			int size = productionSymbols.size();

			GeneralSymbol last = null;

			for (int c = size - 1; c >= 0; c--) {
				GeneralSymbol generalSymbol = productionSymbols.get(c);

				if (generalSymbol instanceof Terminal) {
					last = generalSymbol;
				} else if (generalSymbol instanceof Nonterminal) {
					Set<GeneralSymbol> symbolFollow = follow.get(generalSymbol);

					if (last == null) {
						symbolFollow.addAll(follow.get(nonterminal));
					} else if (last instanceof Terminal) {
						symbolFollow.add(last);
					} else if (last instanceof Nonterminal) {
						Set<GeneralSymbol> lastFirst = first.get(last);

						if (lastFirst.contains(SpecialSymbol.EPSILON)) {
							symbolFollow.addAll(follow.get(nonterminal));
						} else {
							if (symbolFollow.contains(SpecialSymbol.EPSILON)) {
								symbolFollow.addAll(lastFirst);
							} else {
								symbolFollow.addAll(lastFirst);
								symbolFollow.remove(SpecialSymbol.EPSILON);
							}
						}
					}

					if (generalSymbol != nonterminal) {
						getFollowSet((Nonterminal) generalSymbol, follow, nonterminalToProductions, first);
					}

					last = generalSymbol;
				}
			}
		}
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

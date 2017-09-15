package br.ufpe.cin.if688.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.ufpe.cin.if688.parsing.analysis.GeneralSymbol;
import br.ufpe.cin.if688.parsing.analysis.SetGenerator;
import br.ufpe.cin.if688.parsing.analysis.SpecialSymbol;
import br.ufpe.cin.if688.parsing.grammar.Grammar;
import br.ufpe.cin.if688.parsing.grammar.Nonterminal;
import br.ufpe.cin.if688.parsing.grammar.Production;
import br.ufpe.cin.if688.parsing.grammar.Terminal;

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

		Map<Nonterminal, Map<GeneralSymbol, LL1Key>> mapping = new HashMap<Nonterminal, Map<GeneralSymbol, LL1Key>>();
		for (Nonterminal nonterminal : g.getNonterminals()) {
			mapping.put(nonterminal, new HashMap<GeneralSymbol, LL1Key>());

			for (Terminal terminal : g.getTerminals()) {
				LL1Key key = new LL1Key(nonterminal, terminal);

				Map<GeneralSymbol, LL1Key> row = mapping.get(nonterminal);
				row.put(terminal, key);
			}

			LL1Key key = new LL1Key(nonterminal, SpecialSymbol.EOF);

			Map<GeneralSymbol, LL1Key> row = mapping.get(nonterminal);
			row.put(SpecialSymbol.EOF, key);
		}

		for (Production production : g.getProductions()) {
			Nonterminal nonterminal = production.getNonterminal();

			Map<GeneralSymbol, LL1Key> row = mapping.get(nonterminal);

			List<GeneralSymbol> symbols = production.getProduction();

			Map<LL1Key, GeneralSymbol> cells = new HashMap<LL1Key, GeneralSymbol>();

			for (int c = 0; c < symbols.size(); c++) {
				GeneralSymbol symbol = symbols.get(c);

				if (symbol instanceof Terminal) {
					LL1Key cell = row.get(symbol);

					cells.put(cell, symbol);

					break;
				} else if (symbol instanceof Nonterminal) {
					for (GeneralSymbol symbolFirst : first.get(symbol)) {
						if (symbolFirst != SpecialSymbol.EPSILON) {
							LL1Key cell = row.get(symbolFirst);

							cells.put(cell, symbolFirst);
						} else if (c + 1 == symbols.size()) {
							for (GeneralSymbol symbolFollow : follow.get(nonterminal)) {
								LL1Key cell = row.get(symbolFollow);

								cells.put(cell, symbolFollow);
							}
						}
					}

					if (!first.get(symbol).contains(SpecialSymbol.EPSILON)) {
						break;
					}
				} else {
					for (GeneralSymbol symbolFollow : follow.get(nonterminal)) {
						LL1Key cell = row.get(symbolFollow);

						cells.put(cell, symbolFollow);
					}
				}
			}

			for (Entry<LL1Key, GeneralSymbol> entry : cells.entrySet()) {
				LL1Key cell = entry.getKey();
				GeneralSymbol symbol = entry.getValue();

				if (parsingTable.containsKey(cell)) {
					Production production2 = new Production(nonterminal, parsingTable.get(cell));

					throw new NotLL1Exception(String.format("Table cell (%s,%s) has two productions. (%s) (%s)",
							nonterminal, symbol, production, production2));
				} else {
					parsingTable.put(row.get(symbol), symbols);
				}
			}
		}

		/*System.out.printf("%-20s", "");
		for (Terminal terminal : g.getTerminals()) {
			System.out.printf("%-20s", terminal);
		}
		System.out.printf("$\n");

		for (Nonterminal nonterminal : g.getNonterminals()) {
			System.out.printf("%-20s", nonterminal);
			for (Terminal terminal : g.getTerminals()) {
				if (mapping.containsKey(nonterminal) && mapping.get(nonterminal).containsKey(terminal)) {
					if (parsingTable.get(mapping.get(nonterminal).get(terminal)) != null) {
						System.out.printf("%-20s", parsingTable.get(mapping.get(nonterminal).get(terminal)));
					} else {
						System.out.printf("%-20s", "");
					}
				} else {
					System.out.printf("%-20s", "");
				}
			}
			if (mapping.containsKey(nonterminal) && mapping.get(nonterminal).containsKey(SpecialSymbol.EOF)) {
				if (parsingTable.get(mapping.get(nonterminal).get(SpecialSymbol.EOF)) != null) {
					System.out.printf("%-20s", parsingTable.get(mapping.get(nonterminal).get(SpecialSymbol.EOF)));
				} else {
					System.out.printf("%-20s", "");
				}
			} else {
				System.out.printf("%-20s", "");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();*/

		return parsingTable;
	}
}

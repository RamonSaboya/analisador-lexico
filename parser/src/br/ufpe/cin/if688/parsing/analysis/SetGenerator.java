package br.ufpe.cin.if688.parsing.analysis;

import java.util.Collections;
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

	public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
		if (g == null) {
			throw new NullPointerException("g nao pode ser nula.");
		}

		Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);

		// Mapa para auxiliar na iteração (Todos os tipos de símbolo precisam ter um conjunto FIRST, mesmo que unitário e próprio)
		Map<GeneralSymbol, Set<GeneralSymbol>> symbolFirst = new HashMap<GeneralSymbol, Set<GeneralSymbol>>();

		// Define todos os terminais e símbolos especiais como conjuntos unitários e próprios
		for (Terminal terminal : g.getTerminals()) {
			symbolFirst.put(terminal, Collections.singleton(terminal));
		}
		symbolFirst.put(SpecialSymbol.EOF, Collections.singleton(SpecialSymbol.EOF));
		symbolFirst.put(SpecialSymbol.EPSILON, Collections.singleton(SpecialSymbol.EPSILON));

		// Inicia todos os conjuntos dos não-terminais como vazios
		for (Nonterminal nonterminal : g.getNonterminals()) {
			symbolFirst.put(nonterminal, new HashSet<GeneralSymbol>());
		}

		// Auxilio na verificação de mudança dos conjuntos de FIRST
		Map<GeneralSymbol, Set<GeneralSymbol>> copy = null;

		// Auxiliares de verificação em cada produção
		Set<GeneralSymbol> rhs;
		int i, k;

		// Enquanto houver mudança nos conjuntos de FIRST
		while (!symbolFirst.equals(copy)) {
			// Copia o mapa de FIRST para o auxiliar
			copy = new HashMap<GeneralSymbol, Set<GeneralSymbol>>();
			for (Entry<GeneralSymbol, Set<GeneralSymbol>> entry : symbolFirst.entrySet()) {
				copy.put(entry.getKey(), new HashSet<GeneralSymbol>(entry.getValue()));
			}

			// Verifica se é possível atualizar algum conjunto, baseado em cada produção
			for (Production production : g.getProductions()) {
				Nonterminal nonterminal = production.getNonterminal();

				List<GeneralSymbol> symbols = production.getProduction(); // String de símbolos da produção
				GeneralSymbol firstSymbol = symbols.get(0);

				rhs = new HashSet<GeneralSymbol>();
				i = 0;
				k = symbols.size() - 1;

				// Terminais ou não-terminais
				if (firstSymbol != SpecialSymbol.EPSILON) {
					rhs.addAll(symbolFirst.get(firstSymbol));
					rhs.remove(SpecialSymbol.EPSILON);

					while (symbolFirst.get(symbols.get(i)).contains(SpecialSymbol.EPSILON) && i <= k - 1) {
						if (rhs.contains(SpecialSymbol.EPSILON)) { // Ja tinha EPSILON, então sem preocupação
							rhs.addAll(symbolFirst.get(symbols.get(i + 1)));
						} else { // Não tinha EPSILON, apenas remover para garantir
							rhs.addAll(symbolFirst.get(symbols.get(i + 1)));
							rhs.remove(SpecialSymbol.EPSILON);
						}

						i++;
					}
				}

				// Se o FIRST do último símbolo tiver EPSILON, o FIRST terá EPSILON
				if (i == k && symbolFirst.get(symbols.get(k)).contains(SpecialSymbol.EPSILON)) {
					rhs.add(SpecialSymbol.EPSILON);
				}

				// Adiciona ao mapeamento
				Set<GeneralSymbol> nonterminalFirst = symbolFirst.get(nonterminal);
				nonterminalFirst.addAll(rhs);
			}
		}

		// Põe os não-terminais e seus respectivos conjuntos FIRST no mapeamento final
		for (Entry<GeneralSymbol, Set<GeneralSymbol>> entry : symbolFirst.entrySet()) {
			GeneralSymbol symbol = entry.getKey();

			if (symbol instanceof Nonterminal) {
				first.put((Nonterminal) symbol, entry.getValue());
			}
		}

		return first;
	}

	public static Map<Nonterminal, Set<GeneralSymbol>> getFollow(Grammar g, Map<Nonterminal, Set<GeneralSymbol>> first) {
		if (g == null || first == null) {
			throw new NullPointerException();
		}

		Map<Nonterminal, Set<GeneralSymbol>> follow = initializeNonterminalMapping(g);

		// Adiciona o EOF ao conjunto FOLLOW do símbolo inicial da gramática
		Nonterminal startSymbol = g.getStartSymbol();
		Set<GeneralSymbol> startFollow = follow.get(startSymbol);
		startFollow.add(SpecialSymbol.EOF);

		// Auxilio na verificação de mudança dos conjuntos de FOLLOW
		Map<Nonterminal, Set<GeneralSymbol>> copy = null;

		// Auxiliar de verificação em cada produção
		Set<GeneralSymbol> trailer; // Conjunto de símbolos da iteração anterior (como é visto da direita para esquerda, será o FOLLOW)
		Set<GeneralSymbol> symbolFirst;
		Set<GeneralSymbol> symbolFollow;

		// Enquanto houver mudança nos conjuntos de FOLLOW
		while (!follow.equals(copy)) {
			// Copia o mapa de FOLLOW para o auxiliar
			copy = new HashMap<Nonterminal, Set<GeneralSymbol>>();
			for (Entry<Nonterminal, Set<GeneralSymbol>> entry : follow.entrySet()) {
				copy.put(entry.getKey(), new HashSet<GeneralSymbol>(entry.getValue()));
			}

			// Verifica se é possível atualizar algum conjunto, baseado em cada produção
			for (Production production : g.getProductions()) {
				Nonterminal nonterminal = production.getNonterminal();

				Set<GeneralSymbol> nonterminalFollow = follow.get(nonterminal);

				trailer = new HashSet<GeneralSymbol>();
				trailer.addAll(nonterminalFollow);

				// Verifica os símbolos, da direita para a esquerda
				List<GeneralSymbol> symbols = production.getProduction();
				for (int c = symbols.size() - 1; c >= 0; c--) {
					GeneralSymbol symbol = symbols.get(c);

					symbolFirst = first.get(symbol);
					symbolFollow = follow.get(symbol);

					if (symbol instanceof Nonterminal) {
						// Adiciona o conjunto de símbolos obtido na iteração anterior, que será o FOLLOW
						symbolFollow.addAll(trailer);

						if (symbolFirst.contains(SpecialSymbol.EPSILON)) { // Havendo EPSILON no conjunto de FIRST, acrescentá-lo ao TRAILER
							if (trailer.contains(SpecialSymbol.EPSILON)) {
								trailer.addAll(symbolFirst);
							} else {
								trailer.addAll(symbolFirst);
								trailer.remove(SpecialSymbol.EPSILON);
							}
						} else { // No caso negativo, definir o TRAILER como o FIRST
							trailer = new HashSet<GeneralSymbol>();
							trailer.addAll(symbolFirst);
						}
					} else { // Quando for um terminal ou símbolo especial,
						trailer = new HashSet<GeneralSymbol>();
						trailer.add(symbol);
					}
				}
			}
		}

		return follow;
	}

	// Método para inicializar mapeamento não-terminais -> conjunto de símbolos
	private static Map<Nonterminal, Set<GeneralSymbol>> initializeNonterminalMapping(Grammar g) {
		Map<Nonterminal, Set<GeneralSymbol>> result = new HashMap<Nonterminal, Set<GeneralSymbol>>();

		for (Nonterminal nt : g.getNonterminals()) {
			result.put(nt, new HashSet<GeneralSymbol>());
		}

		return result;
	}

}

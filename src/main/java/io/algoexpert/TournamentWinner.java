package io.algoexpert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TournamentWinner {
    public String tournamentWinner(
        ArrayList<ArrayList<String>> competitions,
        ArrayList<Integer> results
    ) {
        var counts = new HashMap<String, Integer>();

        for (var i = 0; i < competitions.size(); i++) {
            var competitors = competitions.get(i);
            var result = results.get(i);

            var winner = getWinnerTeam(competitors, result);
            var wins = counts.computeIfAbsent(winner, ignored -> 0);
            counts.put(winner, wins + 1);
        }

        return counts.entrySet().stream()
            .sorted(Comparator.comparing((Function<Map.Entry<String, Integer>, Integer>) Map.Entry::getValue).reversed())
            .map(Map.Entry::getKey)
            .limit(1)
            .findFirst()
            .orElse(null);
    }

    private static String getWinnerTeam(List<String> competitors, int result) {
        if (result == 1) {
            return competitors.get(0);
        } else {
            return competitors.get(1);
        }
    }
}

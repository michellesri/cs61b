package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {

    private Set<WorldState> processedWorldStates = new HashSet<>();
    private MinPQ<SearchNode> minPQ = new MinPQ<>(new SearchNode.SearchNodeComparator());
    private SearchNode winningNode;


    public Solver(WorldState initial) {
        // Constructor which solves the puzzle, computing
        // everything necessary for moves() and solution() to
        // not have to solve the problem again. Solves the
        // puzzle using the A* algorithm. Assumes a solution exists.

        SearchNode initialNode = new SearchNode(initial, 0, null);
        minPQ.insert(initialNode);

        SearchNode x = minPQ.delMin();
        processedWorldStates.add(x.worldState);

        while (!x.worldState.isGoal()) {
            for (WorldState neighborWorldState : x.worldState.neighbors()) {
                if (processedWorldStates.contains(neighborWorldState)) {
                    continue;
                }
                minPQ.insert(new SearchNode(neighborWorldState, x.numMovesToThisState + 1, x));
            }
            x = minPQ.delMin();
            processedWorldStates.add(x.worldState);
        }

        winningNode = x;
    }

    public int moves() {
        // Returns the minimum number of moves to solve the puzzle starting
        // at the initial WorldState.
        return winningNode.numMovesToThisState;

    }

    public Iterable<WorldState> solution() {
        // Returns a sequence of WorldStates from the initial WorldState
        // to the solution.

        List<WorldState> pathToVictoryReverse = new ArrayList<>();
        List<WorldState> pathToVictory = new ArrayList<>();

        SearchNode currentNode = winningNode;

        while (currentNode != null) {
            pathToVictoryReverse.add(currentNode.worldState);
            currentNode = currentNode.prevSearchNode;
        }

        int pathLength = pathToVictoryReverse.size();

        for (int i = pathLength - 1; i >= 0; i--) {
            pathToVictory.add(pathToVictoryReverse.get(i));
        }

        return pathToVictory;
    }
}

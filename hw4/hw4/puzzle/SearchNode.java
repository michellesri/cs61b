package hw4.puzzle;

import java.util.Comparator;

public class SearchNode {
    WorldState worldState;
    int numMovesToThisState;
    SearchNode prevSearchNode;

    public SearchNode(WorldState worldState, int numMovesToThisState, SearchNode prevSearchNode) {
        this.worldState = worldState;
        this.numMovesToThisState = numMovesToThisState;
        this.prevSearchNode = prevSearchNode;
    }

    public static class SearchNodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Priority = o1.worldState.estimatedDistanceToGoal() + o1.numMovesToThisState;
            int o2Priority = o2.worldState.estimatedDistanceToGoal() + o2.numMovesToThisState;

            if (o1Priority < o2Priority) {
                return -1;
            } else if (o1Priority == o2Priority){
                return 0;
            } else {
                return 1;
            }
        }
    }

}

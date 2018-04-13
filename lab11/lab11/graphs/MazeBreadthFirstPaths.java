package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    // Inherits public fields:
    private int s;
    private int t;
    private boolean targetFound = false;



    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()


        Queue<Integer> queue = new ArrayDeque<Integer>();

        queue.add(v);

        while(queue.peek() != null) {
            int removed = queue.remove();
            marked[removed] = true;
            announce();

            if (removed == t) {
                return;
            }
            for (int i : maze.adj(removed)) {
                if (!marked[i]) {
                    queue.add(i);
                    distTo[i] = distTo[removed] + 1;
                    edgeTo[i] = removed;

                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}


package byog.Core;

import java.io.Serializable;

public class Position implements Serializable {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position pos) {
        return (pos.x == this.x && pos.y == this.y);
    }
}

package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    int period;
    int state = 0;
    double result = -1;

    public SawToothGenerator(int period) {
        this.period = period;
    }

    private double normalize(int state) {
        return -1 + (state % period) / ((double) period / 2.0);
    }

    @Override
    //next generates next state
    // state is x axis
    // compute the sawtooth wave -> linear
    // instead of using sin what is f(x)
    // return value of next is y value
    public double next() {
        state = (state + 1);
        result = normalize(state);
        System.out.println(result);
        if (result == 1) {
            result = -1;
        }
        return result;
    }

}

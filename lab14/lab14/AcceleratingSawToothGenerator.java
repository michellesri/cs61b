package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    double period;
    int state = 0;
    double result;
    double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
    }

    private double normalize(int state) {
        return -1 + (state % period) / (period / 2.0);
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
            period = (int) (factor * period);
        }
        return result;
    }

}

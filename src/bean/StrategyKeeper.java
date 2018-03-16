package bean;

public class StrategyKeeper {
    private boolean stepStrategy;
    private int begin;
    private int end;
    private int defaultBegin;
    private int defaultEnd;
    private double[] signal;
    private int stepStrategyBegin;

    public boolean isStepStrategy() {
        return stepStrategy;
    }

    public void setStepStrategy(boolean stepStrategy) {
        this.stepStrategy = stepStrategy;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getDefaultBegin() {
        return defaultBegin;
    }

    public void setDefaultBegin(int defaultBegin) {
        this.defaultBegin = defaultBegin;
    }

    public int getDefaultEnd() {
        return defaultEnd;
    }

    public void setDefaultEnd(int defaultEnd) {
        this.defaultEnd = defaultEnd;
    }

    public double[] getSignal() {
        return signal;
    }

    public void setSignal(double[] signal) {
        this.signal = signal;
    }

    public int getStepStrategyBegin() {
        return stepStrategyBegin;
    }

    public void setStepStrategyBegin(int stepStrategyBegin) {
        this.stepStrategyBegin = stepStrategyBegin;
    }

    public void copySignal(SignalKeeper signalKeeper1, SignalKeeper signalKeeper2) {
        double signal2[]=new double[signalKeeper1.getSignal().length+(signalKeeper2.getSignal().length-1)*2];
        for(int i=0;i<signalKeeper2.getSignal().length;i++){
            signal2[i]=signalKeeper2.getSignal()[i];
        }

        this.signal=signal2;
    }
}

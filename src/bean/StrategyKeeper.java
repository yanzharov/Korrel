package bean;

public class StrategyKeeper {
    private boolean stepStrategy;
    private int begin;
    private int end;
    private int defaultBegin;
    private int defaultEnd;
    private double[] signal;

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

    public void copySignal(double[] originSignal) {
        signal=new double[originSignal.length];
        for(int i=0;i<signal.length-1;i++){
            signal[i]=originSignal[i];
        }
    }
}

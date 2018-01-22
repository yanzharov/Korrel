package bean;

public class StrategyKeeper {
    private boolean stepStrategy;
    private int begin;
    private int end;
    private int defaultBegin;
    private int defaultEnd;

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
}

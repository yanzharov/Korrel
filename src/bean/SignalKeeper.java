package bean;

public class SignalKeeper {
    private String type;
    private int amplitude;
    private int begin;
    private int end;
    private double[] signal;
    private int duration;
    private int step;
    private double[] originSignal;
    private boolean changed;
    private boolean moved;
    private double[] signalX;
    private double[] signalY;
    private int shiftBegin;
    private int shiftEnd;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
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

    public double[] getSignal() {
        return signal;
    }

    public void setSignal(double[] signal) {
        this.signal = signal;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public double[] getOriginSignal() {
        return originSignal;
    }

    public void setOriginSignal(double[] originSignal) {
        this.originSignal = originSignal;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public double[] getSignalX() {
        return signalX;
    }

    public void setSignalX(double[] signalX) {
        this.signalX = signalX;
    }

    public double[] getSignalY() {
        return signalY;
    }

    public void setSignalY(double[] signalY) {
        this.signalY = signalY;
    }

    public int getShiftBegin() {
        return shiftBegin;
    }

    public void setShiftBegin(int shiftBegin) {
        this.shiftBegin = shiftBegin;
    }

    public int getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(int shiftEnd) {
        this.shiftEnd = shiftEnd;
    }
}

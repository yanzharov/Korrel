package bean;

public class SignalKeeper {
    private String type;
    private int amplitude;
    private int begin;
    private int end;
    private int vertex;
    private double[] signal;
    private int duration;
    private int step;
    private double[] originSignal;

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

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
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

    public void changeOriginSignal(int step){
        int difference=step/getStep();
        signal=new double[originSignal.length/difference+originSignal.length%difference];
        int currentOriginDiscret=0;
        for(int i=0;i<signal.length;i++){
            signal[i]=originSignal[currentOriginDiscret];
            currentOriginDiscret+=difference;
        }
    }
}

package measures;

public class Result {
    private String measurename;
    private double measurevalue;

    public String getMeasurename() {
        return measurename;
    }

    public void setMeasurename(String measurename) {
        this.measurename = measurename;
    }

    public double getMeasurevalue() {
        return measurevalue;
    }

    public void setMeasurevalue(double measurevalue) {
        this.measurevalue = measurevalue;
    }

    public Result() {
        this.measurename = measurename;
        this.measurevalue = measurevalue;
    }

    @Override
    public String toString() {
        return "Result{" +
                "measurename='" + measurename + '\'' +
                ", measurevalue=" + measurevalue +
                '}';
    }
}

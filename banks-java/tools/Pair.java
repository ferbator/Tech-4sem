package tools;

public class Pair{
    private double sum;
    private double percentage;
    public Pair(double sum, double percentage){
        this.sum = sum;
        this.percentage = percentage;
    }
    public double getSum(){ return sum; }
    public double getPercentage(){ return percentage; }
    public void setSum(double sum){ this.sum = sum; }
    public void setPercentage(double percentage){ this.percentage = percentage; }
}

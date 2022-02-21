package accountServices;

import tools.Pair;

import java.util.ArrayList;

public class DepositAccountPercentage {
    private ArrayList<Pair> pairsSumAndPercent;

    public DepositAccountPercentage()
    {
        pairsSumAndPercent = new ArrayList<Pair>();
    }

    public void addParametersForDepositAccountBank(double sum, double percentage)
    {
        pairsSumAndPercent.add(new Pair(sum, percentage));
    }
    public ArrayList<Pair> getPairsSumAndPercent() {
        return pairsSumAndPercent;
    }
    public void setPairsSumAndPercent(ArrayList<Pair> value) {
        this.pairsSumAndPercent = value;
    }

}

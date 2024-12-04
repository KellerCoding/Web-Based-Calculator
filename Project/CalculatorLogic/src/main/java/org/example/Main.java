
import java.util.ArrayList;
import java.util.List;


/*
This code contains the mathematical computations for the sake of the web-based calculator.

 */
public class Main {
    static String emptyList = "valuesList parameter cannot be null or empty";
    static String insufficientValues = "numValues is too low (sample size must be >= 2, population size must be >= 1)";
    static String nonNumericalValues = "No input can contain any nonnumerical values";
    static String invalidDataInput = "Invalid data entry. Input appropriate data for function";

    CalculationResult result = new CalculationResult();

    //STANDARD DEVIATION BASED METHODS - START



    static CalculationResult computeSquareOfDifferences(List<Double> list, double mean, CalculationResult result) {
        double squareAccumulator=0.0;
        if (list.isEmpty()) result.calculationFailure(emptyList);

        for (Double aDouble : list) {
            double difference=0.0;
            difference = aDouble-mean;
            squareAccumulator+=(difference*difference);
        }

        result.setResult(squareAccumulator);
        return result;
    }

    private static Double computeVariance(double squareOfDifferences, int length){

        return squareOfDifferences/length;
    }

    private static void computeStandardDeviation(List<Double> list, boolean isPopulation, CalculationResult result){

        int length = list.size();
        if (!isPopulation) length = length - 1;
        if (length<1) {
            result.setIsSuccess(false);
            result.setError(emptyList);
        }

        double mean = computeMean(list).getResult();

        double squareOfDifferences = computeSquareOfDifferences(list, mean, result).getResult();
        double variance=computeVariance(squareOfDifferences,length);

        result.setResult(Math.sqrt(variance));
    }

    public static CalculationResult computeSampleStandardDeviation(List<Double> list){
        CalculationResult result = new CalculationResult();
        result.setOperation("sample standard deviation");
        if (list.isEmpty()){
            result.calculationFailure(emptyList);
        } else if (list.size()<2) {
            result.calculationFailure(insufficientValues);
        }
        computeStandardDeviation(list, false, result);
        return result;
    }

    public static CalculationResult computePopulationStandardDeviation(List<Double> list){
        CalculationResult result = new CalculationResult();
        result.setOperation("population standard deviation");

        if (list.isEmpty()){
            result.calculationFailure(emptyList);
        }

        computeStandardDeviation(list, true, result);

        return result;
    }

    //STANDARD DEVIATION BASED METHODS - END

    public static CalculationResult computeMean(List<Double> list){
        CalculationResult result = new CalculationResult();
        if (list.isEmpty()) {
            result.setError("valuesList parameter cannot be null or empty");
            result.setIsSuccess(false);
        }
        double sum=0.0;
        int length = list.size();

            for (double aDouble : list) {
                sum += aDouble;
            }

        result.setResult(sum/length);
        return result;
    }



    public static CalculationResult computeZScore(String data){
        CalculationResult result = new CalculationResult();
        threeParse(data, result);

        /*First indexed item is the data value for comparison
        Second indexed double is the mean of the dataset
        Third indexed double is the standard deviation of the dataset*/

        result.setOperation("(x-mean)/Std-Deviation");

        result.setResult((result.getResultArray()[0]-result.getResultArray()[1])/result.getResultArray()[2]);
        return result;
    }

    private static void threeParse(String data, CalculationResult result){
        String[] parsed = data.split(",");
        if (parsed.length!=3){
            result.calculationFailure(invalidDataInput);
            return;
        }
        double[] doubleList = new double[parsed.length];
        for (int i=0;i< parsed.length;i++){
            doubleList[i]=Double.parseDouble(parsed[i]);
        }
        result.setResultArray(doubleList);
    }


    public static CalculationResult computeSingleRegression(String[] data) {

        CalculationResult result = new CalculationResult();
        int count=0;
        if (data.length<1){
            result.calculationFailure(emptyList);
            return result;
        }
        for (int i=0;i< data.length;i++){
            if (data[i]==null){

            }
        }

        int length=data.length;
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        for (int i=0;i< length;i++){
            double[] xy = new double[2];
            String[] parsed=new String[2];
            if (data[i]==null){
                result.calculationFailure(invalidDataInput);
                parsed[0]="0";
                parsed[1]="0";
            } else {
                parsed = data[i].split(",");
            }
            xy[0]=Double.parseDouble(parsed[0]);
            xy[1]=Double.parseDouble(parsed[1]);
            xList.add(xy[0]);
            yList.add(xy[1]);


        }

        double xMean=computeMean(xList).getResult(),yMean=computeMean(yList).getResult();

        List<Double> xy = new ArrayList<>();
        List<Double> xSquared = new ArrayList<>();

        for (int i=0;i<length;i++){
            xy.add(xList.get(i)*yList.get(i));
            xSquared.add(xList.get(i)*xList.get(i));

        }

        double xSum=0,xSquaredSum=0,xySum=0,ySum=0;

        for (int i=0;i<length;i++){
            xSum+=xList.get(i);
            xSquaredSum+=xSquared.get(i);
            xySum+=xy.get(i);
            ySum+=yList.get(i);
        }

        double intercept=((ySum*xSquaredSum)-(xSum*xySum))/((length*xSquaredSum)-(xSum*xSum));
        double slope=(length*xySum-(xSum*ySum))/(length*(xSquaredSum)-(xSum*xSum));


        result.setResultString("y="+slope+"x+"+intercept);
        return result;
    }



    public static CalculationResult predictY(String data){

        CalculationResult result= new CalculationResult();
        threeParse(data, result);
        double y = (result.getResultArray()[0]*result.getResultArray()[1])+result.getResultArray()[2];

        result.setResultString("y="+y);
        return result;
    }
}

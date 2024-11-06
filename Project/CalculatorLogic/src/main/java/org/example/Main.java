
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
            result.setIsSuccess(false);
            result.setError(emptyList);
            return result;
        }
        computeStandardDeviation(list, false, result);
        return result;
    }

    public static CalculationResult computePopulationStandardDeviation(List<Double> list){
        CalculationResult result = new CalculationResult();
        result.setOperation("population standard deviation");

        if (list.isEmpty()){
            result.setIsSuccess(false);
            result.setError("valuesList parameter cannot be null or empty");
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



    public CalculationResult computeZScore(String data) throws Exception{
        CalculationResult result = new CalculationResult();
        double[] parsed = threeParse(data, result);

        /*First indexed item is the data value for comparison
        Second indexed double is the mean of the dataset
        Third indexed double is the standard deviation of the dataset*/

        result.setResult((parsed[0]-parsed[1])/parsed[2]);
        return result;
    }

    private double[] threeParse(String data, CalculationResult result){
        String[] parsed = data.split(",");
        if (parsed.length!=3){
            result.setError(invalidDataInput);
        }
        double[] doubleList = new double[3];
        for (int i=0;i< parsed.length;i++){
            doubleList[i]=Double.parseDouble(parsed[i]);
        }
        return doubleList;
    }

    private static double[][] multiParse(String[] data,CalculationResult result) {
        int length = data.length;
        double[][] parsed = new double[length][2];
        for (int i=0;i<length;i++){
            String[] xy=data[i].split(",");
            if (xy.length!=2) {
                result.setError(invalidDataInput);
                result.setIsSuccess(false);
            }
            parsed[i][0]=Double.parseDouble(xy[0]);
            parsed[i][1]=Double.parseDouble(xy[1]);
        }

        return parsed;
    }

    public static CalculationResult computeSingleRegression(String[] data) {
        int length=data.length;
        CalculationResult result = new CalculationResult();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        for (int i=0;i< length;i++){
            double[] xy = new double[2];
            String[] parsed = data[i].split(",");
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



    public CalculationResult predictY(String data){
        CalculationResult result= new CalculationResult();
        double[] parsed = threeParse(data, result);
        double y = (parsed[0]*parsed[1])+parsed[2];

        result.setResultString("y="+y);
        return result;
    }
}

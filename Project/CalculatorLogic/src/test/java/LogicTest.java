import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import Calc.*;
import static Calc.CalculatorMethods.*;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    static Exception emptyArray = new Exception("valuesList parameter cannot be null or empty");
    static Exception zeroValues = new Exception("numValues is too low (sample size must be >= 2, population size must be >= 1)");

    //computeSquareOfDifferences - Tests to complete 100% of functionality
    @Test
    void ComputeSquareOfDifferences_ReceiveNullList_ThrowException() {
        List<Double> list = new ArrayList<Double>();
        CalculationResult result = new CalculationResult();
        CalculatorMethods.computeSquareOfDifferences(list,5,result);
        assertAll(
                () -> assertFalse(result.getIsSuccess(), result.getError()),
                () -> assertEquals(0.0,result.getResult())
        );
    }

    @Test
    void ComputeSingleRegression_ReceiveValidInput_ReturnAnswer(){
        CalculationResult result;
        String[] data = new String[15];
        data[0]="1.47,52.21";
        data[1]="1.5,53.12";
        data[2]="1.52,54.48";
        data[3]="1.55,55.84";
        data[4]="1.57,57.2";
        data[5]="1.6,58.57";
        data[6]="1.63,59.93";
        data[7]="1.65,61.29";
        data[8]="1.68,63.11";
        data[9]="1.7,64.47";
        data[10]="1.73,66.28";
        data[11]="1.75,68.1";
        data[12]="1.78,69.92";
        data[13]="1.8,72.19";
        data[14]="1.83,74.46";

        result= CalculatorMethods.computeSingleRegression(data);
        result.stringResultReport();
        assertTrue(result.getResultString().contains("y=61.272186542107434x+-39.061955918841036"));

    }

    @Test
    void ComputeSingleRegression_ReceiveOneInput_ReturnAnswer(){
        CalculationResult result;
        String[] data = new String[1];
        data[0]="1.47,52.21";
        /*
        data[1]="1.5,53.12";
        data[2]="1.52,54.48";
        data[3]="1.55,55.84";
        data[4]="1.57,57.2";
        data[5]="1.6,58.57";
        data[6]="1.63,59.93";
        data[7]="1.65,61.29";
        data[8]="1.68,63.11";
        data[9]="1.7,64.47";
        data[10]="1.73,66.28";
        data[11]="1.75,68.1";
        data[12]="1.78,69.92";
        data[13]="1.8,72.19";
        data[14]="1.83,74.46";

         */

        result= CalculatorMethods.computeSingleRegression(data);
        result.stringResultReport();
        assertTrue(result.getIsSuccess());

    }
    @Test
    void ComputeSingleRegression_ReceiveNullInput_ReturnError(){
        CalculationResult result;
        String[] data = new String[15];


        result= CalculatorMethods.computeSingleRegression(data);
        result.stringResultReport();
        assertFalse(result.getIsSuccess());

    }

    @Test
    void ComputeSingleRegression_ReceiveEmptyList_ReturnError(){
        CalculationResult result;
        String[] data = new String[0];


        result= CalculatorMethods.computeSingleRegression(data);
        result.stringResultReport();
        assertFalse(result.getIsSuccess());

    }

    @Test
    void ComputeSquareOfDifferences_ReceiveValidValue_ReturnAnswer(){
        CalculationResult result = new CalculationResult();
        List<Double> list = new ArrayList<Double>();
        list.add(6.425);
        list.add(2.5);
        double mean=2.25;
        CalculatorMethods.computeSquareOfDifferences(list,mean,result);

        assertEquals(17.493125, result.getResult());
    }

    @Test
    void computeSampleStandardDeviation_ReceiveValidInput_ReturnAnswer() {
        CalculationResult result;
        List<Double> nums=new ArrayList<>();

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);

        result= CalculatorMethods.computeSampleStandardDeviation(nums);
        assertEquals(1.5811388300841898, result.getResult());
    }

    @Test
    void computeSampleStandardDeviation_ReceiveZeroesInput_ReturnError(){
        CalculationResult result;
        List<Double> nums=new ArrayList<>();

        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);

        result= CalculatorMethods.computeSampleStandardDeviation(nums);
        result.resultReport();
        assertTrue(result.getIsSuccess());
    }

    @Test
    void ComputeSampleStandardDeviation_ReceiveNullList_ReturnNull() {
        List<Double> list = new ArrayList<Double>();
        CalculationResult result;
        result= CalculatorMethods.computeSampleStandardDeviation(list);
        result.resultReport();
        assertFalse(result.getIsSuccess());
    }

    @Test
    void ComputeSampleStandardDeviation_ReceiveInsufficientList_ReturnNull() {
        List<Double> list = new ArrayList<Double>();
        list.add(5.5);
        CalculationResult result;
        result= CalculatorMethods.computeSampleStandardDeviation(list);
        result.resultReport();
        assertFalse(result.getIsSuccess());
    }


    @Test
    void computePopulationStandardDeviation_ReceiveValidInput_ReturnAnswer() {
        List<Double> nums=new ArrayList<>();
        CalculationResult result;

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);
//Test passes at equivalency to 5 decimal places.
        result= CalculatorMethods.computePopulationStandardDeviation(nums);
        result.resultReport();
        assertEquals(1.4142135623730951, result.getResult());


    }

    @Test
    void computePopulationStandardDeviation_ReceiveZeroesInput_ReturnError(){
        CalculationResult result;
        List<Double> nums=new ArrayList<>();

        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);
        nums.add((double)0.0);

        result= CalculatorMethods.computePopulationStandardDeviation(nums);
        result.resultReport();
        assertTrue(result.getIsSuccess());
    }

    @Test
    void ComputePopulationStandardDeviation_ReceiveNullList_ReturnNull() {
        List<Double> list = new ArrayList<Double>();
        CalculationResult result;
        result= CalculatorMethods.computePopulationStandardDeviation(list);
        result.resultReport();
        assertFalse(result.getIsSuccess());
    }

    @Test
    void ComputeMean_ReceiveValidInput_ReturnAnswer() {
        CalculationResult result;
        List<Double> nums=new ArrayList<>();

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);
//In the Requirements document, the asserted correct mean is the sum of all the values.
        result= CalculatorMethods.computeMean(nums);
        result.resultReport();
        assertEquals(7.0,result.getResult());
    }

    @Test
    void ComputeZScore_ReceiveValidInput_ReturnAnswer(){
        CalculationResult result;
        String input="11.5,7,1.5811388300841898";

        result= CalculatorMethods.computeZScore(input);
        result.resultReport();
        assertEquals(2.846049894151541,result.getResult());

    }

    @Test
    void ComputeZScore_ReceiveInvalidInput_ReturnFailure(){
        CalculationResult result;
        String input="11.5,7,1.5811388300841898,2.2";

        result= CalculatorMethods.computeZScore(input);
        result.resultReport();
        assertFalse(result.getIsSuccess());

    }

    @Test
    void PredictY_ReceiveValidInput_ReturnAnswer(){
        String data="1.535,61.272186542107434, -39.061955918838656";
        CalculationResult result = CalculatorMethods.predictY(data);

        result.stringResultReport();
        assertTrue(result.getResultString().contains("y=54.990850423296244"));

    }
}
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    static Exception emptyArray = new Exception("valuesList parameter cannot be null or empty");
    static Exception zeroValues = new Exception("numValues is too low (sample size must be >= 2, population size must be >= 1)");

    //computeSquareOfDifferences - Tests to complete 100% of functionality
    @Test
    void ComputeSquareOfDifferences_ReceiveNullList_ThrowException() {
        List<Double> list = new ArrayList<Double>();
        CalculationResult result = new CalculationResult();
        Main.computeSquareOfDifferences(list,5,result);
        assertFalse(result.getIsSuccess());
    }

    @Test
    void ComputeSingleRegression_ReceiveValidInput_ReturnAnswer(){
        CalculationResult result = new CalculationResult();
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

        result=Main.computeSingleRegression(data);

        assertTrue(result.getResultString().contains("y=61.272186542107434x+-39.061955918838656"));

    }

    @Test
    void ComputeSquareOfDifferences_ReceiveValidValue_ReturnAnswer(){
        CalculationResult result = new CalculationResult();
        List<Double> list = new ArrayList<Double>();
        list.add(6.425);
        list.add(2.5);
        double mean=2.25;
        try {
            Main.computeSquareOfDifferences(list,mean,result);
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        assertEquals(17.493125, result.getResult());
    }

    @Test
    void computeSampleStandardDeviation_ReceiveValidInput_ReturnAnswer() {
        List<Double> nums=new ArrayList<>();

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);

        assertEquals(1.5811388300841898, Main.computeSampleStandardDeviation(nums).getResult());
    }

    @Test
    void computePopulationStandardDeviation_ReceiveValidInput_ReturnAnswer() {
        List<Double> nums=new ArrayList<>();

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);
//Test is still failing to Professor's requirements doc. Check back with Professor at earliest convenience.
        assertEquals(1.4142135623731, Main.computePopulationStandardDeviation(nums).getResult());
    }

    @Test
    void computeMean_ReceiveValidInput_ReturnAnswer() {
        List<Double> nums=new ArrayList<>();

        nums.add((double)9);
        nums.add((double)6);
        nums.add((double)8);
        nums.add((double)5);
        nums.add((double)7);
//In the Requirements document, the asserted correct mean is the sum of all the values.
        assertEquals(7.0,Main.computeMean(nums).getResult());
    }
}
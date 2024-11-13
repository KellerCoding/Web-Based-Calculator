//Java

public class CalculationResult {
    private double result = 0.0;
    private double[] resultArray= new double[3];
    private boolean isSuccess=true;
    private String operation,resultString;
    private String error;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
    public String getResultString(){
        return resultString;
    }

    public void setResultString(String resultString){
        this.resultString=resultString;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getOperation() {
        return operation;
    }

    // for example, "1.25 + 3.8 ="
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getError() {
        return error;
    }

    // for example, "" or "Not A Number"
    public void setError(String error) {
        this.error = error;
    }

    //Returns Print statement describing condition of result object
    public void resultReport(){
        System.out.print("result: "+getResult()+"\nOperation: "+getOperation()+"\nis success?: "+getIsSuccess()+"\nError: "+getError());
    }

    public void stringResultReport(){
        System.out.println("result: "+getResultString()+"\nOperation: "+getOperation()+"\nis success?: "+getIsSuccess()+"\nError: "+getError());
    }

    public void calculationFailure( String error){
        setIsSuccess(false);
        setError(error);
    }

    public double[] getResultArray() {
        return resultArray;
    }

    public void setResultArray(double[] resultArray) {
        this.resultArray = resultArray;
    }
}

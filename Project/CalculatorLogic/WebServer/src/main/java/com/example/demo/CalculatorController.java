package com.example.demo;

import Calc.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculatorController {

    //Honestly not sure what this does
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    //Method to return
    public static void returnColor(CalculationResult result, Model model){
        if (!result.getIsSuccess()){
            model.addAttribute("color", "#B70F0A");
            model.addAttribute("textcolor",false);
        }else{
            model.addAttribute("textcolor",true);
            model.addAttribute("color", "#FFECD7");
        }
    }

    @GetMapping("/result")
     public String result(Model model, @RequestParam(value="method", defaultValue = "") String input,
                          @RequestParam(value = "inputA", defaultValue = "")String boxInput){

        System.out.println(boxInput);
        List<String> stringList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(boxInput))) {
            String line;

            while ((line=reader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            e.getLocalizedMessage();
        }


        int length=stringList.size();
        /*for (int i=0;i<length;i++) {
            System.out.println(stringList.get(i));
        }*/
        CalculationResult result = new CalculationResult();
        List<Double> list=new ArrayList<Double>();
        try {
        switch (input) {


            //Here is the button value returned for the Sample Standard Deviation button function
                case "compSTD":
                    for (int i = 0; i < length; i++) {
                        list.add(Double.parseDouble(stringList.get(i)));
                    }
                    result = CalculatorMethods.computeSampleStandardDeviation(list);
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else {
                        model.addAttribute("something", result.getResult());
                        model.addAttribute("oper","Sample Standard Deviation");

                    }
                    returnColor(result,model);
                    break;


            //Button value returned for the Population Standard Deviation button function
                case "compPop":
                    for (int i = 0; i < length; i++) {
                        list.add(Double.parseDouble(stringList.get(i)));
                    }
                    result = CalculatorMethods.computePopulationStandardDeviation(list);
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else {
                        model.addAttribute("something", result.getResult());
                        model.addAttribute("oper","Population Standard Deviation");
                    }
                    returnColor(result,model);
                    break;


            //Button value returned for the Mean button function
                case "compMean":
                    for (int i = 0; i < length; i++) {
                        list.add(Double.parseDouble(stringList.get(i)));
                    }
                    result = CalculatorMethods.computeMean(list);
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else {
                        model.addAttribute("something", result.getResult());
                        model.addAttribute("oper","Mean Calculation");

                    }
                    returnColor(result,model);
                    break;


            //Button value returned for the Z-Score button function
                case "compZ":
                    result = CalculatorMethods.computeZScore(stringList.getFirst());
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else {
                        model.addAttribute("something", result.getResult());
                        model.addAttribute("oper","Z-Score");

                    }
                    returnColor(result,model);
                    break;


            //Button value returned for the Single Linear Regression Formula button function
                case "compSingle":
                    String[] stringArray = new String[length];
                    for (int i = 0; i < length; i++) {
                        stringArray[i] = stringList.get(i);
                    }

                    result = CalculatorMethods.computeSingleRegression(stringArray);
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else{
                        model.addAttribute("something", result.getResultString());
                        model.addAttribute("oper","Single Linear Regression Formula");

                    }
                    returnColor(result,model);
                    break;


            //Button value returned for the Single Linear Regression Prediction button function
                case "compY":
                    result = CalculatorMethods.predictY(stringList.getFirst());
                    if (!result.getIsSuccess()) {
                        model.addAttribute("something", result.getError());

                    } else {
                        model.addAttribute("something", result.getResultString());
                        model.addAttribute("oper","Single Linear Regression Prediction");

                    }
                    returnColor(result,model);
                    break;

            }
            //The cases for invalid input are all covered here in this Catch
            } catch (Exception e){
            model.addAttribute("something", "Invalid Input - Clear and Try Again\n");
            model.addAttribute("color", "#B70F0A");
            model.addAttribute("textcolor","#FFFFFF");
            }


        return "result";
}

//The clear button returns the page to the initial page
    @RequestMapping("/index")
    public String index(Model model, @RequestParam() Boolean button){

        return "index";
}

}

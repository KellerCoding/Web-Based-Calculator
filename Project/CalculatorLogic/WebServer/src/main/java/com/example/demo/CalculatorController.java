package com.example.demo;

import Calc.*;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Controller
public class CalculatorController {

    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }
    public static void returnAnswer(CalculationResult result, Model model){
        if (result.getIsSuccess()){
            model.addAttribute("something", result.getError());
        }else model.addAttribute("something", result.getResult());
    }

    @GetMapping("/result")
     public String result(Model model, @RequestParam(value="method", defaultValue = "") String input,
                          @RequestParam(value = "inputA", defaultValue = "")String boxInput){

        System.out.println(boxInput);
        List<String> stringList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(boxInput))) {
            String line;
            while ((line = reader.readLine()) != null) {
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

        switch (input){
            case "compSTD":

                for (int i=0;i<length;i++) {
                    list.add(Double.parseDouble(stringList.get(i)));
                }
                result=CalculatorMethods.computeSampleStandardDeviation(list);
                returnAnswer(result,model);
                break;
            case "compPop":

                for (int i=0;i<length;i++) {
                    list.add(Double.parseDouble(stringList.get(i)));
                }
                result=CalculatorMethods.computePopulationStandardDeviation(list);
                returnAnswer(result,model);
                break;
            case "compMean":
                for (int i=0;i<length;i++) {
                    list.add(Double.parseDouble(stringList.get(i)));
                }
                result = CalculatorMethods.computeMean(list);
                returnAnswer(result,model);
                break;
            case "compZ":
                result=CalculatorMethods.computeZScore(stringList.getFirst());
                returnAnswer(result,model);
                break;
            case "compSingle":
                String[] stringArray=new String[length];
                for (int i = 0; i < length; i++) {
                    stringArray[i]=stringList.get(i);
                }
                result=CalculatorMethods.computeSingleRegression(stringArray);
                returnAnswer(result,model);
                break;
            case "compY":
                result=CalculatorMethods.predictY(stringList.getFirst());
                returnAnswer(result,model);
                break;
            default:
                model.addAttribute("something", "Function Failure");
        }


        return "result";
}

    @RequestMapping("/index")
    public String index(Model model, @RequestParam() Boolean button){



        return "index";
}


/*
    @GetMapping("/add")
    public String input(@RequestParam(value="InputA", defaultValue = "0") String InputA, BindingResult bindingResult){

        double inputANum=Double.parseDouble(InputA);

        return ;
    }
*/

   /* @GetMapping("/")
    public String index() {
        return "<h1>Greetings from Kahmin!</h1>";
    }
*/
 /*   @GetMapping("/")
    public String inputForm(Model model) {
        model.addAttribute("input", new Input());
        return "Let me guess";
    }

    @PostMapping("/")
    public String inputSubmit(@ModelAttribute Input input, Model model) {
        model.addAttribute("input", input);
        return "result";
    }
  */
}

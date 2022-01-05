package io.hari.machinecodingtips.oops_principles.openClose_eq_interface_eq_StrategyPattern;

// approach : rule 1: always create interface == open close principle
// Context uses Strategy
// Context only know about interface method not the internal impl
// Strategy is like different Strategy
// HL :Context class --> uses Interface Strategy to call its interface method

import java.util.LinkedList;
import java.util.List;
import java.util.function.IntUnaryOperator;

// on HL strategy pattern start
class ContextClass { // https://en.wikipedia.org/wiki/Strategy_pattern == Context == Service class
    IStrategy iStrategy;

    public void myMagic() {
        iStrategy.sFoo();
    }
}

interface IStrategy {
    public void sFoo();
}

class Strategy1 implements IStrategy {
    @Override
    public void sFoo() {
        // some code : strategy 1
    }
}

class Strategy2 implements IStrategy {
    @Override
    public void sFoo() {
        // some code : strategy 2
    }
}
// on HL strategy pattern end

// strategy pattern simple use case
// we have 2 strategy + 2 different logic for these 2 strategy

// when user create IBillingStrategy -> that means 2 different enum -> choose any one of enum it means selected strategy 1 && strategy 1 lambda logic
// another view : user never pass logic to this Strategy interface
// another view : since we have added lambda or some logic related to enum/strategy , then we need to write one method that will execute lambda logic / strategy logic
// another view : service class use logic apply method to get result and store or do processing
enum IBillingStrategy {//this class is act as IStrategy + its 2 different implementation, no need to create impl class
    normal(inputValue -> inputValue),// strategy 1 logic in lambda style
    happy_hour(inputValue -> inputValue / 2);// strategy 2 logic in lambda style
    private final IntUnaryOperator unaryOperator_logic1or2_orStrategy;

    IBillingStrategy(IntUnaryOperator unaryOperator_logic1or2_orStrategy) {
        this.unaryOperator_logic1or2_orStrategy = unaryOperator_logic1or2_orStrategy;
    }

    int executeStrategyLogic(Integer userInputValue) {//helper / apply method: execute logic
        int executeLogicOutput = unaryOperator_logic1or2_orStrategy.applyAsInt(userInputValue);
        return executeLogicOutput;
    }
}

class CustomerBill_Context {//Context == Service class
    // 1. we want to use Strategy so need constructor
    // 2. we want to execute the logic method + store its result (optional)

    private IBillingStrategy iBillingStrategy;//1
    public static List<Integer> results = new LinkedList<>();//2

    // 1 -> 1.1 now we have use Strategy interface , now set, m1: via constructor m2: via setter
    // 1.1. m1
    public CustomerBill_Context(IBillingStrategy iBillingStrategy) {
        this.iBillingStrategy = iBillingStrategy;
    }
    // 1.1. m2
    public void setiBillingStrategy(IBillingStrategy iBillingStrategy) {
        this.iBillingStrategy = iBillingStrategy;
    }


    // 2 -> 2.1 call strategy lambda logic by its helper logic apply method
    public void executeStrategyLogicAndStoreResult(Integer userInputValue) {
        int resultFromStrategy = iBillingStrategy.executeStrategyLogic(userInputValue);// call strategy + strategy execute logic + return result
        results.add(resultFromStrategy);//store result :optional
    }
}


public class TestA {
    public static void main(String[] args) {

        // 1 simple use case , Context using Strategy1
        IBillingStrategy iBillingStrategy = IBillingStrategy.normal;// 1. strategy1 instance
        CustomerBill_Context customerBillContext = new CustomerBill_Context(iBillingStrategy);// 2. create context/service class + pass impl of strategy1

        customerBillContext.executeStrategyLogicAndStoreResult(10);// logic will be executed and store as strategy1
        customerBillContext.executeStrategyLogicAndStoreResult(10);// -- "" --
        System.out.println("results = " + CustomerBill_Context.results);

        //-----------------------------------------------
        CustomerBill_Context.results.clear();//clean data //this can be done inside Context class : create a print method and in end just add clean()
        //-----------------------------------------------

        // 2 simple use case , Context using Strategy2
        IBillingStrategy iBillingStrategy2 = IBillingStrategy.happy_hour;// 1. strategy2 instance
        CustomerBill_Context customerBillContext2 = new CustomerBill_Context(iBillingStrategy2);// 2. create context/service class + pass impl of strategy2

        customerBillContext2.executeStrategyLogicAndStoreResult(10);// logic will be executed and store as strategy2
        customerBillContext2.executeStrategyLogicAndStoreResult(10);// -- "" --
        System.out.println("results = " + CustomerBill_Context.results);

        //-----------------------------------------------
        CustomerBill_Context.results.clear();//clean data
        //-----------------------------------------------

        // 3 Context uses 2 different Strategy , first Strategy1 and then Strategy2
        IBillingStrategy iBillingStrategy_S1 = IBillingStrategy.normal;
        CustomerBill_Context customerBillContext3 = new CustomerBill_Context(iBillingStrategy_S1);

        customerBillContext3.executeStrategyLogicAndStoreResult(20);
        customerBillContext3.executeStrategyLogicAndStoreResult(20);

        IBillingStrategy iBillingStrategy_S2 = IBillingStrategy.happy_hour;
        customerBillContext3.setiBillingStrategy(iBillingStrategy_S2);//we change strategy for current Context/Customer/Service class, now all logic will be perform based on Strategy2

        customerBillContext3.executeStrategyLogicAndStoreResult(20);
        customerBillContext3.executeStrategyLogicAndStoreResult(20);

        System.out.println("results = " + CustomerBill_Context.results);//we can print or we can process this strategy output data store
        //code source : see java version : https://en.wikipedia.org/wiki/Strategy_pattern
    }
}

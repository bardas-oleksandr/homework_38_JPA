package ua.levelup;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.levelup.state.FinalState;
import ua.levelup.state.State;
import ua.levelup.state.support.StateProcessor;
import ua.levelup.util.AppUtil;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");

        State initState = (State)ctx.getBean("initState");
        StateProcessor processor = initState.getProcessor();
        processor.setState(initState);

        while(!(processor.getState() instanceof FinalState)){
            try{
                processor.goNext();
            }catch(Exception e){
                AppUtil.cleanConsole();
                System.out.println("Operation was not successful!");
                System.out.println("Reason: " + e.getMessage());
                processor.setState(initState);
                AppUtil.pressEnterToContinue();
            }
        }
        System.out.println("Buy-buy!");
    }
}
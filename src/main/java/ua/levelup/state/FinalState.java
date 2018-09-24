package ua.levelup.state;

import org.springframework.stereotype.Component;
import ua.levelup.exception.ApplicationException;

@Component("finalState")
public class FinalState extends State {

    @Override
    public void goNext() {
        throw new ApplicationException("Application should be shut down by hte moment");
    }
}

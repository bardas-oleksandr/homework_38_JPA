package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ua.levelup.state.support.StateHolder;
import ua.levelup.state.support.StateProcessor;

public abstract class State {
    private StateProcessor processor;
    private StateHolder stateHolder;

    public State() { }

    public State(StateProcessor processor) {
        this.processor = processor;
    }

    public abstract void goNext();

    public StateProcessor getProcessor() {
        return processor;
    }

    @Autowired
    public void setProcessor(@Value("#{stateProcessor}")StateProcessor processor) {
        this.processor = processor;
    }

    public StateHolder getStateHolder() {
        return stateHolder;
    }

    @Autowired
    public void setStateHolder(StateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }
}

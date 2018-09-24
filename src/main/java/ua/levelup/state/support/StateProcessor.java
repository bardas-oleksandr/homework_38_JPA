package ua.levelup.state.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.state.State;

@Component("stateProcessor")
public class StateProcessor {
    private State state;
    private Model model;

    public StateProcessor() { }

    public StateProcessor(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Model getModel() {
        return model;
    }

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }

    public void goNext(){
        state.goNext();
    }
}

package ua.levelup.state.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.state.State;

@Component("stateProcessor")
public class StateProcessor {
    private State state;
    private Session session;

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

    public Session getSession() {
        return session;
    }

    @Autowired
    public void setSession(Session session) {
        this.session = session;
    }

    public void goNext(){
        state.goNext();
    }
}

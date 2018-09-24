package ua.levelup.state.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.state.State;

@Component("stateHolder")
public class StateHolder {
    private State initState;
    private State addQuestionState;
    private State addTopicState;
    private State userChoiceState;
    private State adminChoiceState;
    private State finalState;
    private State loginState;
    private State passTestState;
    private State registerState;
    private State showUsersResultsState;

    public StateHolder() {
    }

    public State getInitState() {
        return initState;
    }

    @Autowired
    public void setInitState(State initState) {
        this.initState = initState;
    }

    public State getAddQuestionState() {
        return addQuestionState;
    }

    @Autowired
    public void setAddQuestionState(State addQuestionState) {
        this.addQuestionState = addQuestionState;
    }

    public State getAddTopicState() {
        return addTopicState;
    }

    @Autowired
    public void setAddTopicState(State addTopicState) {
        this.addTopicState = addTopicState;
    }

    public State getLoginState() {
        return loginState;
    }

    @Autowired
    public void setLoginState(State loginState) {
        this.loginState = loginState;
    }

    public State getRegisterState() {
        return registerState;
    }

    @Autowired
    public void setRegisterState(State registerState) {
        this.registerState = registerState;
    }

    public State getShowUsersResultsState() {
        return showUsersResultsState;
    }

    @Autowired
    public void setShowUsersResultsState(State showUsersResultsState) {
        this.showUsersResultsState = showUsersResultsState;
    }

    public State getFinalState() {
        return finalState;
    }

    @Autowired
    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    public State getUserChoiceState() {
        return userChoiceState;
    }

    @Autowired
    public void setUserChoiceState(State userChoiceState) {
        this.userChoiceState = userChoiceState;
    }

    public State getPassTestState() {
        return passTestState;
    }

    @Autowired
    public void setPassTestState(State passTestState) {
        this.passTestState = passTestState;
    }

    public State getAdminChoiceState() {
        return adminChoiceState;
    }

    @Autowired
    public void setAdminChoiceState(State adminChoiceState) {
        this.adminChoiceState = adminChoiceState;
    }
}

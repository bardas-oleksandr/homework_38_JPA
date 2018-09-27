package ua.levelup.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import ua.levelup.state.support.Session;

import java.util.Optional;

public class AuditorAwareBean implements AuditorAware<String> {
    @Autowired
    private Session session;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(session.getUser().getLogin());
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

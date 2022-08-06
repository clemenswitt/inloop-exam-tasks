import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JIdea extends JContent {
    private List<JAttachment> attachments = new ArrayList<>();
    private JState state;

    public JIdea(String title, String description) {
        super(title, description);
        this.state = new Draft();
    }

    public String toString() {
        return "Idea: " + super.getTitle() + "\n" + super.getDescription();
    }

    public void discuss(String text) {
        state.discuss(text);
    }

    public void evaluate(JValuation valuation) {
        state.evaluate(valuation);
    }

    public void hold() {
        state.hold();
    }

    public void release() {
        state.release();
    }

    public void decline() {
        state.decline();
    }

    public boolean isDeclined() {
        return state instanceof DeclinedIdea;
    }

    public boolean isReleased() {
        return state instanceof ReleasedIdea;
    }

    public String getCurrentDiscussion() {
        return state.getCurrentDiscussion();
    }

    public JValuation getValuation() {
        return state.getValuation();
    }

    public void addAttachment(JAttachment attachment) {
        if(attachment == null) throw new NullPointerException();
        attachments.add(attachment);
    }

    public List<JAttachment> getAttachments() {
        return attachments;
    }

    public boolean removeAttachment(JAttachment attachment) {
        if(attachment == null) throw new NullPointerException();
        return attachments.remove(attachment);
    }

    @Override
    public int hashCode() {
        return super.getTitle().hashCode();
    }

    abstract class JState {
        private String currentDiscussion = "";
        private JValuation valuation;

        public void discuss(String text) {
            throw new IllegalStateException();
        }

        public void evaluate(JValuation valuation) {
            throw new IllegalStateException();
        }

        public void hold() {
            throw new IllegalStateException();
        }

        public void release() {
            throw new IllegalStateException();
        }

        public void decline() {
            throw new IllegalStateException();
        }

        public String getCurrentDiscussion() {
            return currentDiscussion;
        }

        public JValuation getValuation() {
            return valuation;
        }

        public void setValuation(JValuation valuation) {
            if(valuation == null) throw new NullPointerException();
            this.valuation = valuation;
        }

        public void setCurrentDiscussion(String currentDiscussion) {
            if(currentDiscussion == null) throw new NullPointerException();
            this.currentDiscussion = currentDiscussion;
        }
    }

    class Draft extends JState {
        @Override
        public void hold() {
            state = new OpenDraft();
        }

        @Override
        public void decline() {
            state = new DeclinedIdea();
        }
    }

    class OpenDraft extends JState {
        @Override
        public void discuss(String text) {
            if(text == null) throw new NullPointerException();
            if(text.isEmpty()) throw new IllegalArgumentException();
            super.setCurrentDiscussion(super.getCurrentDiscussion() + text + "\n");
        }

        @Override
        public void evaluate(JValuation valuation) {
            if(valuation == null) throw new NullPointerException();
            super.setValuation(valuation);
        }

        @Override
        public void hold() {
            state = new ApprovedIdea();
        }

        @Override
        public void decline() {
            state = new DeclinedIdea();
        }
    }

    class ApprovedIdea extends JState {
        @Override
        public void release() {
            state = new ReleasedIdea();
        }
    }

    class DeclinedIdea extends JState { }

    class ReleasedIdea extends JState { }
}
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class JIdea extends JContent {
    private JState state;
    private List<JAttachment> attachments;

    private JValuation valuation;

    public JIdea(String title, String description) {
        super(title, description);
        this.attachments = new ArrayList<>();
        this.state = new Draft(); // initial state
    }

    public String toString() {
        return "Idea: " + super.getTitle() + "\n" + super.getDescription();
    }

    public boolean removeAttachment(JAttachment attachment) {
        if(attachment == null) {
            throw new NullPointerException();
        }
        return attachments.remove(attachment);
    }

    public List<JAttachment> getAttachments() {
        return attachments;
    }

    public void addAttachment(JAttachment attachment) {
        if(attachment == null) {
            throw new NullPointerException();
        }
        attachments.add(attachment);
    }

    public JValuation getValuation() {
        return state.getValuation();
    }

    public String getCurrentDiscussion() {
        return state.getCurrentDiscussion();
    }

    public boolean isReleased() {
        return state instanceof ReleasedIdea;
    }

    public boolean isDeclined() {
        return state instanceof DeclinedIdea;
    }

    public void decline() {
        state.decline();
    }

    public void release() {
        state.release();
    }

    public void hold() {
        state.hold();
    }

    public void discuss(String text) {
        state.discuss(text);
    }

    public void evaluate(JValuation valuation) {
        state.evaluate(valuation);
    }

    public abstract class JState {
        private String currentDiscussion = "";
        private JValuation valuation;

        public void discuss(String text) {
            if(text.isEmpty()) {
                throw new IllegalArgumentException();
            }
            currentDiscussion += text;
        }

        public void evaluate(JValuation valuation) {
            if(valuation == null) throw new NullPointerException();
            valuation = new JValuation(valuation.getTitle(), valuation.getDescription());
        }

        public void hold() {
            if(state instanceof Draft || state instanceof OpenDraft) state.hold();
            else throw new IllegalStateException();
        }

        public void release() {
            if(state instanceof ApprovedIdea) state.release();
            else throw new IllegalStateException();
        }

        public void decline() {
            if(state instanceof Draft || state instanceof OpenDraft) state.decline();
            else throw new IllegalStateException();
        }

        public String getCurrentDiscussion() {
            return currentDiscussion;
        }

        public void setCurrentDiscussion(String currentDiscussion) {
            if(currentDiscussion == null) {
                throw new NullPointerException();
            }
            if(currentDiscussion.isEmpty()) {
                throw new IllegalArgumentException();
            }
            this.currentDiscussion = currentDiscussion;
        }

        public JValuation getValuation() {
            return valuation;
        }

        public void setValuation(JValuation valuation) {
            if(valuation == null) {
                throw new NullPointerException();
            }
            this.valuation = valuation;
        }
    }

    class Draft extends JState {
        public void hold() {
            state = new OpenDraft();
        }

        public void decline() {
            state = new DeclinedIdea();
        }

        public void discuss(String text) {
            throw new IllegalStateException();
        }

        public void evaluate(JValuation valuation) {
            throw new IllegalStateException();
        }

        public void release() {
            throw new IllegalStateException();
        }
    }

    class OpenDraft extends JState {
        public void hold() {
            state = new ApprovedIdea();
        }

        public void decline() {
            state = new DeclinedIdea();
        }

        public void discuss(String text) {
            if(text.isEmpty()) throw new IllegalArgumentException();
            state.setCurrentDiscussion(state.getCurrentDiscussion() + text + "\n");
        }

        public void evaluate(JValuation valuation) {
            state.setValuation(valuation);
        }

        public void release() {
            throw new IllegalStateException();
        }
    }

    class ApprovedIdea extends JState {
        public void hold() {
            throw new IllegalStateException();
        }

        public void decline() {
            throw new IllegalStateException();
        }

        public void discuss(String text) {
            throw new IllegalStateException();
        }

        public void evaluate(JValuation valuation) {
            throw new IllegalStateException();
        }

        public void release() {
            state = new ReleasedIdea();
        }
    }

    class ReleasedIdea extends JState {
        public void hold() {
            throw new IllegalStateException();
        }

        public void decline() {
            throw new IllegalStateException();
        }

        public void discuss(String text) {
            throw new IllegalStateException();
        }


        public void evaluate(JValuation valuation) {
            throw new IllegalStateException();
        }

        public void release() {
            throw new IllegalStateException();
        }
    }

    class DeclinedIdea extends JState {
        public void hold() {
            throw new IllegalStateException();
        }

        public void decline() {
            throw new IllegalStateException();
        }

        public void discuss(String text) {
            throw new IllegalStateException();
        }

        public void evaluate(JValuation valuation){
            throw new IllegalStateException();
        }

        public void release() {
            throw new IllegalStateException();
        }
    }
}

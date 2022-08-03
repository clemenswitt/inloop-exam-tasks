import javax.swing.text.html.HTMLDocument;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TextFileIterator implements Iterator<String> {
    private Resource res;
    private String simText = "We wish you good luck in this exam!\nWe hope you are well pre-\npared.";
    private Iterator<String> it;
    private List<String> iterableSimText;

    public TextFileIterator(Resource res) {
        if(res == null) {
            throw new NullPointerException();
        }

        //Replace marks and escape sequences from simText
        simText = simText.replace("-\n", "").replace("\n", "").replace("!", " ").replace(".", "");

        //Put simText into List iterableSimText
        iterableSimText = Arrays.asList(simText.split(" "));

        //Iterator auf Liste aufrufen
        it = iterableSimText.iterator();
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    public String next() {
        return it.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public String getAsString(Resource res) {
        return "";
    }
}
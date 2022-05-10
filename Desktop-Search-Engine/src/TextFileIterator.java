import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TextFileIterator implements Iterator<String> {
    private Resource res;
    private Iterator<String> it;
    private String simText = "We wish you good luck in this exam!\nWe hope you are well pre-\npared.";
    private String[] separatedText;
    private List<String> separatedTextIterable;


    public TextFileIterator(Resource res) {
        if(res == null) {
            throw new NullPointerException("Ressource darf nicht null sein.");
        }

        this.res = res;

        separatedText = replaceIllegalCharacters(simText).split(" ");

        // separated Text iterierbar machen -> Array in Liste umwandeln
        separatedTextIterable = Arrays.asList(separatedText);

        // Iterator initialisieren
        it = separatedTextIterable.iterator();
    }

    public String replaceIllegalCharacters(String str) {
        str = str.replace("-\n", "");
        str = str.replace("\n", "");
        str = str.replace("!", " ");
        str = str.replace(".", "");
        return str;
    }

    public String next() {
        return it.next();
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove-Operation nicht unterstützt.");
    }

    public String getAsString(Resource res) {
        return "";
    }

}

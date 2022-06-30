import java.io.File;

public class JAttachment extends  JContent {
    private File file;

    public JAttachment(String title, String description, File file) {
        super(title, description);

        if(file == null) {
            throw new NullPointerException();
        }
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        if(file == null) {
            throw new NullPointerException();
        }
        this.file = file;
    }

    public String toString() {
        return "Attachment: " + super.getTitle() + "\n" + super.getDescription();
    }
}
package reader;

public class PipeFileReader extends DelimitedFileReader {

    public PipeFileReader() {
        super("\\|");
    }
}

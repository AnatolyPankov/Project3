package adapter;

import reader.PipeFileReader;

import java.nio.file.Path;

public class PipeOrderSourceAdapter extends AbstractFileOrderAdapter {
    public PipeOrderSourceAdapter(Path filePath) {
        super(new PipeFileReader(), filePath);
    }
}

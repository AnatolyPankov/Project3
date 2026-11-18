package adapter;

import reader.HashFileReader;

import java.nio.file.Path;

public class HashOrderSourceAdapter extends AbstractFileOrderAdapter {

    public HashOrderSourceAdapter(Path filePath) {
        super(new HashFileReader(), filePath);
    }
}

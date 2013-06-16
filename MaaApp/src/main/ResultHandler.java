package main;

import java.io.InputStream;

public interface ResultHandler {
	void processResults(InputStream results);
}

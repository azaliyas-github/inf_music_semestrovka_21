package ru.itis.repository;

import org.apache.commons.io.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@Component
public class FileRepository {
	private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();
	private final HashMap<String, Long> currentFileIndexes = new HashMap<>();

    public byte[] get(String fileName) {
		try {
			var file = new FileInputStream(fileName);
			return file.readAllBytes();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
    }

    public String getNewFileName(String directoryPath, String fileExtensionWoPeriod) {
    	String fileName = null;
    	directoryPath = normalizePath(directoryPath);

    	locks.putIfAbsent(directoryPath, new Object());
		synchronized (locks.get(directoryPath)) {
		    ensureDirectoryExists(directoryPath);

			currentFileIndexes.putIfAbsent(directoryPath, -1L);
			var currentFileIndex = currentFileIndexes.get(directoryPath);

			var fileExists = true;
			while (fileExists) {
				++currentFileIndex;
				fileName = getFileName(currentFileIndex.toString(), fileExtensionWoPeriod);
				fileExists = new File(directoryPath + fileName).exists();
			}

			currentFileIndexes.put(directoryPath, currentFileIndex);
			return fileName;
		}
    }

    public void save(String fileName, byte[] bytes) {
        var directoryPath = FilenameUtils.getPath(fileName);
        ensureDirectoryExists(directoryPath);

		try {
        	var file = new FileOutputStream(fileName);
        	file.write(bytes);
        	file.flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
    }

    private static String normalizePath(String path) {
    	path = new File(path).toPath().normalize().toString();
    	return path.equals(File.separator) ? path : path + File.separator;
	}

	private static void ensureDirectoryExists(String path) {
        var directory = new File(path);
        if (!directory.exists() || !directory.isDirectory())
            if (!directory.mkdir())
                throw new IllegalStateException("Can't create directory " + path);
    }

    private static String getFileName(String fileName, String fileExtensionWoPeriod) {
    	return fileName + "." + fileExtensionWoPeriod;
	}
}

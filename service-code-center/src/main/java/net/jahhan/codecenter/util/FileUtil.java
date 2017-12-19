package net.jahhan.codecenter.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static void checkNewFile(File file) throws IOException {
		if (!file.exists()) {
			if (file.getName().indexOf(".") > 0) {
				checkDir(file.getParentFile());
				file.createNewFile();
			} else {
				file.mkdirs();
			}
		} else if (file.isFile()) {
			file.delete();
			file.createNewFile();
		}
	}

	public static void checkDir(File dir) {
		if (!dir.isDirectory())
			dir.mkdirs();
	}

	/**
	 * 删除dir目录下的文件和空文件夹
	 */
	public static void deleteAllFilesOfDir(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
			return;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFilesOfDir(files[i]);
		}
		file.delete();
	}
	
	public static void deleteAllFilesOfDir(String path) {
		File file = new File(path);
		deleteAllFilesOfDir(file);
	}
}

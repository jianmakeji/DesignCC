package com.cidic.design.util;

import java.io.File;
import java.util.UUID;

public class FileUtil {
	
	public static String makeFileName() {

		return UUID.randomUUID().toString();
	}

	public static String makePath(String filename, String savePath) {

		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15

		String dir = savePath + File.separator + dir1 + File.separator + dir2;
															
		File file = new File(dir);

		if (!file.exists()) {

			file.mkdirs();
			file.setWritable(true,false);
		}
		return dir;
	}

}

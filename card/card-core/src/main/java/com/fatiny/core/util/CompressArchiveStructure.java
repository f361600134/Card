package com.fatiny.core.util;

import java.io.File;
import java.util.List;

public class CompressArchiveStructure {

	static class Item {
		
		private String path;
		private byte[] content;

		Item(String path, String content) {
			this(path, content.getBytes());
		}

		Item(String path, byte[] content) {
			this.path = path;
			this.content = content;
		}

		String getPath() {
			return path;
		}

		byte[] getContent() {
			return content;
		}
	}
	
	
	public static Item[] readFolderAllFiles(String path) {
		File readedFile = new File(path);
		if (readedFile.isDirectory()) {
			List<File> fileList = FileUtil.getAllFiles(readedFile);
			Item[] items = new Item[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				File file = fileList.get(i);
				String filePath = file.getName(); // file name path
				byte[] content = FileUtil.readFileToBytes(file);
				Item item = new Item(filePath, content);
				items[i] = item;
			}
			return items;
		} else {
			String filePath = readedFile.getName(); // file name path
			byte[] content = FileUtil.readFileToBytes(readedFile);
			Item item = new Item(filePath, content);
			return new Item[] { item };
		}
		
	} 
	
}
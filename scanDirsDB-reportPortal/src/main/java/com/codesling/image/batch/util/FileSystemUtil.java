package com.codesling.image.batch.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemUtil {

	public static List<String> readFileDirectory(File node){
		List<String> filesDirectory = new ArrayList<String>(); 
		if (!node.isDirectory() &&  !( node.getAbsoluteFile().toString().indexOf("\\._") > 0 ) ) {
			filesDirectory.add( node.getAbsoluteFile().toString() );
		}
		if (node.isDirectory()) {
			String[] subNode = node.list();

			for (String filename : subNode) {
				filesDirectory.addAll(readFileDirectory(new File(node, filename)));
			}

		}
		return filesDirectory;
	}
}

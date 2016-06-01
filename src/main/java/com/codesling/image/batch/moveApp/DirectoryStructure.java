package com.codesling.image.batch.moveApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryStructure {

	private static List<String> filesDirectory = new ArrayList<String>(); 
	
	
	public List<String> getFilesDirectory(){
		return filesDirectory;
	}
	
	
	public static List<String> getDirectoryStructure(String base){
		
		readFileDirectory( new File(base)  );
		
		return filesDirectory;
		
	}
	
	
	private static void readFileDirectory(File node){

		if (!node.isDirectory() &&  !( node.getAbsoluteFile().toString().indexOf("\\._") > 0 ) ) {
			filesDirectory.add( node.getAbsoluteFile().toString() );
//			writeFilesStructure( node.getAbsoluteFile().toString() + "\r\n");
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();

			for (String filename : subNote) {
				readFileDirectory(new File(node, filename));
			}

		}

	}
	
	
}

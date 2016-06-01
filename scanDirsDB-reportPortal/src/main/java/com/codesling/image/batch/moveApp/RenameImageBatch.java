package com.codesling.image.batch.moveApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class RenameImageBatch {

	
	private static List<String[]> csvFile;
	private static List<String> fileDirectory;
	private static String diretorioDestino = "imagens_destino";
	private static String diretorioOrigem = "imagens_origem";
	private static String csvOrigem = "image-batch\\src\\main\\resources\\ProdCatalogEntry.csv";
	private static String logDirectoryFiles = "imagens_destino";
	private static String logDirectoryProcessed = "imagens_destino";

	
	
	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		
		fileDirectory = new ArrayList<String>();
		
		csvFile = readCSVFile();
		readFileDirectory( new File(diretorioOrigem)  );
		
		String partNumber = "";
		String URL = "";

	
		for(String[] itens : csvFile){

			partNumber = itens[0];
			URL = itens[15];
			
			processaArquivo(URL, partNumber );
			
		}
		
		System.out.println("Done");
		
	}
	
	
	

	private static void readFileDirectory(File node) throws Exception{

		if (!node.isDirectory() &&  !( node.getAbsoluteFile().toString().indexOf("\\._") > 0 ) ) {
			fileDirectory.add( node.getAbsoluteFile().toString() );
			writeFilesStructure( node.getAbsoluteFile().toString() + "\r\n");
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();

			for (String filename : subNote) {
				readFileDirectory(new File(node, filename));
			}

		}

	}
	
	
	

	private static void processaArquivo(String origem, String fileNumber)  throws Exception{
		if (origem.indexOf(".jpg") > 0) {

			String partCode = getDescImageProductCode(origem);
			List<String> files = findInDirectories(partCode);
			
			StringBuffer sb = new StringBuffer();
			
			String primeiroCodigo = fileNumber;

			for (String file : files){
				
				//OBTENDO O DIRETORIO DA IMAGEM
				String[] fileFullPath = file.split(Pattern.quote(File.separator));
				String oldFileName = fileFullPath[fileFullPath.length - 1];
				String imageSizeDirectory = fileFullPath[fileFullPath.length - 2];				

				if ( !oldFileName.substring(0, 1).equals(".") ) {
					
					//OBTEDO O NOME FINAL DO ARQUIVO
					String[] splitedValues = origem.split("\\-", -1);
					String newFileName = splitedValues[splitedValues.length - 1];
					
					String log = primeiroCodigo + ";" + 
							origem + ";" + 
							file+ ";" + 
							newFileName + ";" + 
							oldFileName + ";" + 
							diretorioDestino + "\\" +  imageSizeDirectory + "\\" + newFileName + "\r\n";
					
					sb.append( log );
					
					primeiroCodigo = "";					
					
					
					moveFile(file,
							diretorioDestino + "\\" +  imageSizeDirectory + "\\" + newFileName);
					
				}
			}
			
			if ( files.size() > 0 ) {
				
				writeValidationFile( sb.toString() );
				
			}

		}
	}
	
	
	

	private static void writeValidationFile(String text) throws Exception{

		BufferedWriter output = null;
		
		try {
			File file = new File(logDirectoryProcessed + "\\log.csv");
			
			output = new BufferedWriter(new FileWriter(file, true));
			output.write(text);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			if (output != null) {
				output.close();
			}
			
		}

	}
	
	
	

	private static void writeFilesStructure(String text) throws Exception{

		BufferedWriter output = null;
		
		try {
			File file = new File(logDirectoryFiles + "\\files.csv");
			
			output = new BufferedWriter(new FileWriter(file, true));
			output.write(text);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			if (output != null) {
				output.close();
			}
			
		}

	}
	
	
	
	private static void moveFile(String origem, String destino) {

		File source = new File(origem);
		File dest = new File(destino);

		try {
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {

			System.out.println(origem);
		}
	}
	
	
	
	
	public static List<String[]> readCSVFile() {

		List<String[]> result = new ArrayList<String[]>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		int skiptLines = 1;
		int positionLine = 0;

		try {

			br = new BufferedReader(new FileReader(csvOrigem));
			while ((line = br.readLine()) != null) {

				if (positionLine > skiptLines){
				
					result.add( line.replace('|', ';').split(cvsSplitBy) );
					
				}
				
				positionLine++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;

	}
	
	

	/***
	 * 
	 * @param URL
	 * @return
	 */
	private static String getDescImageProductCode(String URL){
      	String[] splitedValues = URL.split("\\-",-1); 
   	
    	String code = splitedValues [ splitedValues.length-1];
    	
    	code = code.replace(".jpg", "");
    	code = code.replace("p", "");
    	code = code.replace("k", "");
    	code = code.replace("P", "");
    	code = code.replace("K", "");    	
    	
       	return code;
    }
	
	
	
	public static List<String> findInDirectories(String value) {
		
		List<String> retr = new ArrayList<String>();
		
		for(String item : fileDirectory){
			if (item.indexOf( value ) > 0){
				retr.add(item);
			}
		}
		
		return retr;
	}	

}

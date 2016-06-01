package com.codesling.image.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.env.Environment;

import com.codesling.image.batch.jdbc.beans.CampaignDTO;
import com.codesling.image.batch.jdbc.beans.JsonIndexBean;


public class NovaImgItemWriter implements ItemWriter<CampaignDTO> {

//    private static final Logger log = LoggerFactory.getLogger(NovaImgItemWriter.class);

	private String jsonFilePath;
	private List<JsonIndexBean> jsonMap;

	public NovaImgItemWriter(Environment environment){
		super();
		jsonMap = new ArrayList<JsonIndexBean>();
		jsonFilePath = environment.getProperty("json.path");
//		System.out.println("******** INIT: WRITER");
	}
	
	@Override
	public void write(List<? extends CampaignDTO> campList) throws Exception {
		
		System.out.print("- WRITE : " + jsonFilePath);
		ObjectMapper mapper = new ObjectMapper();
		
		for (CampaignDTO camp : campList){
			System.out.println(camp.getFileName());
			String campJson = jsonFilePath.concat(camp.getFileName());
			mapper.writeValue(new File(campJson), camp);
			
			String labelForHtml = camp.getCmpgnNumber() + "/" + camp.getCmpgnYear();
			jsonMap.add(new JsonIndexBean(camp.getFileName(), labelForHtml));
		}
		
		String indexJson = jsonFilePath.concat("index.json");
		
		mapper.writeValue(new File(indexJson), jsonMap);
	
        
	}


}
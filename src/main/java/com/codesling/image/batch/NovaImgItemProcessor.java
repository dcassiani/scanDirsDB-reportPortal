package com.codesling.image.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.codesling.image.batch.jdbc.beans.CampaignDTO;
import com.codesling.image.batch.jdbc.beans.ImageBean;
import com.codesling.image.batch.jdbc.beans.ProductsVO;
import com.codesling.image.batch.jdbc.beans.SkuVO;
import com.codesling.image.batch.util.FileSystemUtil;


public class NovaImgItemProcessor implements ItemProcessor<CampaignDTO, CampaignDTO> {

//    private static final Logger log = LoggerFactory.getLogger(NovaImgItemProcessor.class);
    
	@Autowired
	private Environment environment;
	
    private List<String> listDirs;
    
	public NovaImgItemProcessor(){
		super();
	}
	

	@Override
    public CampaignDTO process(CampaignDTO campaign) throws Exception {
		String path = environment.getProperty("catalog.path");
		this.listDirs = FileSystemUtil.readFileDirectory(new File(path));
		
		
		List<ProductsVO> listProdDelete = new ArrayList<ProductsVO> ();
		
		for ( ProductsVO prod : campaign.getListProducts()){
//			System.out.println("***** PROCESS :" + prod.getProfile());
			
			boolean isProdOK = setImageValidationFlag(prod.getFullImage());
			isProdOK = isProdOK && setImageValidationFlag(prod.getThumbnail());

			//remove da lista se estiver tudo certo
			if (isProdOK){
				listProdDelete.add(prod);
			}
		}
		campaign.getListProducts().removeAll(listProdDelete);

		List<SkuVO> listSKUDelete = new ArrayList<SkuVO> ();
		
		for (SkuVO sku : campaign.getListSKU()){
//			System.out.println("***** PROCESS :" + sku.getLineNumber());
			
			boolean isSkuOK = setImageValidationFlag(sku.getThumbnail());
			isSkuOK = isSkuOK && setImageValidationFlag(sku.getThumbnail());
			
			//remove da lista se estiver tudo certo
			if (isSkuOK){
				listSKUDelete.add(sku);
			}
		}
		campaign.getListSKU().removeAll(listSKUDelete);
		
    	return campaign;
    }


	private boolean setImageValidationFlag(ImageBean img) {
		for (String pathReal : listDirs){
			if (pathReal.endsWith(img.getImage())){
				img.setReady(true);
				listDirs.remove(pathReal);
				return true;
			}
		}
		return false;
	}


	

};
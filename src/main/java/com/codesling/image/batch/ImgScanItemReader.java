package com.codesling.image.batch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.codesling.image.batch.jdbc.beans.AtributoVO;
import com.codesling.image.batch.jdbc.beans.CampaignDTO;
import com.codesling.image.batch.jdbc.beans.CampaignVO;
import com.codesling.image.batch.jdbc.beans.ProductsVO;
import com.codesling.image.batch.jdbc.beans.SkuVO;
import com.codesling.image.batch.jdbc.wrapper.AtributoRowMapper;
import com.codesling.image.batch.jdbc.wrapper.CampaignRowMapper;
import com.codesling.image.batch.jdbc.wrapper.ProductRowMapper;
import com.codesling.image.batch.jdbc.wrapper.SkuRowMapper;
import com.codesling.image.batch.util.StringManipulationUtil;

@Qualifier("DB2ds")
public class ImgScanItemReader implements ItemReader<CampaignDTO> {

	
	private static final String SQL_CAMPAIGNS = " SELECT  prev_num, prev_year, "
			+ " cur_num, cur_year, "+
			" fut_1_num, fut_1_year, "
			+ " fut_2_num, fut_2_year, "
			+ " fut_3_num, fut_3_year " +
			" FROM 	CMPGN_CALNDR"+
			" WHERE (CURRENT DATE) BETWEEN CMPGN_STRT_DT CMPGN_END_DT ";

	private static final String SQL_PRODUTOS = " SELECT DISTINCT PROFILE, NAME, FULLIMAGE, "
			+ " THUMBNAIL, CATEGORIA_CAT_MASTER "
			+ "	FROM #PROP_SCHEMA#.cat WHERE CMPGN_YR_NR = ? and CMPGN_NR = ? ";

	private static final String SQL_SKUS = " SELECT LINE_NUMBER, FSCODE, "
			+ " NAME, FULLIMAGE, THUMBNAIL, CATEGORIA_CAT_MASTER "
			+ " FROM #PROP_SCHEMA#.cat2 WHERE CMPGN_YR_NR = ? and CMPGN_NR = ? ";
	
	private static final String SQL_ATRIBUTOS = " SELECT * from cat3"
			+ " CMPGN_YR_NR = ? and CMPGN_NR = ? ";
	
	private Iterator<CampaignDTO> campIterator;

	public ImgScanItemReader (@Qualifier("DB2ds") DataSource datasource , Environment env){
		
		String schema = env.getProperty("db2.datasource.schema.for.table");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		List<CampaignVO> listCampParameters = jdbcTemplate.query(getFinalSQL(SQL_CAMPAIGNS, schema), new CampaignRowMapper()).get(0);

		List<CampaignDTO> listCampaigns = new ArrayList<CampaignDTO>();
		
		for (CampaignVO camp : listCampParameters){
			CampaignDTO dto = new CampaignDTO( camp );
			dto.setListProducts(loadProducts(datasource, camp, schema));
			dto.setListSKU(loadSkus(datasource, camp, schema));
			dto.setListAtributos(loadAtributos(datasource, camp, schema));
			listCampaigns.add(dto);
		}
		campIterator = listCampaigns.iterator();
	}
	
	private String getFinalSQL(String sql, String schema) {
		return StringManipulationUtil.replaceToken(sql, schema, "#PROP_SCHEMA#");
	}

	private List<SkuVO> loadSkus(@Qualifier("DB2ds") DataSource datasource, CampaignVO camp, String schema) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(getFinalSQL(SQL_SKUS, schema), 
				new String[] {camp.getCmpgnYear(), camp.getCmpgnNumber()}, 
				new SkuRowMapper());
		
	}

	private List<ProductsVO> loadProducts(@Qualifier("DB2ds") DataSource datasource, CampaignVO camp, String schema) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(getFinalSQL(SQL_PRODUTOS, schema), 
				new String[] {camp.getCmpgnYear(), camp.getCmpgnNumber()}, 
				new ProductRowMapper());
	}
	
	private List<AtributoVO> loadAtributos(@Qualifier("DB2ds") DataSource datasource, CampaignVO camp, String schema) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(getFinalSQL(SQL_ATRIBUTOS, schema), 
				new String[] {camp.getCmpgnYear(), camp.getCmpgnNumber()}, 
				new AtributoRowMapper());
		
	}

	@Override
	public CampaignDTO read() {
		if (!campIterator.hasNext()){
			return null;
		}
		return campIterator.next();
	}

}
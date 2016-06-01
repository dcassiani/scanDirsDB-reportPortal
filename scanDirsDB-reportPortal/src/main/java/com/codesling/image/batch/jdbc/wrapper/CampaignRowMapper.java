package com.codesling.image.batch.jdbc.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.codesling.image.batch.jdbc.beans.CampaignVO;

public class CampaignRowMapper implements RowMapper<List<CampaignVO>>{

	@Override
	public List<CampaignVO> mapRow(ResultSet rs, int rowNum) throws SQLException {

		List<CampaignVO> ret = new ArrayList<CampaignVO>();
		
		CampaignVO retPrev = new CampaignVO();
		retPrev.setCmpgnNumber(rs.getString("prev_num").trim());
		retPrev.setCmpgnYear(rs.getString("prev_year").trim());
		
		CampaignVO retCur = new CampaignVO();
		retCur.setCmpgnNumber(rs.getString("cur_num").trim());
		retCur.setCmpgnYear(rs.getString("cur_year").trim());
		
		CampaignVO retFut1 = new CampaignVO();
		retFut1.setCmpgnNumber(rs.getString("fut_1_num").trim());
		retFut1.setCmpgnYear(rs.getString("fut_1_year").trim());
		
		CampaignVO retFut2 = new CampaignVO();
		retFut2.setCmpgnNumber(rs.getString("fut_2_num").trim());
		retFut2.setCmpgnYear(rs.getString("fut_2_year").trim());
		
		CampaignVO retFut3 = new CampaignVO();
		retFut3.setCmpgnNumber(rs.getString("fut_3_num").trim());
		retFut3.setCmpgnYear(rs.getString("fut_3_year").trim());
		
		ret.add(retPrev);
		ret.add(retCur);
		ret.add(retFut1);
		ret.add(retFut2);
		ret.add(retFut3);

		return ret;
	}

}

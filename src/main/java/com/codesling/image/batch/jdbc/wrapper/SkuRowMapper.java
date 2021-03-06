package com.codesling.image.batch.jdbc.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codesling.image.batch.jdbc.beans.SkuVO;

public class SkuRowMapper  implements RowMapper<SkuVO>{

	@Override
	public SkuVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		SkuVO ret = new SkuVO();
		ret.setLineNumber(rs.getString("LINE_NUMBER")==null?"":rs.getString("LINE_NUMBER").trim());
		ret.setFscode(rs.getString("FSCODE")==null?"":rs.getString("FSCODE").trim());
		ret.setName(rs.getString("NAME")==null?"":rs.getString("NAME").trim());
		ret.setFullImage(rs.getString("FULLIMAGE")==null?"":rs.getString("FULLIMAGE").trim());
		ret.setThumbnail(rs.getString("THUMBNAIL")==null?"":rs.getString("THUMBNAIL").trim());
		ret.setCategory(rs.getString("CATEGORIA_CAT_MASTER")==null?"":rs.getString("CATEGORIA_CAT_MASTER").trim());
		ret.setType("Item");
		return ret;
	}
}

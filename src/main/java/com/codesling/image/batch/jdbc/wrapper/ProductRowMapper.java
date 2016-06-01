package com.codesling.image.batch.jdbc.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codesling.image.batch.jdbc.beans.ProductsVO;

public class ProductRowMapper  implements RowMapper<ProductsVO>{

	@Override
	public ProductsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductsVO ret = new ProductsVO();
		ret.setProfile(rs.getString("PROFILE")==null?"":rs.getString("PROFILE").trim());
		ret.setName(rs.getString("NAME")==null?"":rs.getString("NAME").trim());
		ret.setFullImage(rs.getString("FULLIMAGE")==null?"":rs.getString("FULLIMAGE").trim());
		ret.setThumbnail(rs.getString("THUMBNAIL")==null?"":rs.getString("THUMBNAIL").trim());
		ret.setCategory(rs.getString("CATEGORIA_CAT_MASTER")==null?"":rs.getString("CATEGORIA_CAT_MASTER").trim());
		ret.setType("Produto");
		return ret;
	}
}

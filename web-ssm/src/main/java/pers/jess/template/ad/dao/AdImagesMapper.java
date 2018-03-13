package pers.jess.template.ad.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.ad.model.AdImages;

import java.util.List;
import java.util.Map;

@Repository
public interface AdImagesMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AdImages record);

    List<AdImages> listByUserIdAndOrgId(Map<String, Object> params);

    AdImages query(Map<String, Object> params);

    int update (AdImages adImages);
}
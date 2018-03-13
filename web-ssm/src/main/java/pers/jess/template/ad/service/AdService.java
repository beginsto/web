package pers.jess.template.ad.service;

import pers.jess.template.ad.model.AdImages;
import pers.jess.template.ad.model.AdOrganization;
import pers.jess.template.ad.model.AdUser;

import java.util.List;
import java.util.Map;

public interface AdService {


    List<AdOrganization> listByUserId(Integer userid);

    AdUser queryByPhone(String phone);

    int update(AdUser user);

    int insert(AdImages record);

    List<AdImages> listByUserIdAndOrgId(Map<String, Object> params);

    AdImages query(Map<String, Object> params);

    int update (AdImages adImages);
}

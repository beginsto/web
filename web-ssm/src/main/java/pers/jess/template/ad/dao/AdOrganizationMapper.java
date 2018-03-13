package pers.jess.template.ad.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.ad.model.AdOrganization;

import java.util.List;

@Repository
public interface AdOrganizationMapper {

    /**
     * 用户对应门店列表
     * @param userid
     * @return
     */
    List<AdOrganization> listByUserId(Integer userid);
}
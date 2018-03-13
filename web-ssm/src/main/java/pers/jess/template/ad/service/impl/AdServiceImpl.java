package pers.jess.template.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.ad.dao.AdImagesMapper;
import pers.jess.template.ad.dao.AdOrganizationMapper;
import pers.jess.template.ad.dao.AdUserMapper;
import pers.jess.template.ad.model.AdImages;
import pers.jess.template.ad.model.AdOrganization;
import pers.jess.template.ad.model.AdUser;
import pers.jess.template.ad.service.AdService;

import java.util.List;
import java.util.Map;

@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdOrganizationMapper adOrganizationMapper;

    @Autowired
    private AdUserMapper adUserMapper;

    @Autowired
    private AdImagesMapper adImagesMapper;

    @Override
    public List<AdOrganization> listByUserId(Integer userid){
        return adOrganizationMapper.listByUserId(userid);
    }

    @Override
    public AdUser queryByPhone(String phone){
        return adUserMapper.queryByPhone(phone);
    }

    @Override
    public int update(AdUser user){
        return adUserMapper.update(user);
    }

    @Override
    public int insert(AdImages record){
        return adImagesMapper.insert(record);
    }

    @Override
    public List<AdImages> listByUserIdAndOrgId(Map<String, Object> params){
        return adImagesMapper.listByUserIdAndOrgId(params);
    }

    @Override
    public AdImages query(Map<String, Object> params){
        return adImagesMapper.query(params);

    }

    @Override
    public int update (AdImages adImages){
        return adImagesMapper.update(adImages);
    }
}

package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.MPInvestigate;

@Repository
public interface MPInvestigateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MPInvestigate record);

    MPInvestigate queryByPhone(String phone);

}
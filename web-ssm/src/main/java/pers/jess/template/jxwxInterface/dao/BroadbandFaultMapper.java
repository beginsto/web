package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.BroadbandFault;

@Repository
public interface BroadbandFaultMapper {

    int insert(BroadbandFault record);

}
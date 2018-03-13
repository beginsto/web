package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.SpringFestivalAward;

import java.util.List;

@Repository
public interface SpringFestivalAwardMapper {

    List<SpringFestivalAward> listAwards(Integer period);

    int updateAward(SpringFestivalAward award);


}
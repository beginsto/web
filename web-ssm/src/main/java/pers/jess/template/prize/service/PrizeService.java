package pers.jess.template.prize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.prize.dao.PrizeMapper;
import pers.jess.template.prize.model.Prize;

import java.util.List;

@Service
public class PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    public Prize queryById(Integer id){
        return prizeMapper.queryById(id);
    }

    public int updateById(Prize record){
        return prizeMapper.updateById(record);
    }

    public List<Prize> list(String source){
        return prizeMapper.list(source);
    }
}

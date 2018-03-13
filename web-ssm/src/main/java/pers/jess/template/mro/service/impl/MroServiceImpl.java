package pers.jess.template.mro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.mro.dao.MroAmountMapper;
import pers.jess.template.mro.dao.MroContentMapper;
import pers.jess.template.mro.dao.MroDetailMapper;
import pers.jess.template.mro.model.MroAmount;
import pers.jess.template.mro.model.MroContent;
import pers.jess.template.mro.model.MroDetail;
import pers.jess.template.mro.service.MroService;

import java.util.List;
import java.util.Map;

@Service
public class MroServiceImpl implements MroService {

    @Autowired
    private MroContentMapper mroContentMapper;

    @Autowired
    private MroDetailMapper mroDetailMapper;

    @Autowired
    private MroAmountMapper mroAmountMapper;

    @Override
    public List<MroContent> query(){
        return mroContentMapper.query();
    }

    @Override
    public MroDetail queryByOpenidAndIssue(Map<String, Object> map){
        return mroDetailMapper.queryByOpenidAndIssue(map);
    }

    @Override
    public MroAmount queryByPid(Integer pid){
        return mroAmountMapper.queryByPid(pid);
    }

    @Override
    public int insert(MroAmount record){
        return mroAmountMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(MroAmount record){
        return mroAmountMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(MroDetail record){
        return mroDetailMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(MroDetail record){
        return mroDetailMapper.updateByPrimaryKey(record);
    }

    @Override
    public MroContent selectByPrimaryKey(Integer id){
        return mroContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public  List<MroDetail> queryList(Map<String, Object> map){
        return mroDetailMapper.queryList(map);
    }
}

package pers.jess.template.qd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.qd.dao.QDDetailMapper;
import pers.jess.template.qd.dao.QDINFOMapper;
import pers.jess.template.qd.model.QDDetail;
import pers.jess.template.qd.model.QDINFO;
import pers.jess.template.qd.service.QDService;

import java.util.List;

@Service
public class QDServiceImpl implements QDService{

    @Autowired
    private QDDetailMapper detailMapper;

    @Autowired
    private QDINFOMapper qdinfoMapper;

    @Override
    public int insert(QDDetail record){
        return detailMapper.insert(record);
    }
    @Override
    public QDDetail queryByOpenid(String openid){
        return detailMapper.queryByOpenid(openid);
    }

    @Override
    public List<QDINFO> query(String phone){
        return qdinfoMapper.query(phone);
    }
}

package pers.jess.template.qd.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.qd.model.QDINFO;

import java.util.List;

@Repository
public interface QDINFOMapper {

    List<QDINFO> query(String phone);
}
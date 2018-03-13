package pers.jess.template.signin.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.signin.model.SignInLikeAmount;

import java.util.List;
import java.util.Map;

@Component
public interface SignInLikeAmountMapper {
    int insert(SignInLikeAmount record);

    int insertSelective(SignInLikeAmount record);

    List<SignInLikeAmount> queryRankPopularity(Integer version);

    SignInLikeAmount queryData(Map<String, Object> map);

    int updateLikeData(SignInLikeAmount record);
}
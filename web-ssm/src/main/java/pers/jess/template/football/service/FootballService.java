package pers.jess.template.football.service;

import pers.jess.template.football.model.Football;

public interface FootballService {

    int insertData(Football record);

    int queryByMobile(String mobile);

    int queryCount();
}

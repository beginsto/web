package pers.jess.template.fxll.service;

import pers.jess.template.fxll.model.Fxll;

public interface FxllService {

    int insert(Fxll record);

    Fxll selectByMobile(String mobile);
}

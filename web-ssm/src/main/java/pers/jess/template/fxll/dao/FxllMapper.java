package pers.jess.template.fxll.dao;

        import org.springframework.stereotype.Component;
        import pers.jess.template.fxll.model.Fxll;

@Component
public interface FxllMapper {

    int insert(Fxll record);

    Fxll selectByMobile(String mobile);
}
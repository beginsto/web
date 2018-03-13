package pers.jess.template.exception;

public class NullParamsException extends RuntimeException{

    public NullParamsException(){

    }

    public NullParamsException(String msg){
        super(msg);
    }

}

package pers.jess.template.manager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2017/3/27.
 */

@XmlRootElement
public class TextMessage {

    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String msgType;
   // private int funcFlag;
    private String content;

    public String getToUserName(){
        return toUserName;
    }

    public void setToUserName(String toUserName){
        this.toUserName = toUserName;
    }

    public String getFromUserName(){
        return fromUserName;
    }

    public void setFromUserName(String fromUserName){
        this.fromUserName = fromUserName;
    }

    public long getCreateTime(){
        return createTime;
    }

    public void setCreateTime(long createTime){
        this.createTime = createTime;
    }

    public String getMsgType(){
        return msgType;
    }

    public void setMsgType(String msgType){
        this.msgType = msgType;
    }

  /*  public int getFuncFlag(){
        return funcFlag;
    }

    public void setFuncFlag(int funcFlag){
        this.funcFlag = funcFlag;
    }*/

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}

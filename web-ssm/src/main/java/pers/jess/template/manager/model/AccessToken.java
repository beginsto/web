package pers.jess.template.manager.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/29.
 */
public class AccessToken implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7021131613095678023L;

    private String accessToken;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    @Override
    public String toString()
    {
        return "GetAccessTokenRsp [accessToken=" + accessToken + "]";
    }
}

package com.jiuselu.helloworld.db;

import org.litepal.crud.DataSupport;

/**
 * Description: ：
 * Company      : 北京云磅科技
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class County extends DataSupport {

    private int id;
    private String countyName;
    private int countyCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }
}

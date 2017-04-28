package com.jiuselu.helloworld.db;

import org.litepal.crud.DataSupport;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class Province extends DataSupport {

    private int id;
    private String prvinceName;
    private int prvinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrvinceName() {
        return prvinceName;
    }

    public void setPrvinceName(String prvinceName) {
        this.prvinceName = prvinceName;
    }

    public int getPrvinceCode() {
        return prvinceCode;
    }

    public void setPrvinceCode(int prvinceCode) {
        this.prvinceCode = prvinceCode;
    }
}

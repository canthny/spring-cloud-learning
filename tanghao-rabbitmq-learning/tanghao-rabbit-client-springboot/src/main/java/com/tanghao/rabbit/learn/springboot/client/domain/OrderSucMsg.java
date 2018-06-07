package com.tanghao.rabbit.learn.springboot.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author： Canthny
 * @Description： 订单处理成功消息对象
 * @Date： Created in 2018/5/23 16:47
 */
public class OrderSucMsg implements Serializable{

    private String payOrderNo;

    private String status;

    private Date sucTime;

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSucTime() {
        return sucTime;
    }

    public void setSucTime(Date sucTime) {
        this.sucTime = sucTime;
    }
}

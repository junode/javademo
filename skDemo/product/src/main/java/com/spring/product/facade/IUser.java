package com.spring.product.facade;

import spring.cloud.common.model.ResultMessage;

import java.util.List;
import java.util.concurrent.Future;

public interface IUser {

    public ResultMessage timeout();

    public ResultMessage exp(String msg);

    public ResultMessage timeout2();

    public Future<ResultMessage> asyncTimeout();

    public List<ResultMessage> exp2(String[] params);
}



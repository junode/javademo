package com.demo.io_jdbc.util;

import com.alipay.rdf.file.interfaces.LogCallback;

/**
 * 默认日志打印
 * 
 * @author hongwei.quhw
 * @version $Id: TestLog.java, v 0.1 2017年7月24日 下午8:39:17 hongwei.quhw Exp $
 */
public class TestLog implements LogCallback {

    @Override
    public boolean isDebug() {
        return true;
    }

    /** 
     * @see LogCallback#isWarn()
     */
    @Override
    public boolean isWarn() {
        return true;
    }

    /** 
     * @see LogCallback#isInfo()
     */
    @Override
    public boolean isInfo() {
        return true;
    }

    /** 
     * @see LogCallback#info(String)
     */
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    /** 
     * @see LogCallback#info(String, Throwable)
     */
    @Override
    public void info(String msg, Throwable throwable) {
        System.out.println(msg);
        throwable.printStackTrace();
    }

    /** 
     * @see LogCallback#warn(String)
     */
    @Override
    public void warn(String msg) {
        System.out.println(msg);
    }

    /** 
     * @see LogCallback#warn(String, Throwable)
     */
    @Override
    public void warn(String msg, Throwable throwable) {
        System.out.println(msg);
        throwable.printStackTrace();
    }

    /** 
     * @see LogCallback#debug(String)
     */
    @Override
    public void debug(String msg) {
        System.out.println(msg);
    }

    /** 
     * @see LogCallback#debug(String, Throwable)
     */
    @Override
    public void debug(String msg, Throwable throwable) {
        System.out.println(msg);
        throwable.printStackTrace();
    }

    /** 
     * @see LogCallback#error(String)
     */
    @Override
    public void error(String msg) {
        System.err.println(msg);
    }

    /** 
     * @see LogCallback#error(String, Throwable)
     */
    @Override
    public void error(String msg, Throwable throwable) {
        System.err.println(msg);
        throwable.printStackTrace();
    }

}

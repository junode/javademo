package com.demo;

import org.junit.Test;

/**
 * @Auther: zwy
 * @Date: 2020/10/15
 * @Description: 若干交易文件格式
 */
public class ReturnMoney {
    private Integer nexId = 1123;
    private String sBusiFalg = "还款";
    private String acct_no = "11111111111111111111111111111111";
    private String ack_amt2 = "0000000000000022.00";
    private String interest2= "0000000000000000.00";
    private String sub_branch_code = "112311";
    private String inputuser = "99221133";
    private String cur_no = "0000111100";
    private String in_flag = "00";
    private String trans_serno = "1111222211112222111122221111222211112222";
    private String remark = "junode";
    private String income_characteristic = "1";
    private String prod_code = "11231111112311111123111111231111";
    private String cust_type = "0";
    private String busi_code = "111222";
    public static void main(String[] args) {

    }

    /**
    * 功能描述: 还款文件格式实例
    * @auther: zwy
    */
    @Test
    public void test1(){
        String demo = "[%d]:[业务类型10]  sBusiFalg: len=[%d] val=[%s]\n" +
                "[转入账号32]  acct_no: len=[%d] val=[%s]\n " +
                "[交易本金17]  ack_amt2: len=[%d] val=[%s]\n " +
                "[交易利息17]  interest2: len=[%d] val=[%s]\n"+
                "[交易网点6]  sub_branch_code: len=[%d] val=[%s]\n" +
                "[交易柜员8] inputuser: len=[%d] val=[%s]\n" +
                "[货币号10]  cur_no: len=[%d] val=[%s]\n" +
                "[转入钞汇标志2]in_flag: len=[%d] val=[%s] \n" +
                "[交易流水号40] trans_serno: len=[%d] val=[%s]\n" +
                "[备注256]  remark: len=[%d] val=[%s]\n" +
                "[收益特点1]  income_characteristic: len=[%d] val=[%s]\n" +
                "[产品代码32]  prod_code: len=[%d] val=[%s]\n" +
                "[客户类型1] cust_type: len=[%d] val=[%s]\n" +
                "[业务代码]  busi_code: len=[%d] val=[%s]\n\n";
        System.out.println(String.format(demo,nexId,sBusiFalg.length(),sBusiFalg,
                acct_no.length(),acct_no,ack_amt2.length(),ack_amt2,interest2.length(),interest2,
                sub_branch_code.length(),sub_branch_code,inputuser.length(),inputuser,
                cur_no.length(),cur_no,in_flag.length(),in_flag,trans_serno.length(),trans_serno,
                remark.length(),remark,
                income_characteristic.length(),income_characteristic,prod_code.length(),prod_code,
                cust_type.length(),cust_type,busi_code.length(),busi_code ));;




    }
}

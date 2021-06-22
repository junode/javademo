package com.demo;

/**
 * @Auther: zwy
 * @Date: 2020/10/12
 * @Description: 第15章，求公司价值
 * @version:
 */
public class char15 {
    private static double fcf0 = 289836.00;
    private static double g1 = 0.15;
    private static double wacc = 0.0616;
    private static double g2 = 0.04;
    private static int n = 2;

    public static void main(String[] args) {
//        caculateV();
        caculateV1();

//        caculateV2();
    }

    /**
     * 功能描述: 两阶段增长模型的计算过程
     * 参照：https://wenku.baidu.com/view/eff2cb53492fb4daa58da0116c175f0e7dd11949
     * 《第八章企业价值评估方法(二)》
     *
     * @auther: zwy
     */
    public static void caculateV() {
        // 成长期现值求值过程
        double growNowValue = 614 / (1 + 0.11) + 663.12 / Math.pow(1 + 0.11, 2) + 716.17 / Math.pow(1 + 0.11, 3)
                + 773.64 / Math.pow(1 + 0.11, 4) + 835.34 / Math.pow(1 + 0.11, 5);
        System.out.println(" 成长期现值： " + growNowValue);
        // 后续期价值求值过程
        double afterContinueFinalValue = 1142.40 / (0.1 - 0.05) / Math.pow(1 + 0.11, 5);
        System.out.println(" 后续期价值： " + afterContinueFinalValue);
        System.out.println("实体价值合计：" + (growNowValue + afterContinueFinalValue));
    }
    
    public static void caculateV1(){
        // 后续期价值求值过程：
        double fcfn = fcf0 * Math.pow(1+g1,n);
        double afterValue = fcfn * (1+g2) / ((wacc-g2)*Math.pow(1+wacc,n));
        double v = 0;
        for (int t = 1;t < n+1; t++) {
            v += fcf0 * (1+g1) / Math.pow(1+wacc,t);
        }
        // 经营资产自由现金流现值：
        double freeValue = v + afterValue;
        System.out.println(afterValue);
        System.out.println(v);
        System.out.println(v+afterValue);

        // 万华化学公司价值= 金融资产价值 + 长期股权投资 + 经营资产价值
        double wh_companyValue = 20836.18 + 18612.81 + afterValue;
        // 股票价值的计算公式：股权价值 = 股票价值 = 公司价值 - 公司债务
        // 上市公司股票价值 = 股权资本价值 - 少数股东权益价值 = 股权资本价值 - (1 - 少数股东权益比例)
        // 少数股东权益价值= 少数股东权益/股东权益合计 * 公司价值
        // 万华化学债务
        double wh_debit = 2313423.57;
        // 万华化学股权价值 = 公司价值-公司债务
        double wh_gqJZ = wh_companyValue - wh_debit;
        // 万华化学上市公司股票价值 = 股权价值 - (1- 少数股东权益比例)
        double wh_gpJZ = wh_gqJZ * (1-0.188);
        // 万华化学发行股份数量
        double wh_totalGF = 216233;
        // 万华化学每股内在价值 = 上市公司股票价值 / 发行的股份总数
        double wh_mgJZ = wh_gpJZ / wh_totalGF;
        System.out.println("万华化学每股内在价值："+wh_mgJZ);
    }
    

    /**
     * 功能描述:15.3.5 经营资产自由现金流现值的计算过程
     *
     * @auther: zwy
     */
    public static void caculateV2() {
        // fcf6 = fcf5 * (1+g2)
        double fcf6 = fcf0 * Math.pow(1 + g1, n) * Math.pow(1+g2,1);
        System.out.println("股权现金流：n+1 " + fcf6);
        double v = 0.0;
        // 逐步计算把
        for (int t = 0; t < 6; t++) {
            v += fcf0 * Math.pow(1 + g1, t) / Math.pow(1 + wacc, t)
                    + fcf6 / ((wacc - g2) * Math.pow(1 + wacc, n));
        }
        System.out.println("实体价值： " + v);
        System.out.println("书本预测值与本人算的预测值："+21346317.0/12704537);
    }
}

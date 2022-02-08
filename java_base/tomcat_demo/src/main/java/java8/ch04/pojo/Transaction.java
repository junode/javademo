package java8.ch04.pojo;

/**
 * @Auther: zwy
 * @Date: 2020/7/18
 * @Description: 交易信息
 * @version:
 */
public class Transaction {
    private Trader trader; // 交易员信息
    private Integer year; // 交易年限
    private Integer amount; // 交易金额

    public Transaction(Trader trader, Integer year, Integer amount) {
        this.trader = trader;
        this.year = year;
        this.amount = amount;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", amount=" + amount +
                '}';
    }
}

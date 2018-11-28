package tanghao.learning.test.innerclass;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2018/9/12 14:24
 */
public class InnerClassConvert {

    public InnerClassConvert(){
        this.busiParam = new BusiParam();
    }


    private BusiParam busiParam;

    public BusiParam getBusiParam() {
        return busiParam;
    }

    public void setBusiParam(BusiParam busiParam) {
        this.busiParam = busiParam;
    }

    private static class BusiParam{
        /** 外部订单号 */
        private String outTradeNo;
        /** 外部业务日期 */
        private String outTradeDate;
        /** 买家账号 */
        private String buyerAccountNo;
        /** 卖家账号 */
        private String sellerAccountNo;

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getOutTradeDate() {
            return outTradeDate;
        }

        public void setOutTradeDate(String outTradeDate) {
            this.outTradeDate = outTradeDate;
        }

        public String getBuyerAccountNo() {
            return buyerAccountNo;
        }

        public void setBuyerAccountNo(String buyerAccountNo) {
            this.buyerAccountNo = buyerAccountNo;
        }

        public String getSellerAccountNo() {
            return sellerAccountNo;
        }

        public void setSellerAccountNo(String sellerAccountNo) {
            this.sellerAccountNo = sellerAccountNo;
        }
    }
}

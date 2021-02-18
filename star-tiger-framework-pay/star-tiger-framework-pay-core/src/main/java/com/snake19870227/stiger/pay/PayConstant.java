package com.snake19870227.stiger.pay;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class PayConstant {

    public static final String OUT_TRADE_NO_CFG_PREFIX = "OUT_TRADE_NO_";

    public static final String OUT_REFUND_NO_CFG_PREFIX = "OUT_REFUND_NO_";

    public static class Wxpay {
        public static final String BEAN_PREFIX = "Wxpay_";

        public static final String STATUS_SUCCESS = "SUCCESS";
        public static final String STATUS_FAIL = "FAIL";

        public static final String TRADE_STATUS_SUCCESS = "SUCCESS"; // "支付成功"
        public static final String TRADE_STATUS_REFUND = "REFUND"; // 转入退款"
        public static final String TRADE_STATUS_NOTPAY = "NOTPAY"; // 未支付"
        public static final String TRADE_STATUS_CLOSED = "CLOSED"; // 已关闭"
        public static final String TRADE_STATUS_REVOKED = "REVOKED"; // 已撤销（刷卡支付）"
        public static final String TRADE_STATUS_USERPAYING = "USERPAYING"; // 用户支付中"
        public static final String TRADE_STATUS_PAYERROR = "PAYERROR"; // 支付失败(其他原因，如银行返回失败)"

        public static final String SUB_CODE_ORDERNOTEXIST = "ORDERNOTEXIST";
    }

    public static class Alipay {

        public static final String BEAN_PREFIX = "Alipay_";

        public static final String COMMON_PREFIX = BEAN_PREFIX + "Common_";
        public static final String PAGE_PREFIX = BEAN_PREFIX + "Page_";
        public static final String WAP_PREFIX = BEAN_PREFIX + "Wap_";
        public static final String APP_PREFIX = BEAN_PREFIX + "App_";

        public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        public static final String TRADE_CLOSED = "TRADE_CLOSED";
        public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
        public static final String TRADE_FINISHED = "TRADE_FINISHED";

        public static final String CODE_10000 = "10000";
        public static final String CODE_20000 = "20000";
        public static final String CODE_40004 = "40004";

        public static final String SUB_CODE_ACQ_TRADE_NOT_EXIST = "ACQ.TRADE_NOT_EXIST";

        public static final String PARAM_NAME_TIME_EXPIRE = "time_expire";

        public static final String PARAM_NAME_NOTIFY_URL = "notify_url";
        public static final String PARAM_NAME_NOTIFY_TIME = "notify_time";
        public static final String PARAM_NAME_NOTIFY_TYPE = "notify_type";
        public static final String PARAM_NAME_NOTIFY_ID = "notify_id";
        public static final String PARAM_NAME_APP_ID = "app_id";
        public static final String PARAM_NAME_TRADE_NO = "trade_no";
        public static final String PARAM_NAME_OUT_TRADE_NO = "out_trade_no";
        public static final String PARAM_NAME_TRADE_STATUS = "trade_status";
        public static final String PARAM_NAME_TOTAL_AMOUNT = "total_amount";
        public static final String PARAM_NAME_RECEIPT_AMOUNT = "receipt_amount";
        public static final String PARAM_NAME_BUYER_ID = "buyer_id";
        public static final String PARAM_NAME_BUYER_LOGON_ID = "buyer_logon_id";
        /**
         * 交易创建时间
         */
        public static final String PARAM_NAME_GMT_CREATE = "gmt_create";
        /**
         * 交易付款时间
         */
        public static final String PARAM_NAME_GMT_PAYMENT = "gmt_payment";
        /**
         * 交易退款时间
         */
        public static final String PARAM_NAME_GMT_REFUND = "gmt_refund";
        /**
         * 交易结束时间
         */
        public static final String PARAM_NAME_GMT_CLOSE = "gmt_close";
        /**
         * 公共回传参数
         */
        public static final String PARAM_NAME_PASSBACK_PARAMS = "passback_params";
    }
}

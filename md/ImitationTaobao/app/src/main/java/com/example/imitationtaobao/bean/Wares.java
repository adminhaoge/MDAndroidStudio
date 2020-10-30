package com.example.imitationtaobao.bean;

import java.util.List;

public class Wares<T> {


    /**
     * copyright : 本API接口只允许菜鸟窝(https://www.cniao5.com)用户使用,其他机构或者个人使用均为侵权行为
     * totalCount : 22
     * currentPage : 1
     * totalPage : 0
     * pageSize : 10
     * orders : [{"orderType":"DESC","field":"sale"}]
     * list : [{"id":262,"categoryId":10,"campaignId":3,"name":"苏泊尔（supor）炒锅少烟不粘锅电磁炉燃气通用32cm炒菜锅具EC32FP01","imgUrl":"http://m.360buyimg.com/n4/jfs/t2302/348/574767572/148829/2ad5db57/5618ce19N91191a4d.jpg!q70.jpg","price":189,"sale":8846},{"id":255,"categoryId":10,"campaignId":17,"name":"苏泊尔 火红点2代 无油烟不粘锅炒锅 燃气灶专用 32CM PC32R4","imgUrl":"http://m.360buyimg.com/n4/jfs/t1756/207/931298198/63906/cbb7b47a/55de7fdbN691d0723.jpg!q70.jpg","price":0,"sale":8629},{"id":250,"categoryId":10,"campaignId":3,"name":"尚尼（Serafinozani）18/10不锈钢长柄炒锅 芬兰30cm 不沾少油烟","imgUrl":"http://m.360buyimg.com/n4/jfs/t2395/321/181622585/60202/b1aaa1a3/55f619a9N56ceb0d6.jpg!q70.jpg","price":0,"sale":8510},{"id":270,"categoryId":10,"campaignId":14,"name":"ADC麦饭石压铸炒锅无油烟铝锅 电磁炉炒锅不粘锅无烟不沾炒菜锅具明火通用 30cm黑色款","imgUrl":"http://m.360buyimg.com/n4/jfs/t2275/327/715634690/558534/9ba0c65f/5625e806N0e0fd92a.jpg!q70.jpg","price":258,"sale":8133},{"id":263,"categoryId":10,"campaignId":1,"name":"炊大皇健康无油烟不粘炒锅32cm电磁炉通用","imgUrl":"http://m.360buyimg.com/n4/jfs/t1309/362/862588466/237677/e3715adc/55aa1c89N245fac24.jpg!q70.jpg","price":99,"sale":7932},{"id":258,"categoryId":10,"campaignId":7,"name":"钻技ZUANJ炒锅不粘锅韩国进口30cm无油烟无涂层所有炉具通用","imgUrl":"http://m.360buyimg.com/n4/jfs/t1570/101/578967155/219307/625d2bd/5599e585N527c1f04.jpg!q70.jpg","price":0,"sale":7438},{"id":264,"categoryId":10,"campaignId":15,"name":"居家夫人高端优质不锈钢大炒锅不粘锅无油烟无涂层厨房锅具电磁炉通用","imgUrl":"http://m.360buyimg.com/n4/jfs/t1234/309/518347477/97941/24718259/552b325cNb4c75de8.jpg!q70.jpg","price":358,"sale":7399},{"id":257,"categoryId":10,"campaignId":12,"name":"钻技ZUANJ韩国进口炒锅不粘锅无油烟无涂层电磁炉炒菜无烟锅","imgUrl":"http://m.360buyimg.com/n4/jfs/t1264/255/1408825040/316388/a780e140/559b62edN80565164.jpg!q70.jpg","price":0,"sale":7344},{"id":265,"categoryId":10,"campaignId":16,"name":"意达尔无油烟平底不粘锅炒锅不粘锅炒菜锅不锈钢玻璃盖燃/煤气电磁炉通用炒锅30厘米","imgUrl":"http://m.360buyimg.com/n4/jfs/t1729/267/631106206/28794/cc1772c3/55d52eb7Ncdb5fb45.jpg!q70.jpg","price":168.98,"sale":6215},{"id":267,"categoryId":10,"campaignId":8,"name":"堡迪斯 304不锈钢炒锅不粘锅无烟锅无涂层平底无油烟电磁炉锅具通用30cm","imgUrl":"http://m.360buyimg.com/n4/jfs/t1981/182/761271594/161701/c9116f16/562746a9N35ca7840.jpg!q70.jpg","price":199,"sale":5920}]
     */

    private String copyright;
    private int totalCount;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<OrdersBean> orders;
    private List<T> list;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static class OrdersBean {
        /**
         * orderType : DESC
         * field : sale
         */

        private String orderType;
        private String field;

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

}

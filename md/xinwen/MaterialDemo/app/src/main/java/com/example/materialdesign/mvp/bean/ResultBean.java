package com.example.materialdesign.mvp.bean;

import java.util.List;

public class ResultBean {

    /**
     * date : 20200730
     * stories : [{"image_hue":"0x0a0807","title":"上海博物馆那么多青铜器从哪来的？","url":"https://daily.zhihu.com/story/9726393","hint":"信古斋主人 · 3 分钟阅读","ga_prefix":"073007","images":["https://pic2.zhimg.com/v2-c2d140a8ed832d3532887d989c3125b1.jpg"],"type":0,"id":9726393},{"image_hue":"0xa46846","title":"猫以高难度的姿势睡觉真的舒服吗？（多图）","url":"https://daily.zhihu.com/story/9726399","hint":"猫研所 · 1 分钟阅读","ga_prefix":"073007","images":["https://pic1.zhimg.com/v2-72e241626a25dcc94957d0fef29e08a8.jpg"],"multipic":true,"type":0,"id":9726399},{"image_hue":"0xb3837d","title":"「坐月子」喝酒酿真的有用吗？","url":"https://daily.zhihu.com/story/9726388","hint":"营养师顾中一 · 3 分钟阅读","ga_prefix":"073007","images":["https://pic4.zhimg.com/v2-d2f96cffefeddcd95f05cc181133d0fb.jpg"],"type":0,"id":9726388},{"image_hue":"0x917244","title":"用高能粒子轰击重元素制作黄金，能赚钱吗？","url":"https://daily.zhihu.com/story/9726407","hint":"赵东东 · 3 分钟阅读","ga_prefix":"073007","images":["https://pic4.zhimg.com/v2-54cd822d404d94167268231b679feda3.jpg"],"type":0,"id":9726407},{"image_hue":"0x7d99b3","title":"离子溶液导电，为什么可以是透明的？","url":"https://daily.zhihu.com/story/9726412","hint":"Luyao Zou · 3 分钟阅读","ga_prefix":"073007","images":["https://pic1.zhimg.com/v2-f10560620d4da436dbdf8c631a7fd878.jpg"],"type":0,"id":9726412},{"image_hue":"0xb39e7d","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9726414","hint":"VOL.2452","ga_prefix":"073006","images":["https://pic1.zhimg.com/v2-4e2bf6983ce1a97ca1db1ab27f07243c.jpg"],"type":0,"id":9726414}]
     * top_stories : [{"image_hue":"0x383b29","hint":"作者 / 磅礴科技","url":"https://daily.zhihu.com/story/9726376","image":"https://pic2.zhimg.com/v2-7f73a4d2c8e2ec0cdd6971ae63bbd2c5.jpg","title":"如何自己组装台式机？","ga_prefix":"072907","type":0,"id":9726376},{"image_hue":"0x7daab3","hint":"作者 / 混乱博物馆","url":"https://daily.zhihu.com/story/9726341","image":"https://pic1.zhimg.com/v2-22ea5f108e79162ff543edcbb0844204.jpg","title":"吃菠萝泡盐水有什么用？","ga_prefix":"072807","type":0,"id":9726341},{"image_hue":"0x64688f","hint":"作者 / 把科学带回家","url":"https://daily.zhihu.com/story/9726312","image":"https://pic3.zhimg.com/v2-e26112f69109debd572b4915cd765c72.jpg","title":"为什么纸片割手那么疼？","ga_prefix":"072707","type":0,"id":9726312},{"image_hue":"0xb3a97d","hint":"作者 / Justin Lee","url":"https://daily.zhihu.com/story/9726196","image":"https://pic1.zhimg.com/v2-732d2b5444a31b48a3c09dd17f629bec.jpg","title":"对电话销售需要礼貌回复吗？","ga_prefix":"072407","type":0,"id":9726196},{"image_hue":"0x382b27","hint":"作者 / 二氧化硅","url":"https://daily.zhihu.com/story/9726157","image":"https://pic3.zhimg.com/v2-280c45d935e12ebccb9631817ca188e6.jpg","title":"淘宝上那些十几块钱的衣服能穿么？","ga_prefix":"072307","type":0,"id":9726157}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;


    @Override
    public String toString() {
        return "ResultBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x0a0807
         * title : 上海博物馆那么多青铜器从哪来的？
         * url : https://daily.zhihu.com/story/9726393
         * hint : 信古斋主人 · 3 分钟阅读
         * ga_prefix : 073007
         * images : ["https://pic2.zhimg.com/v2-c2d140a8ed832d3532887d989c3125b1.jpg"]
         * type : 0
         * id : 9726393
         * multipic : true
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private boolean multipic;
        private List<String> images;

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "image_hue='" + image_hue + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", hint='" + hint + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0x383b29
         * hint : 作者 / 磅礴科技
         * url : https://daily.zhihu.com/story/9726376
         * image : https://pic2.zhimg.com/v2-7f73a4d2c8e2ec0cdd6971ae63bbd2c5.jpg
         * title : 如何自己组装台式机？
         * ga_prefix : 072907
         * type : 0
         * id : 9726376
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image_hue='" + image_hue + '\'' +
                    ", hint='" + hint + '\'' +
                    ", url='" + url + '\'' +
                    ", image='" + image + '\'' +
                    ", title='" + title + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    '}';
        }

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

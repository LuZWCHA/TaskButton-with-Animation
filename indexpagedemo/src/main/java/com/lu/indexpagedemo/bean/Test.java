package com.lu.indexpagedemo.bean;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class Test {


    public static class DataBean {
        /**
         * id : 20
         * nickname : zimo
         * title : 666
         * content : 你好
         * time : 2017-04-07 23:33:22
         * star : 0
         * collection : 0
         * image : /owap/public/patent/20170407/76666051c8638f5ee9e20fb5b26b90b9.png
         * price : 40
         */

        private int id;
        private String nickname;
        private String title;
        private String content;
        private String time;
        private int star;
        private int collection;
        private String image;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}

package com.lu.indexpagedemo.view.simpleviews.fixtures;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

/*
 * Created by Anton Bevza on 1/13/17.
 */
abstract class FixturesData {

    static SecureRandom rnd = new SecureRandom();

    static ArrayList<String> avatars = new ArrayList<String>() {
        {
            add("http://i.imgur.com/pv1tBmT.png");
            add("http://i.imgur.com/R3Jm1CL.png");
            add("http://i.imgur.com/ROz4Jgh.png");
            add("http://i.imgur.com/Qn9UesZ.png");
        }
    };

    static final ArrayList<String> groupChatImages = new ArrayList<String>() {
        {
            add("http://i.imgur.com/hRShCT3.png");
            add("http://i.imgur.com/zgTUcL3.png");
            add("http://i.imgur.com/mRqh5w1.png");
        }
    };

    static final ArrayList<String> groupChatTitles = new ArrayList<String>() {
        {
            add("晓峰, 小李");
            add("阿田, 小阳, BOBO");
            add("VV, 路人甲, 路人乙, 阿宽");
        }
    };

    static final ArrayList<String> names = new ArrayList<String>() {
        {
            add("小李");
            add("晓峰");
            add("阿田");
            add("阿宽");
            add("VV");
            add("BOBO");
            add("小阳");
            add("客服1");
            add("客服2");
            add("路人甲");
            add("路人乙");
        }
    };

    static final ArrayList<String> messages = new ArrayList<String>() {
        {
            add("你好！");
            add("这是我的电话");
            add("这是我的 e-mail - myemail@example.com");
            add("嘿，你好！");
            add("这只是一个测试.");
            add("请问这围巾怎么样？");
            add("围巾要怎么定制？");
            add("围巾定制贵吗？");
            add("这个app做得实在太好了！");
            add("VR商城的Web服务太周到了！");
            add("围巾的展示方式实在太奇妙了，真的很喜欢！");
        }
    };

    static final ArrayList<String> images = new ArrayList<String>() {
        {
            add("https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg");
            add("http://www.designboom.com/wp-content/uploads/2015/11/stefano-boeri-architetti-vertical-forest-residential-tower-lausanne-switzerland-designboom-01.jpg");
        }
    };

    static final ArrayList<String> images2=new ArrayList<String>(){
        {
            add("http://imgwww.heiguang.net/uploadfile/2016/0302/20160302095417572.jpg");
            add("http://imgwww.heiguang.net/uploadfile/2016/0302/20160302095426349.jpg");
            add("http://imgwww.heiguang.net/uploadfile/2016/0302/20160302095434989.jpg");
            add("http://imgwww.heiguang.net/uploadfile/2016/0302/20160302095627103.jpg");
            add("http://www.ismxx.com/uploads/allimg/170420/2100359204_0.jpg");
            add("http://www.ismxx.com/uploads/allimg/170420/21012515B_0.jpg");
            add("http://www.ismxx.com/uploads/allimg/170420/205934bD_0.jpg");
            add("http://img.mp.itc.cn/upload/20161104/00e67e2e970e4039a2b3cd2018ea8353_th.jpg");
            add("http://img.mp.itc.cn/upload/20161104/7a234861193f423b9cf10a09007623a7_th.jpg");
        }
    };

    static String getRandomArticleImage(){
        return images2.get(rnd.nextInt(images2.size()));
    }

    static Long getRandomId() {
        return UUID.randomUUID().getLeastSignificantBits();
    }

    static String getRandomAvatar() {
        return avatars.get(rnd.nextInt(avatars.size()));
    }

    static String getRandomGroupChatImage() {
        return groupChatImages.get(rnd.nextInt(groupChatImages.size()));
    }

    static String getRandomGroupChatTitle() {
        return groupChatTitles.get(rnd.nextInt(groupChatTitles.size()));
    }

    static String getRandomName() {
        return names.get(rnd.nextInt(names.size()));
    }

    static String getRandomMessage() {
        return messages.get(rnd.nextInt(messages.size()));
    }

    static String getRandomImage() {
        return images.get(rnd.nextInt(images.size()));
    }

    static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }
}

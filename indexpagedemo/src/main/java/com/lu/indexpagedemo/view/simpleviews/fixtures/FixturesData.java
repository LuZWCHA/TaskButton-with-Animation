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

    static final ArrayList<String> image3 = new ArrayList<String>(){
        {
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=822648e4d60735fa91f04eb1ae500f9f/1061ec24b899a9016536b1c91f950a7b0308f59b.jpg");
            add("http://img.hb.aicdn.com/6f6f6f0e4209e3e88c62eb7934980592a8e017a3bb2aa-t3LQMo_fw658");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=6e54a43b08d162d985ee621421dea950/35d6ec50352ac65c7c648c8cf9f2b21192138ab9.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=38cf28f9adc379317d688621dbc5b784/5e6c239759ee3d6d15c46d3441166d224e4adea4.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=eed05278d2a20cf44690fed746084b0c/c3ee6981800a19d80b6c51fa31fa828ba71e46b7.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=7a3e1122a964034f0fcdc20e9fc17980/f852948fa0ec08fa60f8cc585bee3d6d54fbda22.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=46f503ebba99a9013b355b3e2d970a58/0c6a5eb5c9ea15ce6f163ff6b4003af33b87b222.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=62273b07a586c91708035231f93f70c6/f4bcae64034f78f000db450c7b310a55b1191cca.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=a76281040846f21fc9345e5bc6256b31/3650243fb80e7bec244fcc1e2d2eb9389a506b0d.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=30543901c1fdfc03e578e3b0e43e87a9/45c1f303738da977c1ba49d4b251f8198718e30e.jpg");
            add("http://imgsrc.baidu.com/forum/w%3D580/sign=7c5f4f0160d9f2d3201124e799ed8a53/e601302ac65c103854e8be22b0119313b17e8908.jpg");
            add("http://img.hb.aicdn.com/b346a3dfe6cac8bce9682adb399da8772ef216ee1927c-lZYArD_fw658");
            add("http://img.hb.aicdn.com/9efe8cb93a30a052a17a4aa7a5d7f20827b81c5c89875-hFzHWY_fw658");
            add("http://img.hb.aicdn.com/2531235a36571cae099edba9bd7b4d158a3194ed8d00e-JVh7IW_fw658");
            add("http://img.hb.aicdn.com/c9464e1d112ffbca6a03dafdb7de17012519234e3933e-UT7X7Y_fw658");
            add("http://img.hb.aicdn.com/b54711bee634b826122e8ae7f3c1a5d9bbdba695148c7-Vzg9Na_fw658");
            add("http://img.hb.aicdn.com/30c38da0e212333dc4355913b2710ee90f64d61b37ccf-dxeSuO_fw658");
            add("http://img.hb.aicdn.com/ec0dc0f218c338ae6e8651ade107c6d5f1e96b1d69c7c-dRoLGA_fw658");
            add("http://img.hb.aicdn.com/9230443bff427516085cf17282d54939468e4eb318199-ZIJyCb_fw658");
            add("http://img.hb.aicdn.com/0d42c0ed59ee433e4902d20222c03d0db260ce01fc3c-ORKt2q_fw658");
            add("http://img.hb.aicdn.com/8990508fc8e08e274e492fe4fcb973b2ba7d3fcb47881-6Igqml_fw658");
            add("http://img.hb.aicdn.com/7ccfbf551833626770c54bb918dc801cf6e320e9249c7-zprbNd_fw658");
            add("http://img.hb.aicdn.com/aaf4f6e8df8be9dcdba52d211f7adc6ecbbed89914511-lBjTxr_fw658");
            add("http://img.hb.aicdn.com/5db42f539fa31320c37472454c1acf2d3a8aa03b13e73-o0VL3U_fw658");
            add("http://img.hb.aicdn.com/5df6307901bf592a714e244b7a5761ec7e404749116bc-fq0Dqd_fw658");
            add("http://img.hb.aicdn.com/6383af499a79cc751c87a31ac44cfa73cad62402197d6-wuQQJX_fw658");
            add("http://img.hb.aicdn.com/9d6a0deb1919f4c22c71765190dbf25347d1de89201e8-9wKZTZ_fw658");
            add("http://img.hb.aicdn.com/8b598191377c2b09cbacdb2302568c0ecb6b93cb66f9-jDe5DM_fw658");
            add("http://img.hb.aicdn.com/42cbd5fa00e338054e494f7d01430b2f909b5a21403e2-JHwS4t_fw658");
            add("http://img.hb.aicdn.com/be82865ac675ed9b56d6fddfede344746babd9bb306fa-lkOw51_fw658");
            add("http://img.hb.aicdn.com/7d944ddfd58f13b9ffa27773ce0fce23d8f8a74b1e26d-g7BJh8_fw658");
            add("http://img.hb.aicdn.com/78774fa9861ca5b053697230a08c1d59a4470e069c03d-1WgcmS_fw658");
            add("http://img.hb.aicdn.com/31279ba5019fd69ae034dddd569e644bf1ad2eee6b062-DRt8Bf_fw658");
            add("http://img.hb.aicdn.com/bcb9f44c50bbde762534b4dfa0d86e01540b7d0a72e7a-2IBWVS_fw658");
            add("http://img.hb.aicdn.com/773b23da3c96bea3212e3b37fc3a5462555b90563b944-m5xSek_fw658");
        }
    };

    static String getRandomArticleImage(){
        return images2.get(rnd.nextInt(images2.size()));
    }

    static String getRandomMaterialImage(){
        return image3.get(rnd.nextInt(image3.size()));
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

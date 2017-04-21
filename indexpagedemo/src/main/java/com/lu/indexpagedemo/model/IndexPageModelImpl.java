package com.lu.indexpagedemo.model;
import com.lu.indexpagedemo.contract.IndexPageContract;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/06
*/

public class IndexPageModelImpl implements IndexPageContract.Model{

    @Override
    public Observable<ArrayList<String>> getRollViewPagerFromLocation() {
        // TODO: 2017/4/15 本地数据存储
        return getRollViewPagerFromNetWork();
    }

    @Override
    public Observable<ArrayList<String>> getRollViewPagerFromNetWork()
    {
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        imageUrls.add("https://images.pexels.com/photos/316591/pexels-photo-316591.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        imageUrls.add("https://images.pexels.com/photos/279473/pexels-photo-279473.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        imageUrls.add("https://images.pexels.com/photos/273065/pexels-photo-273065.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        imageUrls.add("https://images.pexels.com/photos/195532/pexels-photo-195532.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        return Observable.just(imageUrls);
    }

}
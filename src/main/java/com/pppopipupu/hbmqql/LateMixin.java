package com.pppopipupu.hbmqql;

import com.google.common.collect.Lists;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
/*
卧槽，原来一mixin其他mod就崩溃是因为少了这个类........
这个问题困扰了我一个学期！！！！
其实最开始那个项目我也写了这个，但是屁用没有，我还以为他没用...
换了个项目就行了
码魂是很重要的
2024/1/17
*/


public class LateMixin implements ILateMixinLoader{

    @Override
    public List<String> getMixinConfigs() {
        return   Lists.newArrayList("mixins.hbmqql.json");
    }
}

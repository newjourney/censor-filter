package com.zxk.censor.ac;

import java.util.HashMap;
import java.util.Map;

/**
 * AC自动机节点
 * @author xingkai.zhang
 * @date 2020/7/27
 */
public class ACNode {
    char data;
    String string;
    Map<Character, ACNode> children = new HashMap<>();
    boolean isEndingChar = false;
    ACNode fail;

    public ACNode(char data) {
        this.data = data;
        this.string = String.valueOf(data);
    }

    public ACNode(char data, String string) {
        this.data = data;
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}


package com.zxk.censor.ac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 基于AC自动机的字典树
 *
 * @author xingkai.zhang
 * @date 2020/7/27
 */

public class ACTree {
    private final ACNode root = new ACNode('/');

    public void insert(String text) {
        insert(text.toCharArray());
    }

    public void insert(char[] text) {
        ACNode p = root;
        StringBuilder s = new StringBuilder();
        for (char c : text) {
            s.append(c);
            if (p.children.get(c) == null) {
                ACNode newNode = new ACNode(c, s.toString());
                p.children.put(c, newNode);
            }
            p = p.children.get(c);
        }
        p.isEndingChar = true;
    }

    public List<String> match(String text) {
        return match(text.toCharArray());
    }

    public List<String> match(char[] text) {
        List<String> ret = new ArrayList<>();
        ACNode p = root;
        for (char ci : text) {
            while (p.children.get(ci) == null && p != root) {
                p = p.fail; // 失败指针在这里发挥作用
            }
            p = p.children.get(ci);
            if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配
            ACNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndingChar) {
                    ret.add(tmp.string);
                }
                tmp = tmp.fail;
            }
        }
        return ret;
    }

    public void buildFailurePointer() {
        Queue<ACNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            ACNode p = queue.remove();
            for (ACNode pc : p.children.values()) {
                if (p == root) {
                    pc.fail = root;
                } else {
                    ACNode q = p.fail;
                    while (q != null) {
                        ACNode qc = q.children.get(pc.data);
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

}


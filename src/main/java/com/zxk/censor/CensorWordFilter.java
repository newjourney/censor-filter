package com.zxk.censor;

import com.zxk.censor.ac.ACTree;

import java.util.List;

public class CensorWordFilter {

    public static class Builder {
        private final CensorWordFilter filter;
        private Builder() {
            this.filter = new CensorWordFilter();
            this.filter.tree = new ACTree();
        }
        public Builder addWord(String word) {
            filter.tree.insert(word);
            return this;
        }
        public CensorWordFilter build() {
            filter.tree.buildFailurePointer();
            return filter;
        }
    }

    private ACTree tree;
    private CensorWordFilter() {}

    public boolean isSensitive(String text) {
        return !tree.match(text).isEmpty();
    }

    public String replaceSensitive(String text, String replacement) {
        List<String> matched = tree.match(text);
        for (String s : matched) {
            text = text.replace(s, replacement);
        }
        return text;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

}


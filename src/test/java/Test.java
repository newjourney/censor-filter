import com.zxk.censor.CensorWordFilter;

public class Test {

    public static void main(String[] args) {
        CensorWordFilter filter = CensorWordFilter.newBuilder()
                .addWord("abc")
                .addWord("ef")
                .addWord("http://123.com")
                .build();

        String text = "link: http://123.com abcdef";
        System.out.println(filter.isSensitive(text));
        System.out.println(filter.replaceSensitive(text, "***"));
    }

}

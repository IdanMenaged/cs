package news_paper;

public class Article {
    private String writer; // name of the writer
    private String headLine; // the title
    private int num; // n of words in the article

    /**
     * constructor
     * @param writer name of the writer
     * @param headLine title
     * @param num n of words in the article
     */
    public Article(String writer, String headLine, int num) {
        this.writer = writer;
        this.headLine = headLine;
        this.num = num;
    }

    /**
     * copy constructor
     * @param article article to copy
     */
    public Article(Article article) {
        this.writer = article.writer;
        this.headLine = article.headLine;
        this.num = article.num;
    }

    /**
     * get writer name
     * @return writer name
     */
    public String getWriter() {
        return this.writer;
    }

    /**
     * get num
     * @return num
     */
    public int getNum() {
        return this.num;
    }

    /**
     * check if both articles have the same writer
     * this method is in the Article class since it acts on an article
     * @param article another article
     * @return true if same writer, otherwise false
     */
    public boolean sameWriter(Article article) {
        return this.writer.equals(article.writer);
    }
}

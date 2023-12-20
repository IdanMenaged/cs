package news_paper;

import java.util.Scanner;

public class NewsPaper {
    public static Scanner reader = new Scanner(System.in);

    private String name; // name of the paper
    private Date date; // publish date
    private Article[] articles; // all the articles in the paper

    /**
     * copy constructor
     * @param newsPaper newspaper to copy
     */
    public NewsPaper(NewsPaper newsPaper) {
        this.name = newsPaper.name;
        this.date = new Date(newsPaper.date);

        this.articles = new Article[newsPaper.articles.length];
        int i;
        for (i = 0; i < newsPaper.articles.length; i++) {
            this.articles[i] = new Article(newsPaper.articles[i]);
        }
    }

    /**
     * constructor
     * @param name name of the paper
     * @param date publish date
     * @param articles articles in the paper
     */
    public NewsPaper(String name, Date date, Article[] articles) {
        this.name = name;
        this.date = new Date(date);

        this.articles = new Article[articles.length];
        int i;
        for (i = 0; i < articles.length; i++) {
            this.articles[i] = new Article(articles[i]);
        }
    }

    /**
     * constructor that takes user input for the articles
     * @param name name of the paper
     * @param date publish date
     * @param nArticles n of articles
     */
    public NewsPaper(String name, Date date, int nArticles) {
        this.name = name;
        this.date = new Date(date);

        this.articles = new Article[nArticles];
        int i, num;
        String writer, headLine;
        for (i = 0; i < nArticles; i++) {
            System.out.println("Please enter writer");
            writer = reader.next();
            System.out.println("Please enter headline");
            headLine = reader.next();
            System.out.println("Please enter number of words");
            num = reader.nextInt();

            this.articles[i] = new Article(writer, headLine, num);
        }
    }

    /**
     * sets articles
     * @param articles articles
     */
    public void setArticles(Article[] articles) {
        this.articles = new Article[articles.length];
        int i;
        for (i = 0; i < articles.length; i++) {
            this.articles[i] = new Article(articles[i]);
        }
    }

    /**
     * gets articles
     * @return articles
     */
    public Article[] getArticles() {
        Article[] out = new Article[this.articles.length];
        int i;
        for (i = 0; i < this.articles.length; i++) {
            out[i] = new Article(this.articles[i]);
        }

        return out;
    }

    /**
     * get name of paper
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sums up the total number of words in the paper
     * this method is in the NewsPaper class since it needs access to all the articles
     * @return n of words
     */
    public int sumNum() {
        int sum = 0;

        for (Article article :
                this.articles) {
            sum += article.getNum();
        }

        return sum;
    }

    /**
     * find the number of articles written by a writer
     * is in the NewsPaper class since it needs access to all the articles
     * @param writer name of the writer
     * @return n of articles written by that writer
     */
    public int writerArticle(String writer) {
        int count = 0;

        for (Article article :
                this.articles) {
            if (article.getWriter().equals(writer)) {
                count++;
            }
        }

        return count;
    }
}

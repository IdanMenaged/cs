package news_paper;

import java.util.Scanner;

public class GoodNews {
    public static Scanner reader = new Scanner(System.in);

    /**
     * get user input for 2 papers
     * print name of the paper with more words
     */
    public static void main(String[] args) {
        NewsPaper[] papers = new NewsPaper[2];
        int day, month, year, nArticles, i;
        String name;

        // input
        for (i = 0; i < papers.length; i++) {
            System.out.println("Please enter day");
            day = reader.nextInt();
            System.out.println("Please enter month");
            month = reader.nextInt();
            System.out.println("Please enter year");
            year = reader.nextInt();

            System.out.println("Please enter name");
            name = reader.next();
            System.out.println("Please enter number of articles");
            nArticles = reader.nextInt();

            papers[i] = new NewsPaper(name, new Date(day, month, year), nArticles);
        }

        // find best
        System.out.println("better paper name: " + betterPaper(papers[0], papers[1]));
    }

    /**
     * find the paper with more words
     * @param newsPaper1 a newspaper
     * @param newsPaper2 a second newspaper
     * @return name of the paper with more words. if both have the same number of words, the name of the first followed
     * by a space followed by the name of the second
     */
    public static String betterPaper(NewsPaper newsPaper1, NewsPaper newsPaper2) {
        if (newsPaper1.sumNum() > newsPaper2.sumNum()) {
            return newsPaper1.getName();
        } else if (newsPaper1.sumNum() < newsPaper2.sumNum()) {
            return newsPaper2.getName();
        } else {
            return newsPaper1.getName() + " " + newsPaper2.getName();
        }
    }
}

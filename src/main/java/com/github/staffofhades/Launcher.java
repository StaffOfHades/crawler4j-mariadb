package com.github.staffofhades;

import com.github.staffofhades.crawler.MariaDBCrawlerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Launcher {

    public static void main( String[] args ) throws Exception {
        
        String projectRoot = "/Users/mauriciog/Downloads/Recuperacion de la Información/crawler4j-mariadb/";
        String path = projectRoot + "src/main/java/com/github/staffofhades";
        String crawlStorageFolder = path + "/data/crawl/root";
        int numberOfCrawlers = 4;
        int politnessDelay = 150;
        int maxDepthOfCrawling = 4;

        CrawlConfig config = new CrawlConfig();

        config.setPolitenessDelay( politnessDelay );
        config.setMaxDepthOfCrawling( maxDepthOfCrawling );
        config.setCrawlStorageFolder( crawlStorageFolder );
        config.setIncludeHttpsPages( true );

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher( config );
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer( robotstxtConfig, pageFetcher );
        CrawlController controller = new CrawlController(
            config,
            pageFetcher,
            robotstxtServer
        );

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed( "https://de.wikipedia.org/wiki/Java_Database_Connectivity" );
        controller.addSeed( "https://de.wikipedia.org/wiki/Relationale_Datenbank" );
        controller.addSeed( "https://pt.wikipedia.org/wiki/JDBC" );
        controller.addSeed( "https://pt.wikipedia.org/wiki/Protocolo" );
        controller.addSeed( "https://de.wikipedia.org/wiki/Datenbank" );

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */

        controller.start(
            new MariaDBCrawlerFactory(
                "jdbc:mariadb://localhost:3306/ir_web",
                "root",
                null
            ),
            numberOfCrawlers
        );
    }

}

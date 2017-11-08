package com.github.staffofhades.crawler;

import com.github.staffofhades.db.impl.MariaDBServiceImpl;

import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by rz on 03.06.2016.
 * Modified by StaffOfHades on 07.11.207
 */
public class MariaDBCrawlerFactory 
    implements CrawlController.WebCrawlerFactory<MariaDBWebCrawler> {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPw;

    public MariaDBCrawlerFactory( String dbUrl, String dbUser, String dbPw ) {
        
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPw = dbPw;
    }

    public MariaDBWebCrawler newInstance() throws Exception {
        return new MariaDBWebCrawler(
            new MariaDBServiceImpl(
                dbUrl,
                dbUser,
                dbPw,
                "org.mariadb.jdbc.Driver"
            )
        );
    }
}

package org.appbricks;

import com.github.mongobee.Mongobee;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Mongo configuration
 */
@Configuration
@EnableMongoRepositories
public class MongoConfiguration
    extends AbstractMongoConfiguration {

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
     */
    @Override
    protected String getDatabaseName() {
        return "users";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#mongo()
     */
    @Override
    public Mongo mongo()
        throws Exception {

        Mongo mongo = new MongoClient();
        mongo.setWriteConcern(WriteConcern.SAFE);

        return mongo;
    }

    @Bean
    public Mongobee mongobee()
        throws Exception {

        Mongobee runner = new Mongobee(this.mongo());
        runner.setDbName(this.getDatabaseName());
        runner.setChangeLogsScanPackage("org.appbricks.repository.changelogs");

        return runner;
    }
}

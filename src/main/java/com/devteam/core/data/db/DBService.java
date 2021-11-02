package com.devteam.core.data.db;

import com.devteam.core.data.db.query.SearchFilter;
import com.devteam.lib.util.error.ErrorType;
import com.devteam.lib.util.error.RuntimeError;
import com.devteam.module.common.ClientInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DBService {
    @Autowired
    private ApplicationContext context;

    @Autowired(required = false)
    private List<DBServicePlugin> plugins;

    @Value("${hibernate.dialect:#{null}}")
    private String dialect;

    @PostConstruct
    public void onInit() {
        SearchFilter.supportILike(dialect);
    }

    public <T> void initDb(ClientInfo client, boolean initSampleData) throws Exception {
        if (plugins == null)
            return;
        for (DBServicePlugin plugin : plugins) {
            initDb(client, plugin);
        }

        if (initSampleData) {
            for (DBServicePlugin plugin : plugins) {
                createSampleData(client, plugin);
            }
        }

        for (DBServicePlugin plugin : plugins) {
            Logger logger = plugin.getLogger();
            logger.info("Devteam postInitDb(...)");
            plugin.postInitDb(client, this, context, initSampleData);
        }
    }

    public <T> void initDb(ClientInfo client, T vionCtx, boolean initSampleData) {
        if (plugins == null)
            return;
        try {
            for (DBServicePlugin plugin : plugins) {
                initDb(client, vionCtx, plugin);
            }
            if (initSampleData) {
                for (DBServicePlugin plugin : plugins) {
                    initSample(client, vionCtx, plugin);
                }
            }
        } catch (Throwable error) {
            error.printStackTrace();
            throw new RuntimeError(ErrorType.Unknown, "Cannot create data in database", error);
        }
    }

    public void initDb(ClientInfo client, DBServicePlugin plugin) throws Exception {
        Logger log = plugin.getLogger();
        log.info("Devteam initDb()");
        plugin.initDb(client, context);
    }

    public void createSampleData(ClientInfo client, DBServicePlugin plugin) throws Exception {
        Logger logger = plugin.getLogger();
        logger.info("Devteam createSampleData(...)");
        plugin.createSammpleData(client, context);
    }

    public <T> void initDb(ClientInfo client, T vionCtx, DBServicePlugin plugin) throws Exception {
        Logger logger = plugin.getLogger();
        logger.info("Devteam initDb()");
        plugin.initDb(client, vionCtx, context);
    }

    public <T> void initSample(ClientInfo client, T vionCtx, DBServicePlugin plugin) throws Exception {
        Logger logger = plugin.getLogger();
        logger.info("Devteam createSampleData(...)");
        plugin.createSammpleData(client, vionCtx, context);
    }
}

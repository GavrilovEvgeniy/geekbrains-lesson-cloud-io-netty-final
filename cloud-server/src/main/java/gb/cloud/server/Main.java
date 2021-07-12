package gb.cloud.server;

import gb.cloud.server.factory.Factory;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import gb.cloud.server.service.impl.utils.ServerProperties;

public class Main {

    public static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        ServerProperties.loadProperties();

        Flyway flyway = Flyway.configure().dataSource(ServerProperties.dbconnection, ServerProperties.dblogin, ServerProperties.dbpassword).load();    // baselineOnMigrate(true) добавить при непустой базе без истории миграций.
        flyway.migrate();

        Factory.getServerService().startServer(Integer.parseInt(ServerProperties.serverport));
    }

}

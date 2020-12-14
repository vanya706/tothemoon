import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.parseInt(System.getenv("TOMCAT_PORT")));

        Context ctx = tomcat.addWebapp("", new File("src/main/webapp/").getAbsolutePath());

        WebResourceRoot resource = new StandardRoot(ctx);
        resource.addPreResources(
                new DirResourceSet(resource, "/WEB-INF/classes",
                        new File("target/classes").getAbsolutePath(),"/")
        );
        ctx.setResources(resource);

        tomcat.enableNaming();
        tomcat.getConnector();

        tomcat.start();
        tomcat.getServer().await();
    }
}

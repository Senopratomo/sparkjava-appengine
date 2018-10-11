package org.senolab.sparkdatastore;

import spark.servlet.SparkApplication;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import static spark.Spark.get;


public class Main implements SparkApplication {

    public static void main(String[] args) {
        new Main().init();
    }

    @Override
    public void init() {
        get("/", (request, response) -> "Hello from SparkJava running on GAE Standard Java8 runtime.");

        get("/hello/:name", (request, response) -> {
            return "SparkJava running on GAE Java8 says: Hello: " + request.params(":name");
        });
    }
    // Use Servlet annotation to define the Spark filter without web.xml:
    @WebFilter(
            filterName = "SparkInitFilter", urlPatterns = {"/*"},
            initParams = {
                    @WebInitParam(name = "applicationClass", value = "org.senolab.sparkdatastore.Main")
            })
    public static class SparkInitFilter extends spark.servlet.SparkFilter {
    }
}

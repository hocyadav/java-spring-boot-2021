package managed;

import io.dropwizard.lifecycle.Managed;

/**
 * @Author Hariom Yadav
 * @create 5/24/2021
 * https://www.dropwizard.io/en/latest/manual/core.html#managed-objects
 */
public class MyClientStartAndStop implements Managed {
    //1 create client obj field
    //2. set inside constructor - we will call from Application#run()
    //3. environment.lifecycle().manage(client instance) // now life cycle of our client is managed by HTTP server
    //4. during app startup if start will throw an exception then app will not start,
    // during app stop time if stop will throw an exception then app will stop

    @Override
    public void start() throws Exception {
        System.out.println("client instance started");//when app will start then we can see this log
    }

    @Override
    public void stop() throws Exception {
        System.out.println("client instance stop");//when app will stop then we can see this log
    }
}

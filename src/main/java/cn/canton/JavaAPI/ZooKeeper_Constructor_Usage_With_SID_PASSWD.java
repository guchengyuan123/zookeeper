package cn.canton.JavaAPI;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Constructor_Usage_With_SID_PASSWD implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                                5000,new ZooKeeper_Constructor_Usage_With_SID_PASSWD());
        connectedSemaphore.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        System.out.println(sessionId);
        System.out.println(passwd);

        zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                                5000,
                                new ZooKeeper_Constructor_Usage_With_SID_PASSWD(),
                                1l,
                                "test".getBytes());

        zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                5000,
                new ZooKeeper_Constructor_Usage_With_SID_PASSWD(),
                sessionId,
                passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(" Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}

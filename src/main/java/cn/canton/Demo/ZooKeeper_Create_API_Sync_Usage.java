package cn.canton.Demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Create_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                5000,
                new ZooKeeper_Create_API_Sync_Usage());
        connectedSemaphore.await();

        String path1 = zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println("Success create znode: "+ path1);

        String path2 = zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: "+ path2);
    }


    public void process(WatchedEvent watchedEvent) {
            System.out.println(" Receive watched event: " + watchedEvent);
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
                connectedSemaphore.countDown();
            }
    }

}

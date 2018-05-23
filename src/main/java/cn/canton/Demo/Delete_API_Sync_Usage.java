package cn.canton.Demo;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class Delete_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {

    	String path = "/zk-book";
    	zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
				5000, //
				new Delete_API_Sync_Usage());
    	connectedSemaphore.await();

    	zk.create( path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL );
    	Thread.sleep(5000);
    	zk.delete( path, -1 );
    	
    	Thread.sleep( Integer.MAX_VALUE );
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }
}
package cn.canton.JavaAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZooKeeper_GetChildren_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                5000,
                new ZooKeeper_GetChildren_API_Sync_Usage());
        connectedSemaphore.await();
        zk.create(path,"".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create(path+"/c1","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        List<String> childrenList = zk.getChildren(path,true);
        System.out.println(childrenList);

        zk.create(path+"/c2","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        List<String> childrenList2 = zk.getChildren(path,true);
        System.out.println(childrenList2);

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        if (KeeperState.SyncConnected == watchedEvent.getState()){
            if (EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                connectedSemaphore.countDown();
            }else if (watchedEvent.getType() == EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet Child:"+zk.getChildren(watchedEvent.getPath(),true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

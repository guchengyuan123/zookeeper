package cn.canton.Demo;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 此类包含两个主要的 ZooKeeper 函数，分别为 createZKInstance （）和 ZKOperations （）。<br>
 * <br>
 *
 * (1)createZKInstance （）函数负责对 ZooKeeper 实例 zk 进行初始化。 ZooKeeper 类有两个构造函数，我们这里使用
 * “ ZooKeeper （ String connectString, ， int sessionTimeout, ， Watcher watcher
 * ）”对其进行初始化。因此，我们需要提供初始化所需的，连接字符串信息，会话超时时间，以及一个 watcher 实例。<br>
 * <br>
 *
 * (2)ZKOperations （）函数是我们所定义的对节点的一系列操作。它包括：创建 ZooKeeper
 * 节点、查看节点、修改节点数据、查看修改后节点数据、删除节点、查看节点是否存在。另外，需要注意的是：在创建节点的时候，需要提供节点的名称、数据、
 * 权限以及节点类型。此外，使用 exists 函数时，如果节点不存在将返回一个 null 值。
 */
public class ZookeeperTest {

    // 会话超时时间，设置为与系统默认时间一致

    private static final int SESSION_TIMEOUT = 30000;

    // 创建 ZooKeeper 实例

    ZooKeeper zk;

    // 创建 Watcher 实例

    Watcher wh = new Watcher() {

        public void process(org.apache.zookeeper.WatchedEvent event) {
            System.out.println("event=" + event.toString());
        }

    };

    // 初始化 ZooKeeper 实例

    private void createZKInstance() throws IOException {
        zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", ZookeeperTest.SESSION_TIMEOUT,
                this.wh);
    }

    private void ZKOperations() throws IOException, InterruptedException, KeeperException {

        System.out.println("\n 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2 ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent");
        zk.create("/zoo2", "myData2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("\n 查看是否创建成功： ");
        System.out.println(new String(zk.getData("/zoo2", false, null)));

        System.out.println("\n 修改节点数据 ");
        zk.setData("/zoo2", "shenlan211314".getBytes(), -1);

        System.out.println("\n 查看是否修改成功： ");
        System.out.println(new String(zk.getData("/zoo2", false, null)));

        System.out.println("\n 删除节点 ");
        zk.delete("/zoo2", -1);

        System.out.println("\n 查看节点是否被删除： ");
        System.out.println(" 节点状态： [" + zk.exists("/zoo2", false) + "]");

    }

    private void ZKClose() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperTest dm = new ZookeeperTest();
        dm.createZKInstance();
        dm.ZKOperations();
        dm.ZKClose();
    }

}

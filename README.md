# zookeeperDemo

JavaAPI包中类说明，zookeeper原生api接口测试案例

1、ZookeeperTest                                 ：测试zookeeper连接是否成功

2、ZooKeeper_Constructor_Usage_Simple            ：创建zookeeper最基本的会话实例

3、ZooKeeper_Constructor_Usage_With_SID_PASSWD   ：用sessionId和sessionPassWD来创建zookeeper实例

4、ZooKeeper_Create_API_Sync_Usage               ：使用同步API创建一个节点

5、ZooKeeper_Create_API_ASync_Usage              ：使用异步API创建一个节点

6、Delete_API_Sync_Usage                         ：删除一个节点

7、ZooKeeper_Create_API_Sync_Usage               ：使用同步API获取子节点列表

8、ZooKeeper_Create_API_ASync_Usage              ：使用异步API获取子节点列表

9、GetData_API_Sync_Usage                        ：使用同步API获取节点数据内容

10、GetData_API_ASync_Usage                      ：使用异步API获取节点数据内容

11、SetData_API_Sync_Usage                       ：使用同步API更新节点数据内容

12、SetData_API_ASync_Usage                      ：使用异步API更新节点数据内容

=================异步接口=================

AsyncCallback.StringCallback()

AsyncCallback.StatCallback()

AsyncCallback.DataCallback()

AsyncCallback.ACLCallback()

AsyncCallback.ChildrenCallback()

AsyncCallback.Children2Callback()

AsyncCallback.VoidCallback()

同步接口调用过程中，我们需要关注接口抛出异常的可能

异步接口中，接口本身不会抛出异常，所有的异常都会在回调函数中通过ResultCode（响应码）来提现

=================异步接口=================

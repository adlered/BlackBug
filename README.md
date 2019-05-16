# BlackBug
:bug: A general backdoor payload MultiClient/Server for Windows/macOS/Linux in Java | Windows/macOS/Linux通用系统后门Payload，支持多客户端主动&amp;自动连接服务端，使用Java编写

[English Version](#english-version)

[中文版本](#中文版本)

# English version

## Main method

Client: `pers.adlered.blackbug.client.Client.java`

Server: `pers.adlered.blackbug.server.Server.java`

## Modify the configuration file

Modify the configuration file `config.properties` in the root directory of the project and change `server.ip` and `server.port` to your local address. The server will read the `server.port` value as the listening port, and the client will read `server.ip` and `server.port` as the address to connect to the server.

## Interaction

Run the main methods in `Client.java` and `Server.java`. If the configuration is correct, the connection information and the client's `UID` will be displayed in the console of `Server.java`. You can use the following command to control the client that specifies `UID`:

`/help` - Get help information

`/setuid [UID]` - select the UID to operate on

`/cmd [command]` - execute a remote command on the selected client and get the result

legend:

![DEMO](/images/guide.png)

# 中文版本

## 主方法

客户端：`pers.adlered.blackbug.client.Client.java`

服务端：`pers.adlered.blackbug.server.Server.java`

## 修改配置文件

修改项目根目录下的配置文件`config.properties`，将`server.ip`和`server.port`改为你的本机地址。服务端将读取`server.port`值作为监听端口，客户端将读取`server.ip`和`server.port`作为连接服务端的地址。

## 交互

运行`Client.java`和`Server.java`中的主方法，如果配置正确，`Server.java`的控制台中将显示连接信息和客户端的`UID`。你可以使用以下指令对指定`UID`的客户端进行控制：

`/help` - 获取帮助信息

`/setuid [UID]` - 选定要操作的UID

`/cmd [命令]` - 对选定的客户端执行远程命令并获取返回结果

图例：

![DEMO](/images/guide.png)
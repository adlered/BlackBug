# BlackBug
:bug: A general backdoor payload MultiClient/Server for Windows/macOS/Linux in Java | Windows/macOS/Linux通用系统后门Payload，支持多客户端主动&amp;自动连接服务端，使用Java编写

* 服务端支持多客户端连接
* 客户端断开连接后自动尝试重连
* 自动匹配客户端操作系统，自动修改编码，中文不乱码
* 要求指定客户端执行命令或广播给所有客户端执行命令

警告！本项目不包含任何恶意代码，不会开机自启，仅能用于测试学习使用！

* Server supports multi-client connection
* Automatically try to reconnect after the client disconnects
* Automatically match the client operating system, automatically modify the encoding, Chinese is not garbled
* Requires the specified client to execute commands or broadcast to all clients to execute commands

WARNING! This project does not contain any malicious code, will not boot from the start, can only be used for testing and learning!

![Demo](/images/Demo.png)

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

`/cmd` Enter Shell-Interactive-Mode

`/broadcast [command]` - execute the specified command on all connected clients

legend:

![DEMO](/images/Guide.png)

Attached:

Client listening process control:

![QueueControl](/images/QueueControl.png)

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

`/cmd` - 进入终端交互模式

`/broadcast [命令]` - 对已连接的所有客户端执行指定命令

图例：

![DEMO](/images/Guide.png)

附：

客户端监听流程控制：

![QueueControl](/images/QueueControl.png)

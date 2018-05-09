# geodeconcurrencytest Geoe 高并发测试
在项目中，涉及到对Gemfire 要做高并发测试，尤其是业务高峰期并发量，为达到测试目的，选用jmeter 作为并发测试工具。
1.	安装Gemfire，省略
2.	安装JDK,要求1.8及以上
3.	安装Jemter
下载地址：http://jmeter.apache.org/download_jmeter.cgi 
4.	创建线程组: 
![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic1.png)
5.	配置线程
 
![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic2.png)
6.	添加配置原件
![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic3.png)
 


7.	配置gemfire 客户端
利用GemfireClient端并发访问GemfireServer端，GemfireClient代码打包放在jmeter 安装目录lib/ext下。选择jar包，配置客户端请求参数： 端口，region，locator
 ![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic4.png)
 ![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic5.png)
 

8.	配置监听器
 ![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic6.png)

9.	启动jmeter，通过 Response Time Graph 看到响应时间
 
![can not load image](https://raw.githubusercontent.com/laozhuangHogon/geodeconcurrencytest/master/images/pic7.png)





附：
Gemfire 客户端代码：

前段时间apple推出了Find My iPhone应用，可以在地图上找到自己的手机现在在哪，这样就可以用来追踪被盗的手机了。

因为之前写过一个android平台的防盗软件，但一直没发布，看到苹果这个Find My iPhone，突然想到了我的软件，于是重新起了个名，叫Find My Android(原来叫守护精灵)，正式发布了。

我先后发到了机锋、HIAPK、NDUO等国内几个知名的android论坛，大家的反响挺好。

软件的主要功能：

首次打开时，软件会自动记录当前的SIM卡信息。
用户可以设定暗号短信、用于接收坐标信息的邮箱、管理员密码。
当发现原SIM卡被拔出或者更换的时候(即手机被盗，SIM卡更换)，程序就会向预先设置的邮箱发送一封邮件，邮件内容是当前的新手机号。
用户在收到小偷的新号码后，就可以向这个号码发一条暗号短信（由用户自己设定的），手机在收到暗号短信之后，就会启动GPS定位，只要发现手机的位置发生变化，就向目标邮箱发送当前的坐标。
本程序会在开机自动启动，且无法被task killer等进程管理软件关闭。
软件截图如下：



用户在电脑上打开我们软件的PC端，登录自己的GMAIL，就可以看到手机运动的轨迹，从而找到自己的手机在哪。(效果如下)


http://code.google.com/p/find-my-droid/source/browse/#svn%2Ftrunk
关于手机端和PC端的下载，猛击这里。

PC端分为Linux版和Windows版，Windows版的使用方法见这里。
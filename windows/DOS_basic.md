
# DOS基本命令

+ wing+r  ==> cmd 进入dos控制台

![cmd进入dos控制台](img/DFJDKFDK.png)

+ 打开屏幕键盘 

```
C:\Users\noatn>osk;
```


![屏幕键盘示意图](img/AA745DE6-18E5-4DAF-969F-FFC030E74376_20200216105008.jpg)



+ dos各种操作查看参数用法 command params ==? command /?

```
C:\Users\noatn>cd /?
显示当前目录名或改变当前目录。

CHDIR [/D] [drive:][path]
CHDIR [..]
CD [/D] [drive:][path]
CD [..]

  ..   指定要改成父目录。

键入 CD drive: 显示指定驱动器中的当前目录。
不带参数只键入 CD，则显示当前驱动器和目录。

使用 /D 开关，除了改变驱动器的当前目录之外，
还可改变当前驱动器。

如果命令扩展被启用，CHDIR 会如下改变:

当前的目录字符串会被转换成使用磁盘名上的大小写。所以，
如果磁盘上的大小写如此，CD C:\TEMP 会将当前目录设为
C:\Temp。

CHDIR 命令不把空格当作分隔符，因此有可能将目录名改为一个
带有空格但不带有引号的子目录名。例如:

     cd \winnt\profiles\username\programs\start menu

与下列相同:

     cd "\winnt\profiles\username\programs\start menu"

在扩展停用的情况下，你必须键入以上命令。
```



+ 显示文件夹列表 dir


+ 改变目录 cd

+ 返回上一级目录 cd ..

+ 返回根目录 cd \

+ 创建子目录 md

+ 删除目录 rd 

+ 清理屏幕 cls 


+ cd s ==> 按住tab键切换



##  DOS窗口中创建用户并设定为管理员

+  查看本机用户数量 net user

```
PS E:\> net user;                                                                                                       
\\DESKTOP-GN2SF7M 的用户帐户

-------------------------------------------------------------------------------
Administrator            DefaultAccount           Guest
WDAGUtilityAccount       zch
命令成功完成。
```

+ 创建一个新用户 net user name password /add

```
PS E:\> net user blake 123456 /add ;                                                                                 
   命令成功完成。
```

+ 提升到管理员权限 net localgroup administrators 用户名 /add

```
PS E:\> net localgroup administrators blake /add;                                                                  
     命令成功完成。
PS E:\> 
```

+ 删除某个用户名 net user user1 /del

```
PS E:\> net user alice /del                                                                                      
       命令成功完成。
PS E:\>    
```


+ 修改用户密码 net user user1 （直接输入新密码）即可

```
PS E:\> net user alice 000000                                                                                          
 命令成功完成。
PS E:\>  
```



+ dos备份磁盘

```
 XCOPY D: E:\backup /s/e/c/h/j/g
 
 PS E:\> XCOPY D: E:\kao /e/h/k/y/c
```

# [回到上一级](./index.md)
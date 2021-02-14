









+ git clone时报RPC failed; curl 18 transfer closed with outstanding read data remaining 错误
```
原因1：缓存区溢出

git config http.postBuffer 524288000

执行上面命令如果依旧clone失败，考虑可能原因2：网络下载速度缓慢

解决方法：命令行输入
git config --global http.lowSpeedLimit 0
git config --global http.lowSpeedTime 999999

如果依旧clone失败，则首先浅层clone，然后更新远程库到本地

git clone --depth=1 http://gitlab.xxx.cn/yyy/zzz.git
git fetch --unshallow

```
















## [回到上一级](../index.md)
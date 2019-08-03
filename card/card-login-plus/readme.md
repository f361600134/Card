
#刷新服务器
http://localhost:8181/chole/server/reload

#获取玩家服务器列表
http://localhost:8181/chole/server/list?ch=99&username=aa

#保存玩家角色信息
http://localhost:8181/chole/account/create?actorId=1&userName=bb&name=bb&actorType=1&level=1&serverId=1

#用于游戏服验证登陆
http://localhost:8181/chole/account/auth?userName=bb&channel=1&sessionKey=asdf&level=1&serverId=1
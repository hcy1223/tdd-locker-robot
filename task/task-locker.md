# 存包
1. given 19个空的存储柜 when 有人存包 then 给一张票据（编号，以及密码）and 查询只有18个空的存储柜，被占用的存储柜编号和票据上的编号相同
2. given 0个空的存储柜 when 有人存包 then 提示存储柜已满
3. given 1个空的存储柜 when 有人存包 then 给一张票据（编号是指定空的存储柜，以及密码）and 查询0个空的存储柜

4. given 1个空的存储柜 when 有一个人在存包中，后来的一个人也需要存包 then 后来的人不能存包，提示有人存包中 ?
# 取包
1. given 一张票据 when 有人取包并且密码正确 then 打开存储柜 and 打开的存储柜编号与票据上的一致
2. given 一张票据 when 有人取包但是密码错误 then 存储柜显示票据无效
3. given 一张取过包的票据 when 有人取包 then 存储柜显示票据无效

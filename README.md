## 二手车网站设计需求文档

### 简介

本文档为“二手车网站”的需求文档，主要作为确认需求以及系统分析设计的依据。

1. 用户模块
   - buyer：注册成为买家，可以按条件查看二手车，收藏二手车，给卖家留言
   - seller：注册登录成为卖家，发布二手车信息，查看买家留言
2. 商品模块
   - 发布二手车：通过表单录入数据库，可以在“我的二手车”中查看到。
   - 按分类筛选二手车：通过前端传入类别，后端进行数据库查询并返回二手车列表
   - 二手车详情：通过商品ID查询该辆车的详细信息
   - 卖家查询已发布的二手车：通过卖家ID查询二手车列表
3. 留言模块
   - 留言
   - 查看留言
4. 购物车模块
   - 加入购物车
   - 购物车列表
5. 订单模块
   - 订单详情

----

## 数据库设计

User(bid, email, password)

car(cid, sid, cname, price, time, milage, img, detail, ctype)

comment(cid, bid, msg)

cart(bid, cid)

order_details(oid, bid, sid, cname, money, time)
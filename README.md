# 技術

- Spring boot 後端 (3.3.4)
- MySQL 資料庫
- Intellij 環境
- JUnit5 測試
- Spring JDBC 資料庫存取

# 功能內容

- 商品功能
    - 查詢商品列表
    - 新增/查詢/移除/刪除 商品 (CRUD)
- 帳號功能
    - 註冊新帳號
    - 登入
- 訂單功能
    - 創建訂單
    - 查詢訂單
- 單元測試與Git版本控制

# 實體照片

獲取所有資料(GET method)

![image.png](https://drive.google.com/uc?id=1Pn8_wWoU37aWSGOiyzgqmwk4UT33WD87)

新增資料(POST method)
![image.png](https://drive.google.com/uc?id=1FVSPDg0i2QPVqoSkKW3WLW4fTz1ixK3-)

刪除資料(Delete method)
![image.png](https://drive.google.com/uc?id=19bHm44d7sn3VwXqLW-9ifKwrNrxPO6p7)

修改資料(Put method)
![image.png](https://drive.google.com/uc?id=1eykrMSjYXMFl619DAap2Ci4wZrq2j7Dw)


# 未來擴展

## 商品功能

- 優化查詢列表效率
    - Elastic Search
- 權限管理RBAC
    - 不是所有人可以更新、刪除product
- price用Decimal類型儲存，非INT

## 帳號功能

- 每個api的token認證

## 訂單功能

- 如何解決搶票問題?多人搶單如何解決?
- 狀態問題，如何處理訂單物流狀態?訂單退款
- 怎麼串流金流?


## 前端設計
－技術 React+Vite 呈現後端Spring Boot是否架設成功

登入畫面
![image.png](https://drive.google.com/uc?id=1JRFfuFYAt_TZ9eoe8G49oOYAs14vGdju)

註冊畫面
![image.png](https://drive.google.com/uc?id=1TSUlSLVZBmqr8HCXgqC9yoZgSZrsTF_Y)


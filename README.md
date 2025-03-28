Parking Fee Calculation System
這個系統旨在計算停車費用，並處理相關的停車會話（ParkingSession）管理。包括停車會話的創建、恢復及查詢等功能。

目錄結構
以下是主要的 Java 類和其功能說明：

CalculateParkingFeeService.java: 主要服務類，負責計算停車費用並處理停車會話的邏輯。

添加了停車會話存儲庫（ParkingSessionRepository）和停車會話（ParkingSession）的邏輯。

DailySession.java: 用於計算停車會話中不同日期的停車費，現在可以處理星期六的情況。

ParkingSession.java: 代表一個停車會話，並且新增了一個恢復（restore）的工廠方法來恢復停車會話。

ParkingSessionPO.java: 停車會話的持久化對象，包含恢復停車會話的工廠方法。

ParkingSessionRepository.java: 停車會話存儲庫接口，提供基礎的停車會話操作。

ParkingSessionRepositoryImplement.java: 停車會話存儲庫的實現，提供具體的操作邏輯。

PriceBook.java: 價格清單類，負責定義停車價格和相關邏輯，並將其重命名為 PriceBook。

PriceBookRepository.java: 價格清單存儲庫接口，提供價格清單的基本操作。

PriceBookRepositoryImplement.java: 價格清單存儲庫的實現，提供具體的操作邏輯。

功能說明
1. 計算停車費用
系統根據停車會話的時間長短及星期幾來計算停車費用。停車費用由 PriceBook 中的設定來確定。

2. 停車會話管理
創建會話：每當車輛進入停車場時，會創建一個新的停車會話。

恢復會話：使用 ParkingSession 和 ParkingSessionPO 的工廠方法來恢復過去的停車會話。

查詢會話：可以查詢所有停車會話並計算相應的費用。

3. 計算星期六的停車費用
DailySession 類現在支持計算星期六的停車費用，根據特殊的定價策略來處理。

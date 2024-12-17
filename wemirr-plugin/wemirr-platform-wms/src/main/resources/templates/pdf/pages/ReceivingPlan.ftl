<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>收货计划</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 20px;
            font-size: 14px; /* 设置基础字体大小 */
        }

        h2, h3 {
            color: #333;
        }

        h2 {
            border-bottom: 2px solid #ccc;
            padding-bottom: 5px;
            margin-bottom: 10px; /* 增加间距 */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px; /* 增加内边距 */
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            position: sticky;
            top: 0; /* 使表头在滚动时固定 */
            z-index: 10;
        }

        #itemTable {
            font-size: 12px; /* 根据需要调整表格字体大小 */
        }

        .print-time {
            text-align: center;
            margin-top: 20px;
            font-style: italic;
            color: #666;
        }
    </style>
</head>
<body>
<h2>收货计划</h2>
<table>
    <tr>
        <th style="width: 120px;">收货计划号</th>
        <td>${detail.planNum}</td>
    </tr>
    <tr>
        <th style="width: 120px;">收货通知单号</th>
        <td>${detail.receivingNoticeNum}</td>
    </tr>
    <tr>
        <th style="width: 120px;">预计送达时间</th>
        <td>${detail.expectedArrivalTime}</td>
    </tr>
    <tr>
        <th style="width: 120px;">实际送达时间</th>
        <td>${detail.actualArrivalTime}</td>
    </tr>
    <tr>
        <th style="width: 120px;">送货车辆车牌号</th>
        <td>${detail.deliveryCarNumber}</td>
    </tr>
    <tr>
        <th style="width: 120px;">送货司机</th>
        <td>${detail.deliveryDriver}</td>
    </tr>
    <tr>
        <th style="width: 120px;">司机联系方式</th>
        <td>${detail.driverContact}</td>
    </tr>
    <tr>
        <th style="width: 120px;">备注信息</th>
        <td>
            <#if detail.remark??>
                ${detail.remark}
            </#if>
        </td>
    </tr>
</table>

<h3>收货计划明细行</h3>
<table id="itemTable">
    <thead>
    <tr>
        <th>收货计划行号</th>
        <th>收货通知行号</th>
        <th>物料编码</th>
        <th>物料名称</th>
        <th>计划收货数量</th>
        <th>收货中/收货完成</th>
        <th>生产日期</th>
        <th>过期日期</th>
        <th>批次号</th>
        <th>单价(元)</th>
    </tr>
    </thead>
    <tbody>
    <#list detail.items as item>
        <tr>
            <td>${item.planItemNum}</td>
            <td>${item.receivingNoticeItemNum}</td>
            <td>${item.materialCode}</td>
            <td>${item.materialName}</td>
            <td>${item.qty}(${item.unit})</td>
            <td>${item.progressQty}/${item.completeQty}(${item.unit})</td>
            <td>${item.productionDate}</td>
            <td>${item.expiryDate}</td>
            <td>${item.batchNum}</td>
            <td>${item.unitPrice}</td>
        </tr>
    </#list>
    </tbody>
</table>

<div class="print-time">
    生成时间：${currentDate}
</div>

</body>
</html>

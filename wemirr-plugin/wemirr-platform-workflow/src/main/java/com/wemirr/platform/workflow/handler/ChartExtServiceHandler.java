package com.wemirr.platform.workflow.handler;

import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.flow.core.dto.PromptContent;
import org.dromara.warm.flow.core.utils.MapUtil;
import org.dromara.warm.flow.ui.service.ChartExtService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 流程图提示信息
 *
 * @author battcn
 * @since 2025/8/04
 */
@Component
public class ChartExtServiceHandler implements ChartExtService {


    private static final int STATUS_COMPLETED = 2;
    private static final int STATUS_TODO = 1;


    private static final String HANDLER_KEY = "办理人";
    private static final String HANDLE_TIME_KEY = "办理时间";
    private static final String TODO_USER_KEY = "待办人";

    @Override
    public void execute(DefJson defJson) {
        defJson.getNodeList().forEach(nodeJson -> {
            Map<String, Object> extMap = nodeJson.getExtMap();
            if (MapUtil.isNotEmpty(extMap)) {
                Integer nodeStatus = nodeJson.getStatus();
                List<PromptContent.InfoItem> info = nodeJson.getPromptContent().getInfo();
                // 根据状态添加特定字段
                processStatusFields(nodeStatus, extMap, info);
            }
        });
    }

    /**
     * 处理状态相关字段，返回已处理的key集合
     */
    private void processStatusFields(Integer nodeStatus,
                                     Map<String, Object> extMap,
                                     List<PromptContent.InfoItem> info) {
        switch (nodeStatus) {
            case STATUS_COMPLETED:
                addInfoItem(info, HANDLER_KEY, extMap.get(HANDLER_KEY));
                addInfoItem(info, HANDLE_TIME_KEY, extMap.get(HANDLE_TIME_KEY));
                break;
            case STATUS_TODO:
                addInfoItem(info, TODO_USER_KEY, extMap.get(TODO_USER_KEY));
                break;
            default:
                // 未处理状态不操作
                break;
        }
    }

    /**
     * 创建InfoItem并记录已处理字段
     */
    private void addInfoItem(List<PromptContent.InfoItem> info,
                             String key,
                             Object value) {
        if (value != null) {
            PromptContent.InfoItem item = new PromptContent.InfoItem();
            item.setPrefix(key + ": ");
            item.setContent(String.valueOf(value));
            info.add(item);
        }
    }
}





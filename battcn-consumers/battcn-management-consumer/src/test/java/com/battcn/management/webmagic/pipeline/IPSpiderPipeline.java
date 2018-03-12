package com.battcn.management.webmagic.pipeline;

import com.battcn.management.webmagic.entity.ProxyIp;
import com.battcn.management.webmagic.mapper.ProxyIpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * @author yanglei
 */
@Component("IPSpiderPipeline")
public class IPSpiderPipeline implements Pipeline {

    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("result")) {
                List<ProxyIp> ipList = (List<ProxyIp>) entry.getValue();
                for (ProxyIp proxyIp : ipList) {
                    proxyIpMapper.insert(proxyIp);
                }
            }
        }
    }
}

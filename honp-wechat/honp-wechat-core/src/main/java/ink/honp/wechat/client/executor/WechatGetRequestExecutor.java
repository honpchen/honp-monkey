package ink.honp.wechat.client.executor;

import ink.honp.core.http.HttpUtil;
import ink.honp.wechat.exception.WechatException;

/**
 * 请求执行器
 * @author jeff chen
 * @since 2023-07-28 16:46
 */
public class WechatGetRequestExecutor implements WechatRequestExecutor<String> {

    @Override
    public String execute(String url, Object parameters) throws WechatException {
        String content = HttpUtil.get(url, parameters);

        checkResponseContent(content);

        return content;
    }
}

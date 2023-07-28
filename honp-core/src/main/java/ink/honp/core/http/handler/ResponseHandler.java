package ink.honp.core.http.handler;

import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

/**
 * @author jeff chen
 * @since 2023-07-27 17:25
 */
public interface ResponseHandler<T> {

    /**
     * 处理 http response 结果
     * @param response 响应，可能为空
     * @return 返回指定处理类型结果
     * @throws IOException 异常
     */
    Optional<T> handle(Response response) throws IOException;
}

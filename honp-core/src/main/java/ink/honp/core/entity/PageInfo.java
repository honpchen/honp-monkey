package ink.honp.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeff chen
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class PageInfo<T> {

    /** 当前页 **/
    private long page;

    /** 每页大小 **/
    private long size;

    /** 总页数 **/
    private long totalPages;

    /** 总数 **/
    private long totalCount;

    /** 数据 **/
    private List<T> content;


    public static <T> PageInfo<T> create(long page, long size, long totalPages, long totalCount, List<T> content) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setSize(size);
        pageInfo.setTotalPages(totalPages);
        pageInfo.setTotalCount(totalCount);
        pageInfo.setContent(content);

        return pageInfo;
    }

    public static <T> PageInfo<T> create(long page, long size, long totalPages, long totalCount) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setSize(size);
        pageInfo.setTotalPages(totalPages);
        pageInfo.setTotalCount(totalCount);
        pageInfo.setContent(new ArrayList<>());

        return pageInfo;
    }

}

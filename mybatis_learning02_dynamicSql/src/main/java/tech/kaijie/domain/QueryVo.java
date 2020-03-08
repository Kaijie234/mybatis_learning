package tech.kaijie.domain;

import java.util.List;

/**
 * 在QueryVo类中加入一个List集合用于封装参数
 */
public class QueryVo {
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
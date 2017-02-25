package Recommand;

import java.util.List;

/**
 * Created by 昱凡 on 2016/9/10.
 */
public interface IRecommandEngine {
    public List<Long> baseItemCF(int userId);
    public List<Long> baseUserCF(int userId);
}

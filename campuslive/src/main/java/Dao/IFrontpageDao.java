package Dao;

import Entity.Activity;
import Entity.Frontpage;

import java.util.List;

/**
 * Created by 昱凡 on 2016/9/9.
 */
public interface IFrontpageDao {
    public List<Activity> getFrontpage();
    public void deleteFrontPage(int actId);
    public void saveFrontPage(Frontpage frontpage);

    public List<Activity> getNoneFrontPage();
}

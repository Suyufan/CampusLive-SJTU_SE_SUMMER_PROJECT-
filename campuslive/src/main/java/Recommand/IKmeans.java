package Recommand;

import java.io.IOException;

/**
 * Created by 昱凡 on 2016/9/11.
 */
public interface IKmeans {
    public void readF1() throws IOException;
    public void RecursionKluster();
    public void Kluster();
    public Double CalCentroid();
}

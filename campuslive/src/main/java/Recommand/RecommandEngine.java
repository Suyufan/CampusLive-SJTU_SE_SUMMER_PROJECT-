package Recommand;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.model.*;
import org.hibernate.SessionFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昱凡 on 2016/9/9.
 */
public class RecommandEngine implements IRecommandEngine{

    public List<Long> baseItemCF(int userId){
        List<Long> recommendId = new ArrayList<Long>();
        try {
            DataModel model = new FileDataModel(new File("e:\\data.csv"));

            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);

            Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);

            List<RecommendedItem> recommendedItemList = recommender.recommend(1, 4);

            for (RecommendedItem recommendation : recommendedItemList) {
                System.out.println(recommendation);
                recommendId.add(recommendation.getItemID());
            }

            return recommendId;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return recommendId;
    }

    public List<Long> baseUserCF(int userId) {
        List<Long> recommendId = new ArrayList<Long>();

        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("143133");
            dataSource.setDatabaseName("campus");

            JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource,"actrate", "userId", "actId", "score", "scoretime");
            //JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "data", "uid", "iid", "val", "time");

            DataModel model = dataModel;

            //DataModel model = new FileDataModel(new File("e:\\data1.txt"));

            UserSimilarity similarity=new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood=new NearestNUserNeighborhood(2,similarity,model);

            Recommender recommender=new GenericUserBasedRecommender(model,neighborhood,similarity);

            List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);

                recommendId.add(recommendation.getItemID());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return recommendId;
    }
}

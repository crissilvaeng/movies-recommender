package recommender

import java.io._

import scala.collection.JavaConverters._

import org.apache.mahout.common._

import org.apache.mahout.cf.taste.eval._
import org.apache.mahout.cf.taste.model._
import org.apache.mahout.cf.taste.impl.eval._
import org.apache.mahout.cf.taste.recommender._

import org.apache.mahout.cf.taste.impl.model.file._
import org.apache.mahout.cf.taste.impl.similarity._
import org.apache.mahout.cf.taste.impl.recommender._
import org.apache.mahout.cf.taste.impl.neighborhood._

object Recommender {
    
    RandomUtils.useTestSeed()

    class RecomendatorBuilder extends RecommenderBuilder {
        def buildRecommender(model: DataModel) : Recommender = {
            val similarity = new PearsonCorrelationSimilarity(model)
            val neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model)
            return new GenericUserBasedRecommender(model, neighborhood, similarity)
        }
    }

    def main(args: Array[String]): Unit = {
        val file = new File(args(0))
        val model = new FileDataModel(file)

        val evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator()
        val builder = new RecomendatorBuilder()

        println(evaluator.evaluate(builder, null, model, 0.8, 1.0))

        val userId = args(1).toInt
        val howMany = args(2).toInt

        val recommender = builder.buildRecommender(model)
        val recommendations = recommender.recommend(userId, howMany).asScala
        recommendations.foreach(println)
    }
}

package org.littlewings.javaee7.batch

import javax.persistence.Persistence

import org.jberet.se.Main
import org.scalatest.{FunSpec, Matchers}

class ChunkedBatchSpec extends FunSpec with Matchers {
  describe("Chunked jBatch") {
    it("process file word count, top 5.") {
      // JBeret run.
      Main.main(Array("my-job", "filePath=bocchan.txt"))

      val emf = Persistence.createEntityManagerFactory("javaee7.batch.pu")
      val em = emf.createEntityManager

      val query =
        em
          .createQuery("SELECT w FROM Word w ORDER BY w.count DESC, w.token ASC", classOf[Word])
          .setMaxResults(5)
      val results = query.getResultList

      results should have size (5)
      results.get(0).token should be ("おれ")
      results.get(0).count should be (471)
      results.get(1).token should be ("事")
      results.get(1).count should be (291)
      results.get(2).token should be ("人")
      results.get(2).count should be (213)
      results.get(3).token should be ("君")
      results.get(3).count should be (184)
      results.get(4).token should be ("赤")
      results.get(4).count should be (178)

      em.close()
      emf.close()
    }
  }
}

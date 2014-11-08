package io.github.ptitjes.hmm.analysis

import io.github.ptitjes.hmm.Utils._
import io.github.ptitjes.hmm._
import io.github.ptitjes.hmm.didier.{FullMTDecoder, RelFreqDiscountingTrainer}

object testDiscounting extends App {

  val trainCorpus = Corpora.annotatedFrom(getClass.getResource("/data/ftb.train.encode"))
  val devCorpus = Corpora.annotatedFrom(getClass.getResource("/data/ftb.dev.encode"))

  val conf = Configuration().set(Trainer.ORDER, 2)

  val trainer = RelFreqDiscountingTrainer.instantiate(conf)
  val decoder = FullMTDecoder.instantiate(conf)

  val hmm = trainer.train(trainCorpus)

  import io.github.ptitjes.hmm.analysis.Results._

  timed("Test HMM") {
    val results = decodeAndCheck(decoder, hmm, devCorpus /*, true*/)
    println(results)
  }
}
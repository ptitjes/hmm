package io.github.ptitjes.hmm.didier

import java.io.{PrintWriter, FileWriter, File}

import io.github.ptitjes.hmm.HiddenMarkovModel._
import io.github.ptitjes.hmm.Utils._
import io.github.ptitjes.hmm._
import io.github.ptitjes.hmm.analysis.Analysis
import io.github.ptitjes.hmm.analysis.Results._

object testLoadAndCheck extends App {

	val trainCorpus = Corpora.annotatedFrom(getClass.getResource("/data/ftb.train.encode"), Lexica.WORDS)
	val devCorpus = Corpora.annotatedFrom(getClass.getResource("/data/ftb.dev.encode"), Lexica.WORDS)
	private val PATH_TO_TEST = "/home/didier/Documents/Work/Master/Docs/Inférence Statistique/Alexis Nasr/Code HMM/ftb.test.encode"
	val testCorpus = Corpora.annotatedFrom(new File(PATH_TO_TEST), Lexica.WORDS)

	val hmmFilename = "Full-Disc-Averaging-Yes-Iterations-40-Order-2"

	val conf = Configuration()
		.set(Analysis.DECODER, FullDecoder)
	//		.set(Analysis.DECODER, BeamDecoder)
	//		.set(BeamDecoder.BEAM, 5)

	val (hmm, loadTime) = timed {
		fromFile(new File("temp/" + hmmFilename + ".json"))
	}

	val decoder = conf(Analysis.DECODER).instantiate(conf)

	decodeAndCheck(hmm, decoder, testCorpus, false).display()
}
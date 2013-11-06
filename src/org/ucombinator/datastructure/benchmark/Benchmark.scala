package org.ucombinator.datastructure.benchmark

object Benchmark {
  def measureTime[A] (prefix : Object) (code: => A) : A = {
    val start = System.nanoTime() ;
    val ret = code
    val end = System.nanoTime() ;
   // println(prefix + "" + (end-start)/100000 + "us" )
     println(prefix + "" + (end-start) + "ns" )
    ret
  }

  def repeat (n : Int) (code: => Unit) {
    var i = n
    while (i > 0) {
      code
      i -= 1
    }
  }
}
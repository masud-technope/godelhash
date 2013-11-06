package org.ucombinator.datastructure.utils
import org.ucombinator.datastructure.godelhash.PrimeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import org.ucombinator.datastructure.otherencoding.ArraySet
import scala.collection.mutable.ArrayBuffer
import org.ucombinator.datastructure.benchmark.Benchmark
import scala.collection.immutable.SortedSet
import scala.collection.immutable.HashSet
import org.ucombinator.datastructure.otherencoding.SortedArraySet


trait Utils {
  var lock: AnyRef = new Object()

  implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt] {
    println("in BigIntToPh")
    val primeHash: BigInt = i
    def compare(that: BigInt) = primeHash.toInt - that.toInt
  }

  implicit def LongintToPH(i: Long) = new PrimeHashable with Ordered[Long] {
    val primeHash: BigInt = BigInt(i)
    def compare(that: Long) = primeHash.toInt - that.toInt
  }

  // generatre numberTOGen random number within upperbound
  // zhi zai 1000 zhijian chansheng
  def n_rands(nunmberToGen: Int, upperBound: Int): List[Int] = {
    // var r = new scala.util.Random
    val res = 1 to nunmberToGen map { _ =>
      var tmp = scala.util.Random.nextInt % upperBound
      var flag = (tmp <= 0)

      while (flag) {
        // println("flag" + tmp.toString, flag)
        tmp = scala.util.Random.nextInt % upperBound
        flag = (tmp <= 0)
      }
      tmp
    }
    res.toList
  }

  //def sortedSetEqual(sortedset1: SortedSet[Long], sortedSet2: SortedSet[Long]) : Boolean = {

  //}

  // bao zheng 
  def randWrapper(numberToGen: Int, upperBound: Int): List[Int] = {

    val arrBuffer = new ArrayBuffer[Int](numberToGen)

    val factor = 1000

    if (numberToGen > 1000) {

      val f = numberToGen / factor
      var rest = numberToGen % factor
      var loopTimes = 0
      if (rest == 0) {
        while (loopTimes < f) {
          arrBuffer ++= n_rands(factor, upperBound)
          loopTimes += 1
        }
        //  println("he number to generate: " + numberToGen + "length of the rturn " +  arrBuffer.size)
        arrBuffer.toList
      } else {
        while (loopTimes < f - 1) {
          arrBuffer ++= n_rands(factor, upperBound)
          loopTimes += 1
        }
        val restNums = numberToGen - loopTimes * factor
        arrBuffer ++= n_rands(restNums, upperBound)
        //  println("he number to generate: " + numberToGen + "length of the rturn " +  arrBuffer.size)
        arrBuffer.toList
      } 
    } else {
      val res = n_rands(numberToGen, upperBound)
      //println("he number to generate: " + numberToGen + "length of the rturn " +  res.length )
      res
    }
  }

  def take_rands(number: Int, upb: Int, primeList: List[Long]): List[Long] = {
    val ranIndexList = n_rands(number, upb)
    // println("another n rands finished")
    val res: List[Long] = ranIndexList.map((index) => primeList(index))

    res
  }

  private def getPrimeMembersFromRands(randIndexes: List[Int], primeList: Array[Long]): List[Long] = {
    randIndexes.map((index) => primeList(index))
  }

  private def await(cond: => Boolean) =
    while (!cond) { lock.wait() }

  def genSetList(setCardi: Int, upb: Int, primeList: List[Long], listSetNo: Int): List[PrimeSet[Long]] = {
    import Benchmark._
    val listofCardiRand = new ArrayBuffer[List[Int]](listSetNo)

    measureTime("gen numer of sets :") {
      repeat(listSetNo) {
        listofCardiRand += randWrapper(setCardi, primeList.length)
      }
    } 
    //val listofCardiRand =  (1 to listSetNo map ( (_) => randWrapper(setCardi, primeList.length ))).toList 
    // var lstIndexes = ArrayBuffer[List[Int]]()

    val lstIndexes = listofCardiRand.toList

    //println(lstIndexes)
    var cnt = 0
    measureTime("gen  primeSet") {

      val arrBuffer = measureTime("toArray:") { primeList.toArray }

      // println("primeList", primeList.length)
      val listofMemberPrimes =
        lstIndexes.map((indexRandList) => {
          val members = // measureTime("gen") {
            getPrimeMembersFromRands(indexRandList, arrBuffer) //take_rands(indexRand, upb, primeList) 
          // }
          members
          // cnt = cnt + 1 
        })

      // listSetNo threads
      var x = 0
      var constructorArrays: ArrayBuffer[PrimeSetConstructorThread] = new ArrayBuffer[PrimeSetConstructorThread](listSetNo)
      while (x < listSetNo) {
        val pst = new PrimeSetConstructorThread(listofMemberPrimes(x))
        constructorArrays += pst
        pst.start()
        x += 1
      }

      println("thread generated: ", constructorArrays.size)
      // we want to wait for to finish
      var y = 0
      measureTime("multifinish") {
        // lock.synchronized{
        while (y < listSetNo) {
          val pst = constructorArrays(y)
          //lock.synchronized{
          pst.join()
          // }
          y += 1
          // }
          //   lock.notifyAll()
        }
      }

      var primeArr: ArrayBuffer[PrimeSet[Long]] = new ArrayBuffer[PrimeSet[Long]](listSetNo)
      var z = 0
      while (z < listSetNo) {
        val pst = constructorArrays(z)
        //  val ps = primeArr(z)
        val psss = pst.ps
        if (psss != null)
          primeArr += psss

        z += 1
      }
      primeArr.toList

    }
  }

  def genArraySet(members: List[List[Long]]): List[ArraySet[Long]] = {
    members.map((ml) => {
      val arS = new ArraySet[Long](ArrayBuffer())
      var i = 0
      ml.foreach((m) => {
        if(i%2==0)
        	arS.arrBuffer += m
        else
        	arS.arrBuffer += m
      })
      arS
    })
  }
  
  // 
  def genSortedArraySet(members: List[List[Long]]): List[SortedArraySet[Long]] = {
    members.map((ml) => {
      val arS = new SortedArraySet[Long](ArrayBuffer()) 
      var i = 0
      ml.foreach((m) => {
        if(i % 2==0)
        	arS.arrBuffer += m
        else 
        	arS.arrBuffer += m  
      })
      arS
    })
  }
  
  def genHashSet(members: List[List[Long]]): List[HashSet[Long]] = {
    members.map((ml) => {  
      ml.foldLeft(HashSet[Long]())((res, elem) => {
        res + elem
    })
    } )
  }

  // this is a slow method

  def favorListSet(lsp: List[PrimeSet[Long]], cardi: Int): List[PrimeSet[Long]] = {
    lsp.map((ps) => {
      val mms = ps.members.toList.take(cardi)
      PrimeSet(mms: _*)
    })

  }
  /**
   * Firstly "the size of an object" isn't a well-defined concept in Java.
   * You could mean the object itself, with just its members,
   * the Object and all objects it refers to (the reference graph).
   * You could mean the size in memory or the size on disk.
   * And the JVM is allowed to optimise things like Strings.
   *
   * However, from the description above it sounds like each row will be self-contained,
   * and not have a big dependency tree, so the serialization method will probably be a
   * good approximation on most JVMs. The easiest way to do this is as follows:
   */

  def approximateSizeOfObject(obj: Any): Int = {
    val baos: ByteArrayOutputStream = new ByteArrayOutputStream()
    val oos: ObjectOutputStream = new ObjectOutputStream(baos);

    oos.writeObject(obj)
    oos.close()
    baos.size()
  }

  def approximateSizeOfObjectS(objs: List[Any]): Int = {
    objs.foldLeft(0)((sum, obj) => {
      sum + approximateSizeOfObject(obj)
    })
  }

  private def calcBitOfBigInts(lsb: List[BigInt]): Int = {
    lsb.foldLeft(0)((res, bi) => {
      res + bi.bitCount
    })
  }

  def calcSpaceUsage(ps: List[PrimeSet[Long]]): (Int, Int) = {

    val totalElems =
      ps.foldLeft(0)((sum, p) => {
        val l = p.members.toList.length
        println("l", l)
        sum + l
      })

    println("totalElem", totalElems)
    val allGodelRepresent1 = ps.map(_.comp)
    val primeSetSize = calcBitOfBigInts(allGodelRepresent1) / totalElems //approximateSizeOfObjectS(allGodelRepresent1) /totalElems 

    val allSortedRepresent1 = ps.map(_.members.toList)
    println("space usage for all sorted set ")
    val orderedSetSize = approximateSizeOfObjectS(allSortedRepresent1) / totalElems
    (primeSetSize, orderedSetSize)
  }

  def calcSpaceUsageVsArray(as: List[ArraySet[Long]], ps: List[PrimeSet[Long]]): (Int, Int) = {

    val totalElems =
      as.foldLeft(0)((sum, p) => {
        //  println("sum",sum)
        val l = p.arrBuffer.length // println("l", p.members.toList)

        sum + l
      })

    // println("totalElem", totalElems)
    val allGodelRepresent1 = ps.map(_.comp)
    val primeSetSize = calcBitOfBigInts(allGodelRepresent1) / totalElems //approximateSizeOfObjectS(allGodelRepresent1) /totalElems 

    val actualArrlst = as.map(_.arrBuffer)
    val arrSize = approximateSizeOfObjectS(actualArrlst) / totalElems
    (primeSetSize, arrSize)
  }

  def noOfBitsToRepresentGodelHashSet(pss: List[PrimeSet[Long]]): BigInt = {

    val noOfSets = pss.length
    // println("Listof PrimeSet: Length", noOfSets)

    val listOfHashCodes = pss.map(_.comp)

    val initSum: BigInt = 0
    val totalBits /*= listOfHashCodes.foldLeft(initSum)((sum, n) => {
      println("n is:  " +  n)
      val bits = Math.log(n.toDouble)
      println("bits: ", bits)
      val finalBits = Math.ceil(bits).toLong
      sum + finalBits
    })*/ = calcBitOfBigInts(listOfHashCodes)

    // println(" totalBits", totalBits)

    // number of bits to represent each set
    val res = totalBits / noOfSets

    //println("the bits for godel is: ", res)
    res
  }

  def noOfBitsToRepresentSortedHashSet(pss: List[PrimeSet[Long]]): Int = {
    val noOfSets = pss.length

    val listOfHashCodes = pss.flatten(_.members.toList)
    val totalBits = approximateSizeOfObjectS(listOfHashCodes)

    totalBits / noOfSets
  }

  def noOfBitsToRepresentArraySet(ass: List[ArraySet[Long]]): Int = {
    val noOfSets = ass.length

    val listOfHashCodes = ass.flatten(_.arrBuffer.toList)
    val totalBits = approximateSizeOfObjectS(listOfHashCodes)

    totalBits / noOfSets
  }
  
 // private def 
  // type mistach
//  private def getTestingTriples(testSetPairs: List[(PrimeSet[Long], PrimeSet[Long])]) : (BigInt, BigInt, BigInt) = {
//    for(
//          (ppm1, ppm2) <- testSetPairs;
//    	   e = ppm1.members.head;
//    	   rem = ppm1.comp / e.primeHash )
//     yield {(ppm1.comp, (ppm2.comp/e.primeHash.pow(50)), e.primeHash)}
//  }

  def printOutPrimeSetStats(testSetpairs: List[(PrimeSet[Long], PrimeSet[Long])]) {
    import Benchmark._
    
    // get real subsets
     val i = testSetpairs.length
   
     
    val new2emes=// getTestingTriples(testSetpairs) 
       for(
          (ppm1, ppm2) <- testSetpairs;
    	   e = ppm1.members.head;
    	   rem = ppm1.comp / e.primeHash )
    // yield {(ppm1.comp, (ppm2.comp/e.primeHash.pow(50)), e.primeHash)}
      yield {(ppm1.comp, ppm2.comp, e.primeHash)}
    
     for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
        //if (primeSet1h.equals(primeSet2h))
         // println("!!!!!Equal!!!!!")
       // println(primeSet1h.bitLength + " " + primeSet2h.bitLength+ " " + eh.bitLength)
      }
     
      measureTime("------------ prime  <=  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
        val res = (primeSet1h % (primeSet2h)) == 0
      }
    }
     
    
     
     measureTime("------------ prime  <=  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
        val res = (primeSet1h % (primeSet2h)) == 0
      }
    }
     
      measureTime("------------ prime  =  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
          primeSet1h == primeSet2h
      }
    } 
     
     measureTime("------------ prime  =  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
          primeSet1h == primeSet2h
      }
    } 
      

     measureTime ("------------ prime  union   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 union primeSet1
      }
     } 
     
      measureTime ("------------ prime  difference   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet1 diff primeSet2
      }
     } 
     
     
        measureTime ("------------ prime  intersect   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 intersect primeSet1
      }
     }
       
    
     measureTime("------------ prime  contains  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
        val res = (primeSet1h % eh) == 0
      }
    }
 

     val members = testSetpairs(testSetpairs.length/2)._2.members.toList
     val elem = members(members.length/3)
     measureTime ("------------ prime  delete   "){  
      for((primeSet1, primeSet2) <- testSetpairs) {
        primeSet1 - elem
      }
     }

       measureTime ("------------ prime  insert   "){
      for((primeSet1, primeSet2) <- testSetpairs) { 
        primeSet1 + elem
      }
    }
       
         measureTime("------------ prime  contains  ") {
      for ((primeSet1h, primeSet2h, eh) <- new2emes) { 
        val res = (primeSet1h % eh) == 0
      }
    }
 
  }

  def printOutArraySetStats(arrayTestPairs: List[(ArraySet[Long], ArraySet[Long])]) {
    import Benchmark._ 
     
    
      measureTime ("------------ unsorted array  <=   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 subSetOf arrset2
      }
     }
       measureTime ("------------ unsorted array  equality   "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 arraySetEqual arrset2
      }
     }
      
       
     measureTime ("------------ unsorted array  union   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 union arrset2
      }
     } 

       measureTime ("------------ unsorted arrar  diff   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 diff arrset2
      }
     }
      
    measureTime("------------unsorted  arrar  intersect  ") {

      for ((arrset1, arrset2) <- arrayTestPairs) {
        arrset1 intersect arrset2
      }
    }
    
//    val elem1 = arrayTestPairs.head
//    val elem2 = elem1._1
//    val elem3 = elem2.arrBuffer 
//    println(elem3)
//    val elem4= elem3.head

    val hd  = arrayTestPairs.head._1.arrBuffer.head + 1
    
     measureTime ("------------unsorted arrar  contains  "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        //println(arrset1.arrBuffer.isEmpty, arrset2.arrBuffer.isEmpty)
        arrset1 isMember hd//hd
      }
     }
  
    measureTime ("------------unsorted array  delete   "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
      
        arrset1 - hd
      }
     }

    measureTime("------------unsorted array  insert   ") {

      for ((arrset1, arrset2) <- arrayTestPairs) { 
          arrset1 + hd
    }}
    println()
  }

  def printOutOrderedSetStats(testSetpairs0: List[(PrimeSet[Long], PrimeSet[Long])]) {
    import Benchmark._

    val  testSetpairs = testSetpairs0.map((elemp) => {
      (elemp._1.members, elemp._2.members)
    })
    
      measureTime ("------------ sorted  <=   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 subsetOf primeSet1 
      }
    }
    
        measureTime ("------------ sorted tree  equality   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
        
        primeSet1.subsetOf(primeSet2) && primeSet2.subsetOf(primeSet1)
         
      }
     }

      measureTime ("------------ sorted tree union   "){ 

      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 union primeSet1
      }
     }

    measureTime ("------------ sorted tree diff   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 diff primeSet1
      }
     }
       
       
       measureTime ("------------ sorted tree intersect  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 intersect primeSet1
      }
     }

       val lst = testSetpairs.head._1.toList
       val elem = lst(lst.length/2) + 4
       
    measureTime("------------ sorted tree contains  ") {

      for ((primeSet1, primeSet2) <- testSetpairs) {

        primeSet2 contains elem
      }
    }

     measureTime ("------------ sorted tree delete   "){  
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2  - elem 
      }
     }

      measureTime ("------------ sorted tree  insert   "){  
    	  for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 + elem
    	  } 
      }

  }
  
  def printOutSortedArraySetStats(testSSetParis: List[(SortedArraySet[Long], SortedArraySet[Long])]) {
     import Benchmark._
  
      measureTime ("------------ SortedArraySet  <=   "){ 
    
      for((sset1, sset2) <- testSSetParis){
        sset2.subSetOf(sset1) 
      }
     }
      
      measureTime ("------------ SortedArraySet  =   "){ 
    
      for((sset1, sset2) <- testSSetParis){
        sset1.arraySetEqual(sset2) 
      }
     }
      measureTime ("------------ SortedArraySet  union   "){ 
    
      for((sset1, sset2) <- testSSetParis){
        sset1.union(sset2) 
      }
     }
        
     measureTime("------------ Sorted Array Set diff  ") {
       for((sset1, sset2) <- testSSetParis){
         sset2 diff sset2
      }
     }
      measureTime ("------------ SortedArraySet  intersect  "){ 
    
      for((sset1, sset2) <- testSSetParis){
        sset1.intersect(sset2) 
      }
     }
      
      var buffer = testSSetParis.head._1.arrBuffer
        var len = buffer.length
        var hd = buffer(len/2) 
      //  var hd1 = hd + 34
     measureTime("------------ Sorted Array Set contains  ") {
       for((sset1, sset2) <- testSSetParis){
         sset2.isMember(hd + 2)
      }
     } 
     
     measureTime("------------ Sorted Array Set deletion  ") {
       for((sset1, sset2) <- testSSetParis){
         sset2- hd
      }
     }
     
     hd = hd+1
     measureTime("------------ Sorted Array Set insertion  ") {
       for((sset1, sset2) <- testSSetParis){
         sset2 + hd
      }
     }
  }
  
  private def copyElems(testSetpairs: List[(HashSet[Long], HashSet[Long])]): List[(HashSet[Long], HashSet[Long])] = {
    testSetpairs.foldLeft(List[(HashSet[Long], HashSet[Long])]()) ((res, pair) => {
      val (hs1, hs2) = pair
      
      val hss1 = hs1.foldLeft(HashSet[Long]()) ((res, elem) => {
        res + elem
      })
      
      val hss2 = hs2.foldLeft(HashSet[Long]()) ((res, elem) => {
        res + elem
      })
      
      (hss1, hss2) :: res
    })
  }

  def printOutHashSetStats(testSetpairs0: List[(HashSet[Long], HashSet[Long])]){
     import Benchmark._
  
     var testSetpairs = copyElems(testSetpairs0)
      val hd = testSetpairs.head._1.toList.head
      measureTime("------------ HashSet  contains  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res= hsset2 contains hd
      }
     }
     testSetpairs = copyElems(testSetpairs0)
     
     measureTime("------------ HashSet  deletion  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res = hsset2 - hd
      }
     }
     testSetpairs = copyElems(testSetpairs0)
      measureTime("------------ HashSet  insert  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res = hsset2 + hd
      }
     }
      measureTime ("------------ HashSet  =   "){  
      for((hsset1, hsset2) <- testSetpairs){
        hsset1.subsetOf(hsset2) && hsset2.subsetOf(hsset1)
      }
     }
     
       measureTime ("------------ HashSet  <=   "){  
      for((hsset1, hsset2) <- testSetpairs){
        hsset1.subsetOf(hsset2) 
      }
     } 
       
     testSetpairs = copyElems(testSetpairs0)
     
     
    testSetpairs = copyElems(testSetpairs0)
      measureTime ("------------ HashSet  <=   "){  
      for((hsset1, hsset2) <- testSetpairs){
        hsset1.subsetOf(hsset2) 
      }
     } 
     testSetpairs = copyElems(testSetpairs0)
     measureTime ("------------ HashSet  =   "){  
      for((hsset1, hsset2) <- testSetpairs){
        hsset1.subsetOf(hsset2) && hsset2.subsetOf(hsset1)
      }
     } 
     testSetpairs = copyElems(testSetpairs0)
     val hdd = testSetpairs.head._1.toList.head
     measureTime("------------ HashSet  union  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res = hsset2 union hsset2
      }
     } 
      testSetpairs = copyElems(testSetpairs0)
     measureTime("------------ HashSet  diff  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res = hsset2 diff hsset2
      }
     }
     
    testSetpairs = copyElems(testSetpairs0)
     measureTime("------------ HashSet  intersect  ") {
       for((hsset1, hsset2) <- testSetpairs){
         val res= hsset2 intersect hsset2
      }
     }
     testSetpairs = copyElems(testSetpairs0)
    
     
     
  }
}
package org.ucombinator.godelhash.utils
import org.ucombinator.godelhash.godelhash.PrimeSet
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import org.ucombinator.godelhash.otherrepresentation.ArraySet
import scala.collection.mutable.ArrayBuffer
import org.ucombinator.godelhash.benchmark.Benchmark
import scala.collection.immutable.SortedSet
 
 

trait Utils {
   var lock : AnyRef = new Object()
  
implicit def BigIntintToPH(i: BigInt) = new PrimeHashable with Ordered[BigInt]{
  val primeHash: BigInt = i
  def compare(that: BigInt) = primeHash.toInt  - that.toInt
}

implicit def LongintToPH(i: Long) = new PrimeHashable with Ordered[Long]{
  val primeHash: BigInt = BigInt(i)
  def compare(that: Long) = primeHash.toInt  - that.toInt
}


// generatre numberTOGen random number within upperbound
// zhi zai 1000 zhijian chansheng
  def n_rands(nunmberToGen : Int, upperBound: Int)  : List[Int]  = {
	// var r = new scala.util.Random
 	 val res = 1 to nunmberToGen map { _ =>  
 	   var tmp = scala.util.Random.nextInt % upperBound
 	   var flag = (tmp <= 0)
 	   
 	   while(flag){
 	    // println("flag" + tmp.toString, flag)
 	      tmp =  scala.util.Random.nextInt % upperBound
 	      flag = (tmp <= 0)
 	   }  
       tmp 
	 } 
	 res.toList
  }
  
  //def sortedSetEqual(sortedset1: SortedSet[Long], sortedSet2: SortedSet[Long]) : Boolean = {
    
  //}
  
  // bao zheng 
 def randWrapper(numberToGen : Int, upperBound :Int) : List[Int] = {
   
   val arrBuffer = new ArrayBuffer[Int](numberToGen) 
    
   val factor = 1000 
 
  
  if(numberToGen > 1000) {
   
    val f = numberToGen/factor
    var rest = numberToGen % factor
       var loopTimes = 0
    if(rest == 0 ) {
      while(loopTimes < f) {
        arrBuffer ++=  n_rands(factor, upperBound)
        loopTimes += 1   
      }
    //  println("he number to generate: " + numberToGen + "length of the rturn " +  arrBuffer.size)
      arrBuffer.toList
    }
    else {
      while(loopTimes < f-1) {
        arrBuffer ++=  n_rands(factor, upperBound)
        loopTimes += 1   
      }
      val restNums = numberToGen - loopTimes * factor
        arrBuffer ++=  n_rands(restNums,upperBound)
      //  println("he number to generate: " + numberToGen + "length of the rturn " +  arrBuffer.size)
        arrBuffer.toList
    }
  
  } 
  else {
   val res =   n_rands(numberToGen, upperBound)
     //println("he number to generate: " + numberToGen + "length of the rturn " +  res.length )
     res
  }
}

  
  def take_rands(number: Int, upb: Int, primeList: List[Long]): List[Long] = {
    val ranIndexList = n_rands(number, upb)
   // println("another n rands finished")
    val res: List[Long] = ranIndexList.map((index)=> primeList(index))
    
    res
  }
  
  private def getPrimeMembersFromRands(randIndexes: List[Int],  primeList: Array[Long]) : List[Long] = {
    randIndexes.map((index)=> primeList(index))
  }
  
  private def await(cond: => Boolean) =
      while (!cond) { lock.wait() }
  
  def genSetList( setCardi: Int, upb: Int, primeList: List[Long], listSetNo: Int ) : List[PrimeSet[Long]] = {
   import Benchmark._
    val listofCardiRand = new ArrayBuffer[List[Int]](listSetNo)
    
    measureTime("gen 1k :") {
    repeat(listSetNo){
      listofCardiRand += randWrapper(setCardi, primeList.length )
    }
   }
    
    //val listofCardiRand =  (1 to listSetNo map ( (_) => randWrapper(setCardi, primeList.length ))).toList 
  // var lstIndexes = ArrayBuffer[List[Int]]()
    
    val lstIndexes = listofCardiRand.toList 
   
    println()
    var cnt = 0 
      measureTime("gen  primeSet") {
      
      
      val arrBuffer = measureTime("toArray:") {primeList.toArray}
      
     // println("primeList", primeList.length)
     val listofMemberPrimes = 
    lstIndexes.map((indexRandList) => {
     val members =// measureTime("gen") {
        getPrimeMembersFromRands(indexRandList, arrBuffer)//take_rands(indexRand, upb, primeList) 
     // }
       members
      // cnt = cnt + 1 
    })
    
    // listSetNo threads
   var x = 0
   var constructorArrays: ArrayBuffer[PrimeSetConstructorThread] = new ArrayBuffer[PrimeSetConstructorThread](listSetNo)
   while(x < listSetNo){
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
   while(y < listSetNo){ 
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
   while(z < listSetNo){
     val pst = constructorArrays(z)
       //  val ps = primeArr(z)
     val psss = pst.ps
      if(psss != null)
        primeArr += psss
        
      
     z += 1
   }
     primeArr.toList
    
    }
  }
    
  
 
  def genArraySet(  members: List[List[Long]] ): List[ArraySet[Long]] = {
    members.map((ml) => {
       val arS = new ArraySet[Long] (ArrayBuffer())
       ml.foreach((m) => {
         arS.arrBuffer += m
       }) 
       arS
    }) 
  }
  
  // this is a slow method
  
  def favorListSet(lsp: List[PrimeSet[Long]], cardi: Int) : List[PrimeSet[Long]] = {
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
     val oos: ObjectOutputStream= new ObjectOutputStream(baos);
    
    oos.writeObject(obj)
   oos.close()
    baos.size()
 } 
  
  def approximateSizeOfObjectS(objs: List[Any]) : Int = {
    objs.foldLeft(0)((sum, obj)=>{
      sum + approximateSizeOfObject(obj)
    })
  }
   
  private def calcBitOfBigInts(lsb: List[BigInt]) : Int = {
    lsb.foldLeft(0)((res, bi)=>{
      res + bi.bitCount
    })
  }
  
  def calcSpaceUsage(ps: List[PrimeSet[Long]]) : (Int, Int) = {
    
    val totalElems = 
      ps.foldLeft(0)((sum, p) =>{
      val l = p.members.toList.length
      println("l",l)
      sum+l
    })
    
    println("totalElem", totalElems)
     val allGodelRepresent1 = ps.map(_.comp) 
     val primeSetSize = calcBitOfBigInts(allGodelRepresent1) /totalElems  //approximateSizeOfObjectS(allGodelRepresent1) /totalElems 
     
       val allSortedRepresent1 = ps.map(_.members.toList)
      println("space usage for all sorted set ")
    val orderedSetSize = approximateSizeOfObjectS(allSortedRepresent1) /totalElems
    (primeSetSize, orderedSetSize)
  }
  
  def calcSpaceUsageVsArray(as: List[ArraySet[Long]], ps: List[PrimeSet[Long]]) : (Int, Int) = {
    
    
    val totalElems = 
      as.foldLeft(0)((sum, p) =>{
      //  println("sum",sum)
      val l = p.arrBuffer.length     // println("l", p.members.toList)
       
      sum+l
    })
    
   // println("totalElem", totalElems)
     val allGodelRepresent1 = ps.map(_.comp) 
     val primeSetSize = calcBitOfBigInts(allGodelRepresent1) /totalElems  //approximateSizeOfObjectS(allGodelRepresent1) /totalElems 
     
        val actualArrlst = as.map(_.arrBuffer)
    val arrSize = approximateSizeOfObjectS(actualArrlst) /totalElems
    (primeSetSize, arrSize)
  }
  
  def noOfBitsToRepresentGodelHashSet(pss: List[PrimeSet[Long]]) : BigInt =  {
  
    val noOfSets = pss.length
     // println("Listof PrimeSet: Length", noOfSets)
    
    val listOfHashCodes = pss.map(_.comp)
    
    val  initSum : BigInt =  0
    val totalBits /*= listOfHashCodes.foldLeft(initSum)((sum, n) => {
      println("n is:  " +  n)
      val bits = Math.log(n.toDouble)
      println("bits: ", bits)
      val finalBits = Math.ceil(bits).toLong
      sum + finalBits
    })*/
    
     = calcBitOfBigInts(listOfHashCodes) 
    
    // println(" totalBits", totalBits)
     
    // number of bits to represent each set
     val res =  totalBits / noOfSets 
     
     //println("the bits for godel is: ", res)
     res
  }
  
  def noOfBitsToRepresentSortedHashSet(pss: List[PrimeSet[Long]]) : Int = {
     val noOfSets = pss.length
    
    val listOfHashCodes = pss.flatten(_.members.toList)
    val totalBits = approximateSizeOfObjectS(listOfHashCodes)
    
    totalBits/noOfSets
  }
  
  def noOfBitsToRepresentArraySet(ass: List[ArraySet[Long]]):Int = {
     val noOfSets = ass.length
    
    val listOfHashCodes = ass.flatten(_.arrBuffer.toList)
    val totalBits = approximateSizeOfObjectS(listOfHashCodes)
    
    totalBits/noOfSets
  }
  
  
  
  def printOutPrimeSetStats(testSetpairs: List[(PrimeSet[Long], PrimeSet[Long])]) {
    import Benchmark._
  
     measureTime ("------------ prime  <=   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 isSubsetOf primeSet1
      }
     }
  
  
      /*measureTime ("------------ prime  equality   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.PSEqual(primeSet1) 
      }
     }
      
*/  
  /* measureTime ("------------ prime  union   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 union primeSet1
      }
     }
   
    
     
        measureTime ("------------ prime  intersect   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 intersect primeSet1
      }
     }
       */ 
     val m = testSetpairs.head._1.members.head
         measureTime ("------------ prime  contains  "){ 
   
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 contains m//primeSet1.members.head
      }
     }
         
        /*    measureTime ("------------ prime  delete   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 - primeSet1.members.head
      }
     }*/
            
      /*   measureTime ("------------ prime  insert   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 + primeSet1.members.head
      }
     }*/
         
       /*   measureTime ("------------ prime  diff  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2 diff primeSet1
      }
     }
         */
         
        println()
      
  }
 
  def printOutArraySetStats(arrayTestPairs:List[(ArraySet[Long], ArraySet[Long])]) {
    import Benchmark._ 
     
   
    
      /*  measureTime ("------------ array  equality   "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 arraySetEqual arrset2
      }
     }
   
     
    
      measureTime ("------------ array  <=   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 subSetOf arrset2
      }
     }
      
       
     measureTime ("------------ array  union   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 union arrset2
      }
     } */
     
      
   /*   measureTime ("------------ arrar  diff   "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 diff arrset2
      }
     }
      */
         measureTime ("------------ arrar  intersect  "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 intersect arrset2
      }
     }
         
          
      /* measureTime ("------------ arrar  contains  "){ 
    
      for((arrset1, arrset2) <- arrayTestPairs){
        arrset1 isMember arrset2.arrBuffer.head
      }
     }*/
         
  
    /*measureTime ("------------ array  delete   "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
       val hlst = arrset2.arrBuffer.toList 
       if(! hlst.isEmpty)
        arrset1 - hlst.head
      }
     }*/
         
            measureTime ("------------ array  insert   "){ 
    
     for((arrset1, arrset2) <- arrayTestPairs){
        val hlst = arrset2.arrBuffer.toList 
       if(! hlst.isEmpty)
        arrset1 + hlst.head
      }
     }
    println()
  }
  
  def printOutOrderedSetStats(testSetpairs: List[(PrimeSet[Long], PrimeSet[Long])]) {
    import Benchmark._
  
    /*   measureTime ("------------ sorted  equality   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
        val mem1 = primeSet2.members
        val mem2 = primeSet1.members
        mem1.subsetOf(mem2) && mem2.subsetOf(mem1)
         
      }
     }*/
      
      
     /*  measureTime ("------------ sorted  <=   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members subsetOf primeSet1.members
      }*/
     
       
      //  measureTime ("------------ sorted  union   "){ 
    
    /*  for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members union primeSet1.members
      }
     }*/
        
      /*measureTime ("------------ sorted  diff   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members diff primeSet1.members
      }
     }
       
       
       measureTime ("------------ sorted  intersect  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members intersect primeSet1.members
      }
     }*/
       
        measureTime ("------------ sorted  contains  "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
        
         primeSet2.members contains primeSet1.members.head
      }
     }
        
    /*    measureTime ("------------ sorted  delete   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members - primeSet1.members.head
      }
     }*/
         
    /*      measureTime ("------------ sorted  insert   "){ 
    
      for((primeSet1, primeSet2) <- testSetpairs){
         primeSet2.members + primeSet1.members.head
      } */
  
  }
 

}
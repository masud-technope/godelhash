package tests
import scala.collection.BitSet
import scala.collection.mutable.ArrayBuffer
import java.util.Arrays

object BitSetTest {

  def main(args: Array[String]): Unit = {
    
    //bitCount is cuonting the number of 1
    // bit length is counting the maximum number of bits to represent the number
    // 
//    val bi = BigInt(12)
//    println(bi.bitCount)
//    println(bi.bitLength)
//    val bs = BitSet(64)
//    
//    println(bs.elements.toList)
//    println(bs)
    
//    val arrbuff = ArrayBuffer[Int]()
////    
//    changeArrayBuffer(arrbuff)
//    
//    println(arrbuff)
//  }
//  
//  def changeArrayBuffer(ab: ArrayBuffer[Int]) {
//    ab += 2
//    ab+=1
    
    val arr = Array(1,2,3)
    //println(Arrays.binarySearch(arr, 5))
    
    val sortedArray1 = ArrayBuffer[Long](1,8,9,12)
    val sortedArray2 = ArrayBuffer[Long](1,2,3,6,15)
    
    val tmpArray = ArrayBuffer[Long](1,8,9,13)
    
    val arrt1 = ArrayBuffer[Long](2)
    val arrt2 = ArrayBuffer[Long](1,2,4)
   // println(merge(sortedArray1, sortedArray2))
    
/*    println(mergeDiffer(arrt1, arrt2)) 
    println(mergeDiffer(arrt2, arrt1))  
    println(mergeDiffer(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](4,5)))
    println(mergeDiffer(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](2,5)))
    println(mergeDiffer(ArrayBuffer[Long](2,4,6), ArrayBuffer[Long](2,4,6)))
     println(mergeIntersect(arrt1, arrt2)) 
    println(mergeIntersect(arrt2, arrt1))  
    println(mergeIntersect(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](4,5)))
    println(mergeIntersect(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](2,5)))
    println(mergeIntersect(ArrayBuffer[Long](2,4,6), ArrayBuffer[Long](3,5)))
        println(mergeIntersect(ArrayBuffer[Long](2,4,6), ArrayBuffer[Long](2,4,6)))*/
        
/*        println(mergeSubsetOf(arrt1, arrt2)) 
    println(mergeSubsetOf(arrt2, arrt1))  
    println(mergeSubsetOf(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](4,5)))
    println(mergeSubsetOf(ArrayBuffer[Long](1,2,3), ArrayBuffer[Long](2,5)))*/
/*    println(mergeSubsetOf(ArrayBuffer[Long](2,4,6), ArrayBuffer[Long](3,5)))
        println(mergeSubsetOf(ArrayBuffer[Long](2,4,6), ArrayBuffer[Long](2,4,6)))*/
       /*       println(mergeSubsetOf(ArrayBuffer[Long]( ), ArrayBuffer[Long](2,4,6)))
              println(mergeSubsetOf(ArrayBuffer[Long](2,4,6 ), ArrayBuffer[Long]()))*/
           println(mergeEqual(ArrayBuffer[Long]( ), ArrayBuffer[Long]( )))
              println(mergeEqual(ArrayBuffer[Long](2,4,6 ), ArrayBuffer[Long](2,4,6)))
               println(mergeEqual(ArrayBuffer[Long]( 6 ), ArrayBuffer[Long](2,4,6)))
                  println(mergeEqual(ArrayBuffer[Long](5, 6 ), ArrayBuffer[Long](2,4,6)))
                        println(mergeEqual(ArrayBuffer[Long](2,4,7 ), ArrayBuffer[Long](2,4,6)))
    /* binaryInsertion(sortedArray1, 13)
    println(sortedArray1)
    binaryInsertion(sortedArray2, 4)
    println(sortedArray2)
    
   binaryInsertion (arrt1, 2)
    
    println(arrt1)
     binaryInsertion (arrt2, 0)
  
    println(arrt2) */
    
     binaryInsertion(sortedArray1, 3)
     println(sortedArray1)
     println(binarySearch(sortedArray1, 1))
     println(binarySearch(sortedArray1, 12))
     println(binarySearch(arrt1, 2))
     println(binarySearch(arrt1,0))
     
      binaryRemoval(sortedArray1, 3)
      println(sortedArray1)
      binaryRemoval(sortedArray1, 1)
        println(sortedArray1)
     binaryRemoval(sortedArray1, 12)
           println(sortedArray1)
                binaryRemoval(sortedArray2, 3)
           println(sortedArray2)
      binaryRemoval(arrt1, 2)
        println(arrt1)
  }
  
  def binaryRemoval(arr:ArrayBuffer[Long], elem: Long) {
    val pos = binarySearch(arr, elem)
    println("removal", pos)
    if(pos != -1)
      moveForwardForRemoval(arr,pos) 
  }
  
  def moveForwardForRemoval(arr: ArrayBuffer[Long], removalPoint:Int) {
    if(removalPoint == arr.length -1) {
      arr.remove(removalPoint)
    }else {
      var i = removalPoint
      while(i < arr.length-1 ) {
        arr(i) = arr(i+1)
        i +=1
      }
      arr.remove(i)
    }
  }

  def binaryInsertion(arr: ArrayBuffer[Long], elem: Long) {
    val pos = binaryInsertionPosition(arr, elem)

    println(pos)
    println(arr.length)
    if (pos == arr.length - 1) {
        arr += elem
        return
      }
    if (pos != -1) {
      if(arr(pos) > elem) {
         moveBackwardFromEnd(arr, pos)
        arr(pos) = elem
      }
      else {
        if(pos == 0) {
          moveBackwardFromEnd(arr, pos+1)
        arr(pos+1) = elem
          return
        }
         moveBackwardFromEnd(arr, pos-1)
        arr(pos-1) = elem
      }
      /*if (pos == arr.length - 1) {
        arr += elem
      } else if (pos == 0){
         moveBackwardFromEnd(arr, pos+1)
        arr(pos+1) = elem
      } else {
        moveBackwardFromEnd(arr, pos)
        arr(pos) = elem
       // arr.insertAll(pos, List(elem)) 
      }*/
    }
  }
  
  /**
   * binary insertion
   * 
   * first find its position.
   */ 
  private def binaryInsertionPosition(arr:ArrayBuffer[Long], elem: Long) :Int =   {
    var low = 0
    var high = arr.length - 1 
    var mid = 0
    while(low <= high) { 
      mid = (low + high) / 2
      if(elem < arr(mid)) {
        high = mid -1
      }
      else if(elem > arr(mid)) {
        low = mid + 1
      } else if(elem == arr(mid)){
        return -1
      }
    }
    return mid 
    //arr.insertAll(mid+1, List(elem))
  }
  
  /**
   * contains 
   */
  private def binarySearch(arr:ArrayBuffer[Long], elem: Long) :Int =   {
    var low = 0
    var high = arr.length - 1 
    var mid = 0
    while(low <= high) { 
      mid = (low + high) / 2
      if(elem < arr(mid)) {
        high = mid -1
      }
      else if(elem > arr(mid)) {
        low = mid + 1
      } else if(elem == arr(mid)){
        return mid
      }
    }
    return -1 
    //arr.insertAll(mid+1, List(elem))
  }
  
  // for insertion
  def moveBackwardFromEnd(arr: ArrayBuffer[Long], startingIndex: Int) {
    var k = arr.length -1
    while( k >= startingIndex) {
      if(k == arr.length -1)
    	  arr  += arr(k)
      else arr(k+1) = arr(k)
      k -= 1
    }
  }
  /** 
   * merge two sorted arrays
   */
  def merge(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){
	    println(i, j)
	    if(arr1(i) < arr2(j)) {
	      newArrBuffer += arr1(i)
	      i += 1
	     
	      }
	    else if(arr1(i) > arr2(j)) { 
	      newArrBuffer += arr2 (j) 
	      j+= 1
	     
	    }
	    else if(arr1(i) == arr2(j)){
	      newArrBuffer += arr1(i)
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length) { 
	      var p = j
	      while(p < arr2.length){
	    	  newArrBuffer += arr2(p)
	    	  p += 1 
	      }
	      return newArrBuffer
	    }
	    
	    if(j >= arr2.length) {
	       var p = i
	      while(p < arr1.length){
	    	  newArrBuffer += arr1(p)
	    	  p += 1
	      }
	      return newArrBuffer
	    } 
	    }
	    return newArrBuffer
	  }
  
   def mergeDiffer(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){ 
	    if(arr1(i) < arr2(j)) {
	      newArrBuffer += arr1(i)
	      i += 1 
	      }
	    else if(arr1(i) > arr2(j)) {  
	      j+= 1
	     
	    }
	    else if(arr1(i) == arr2(j)){ 
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length) {  
	      return newArrBuffer
	    }
	    
	    if(j >= arr2.length) {
	       var p = i
	      while(p < arr1.length){
	    	  newArrBuffer += arr1(p)
	    	  p += 1
	      }
	      return newArrBuffer
	    } 
	    }
	    return newArrBuffer
	  }
  
   def mergeIntersect(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){ 
	    if(arr1(i) < arr2(j)) {
	      i += 1 
	      }
	    else if(arr1(i) > arr2(j)) {  
	      j+= 1
	     
	    }
	    else if(arr1(i) == arr2(j)){ 
	       newArrBuffer += arr1(i)
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length) {  
	      return newArrBuffer
	    }
	    
	    if(j >= arr2.length) { 
	      return newArrBuffer
	    } 
	    }
	    return newArrBuffer
	  }
   
   def mergeSubsetOf(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : Boolean = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0 
	  if(arr1.length == 0) return true
	  if(arr2.length == 0) return false
	  while(true){  
	    if(arr1(i) < arr2(j)) {
	       return false 
	      }
	    else if(arr1(i) > arr2(j)) {  
	      j+= 1
	     
	    }
	    else if(arr1(i) == arr2(j)){ 
	       newArrBuffer += arr1(i)
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length || j >= arr2.length) {  
	       if(newArrBuffer.length < arr1.length) return false
	       else return true
	    }
	  }
	    return false
	  }
   
   
   def mergeEqual(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : Boolean = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0 
	  if(arr1.length == 0) return true
	  if(arr2.length == 0) return false
	  while(true){  
	    if(arr1(i) < arr2(j)) {
	       return false 
	      }
	    else if(arr1(i) > arr2(j)) {  
	       return false
	    }
	    else if(arr1(i) == arr2(j)){ 
	       newArrBuffer += arr1(i)
	      i += 1
	      j += 1 
	      		
	    }
	    
	    if(i>= arr1.length || j >= arr2.length) {  
	       if(newArrBuffer.length == arr1.length) return true
	       else return false
	    }
	  }
	    return false
	  }
	
  
}
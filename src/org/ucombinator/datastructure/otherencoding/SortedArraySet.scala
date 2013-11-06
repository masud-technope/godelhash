package org.ucombinator.datastructure.otherencoding

import scala.collection.mutable.ArrayBuffer
import org.ucombinator.godelhash.mathmatics.numbertheory.PrimeHashable

 class SortedArraySet[A <% PrimeHashable with Ordered[A]] (tmparrCBuffer: ArrayBuffer[Long]) {
 
  var arrBuffer: ArrayBuffer[Long] = tmparrCBuffer.sortWith(_ < _) 
  
  // It is expected to be slow
  // so if it contains the element, the complexity is equal to the contains.
  def +(n : Long) {
    //println("insertion starts")
      binaryInsertion(n)
  }
  
  // changed the set too
  def -(n:Long){ 
    binaryRemoval(n)
  }
  
   // the second array should also be sorted
  // and the first aray should not be changed, isn't it?
  // new arrayset returned
  def union(arrS2: SortedArraySet[A]) : SortedArraySet[A] = { 
       val res = merge(this.arrBuffer, arrS2.arrBuffer)
       SortedArraySet(res)
  }
  
  // direct;y using the --= ops
  // new arraySet returned
  def diff(arrS2: SortedArraySet[A]) : SortedArraySet[A] = {
    // the following is slow!!!
//    var tmpArray = ArrayBuffer[Long]()
//     for(elem <- arrS2.arrBuffer) {
//       if(!arrBuffer.contains(elem))
//    	  tmpArray += elem
//     }
//    SortedArraySet(tmpArray)
    // oh year! Faster!
    val res = mergeDiffer(this.arrBuffer, arrS2.arrBuffer)
    SortedArraySet(res)
  }
  
   // on change to the order
  def subSetOf(arrS2: SortedArraySet[A] ) : Boolean= { 
    // still slow!
//     if(arrBuffer.length > arrS2.arrBuffer.length) false
//      else {
//       for(elem <- arrBuffer) {
//         if(!arrS2.isMember(elem))
//           return false
//       }
//       true
//     }
     mergeSubsetOf(arrBuffer, arrS2.arrBuffer)
  }
  
  def arraySetEqual(arrS2: SortedArraySet[A] ) : Boolean= {
    //slow
//    if(arrBuffer.length != arrS2.arrBuffer.length) false
//    else {
//      for(elem <- arrBuffer) {
//        if(!arrS2.isMember(elem))
//          return false
//     }
//      true
//    }
    mergeEqual(arrBuffer, arrS2.arrBuffer)
  }
  
  // precondition is sorted.array
  // binary search
  def isMember(n: BigInt) : Boolean = {  
		 val res =  binarySearch(n )
		 if(res == -1) false
		 else true
  }
  
  def intersect(arrS2: SortedArraySet[A] ) : SortedArraySet[A] = {
   /* val tmpBuffer = ArrayBuffer[Long]()
    
    if(arrBuffer.length == 0 ) 
      return arrS2
    if(arrS2.arrBuffer.length == 0)
      return this
      
     if(arrBuffer.length < arrS2.arrBuffer.length){
      for(elem <- arrBuffer) {
    	 if(arrS2.isMember(elem))
    	   tmpBuffer += elem 
      }
    } else if(arrBuffer.length >= arrS2.arrBuffer.length) {
      for(elem <- arrS2.arrBuffer) {
        if(this.isMember(elem))
          tmpBuffer += elem
      }
    } 
     SortedArraySet(tmpBuffer) */
    
    val res = mergeIntersect(this.arrBuffer, arrS2.arrBuffer)
    SortedArraySet(res)
  }
  
  /***
   **
   */
  def binaryRemoval(elem: BigInt) {
    
    val pos = binarySearch(elem)
    if(pos != -1)
     moveForwardForRemoval(arrBuffer,pos) 
  }

  /**
   * Some osrint utils below
   */
  def binaryInsertion(  elem: Long) {
    val pos = binaryInsertionPosition( elem) 
   
   if (pos == arrBuffer.length - 1) {
        arrBuffer += elem
        return
      }
     if (pos != -1) {
      if(arrBuffer(pos) > elem) {
         moveBackwardFromEnd(arrBuffer, pos)
        arrBuffer(pos) = elem
      }
      else {
        if(pos == 0) {
          moveBackwardFromEnd(arrBuffer, pos+1)
        arrBuffer(pos+1) = elem
          return
        }
         moveBackwardFromEnd(arrBuffer, pos-1)
        arrBuffer(pos-1) = elem
      }
     }
  }
  
  private def moveForwardForRemoval(arr: ArrayBuffer[Long], removalPoint:Int) {
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
  
    // for insertion
  private def moveBackwardFromEnd(arr: ArrayBuffer[Long], startingIndex: Int) {
    var k = arr.length -1
    while( k >= startingIndex) {
      if(k == arr.length -1)
    	  arr  += arr(k)
      else arr(k+1) = arr(k)
      k -= 1
    }
  }
  
  /**
   * binary insertion
   * 
   * first find its position.
   */ 
  private def binaryInsertionPosition(elem: BigInt) :Int =   {
    var low = 0
    var high = arrBuffer.length - 1 
    var mid = 0
    while(low <= high) { 
      mid = (low + high) / 2
      if(elem < arrBuffer(mid)) {
        high = mid -1
      }
      else if(elem > arrBuffer(mid)) {
        low = mid + 1
      } else if(elem == arrBuffer(mid)){
        return -1
      }
    }
    return mid 
    //arr.insertAll(mid+1, List(elem))
  }
  
  /**
   * if found the elemeent, wil return the indx
   * otherwise, return -1
   */
  private def binarySearch( elem: BigInt) :Int =   {
    var low = 0
    var high = arrBuffer.length - 1 
    var mid = 0
    while(low <= high) {  
      mid = (low + high) / 2
      if(elem < arrBuffer(mid)) {
        high = mid -1
      }
      else if(elem > arrBuffer(mid)) {
        low = mid + 1
      } else if(elem == arrBuffer(mid)){
        return mid
      }
    }
    return -1 
    //arr.insertAll(mid+1, List(elem))
  }
  
  // for union
  /** 
   * merge two sorted arrays
   */
  def merge(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){
	    //(i, j)
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
	
  
  /**
	 * difference based on merge cnocept. faster than O(nlgn)  
	 */
	def mergeDiffer(arr1: ArrayBuffer[Long], arr2: ArrayBuffer[Long]) : ArrayBuffer[Long] = {
	  val newArrBuffer = ArrayBuffer[Long]()
	  
	  var i = 0
	  var j = 0
	  var k = 0
	  
	  while(true){
	  //  println(i, j)
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
  
	// intersect
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

object SortedArraySet {
  def apply[A <% PrimeHashable with Ordered[A]] (arrCBuffer: ArrayBuffer[Long]) =   
     new SortedArraySet( arrCBuffer) 
}
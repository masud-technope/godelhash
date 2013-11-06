package org.ucombinator.datastructure.utils
import java.io.FileInputStream
import java.io.File
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.util.ArrayList



class FastParseInPrimesFile(var filename: String, var estimateNums : Int) {
 
   
  def readNumbers  : ArrayBuffer[Long] = {
    var llst : ArrayBuffer[Long] = null
    var  fis : FileInputStream = null
    
    var f: File  = null
    
    f = new File(filename)
    if(f.length() > Int.MaxValue)
      throw new Exception("File "+filename
						+" size too large: ");
    
    if(this.estimateNums <= 0){
      this.estimateNums = (f.length() >> 3).toInt
    }
    
    llst = new ArrayBuffer[Long](this.estimateNums)
    
    fis = new FileInputStream(f)
    
    
    val buf: Array [Byte] = new Array[Byte]((f.length()+1).toInt)
   
     var readbytes : Int = fis.read(buf)
     
     println("read", readbytes)
     if(readbytes > 0) {
        var startpos :Int = 0
        var i = 0
        while( i  < readbytes) {
        //  println("in ner while")
          if(buf(i) == '\n'.asInstanceOf[Byte]){
            var v = 0
            var p = 1
            
            var j = i-1
            while(j >= startpos) {
              v += p * (buf(j) - '0'.asInstanceOf[Byte])
              p = p *10
              j = j-1
            }
             llst += v //llst.add( v)
            startpos = i+1
          }
          i+=1
        }
     }
    fis.close()
    llst 
  }
  
}

object FastParseInPrimesFile{
  
  //var primes: List[Long] = null
  
  def apply(fn: String, estimateNums: Int) {
    new FastParseInPrimesFile(fn, estimateNums)
  }
  
  def apply(fn: String) {
    new FastParseInPrimesFile(fn, 1000)
  }
}
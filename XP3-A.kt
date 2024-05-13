import kotlin.random.Random

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
private fun distractionGenerator(a:Int):Int{
    return Random.nextInt(0, a-1)
}
private fun encryption(a:Int,b:Int,decryptedText:Int):Int{
    return a*decryptedText+b+distractionGenerator(a)
}
private fun decryption(a:Int,b:Int,encryptedText:Int):Int{
    return (encryptedText-b)/a
}
private fun acceptableGap(a:Int,b: Int, coefficientForEncrypt:Int):Int{
    return when (a-b){
        in 1-coefficientForEncrypt..<coefficientForEncrypt -> 0
        else -> when(a-b<0){
            true->-1
            else->1
        }
    }
}
private fun find(x:Int, referenceSheet: IntArray, l:Int, r:Int,coefficientForEncrypt: Int):Int{
    val mid = (l+r)/2
    if(l>r){
        return -1
    }
    if(acceptableGap(referenceSheet[mid],x,coefficientForEncrypt)==0){
        return mid
    }
    else if (acceptableGap(referenceSheet[mid],x,coefficientForEncrypt)==-1){
        return find(x, referenceSheet, mid+1,r,coefficientForEncrypt)
    }
    else return find(x, referenceSheet, l,mid-1,coefficientForEncrypt)
}

private fun main(){
    print("Encrypt coefficient: ")
    val a=readln().toInt()
    print("Encrypt offset: ")
    val b=readln().toInt()
    print("Enter text to be processed (numbers only): ")
    val toBeProcessed = readln()
    val charsForProcess = toBeProcessed.toCharArray()
    val intsForProcess = IntArray(charsForProcess.size){i->charsForProcess[i].code-'0'.code}
    print("Get the text: ")
    for(i in intsForProcess.indices){
        print(intsForProcess[i])
    }
    println("")
    println("First step in process...")
    Thread.sleep(2000)
    intsForProcess.sort()
    for(i in intsForProcess.indices){
        print("${intsForProcess[i]}")
        if(i<intsForProcess.size-1){
            print("\t")
        }
        else println()
    }
    println()
    println("Second step in process...")
    Thread.sleep(2000)
    val intsAfterProcess = IntArray(intsForProcess.size){i->encryption(a,b,intsForProcess[i])}
    println("Process second stage finished. Result at the moment: ")
    for(i in intsAfterProcess.indices){
        print("${intsAfterProcess[i]}")
        if(i<intsForProcess.size-1){
            print("\t")
        }
        else println()
    }
    Thread.sleep(1000)
    print("Enter a number you want to search: ")
    val searchTarget = readln().toInt()
    val targetIndex = find(encryption(a,b,searchTarget),intsAfterProcess,0,intsAfterProcess.size-1,a)
    println(when(targetIndex != -1){
        true -> "Number found.\nCiphered result: ${intsAfterProcess[targetIndex]}.\nDecrypted result: ${decryption(a,b,intsAfterProcess[targetIndex])}"
        else -> ("ERROR: Target not found in the result of the first stage.")
    })
}

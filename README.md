# 实验1 现代密码学基础技能
## 实验目的
- 掌握现代密码学基础技能。
## 实验内容与要求
- 任选DES/AES/SM4种的**一种**对称密码算法并实现；
- 针对多模式对称密码算法，仅需实现**一种**模式；
- 能够对字符串进行正确的加密和解密。
## 实验原理

## 实验操作
由于本人自身的设备上没有javascript的加密库，因此本次实验的代码托管给了RunKit并在该平台上运行。在本次实验中，本人选择AES加密算法和计数器模式对字符串进行加密和解密；由于线上平台没有办法使用人机交互功能（例如引导使用者通过终端输入内容并根据该内容进行加/解密），在代码中插入了一段随机生成字符串的函数`generateRandomString`来保证此算法的普适性——这一函数可以根据给定数值随机生成长度等于该数值的字符串；本程式利用此函数可以确保密钥和待加密文本的随机性。

为方便起见，待加密文本`message`的长度设定为质数13，密钥`key`长度设定为质数5。显然，这两个长度互质。

为逐步观察，程式将输出随机生成的待加密文本内容加密前后的内容和密钥。由于这些字符串的随机性，逐字符比较显然极为繁琐；为了方便起见，程式将在最后对加密前后的文本内容自动进行比较。当且仅当加密前后的文本内容一致时，结果将输出“Cipher success!”，即加解密流程完全成功；否则加密失败。

```javascript
var CryptoJS = require("crypto-js");

//The function is to be used to generate random strings as keys or text for encryption later on.
function generateRandomString(length) {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * chars.length);
        result += chars[randomIndex];
    }
    return result;
}

var message = generateRandomString(13);
var key = generateRandomString(5);

console.log("Text before encryption: " + message);
console.log("Key generated for encryption: " + key);

//Javascript中所用的crypto库一概使用计数器模式（CTR）进行加密。其将块密码变为流密码，通过递增一个加密计数器来产生连续密钥流，从而确保长时间不重复输出。这里特别要求ECB模式。

var cipherTextAES = CryptoJS.AES.encrypt(message, key,{
    mode: CryptoJS.mode.ECB,  
    padding: CryptoJS.pad.Pkcs7
}).toString();

console.log("Text after encryption: " + cipherTextAES);

var decryptedByteAES = CryptoJS.AES.decrypt(cipherTextAES, key, { 
    mode: CryptoJS.mode.ECB,  
    padding: CryptoJS.pad.Pkcs7
});
var decryptedTextAES = decryptedByteAES.toString(CryptoJS.enc.Utf8);

console.log("Text decryption finished. Before encoding: " + decryptedByteAES);

console.log("Encoded text: " + decryptedTextAES);

//decryptedTextAES=generateRandomString(13);

if (decryptedTextAES == message){
    console.log("Cipher success!");
}
else{
    console.log("Cipher failed. (FATAL: Text before & after the cipher is NOT the same)")
}
```

## 实验结果

运行程式，得到随机的结果之一如下图所示。可以看到最终输出为"Cipher success!"，因此加解密正确。



## 验收
本次实验验收是在腾讯会议上完成的。

<img src="XP1-ASS.png"></img>

# 实验2 属性基访问控制实验
# 实验3 数据安全检索实验
# 实验4 大数据隐私保护实验

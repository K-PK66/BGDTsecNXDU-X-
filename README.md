# 实验1 现代密码学基础技能
## 实验目的
- 掌握现代密码学基础技能。
## 实验内容与要求
- 任选DES/AES/SM4种的**一种**对称密码算法并实现；
- 针对多模式对称密码算法，仅需实现**一种**模式；
- 能够对字符串进行正确的加密和解密。
## 实验原理
**对称加密**指的是加解密使用同样密钥的加密方式。其中，DES加密算法于1977年由美国NSA根据IBM的专利技术Lucifer所制定；明文被分成64位的块，对每个块进行19次变换（替代和换位），其中16次变换由56位的秘钥的不同排列形式控制（IBM使用128位的秘钥），最后产生64位的密文块。后来基于DES设计出了改进算法三重DES（Triple-DES）。不过AES加密法已将DES替代，目前成为了对称加密算法中最为流行的之一。AES算法采用对称分组密码体制，密码的长度最少支持为128、192、256，分组长度128位，有很多轮重复和变换。

Javascript语言中拥有名为`CryptoJS`的库，可以支持包括AES和DES在内的大量对称加密算法。以`CryptoJS.AES`为例，对加密`CryptoJS.AES.encrypt`和解密`CryptoJS.AES.decrypt`有value（待加/解密字符串）、key（密钥）、iv（密钥偏移量）、mode（加密模式，默认为CBC，还可以是CFB、CTR、OFB、ECB）、pad（填充模式，默认为Pkcs7，还可以是AnsiX923、Iso10126、Iso97971、ZeroPadding、NoPadding）和encoding（编码格式，如Hex、Latin1、Utf8、Utf16、Base64）。其中，encoding可以将编码格式与WordArray对象互相转换。
## 实验操作
由于本人自身的设备上没有javascript的加密库，因此本次实验的代码托管给了RunKit并在该平台上运行。在本次实验中，本人选择AES加密算法和CryptoJS默认的电子密码本模式（ECB）对字符串进行加密和解密；由于线上平台没有办法使用人机交互功能（例如引导使用者通过终端输入内容并根据该内容进行加/解密），在代码中插入了一段随机生成字符串的函数`generateRandomString`来保证此算法的普适性——这一函数可以根据给定数值随机生成长度等于该数值的字符串；本程式利用此函数可以确保密钥和待加密文本的随机性。

为方便起见，待加密文本`message`的长度设定为质数13，密钥`key`长度设定为质数5。显然，这两个长度互质。

为逐步观察，程式将输出随机生成的待加密文本内容加密前后的内容和密钥，并将在完成解密后显示解密后的文本。由于这些字符串的随机性，逐字符比较显然极为繁琐；为了方便起见，程式将在最后对加密前后的文本内容自动进行比较。当且仅当加密前后的文本内容一致时，结果将输出“Cipher success!”，即加解密流程完全成功；否则加密失败。

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

//ECB模式，启动

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

运行程式，得到随机的结果之一如下图所示。可以看到，待加密信息为“qvu6pJwJj2Hz3”，密钥为“bWbfo”；加密之后得到密文“U2FsdGVkX18iAVSpSCUQrvmKmE8Wp5/Du3ziMedW6cg=”，解密后得到“71767536704a774a6a32487a33”；经过Utf8转码，得到解密后的文本“qvu6pJwJj2Hz3”，与加密前的明文一致。因此，最终输出为“Cipher success!”，即加解密正确。

<img src="XP1-ARS.png"></img>

## 验收
本次实验验收是在腾讯会议上完成的。

<img src="XP1-ASS.png"></img>

# 实验2 属性基访问控制实验
# 实验3 数据安全检索实验
# 实验4 大数据隐私保护实验
